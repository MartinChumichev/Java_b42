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
        if (app.contacts().getCount() == 0) {
            app.contacts().createContact(new ContactData("", "firstName", "middleName", "lastName",
                   "+32", "+7", "+007",
                   "firstEmail@ya.ru", "secondEmail@ya.ru", "thirdEmail@ya.ru"));
        }
        List<ContactData> oldContacts = app.contacts().getList();
        int index = new Random().nextInt(oldContacts.size());
        app.contacts().removeContact(oldContacts.get(index));
        List<ContactData> newContacts = app.contacts().getList();
        List<ContactData> expectedList = new ArrayList<>(oldContacts);
        expectedList.remove(index);
        Assertions.assertEquals(newContacts, expectedList);
    }

    @Test
    void canRemoveAllContacts() {
        if (app.contacts().getCount() == 0) {
            app.contacts().createContact(new ContactData("", "firstName", "middleName", "lastName",
                   "+32", "+7", "+007",
                   "firstEmail@ya.ru", "secondEmail@ya.ru", "thirdEmail@ya.ru"));
        }

        app.contacts().removeAllContacts();
        Assertions.assertEquals(0, app.contacts().getCount());
    }
}
