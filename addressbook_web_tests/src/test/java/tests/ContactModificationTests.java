package tests;

import common.CommonFunctions;
import model.ContactData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Random;

public class ContactModificationTests extends TestBase {

    @Test
    void canModifyContact() {
        if (app.contacts().getCount() == 0) {
            app.contacts().createContact(new ContactData("", "firstName", "middleName", "lastName",
                   "+32", "+7", "+007",
                   "firstEmail@ya.ru", "secondEmail@ya.ru", "thirdEmail@ya.ru", ""));
        }
        List<ContactData> oldContacts = app.jdbc().getContactList();

        int index = new Random().nextInt(oldContacts.size());
        ContactData testData = new ContactData().contactWithNames("",
               CommonFunctions.randomString(5),
               CommonFunctions.randomString(5));

        app.contacts().modifyContact(oldContacts.get(index), testData);

        List<ContactData> newContacts = app.jdbc().getContactList();
        List<ContactData> expectedList = new ArrayList<>(oldContacts);
        expectedList.set(index, testData.contactWithNames(oldContacts.get(index).getId(), testData.getFirstName(), testData.getLastName()));
        Comparator<ContactData> compareById = (o1, o2) -> {
            return Integer.compare(Integer.parseInt(o1.getId()), Integer.parseInt(o2.getId()));
        };
        newContacts.sort(compareById);
        expectedList.sort(compareById);
        Assertions.assertEquals(newContacts, expectedList);
    }
}
