package tests;

import model.ContactData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ContactRemovalTests extends TestBase{

    @Test
    void canRemovalContact() {
        if(app.contacts().getCount() == 0) {
            app.contacts().createContact(new ContactData("firstName", "middleName", "lastName",
                   "+32", "+7", "+007",
                   "firstEmail@ya.ru", "secondEmail@ya.ru", "thirdEmail@ya.ru"));
        }
        int contactCount = app.contacts().getCount();
        app.contacts().removeContact();

        int newContactCount = app.contacts().getCount();
        Assertions.assertEquals(contactCount - 1, newContactCount);
    }

    @Test
    void canRemoveAllContacts() {
        if(app.contacts().getCount() == 0) {
            app.contacts().createContact(new ContactData("firstName", "middleName", "lastName",
                   "+32", "+7", "+007",
                   "firstEmail@ya.ru", "secondEmail@ya.ru", "thirdEmail@ya.ru"));
        }

        app.contacts().removeAllContacts();
        Assertions.assertEquals(0, app.contacts().getCount());
    }
}
