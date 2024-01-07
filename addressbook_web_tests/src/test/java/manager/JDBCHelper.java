package manager;

import model.ContactData;
import model.GroupData;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class JDBCHelper extends HelperBase {

    public JDBCHelper(ApplicationManager manager) {
        super(manager);
    }

    public List<GroupData> getGroupList() {
        List<GroupData> groups = new ArrayList<>();
        try (var connection = DriverManager.getConnection("jdbc:mysql://localhost/addressbook", "root", "");
             var statement = connection.createStatement();
             var result = statement.executeQuery("SELECT group_id, group_name, group_header, group_footer FROM group_list")) {
            while (result.next()) {
                groups.add(new GroupData()
                       .withId(result.getString("group_id"))
                       .withName(result.getString("group_name"))
                       .withHeader(result.getString("group_header"))
                       .withFooter(result.getString("group_footer")));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return groups;
    }

    public List<ContactData> getContactList() {
        List<ContactData> contacts = new ArrayList<>();
        try (var connection = DriverManager.getConnection("jdbc:mysql://localhost/addressbook", "root", "");
             var statement = connection.createStatement();
             var result = statement.executeQuery("SELECT id, lastname, firstname FROM addressbook")) {
            while (result.next()) {
                contacts.add(new ContactData()
                       .contactWithNames(result.getString("id"),
                              result.getString("firstname"),
                              result.getString("lastname")));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return contacts;
    }

    public void checkConsistency() {
        try (var connection = DriverManager.getConnection("jdbc:mysql://localhost/addressbook", "root", "");
             var statement = connection.createStatement();
             var result = statement.executeQuery(
                    "SELECT * FROM address_in_groups ag LEFT JOIN addressbook ab ON ab.id = ag.id WHERE ab.id IS NULL")) {
            if (result.next()) {
              throw new IllegalStateException("DB is corrupted");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
