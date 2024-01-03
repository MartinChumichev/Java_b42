package tests;

import model.ContactData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.ArrayList;
import java.util.List;

public class ContactCreationTests extends TestBase {

    @ParameterizedTest
    @MethodSource("contactProvider")
    void canCreateContact(ContactData contact) {
        int contactCount = app.contacts().getCount();
        app.contacts().createContact(contact);
        int newContactCount = app.contacts().getCount();
        Assertions.assertEquals(contactCount + 1, newContactCount);
    }

    @ParameterizedTest
    @MethodSource("negativeContactProvider")
    void canNotCreateContacts(ContactData contact) {
        int contactCount = app.contacts().getCount();
        app.contacts().createContact(contact);
        int newContactCount = app.contacts().getCount();
        Assertions.assertEquals(contactCount, newContactCount);
    }

    public static List<ContactData> contactProvider() {
        List<ContactData> list = new ArrayList<>();
        for (String firstName : List.of("", "firstName")) {
            for (String middleName : List.of("", "middleName")) {
                for (String lastName : List.of("", "lastName")) {
                    {
                        list.add(new ContactData().contactWithNames(firstName, middleName, lastName));
                    }
                }
            }
        }
        for (int i = 0; i < 5; i++) {
            list.add(new ContactData(randomString(i), randomString(i), randomString(i),
                   generateRandomPhoneNumber(), generateRandomPhoneNumber(), generateRandomPhoneNumber(),
                   generateRandomEmail(), generateRandomEmail(), generateRandomEmail()));

        }
        return list;
    }

    public static List<ContactData> negativeContactProvider() {
        return new ArrayList<>(List.of(
               new ContactData().contactWithNames("firstName'", "middleName'", "lastName'")));
    }
}

