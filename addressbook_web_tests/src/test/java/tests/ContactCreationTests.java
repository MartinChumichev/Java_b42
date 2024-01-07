package tests;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import common.CommonFunctions;
import model.ContactData;
import model.GroupData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class ContactCreationTests extends TestBase {

    @ParameterizedTest
    @MethodSource("singleRandomContact")
    void canCreateContact(ContactData contact) {
        List<ContactData> oldContacts = app.hbm().getContactList();
        app.contacts().createContact(contact);
        List<ContactData> newContacts = app.hbm().getContactList();
        Comparator<ContactData> compareById = (o1, o2) -> {
            return Integer.compare(Integer.parseInt(o1.getId()), Integer.parseInt(o2.getId()));
        };
        newContacts.sort(compareById);
        List<ContactData> expectedList = new ArrayList<>(oldContacts);
        expectedList.add(contact.contactWithNames(
               newContacts.get(newContacts.size() - 1).getId(),
               contact.getFirstName(),
               contact.getLastName()));
        expectedList.sort(compareById);
        Assertions.assertEquals(newContacts, expectedList);
    }

    @ParameterizedTest
    @MethodSource("negativeContactProvider")
    void canNotCreateContacts(ContactData contact) {
        List<ContactData> oldContacts = app.hbm().getContactList();
        app.contacts().createContact(contact);
        List<ContactData> newContacts = app.hbm().getContactList();
        Assertions.assertEquals(oldContacts, newContacts);
    }

    @Test
    void createContactWithPhoto() {
        ContactData contact = new ContactData().contactWithPhoto(randomFile("src/test/resources/images/"));
        app.contacts().createContactWithPhoto(contact);
    }

    @Test
    void canCreateContactInGroup() {
        ContactData contact = new ContactData().contactWithNames("", "name", "lname");
        if (app.hbm().getGroupCount() == 0) {
            app.hbm().createGroup(new GroupData("", "Group name", "Group header", "Group footer"));
        }
        GroupData group = app.hbm().getGroupList().get(0);
        List<ContactData> oldRelated = app.hbm().getContactsInGroup(group);
        app.contacts().createContact(contact, group);
        List<ContactData> newRelated = app.hbm().getContactsInGroup(group);
        Assertions.assertEquals(oldRelated.size() + 1, newRelated.size());
    }

    public static List<ContactData> contactProvider() throws IOException {
        List<ContactData> list = new ArrayList<>();
        for (String firstName : List.of("", "firstName")) {
            for (String lastName : List.of("", "lastName")) {
                list.add(new ContactData().contactWithNames("", firstName, lastName));
            }
        }
        ObjectMapper mapper = new ObjectMapper();
        var value = mapper.readValue(new File("contacts.json"), new TypeReference<List<ContactData>>() {
        });
        list.addAll(value);
        return list;
    }

    public static List<ContactData> singleRandomContact() {
        return List.of(new ContactData()
               .contactWithNames("",
                      CommonFunctions.randomString(9),
                      CommonFunctions.randomString(9)));
    }

    public static List<ContactData> negativeContactProvider() {
        return new ArrayList<>(List.of(
               new ContactData().contactWithNames("", "firstName'", "lastName'")));
    }
}

