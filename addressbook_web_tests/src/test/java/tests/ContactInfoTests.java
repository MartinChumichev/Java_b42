package tests;

import model.ContactData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ContactInfoTests extends TestBase {

    @Test
    void testContactInformationFromEditForm() {
        if (app.hbm().getContactCount() == 0) {
            app.hbm().createContact(new ContactData()
                   .contactWithPhones("123", "456", "789", "0951")
                   .contactWithAddress("Address")
                   .contactWithEmails("123@ya.ru", "456@gmail.com", "789@ru.ru"));
        }
        List<ContactData> contacts = app.hbm().getContactList();
        ContactData contact = contacts.get(0);
        String phones = app.contacts().getPhonesFromMainPage(contact);
        String emails = app.contacts().getEmailsFromMainPage(contact);
        String address = app.contacts().getAddressFromMainPage(contact);

        ContactData contactInfoFromEditForm = app.contacts().infoFromEditForm(contact);

        Assertions.assertEquals(address, contactInfoFromEditForm.getAddress());
        Assertions.assertEquals(phones, app.contacts().getAllPhones(contact));
        Assertions.assertEquals(emails, app.contacts().getAllEmails(contact));
    }

    @Test
    void testPhones() {
        if (app.hbm().getContactCount() == 0) {
            app.hbm().createContact(new ContactData().contactWithPhones("123", "456", "789", "0951"));
        }
        List<ContactData> contacts = app.hbm().getContactList();
        var expected = contacts.stream().collect(Collectors.toMap(ContactData::getId, contact ->
               app.contacts().getAllPhones(contact)));
        Map<String, String> allPhones = app.contacts().getAllPhones();
        Assertions.assertEquals(expected, allPhones);
    }

    @Test
    void testEmails() {
        if (app.hbm().getContactCount() == 0) {
            app.hbm().createContact(new ContactData().contactWithEmails("123@ya.ru", "456@gmail.com", "789@ru.ru"));
        }
        List<ContactData> contacts = app.hbm().getContactList();
        ContactData contact = contacts.get(0);
        String emails = app.contacts().getEmailsFromMainPage(contact);

        String expected = app.contacts().getAllEmails(contact);

        Assertions.assertEquals(expected, emails);
    }

    @Test
    void testAddress() {
        if (app.hbm().getContactCount() == 0) {
            app.hbm().createContact(new ContactData().contactWithAddress("Address"));
        }
        List<ContactData> contacts = app.hbm().getContactList();
        ContactData contact = contacts.get(0);
        String address = app.contacts().getAddressFromMainPage(contact);

        String expected = Stream.of(contact.getAddress())
               .filter(s -> s != null && !s.equals(""))
               .collect(Collectors.joining("\n"));

        Assertions.assertEquals(expected, address);
    }


}
