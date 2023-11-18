package tests;

import model.ContactData;
import org.junit.jupiter.api.Test;

public class ContactRemovalTests extends TestBase{

    @Test
    void canRemovalContact() {
        if(!app.contacts().isContactPresent()) {
            app.contacts().createContact(new ContactData("firstName", "middleName", "lastName",
                   "+32", "+7", "+007",
                   "firstEmail@ya.ru", "secondEmail@ya.ru", "thirdEmail@ya.ru"));
        }
        app.contacts().removeContact();
    }
}
