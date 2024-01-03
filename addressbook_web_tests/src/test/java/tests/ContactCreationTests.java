package tests;

import model.ContactData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ContactCreationTests extends TestBase {

    @Test
    void canCreateContact() {
        int contactCount = app.contacts().getCount();
        app.contacts().createContact(new ContactData("firstName", "middleName", "lastName",
               "+32", "+7", "+007",
               "firstEmail@ya.ru", "secondEmail@ya.ru", "thirdEmail@ya.ru"));

        int newContactCount = app.contacts().getCount();
        Assertions.assertEquals(contactCount + 1, newContactCount);
    }

    @Test
    void canCreateContactWithNames() {
        int contactCount = app.contacts().getCount();
        app.contacts().createContact(new ContactData().contactWithNames("firstName", "middleName", "lastName"));

        int newContactCount = app.contacts().getCount();
        Assertions.assertEquals(contactCount + 1, newContactCount);
    }

    @Test
    void canCreateContactWithPhones() {
        int contactCount = app.contacts().getCount();
        app.contacts().createContact(new ContactData().contactWithPhones("+32", "+7", "+007"));

        int newContactCount = app.contacts().getCount();
        Assertions.assertEquals(contactCount + 1, newContactCount);
    }

    @Test
    void canCreateContactWithEmails() {
        int contactCount = app.contacts().getCount();
        app.contacts().createContact(new ContactData().contactWithEmails("firstEmail@ya.ru", "secondEmail@ya.ru", "thirdEmail@ya.ru"));

        int newContactCount = app.contacts().getCount();
        Assertions.assertEquals(contactCount + 1, newContactCount);
    }

    @Test
    void canCreateMultiplyContacts() {
        int a = 5;
        int contactCount = app.contacts().getCount();

        for (int i = 0; i < a; i++) {
            app.contacts().createContact(new ContactData().contactWithNames(randomString(5), "middleName", "lastName"));
        }

        int newContactCount = app.contacts().getCount();
        Assertions.assertEquals(contactCount + a, newContactCount);
    }
}
