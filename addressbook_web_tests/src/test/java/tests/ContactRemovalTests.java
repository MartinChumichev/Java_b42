package tests;

import model.ContactData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ContactRemovalTests extends TestBase {

    @Test
    void canRemovalContact() {
        if (app.hbm().getContactCount() == 0) {
            app.hbm().createContact(new ContactData().contactWithNames("", "firstName", "lastName"));
        }
        List<ContactData> oldContacts = app.hbm().getContactList();
        int index = new Random().nextInt(oldContacts.size());
        app.contacts().removeContact(oldContacts.get(index));
        List<ContactData> newContacts = app.hbm().getContactList();
        List<ContactData> expectedList = new ArrayList<>(oldContacts);
        expectedList.remove(index);
        Assertions.assertEquals(newContacts, expectedList);
    }

    @Test
    void canRemoveAllContacts() {
        if (app.hbm().getContactCount() == 0) {
            app.hbm().createContact(new ContactData().contactWithNames("", "firstName", "lastName"));
        }
        app.contacts().removeAllContacts();
        Assertions.assertEquals(0, app.hbm().getContactList().size());
    }
}
