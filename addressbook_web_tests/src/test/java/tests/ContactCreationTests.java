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
import java.util.List;
import java.util.Set;
import java.util.function.Supplier;
import java.util.stream.Stream;

public class ContactCreationTests extends TestBase {

    @ParameterizedTest
    @MethodSource("randomContacts")
    void canCreateContact(ContactData contact) {
        List<ContactData> oldContacts = app.hbm().getContactList();
        app.contacts().createContact(contact);
        List<ContactData> newContacts = app.hbm().getContactList();

        var extraContact = newContacts.stream().filter(c -> !newContacts.contains(c)).toList();
        var newId = extraContact.get(0).getId();

        List<ContactData> expectedList = new ArrayList<>(oldContacts);
        expectedList.add(contact.contactWithNames(
               newId,
               contact.getFirstName(),
               contact.getLastName()));

        Assertions.assertEquals(Set.copyOf(newContacts), Set.copyOf(expectedList));
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

    public static Stream<ContactData> randomContacts() {
        Supplier<ContactData> randomContactData = () ->
               new ContactData()
                      .contactWithNames("",
                             CommonFunctions.randomString(9),
                             CommonFunctions.randomString(9));
        return Stream.generate(randomContactData).limit(2);
    }

    public static List<ContactData> negativeContactProvider() {
        return new ArrayList<>(List.of(
               new ContactData().contactWithNames("", "firstName'", "lastName'")));
    }
}

