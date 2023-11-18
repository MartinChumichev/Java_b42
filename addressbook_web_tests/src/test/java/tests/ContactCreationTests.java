package tests;

import model.ContactData;
import org.junit.jupiter.api.Test;

public class ContactCreationTests extends TestBase {

    @Test
    void canCreateContact() {
        app.contacts().createContact(new ContactData("firstName", "middleName", "lastName",
               "+32", "+7", "+007",
               "firstEmail@ya.ru", "secondEmail@ya.ru", "thirdEmail@ya.ru"));
    }

    @Test
    void canCreateContactWithNames() {
        app.contacts().createContact(new ContactData().contactWithNames("firstName", "middleName", "lastName"));
    }

    @Test
    void canCreateContactWithPhones() {
        app.contacts().createContact(new ContactData().contactWithPhones("+32", "+7", "+007"));
    }

    @Test
    void canCreateContactWithEmails() {
        app.contacts().createContact(new ContactData().contactWithEmails("firstEmail@ya.ru", "secondEmail@ya.ru", "thirdEmail@ya.ru"));
    }
}
