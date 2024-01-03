package tests;

import model.ContactData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class ContactCreationTests extends TestBase {

    @ParameterizedTest
    @MethodSource("contactProvider")
    void canCreateContact(ContactData contact) {
        List<ContactData> oldContacts = app.contacts().getList();
        app.contacts().createContact(contact);
        List<ContactData> newContacts = app.contacts().getList();
        Comparator<ContactData> compareById = (o1, o2) -> {
            return Integer.compare(Integer.parseInt(o1.getId()), Integer.parseInt(o2.getId()));
        };
        newContacts.sort(compareById);
        List<ContactData> expectedList = new ArrayList<>(oldContacts);
        int newContact = newContacts.size() - 1;
        expectedList.add(new ContactData(newContacts.get(newContact).getId(),
               newContacts.get(newContact).getFirstName(),
               newContacts.get(newContact).getMiddleName(),
               newContacts.get(newContact).getLastName(),
               newContacts.get(newContact).getHomePhone(),
               newContacts.get(newContact).getMobilePhone(),
               newContacts.get(newContact).getWorkPhone(),
               newContacts.get(newContact).getFirstEmail(),
               newContacts.get(newContact).getSecondEmail(),
               newContacts.get(newContact).getThirdEmail()));
        expectedList.sort(compareById);
        Assertions.assertEquals(newContacts, expectedList);
    }

    @ParameterizedTest
    @MethodSource("negativeContactProvider")
    void canNotCreateContacts(ContactData contact) {
        List<ContactData> oldContacts = app.contacts().getList();
        app.contacts().createContact(contact);
        List<ContactData> newContacts = app.contacts().getList();
        Assertions.assertEquals(oldContacts, newContacts);
    }

    public static List<ContactData> contactProvider() {
        List<ContactData> list = new ArrayList<>();
        for (String firstName : List.of("", "firstName")) {
            for (String lastName : List.of("", "lastName")) {
                {
                    list.add(new ContactData().contactWithNames("", firstName, lastName));
                }
            }
        }
        for (int i = 0; i < 5; i++) {
            list.add(new ContactData("", randomString(i), randomString(i), randomString(i),
                   generateRandomPhoneNumber(), generateRandomPhoneNumber(), generateRandomPhoneNumber(),
                   generateRandomEmail(), generateRandomEmail(), generateRandomEmail()));

        }
        return list;
    }

    public static List<ContactData> negativeContactProvider() {
        return new ArrayList<>(List.of(
               new ContactData().contactWithNames("", "firstName'", "lastName'")));
    }
}

