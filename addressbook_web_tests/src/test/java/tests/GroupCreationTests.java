package tests;

import model.GroupData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class GroupCreationTests extends TestBase {

    @Test
    void canCreateGroup() {
        int groupCount = app.groups().getCount();
        app.groups().createGroup(new GroupData("Group name", "Group header", "Group footer"));

        int newGroupCount = app.groups().getCount();
        Assertions.assertEquals(groupCount + 1, newGroupCount);

    }

    @Test
    void canCreateGroupWithEmptyName() {
        int groupCount = app.groups().getCount();
        app.groups().createGroup(new GroupData("", "", ""));

        int newGroupCount = app.groups().getCount();
        Assertions.assertEquals(groupCount + 1, newGroupCount);
    }

    @Test
    void canCreateGroupWithName() {
        int groupCount = app.groups().getCount();
        app.groups().createGroup(new GroupData().withName("name"));

        int newGroupCount = app.groups().getCount();
        Assertions.assertEquals(groupCount + 1, newGroupCount);
    }

    @Test
    void carCreateMultiplyGroups() {
        int groupCount = app.groups().getCount();
        int a = 5;

        for (int i = 0; i < a; i++) {
            app.groups().createGroup(new GroupData(randomString(6), "Group header", "Group footer"));
        }
        int newGroupCount = app.groups().getCount();
        Assertions.assertEquals(groupCount + a, newGroupCount);
    }
}
