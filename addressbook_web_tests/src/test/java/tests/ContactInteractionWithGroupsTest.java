package tests;

import model.ContactData;
import model.GroupData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class ContactInteractionWithGroupsTest extends TestBase {

    @Test
    void canAddContactInGroup() {
        if (app.hbm().getContactCount() == 0) {
            app.hbm().createContact(new ContactData().contactWithNames("", "firstName", "lastName"));
        } else if (app.hbm().getGroupCount() == 0) {
            app.hbm().createGroup(new GroupData("", "Group name", "Group header", "Group footer"));
        }
        List<GroupData> groups = app.hbm().getGroupList();
        List<ContactData> contactsBefore = app.hbm().getContactList();
        ContactData contactToAdd = contactsBefore.iterator().next();
        GroupData groupToAdd = groups.iterator().next();

        if (isContactInGroup(contactToAdd, groupToAdd)) {
            app.contacts().removeContactFromGroup(contactToAdd, groupToAdd);
        }

        List<ContactData> oldRelated = app.hbm().getContactsInGroup(groupToAdd);

        app.contacts().addContactInGroup(contactToAdd, groupToAdd);

        List<ContactData> newRelated = app.hbm().getContactsInGroup(groupToAdd);
        List<ContactData> expectedList = new ArrayList<>(oldRelated);
        int newContact = newRelated.size() - 1;
        expectedList.add(contactToAdd.contactWithNames(newRelated.get(newContact).getId(),
               newRelated.get(newContact).getFirstName(),
               newRelated.get(newContact).getLastName()));
        Assertions.assertEquals(newRelated, expectedList);
    }

    @Test
    void canRemoveContactFromGroup() {
        if (app.hbm().getContactCount() == 0) {
            app.hbm().createContact(new ContactData().contactWithNames("", "firstName", "lastName"));
        } else if (app.hbm().getGroupCount() == 0) {
            app.hbm().createGroup(new GroupData("", "Group name", "Group header", "Group footer"));
        }
        List<GroupData> groups = app.hbm().getGroupList();
        List<ContactData> contactsBefore = app.hbm().getContactList();
        ContactData contactToAdd = contactsBefore.iterator().next();
        GroupData groupToAdd = groups.iterator().next();

        if (!isContactInGroup(contactToAdd, groupToAdd)) {
            app.contacts().addContactInGroup(contactToAdd, groupToAdd);
        }

        List<ContactData> oldRelated = app.hbm().getContactsInGroup(groupToAdd);
        app.contacts().removeContactFromGroup(contactToAdd, groupToAdd);
        List<ContactData> newRelated = app.hbm().getContactsInGroup(groupToAdd);
        Assertions.assertEquals(oldRelated.size(), newRelated.size() + 1);
    }

    private static boolean isContactInGroup(ContactData contact, GroupData group) {
        List<ContactData> list = app.hbm().getContactsInGroup(group);
        boolean result = false;
        for (var ctr : list) {
            if (ctr.getId().equals(contact.getId())) {
                result = true;
                break;
            }
        }
        return result;
    }
}
