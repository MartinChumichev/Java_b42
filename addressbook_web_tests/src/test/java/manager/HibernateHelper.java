package manager;

import io.qameta.allure.Step;
import manager.hbm.ContactRecord;
import manager.hbm.GroupRecord;
import model.ContactData;
import model.GroupData;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.AvailableSettings;
import org.hibernate.cfg.Configuration;

import java.util.List;
import java.util.stream.Collectors;

public class HibernateHelper extends HelperBase {
    private SessionFactory sessionFactory;

    public HibernateHelper(ApplicationManager manager) {
        super(manager);
        sessionFactory = new Configuration()
               .addAnnotatedClass(ContactRecord.class)
               .addAnnotatedClass(GroupRecord.class)
               .setProperty(AvailableSettings.URL, "jdbc:mysql://localhost/addressbook?zeroDateTimeBehavior=convertToNull")
               .setProperty(AvailableSettings.USER, "root")
               .setProperty(AvailableSettings.PASS, "")
               .buildSessionFactory();
    }

    static List<GroupData> convertGroupList(List<GroupRecord> records) {
        return records.stream().map(HibernateHelper::convertGroup).collect(Collectors.toList());
    }

    static List<ContactData> convertContactList(List<ContactRecord> records) {
        return records.stream().map(HibernateHelper::convertContact).collect(Collectors.toList());
    }

    private static GroupData convertGroup(GroupRecord record) {
        return new GroupData(String.valueOf(record.id), record.name, record.header, record.footer);
    }

    private static ContactData convertContact(ContactRecord record) {
        return new ContactData().contactWithNames(String.valueOf(record.id), record.firstname, record.lastname)
               .contactWithPhones(record.home, record.mobile, record.work, record.secondaryPhone)
               .contactWithEmails(record.email, record.email2, record.email3)
               .contactWithAddress(record.address);
    }

    private static GroupRecord convertGroup(GroupData data) {
        var id = data.id();
        if ("".equals(id)) {
            id = "0";
        }
        return new GroupRecord(Integer.parseInt(id), data.name(), data.header(), data.footer());
    }

    private static ContactRecord convertContact(ContactData data) {
        var id = data.getId();
        if ("".equals(id)) {
            id = "0";
        }
        return new ContactRecord(Integer.parseInt(id), data.getFirstName(), data.getLastName());
    }

    @Step
    public List<GroupData> getGroupList() {
        return sessionFactory.fromSession(session -> {
            return convertGroupList(session.createQuery("from GroupRecord", GroupRecord.class).list());
        });
    }

    public List<ContactData> getContactList() {
        return sessionFactory.fromSession(session -> {
            return convertContactList(session.createQuery("from ContactRecord", ContactRecord.class).list());
        });
    }

    public long getGroupCount() {
        return sessionFactory.fromSession(session -> {
            return session.createQuery("select count (*) from GroupRecord", Long.class).getSingleResult();
        });
    }

    public long getContactCount() {
        return sessionFactory.fromSession(session -> {
            return session.createQuery("select count (*) from ContactRecord", Long.class).getSingleResult();
        });
    }

    @Step
    public void createGroup(GroupData groupData) {
        sessionFactory.inSession(session -> {
            session.getTransaction().begin();
            session.persist(convertGroup(groupData));
            session.getTransaction().commit();
        });
    }

    public void createContact(ContactData contactData) {
        sessionFactory.inSession(session -> {
            session.getTransaction().begin();
            session.persist(convertContact(contactData));
            session.getTransaction().commit();
        });
    }

    public List<ContactData> getContactsInGroup(GroupData group) {
        return sessionFactory.fromSession(session -> {
            return convertContactList(session.get(GroupRecord.class, group.id()).contacts);
        });
    }
}
