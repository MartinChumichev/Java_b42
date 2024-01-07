package tests;

import common.CommonFunctions;
import model.ContactData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Set;

public class ContactModificationTests extends TestBase {

    @Test
    void canModifyContact() {
        if (app.hbm().getContactCount() == 0) {
            app.hbm().createContact(new ContactData().contactWithNames("", "firstName", "lastName"));
        }
        List<ContactData> oldContacts = app.hbm().getContactList();

        int index = new Random().nextInt(oldContacts.size());
        ContactData testData = new ContactData().contactWithNames("",
               CommonFunctions.randomString(5),
               CommonFunctions.randomString(5));

        app.contacts().modifyContact(oldContacts.get(index), testData);

        List<ContactData> newContacts = app.hbm().getContactList();
        List<ContactData> expectedList = new ArrayList<>(oldContacts);
        expectedList.set(index, testData.contactWithNames(oldContacts.get(index).getId(), testData.getFirstName(), testData.getLastName()));

        Assertions.assertEquals(Set.copyOf(newContacts), Set.copyOf(expectedList));
    }
}
