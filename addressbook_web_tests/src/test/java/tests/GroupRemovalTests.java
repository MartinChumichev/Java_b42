package tests;

import model.GroupData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;


public class GroupRemovalTests extends TestBase {

    @Test
    void canRemoveGroup() {
        if (app.groups().getCount() == 0) {
            app.groups().createGroup(new GroupData("Group name", "Group header", "Group footer"));
        }
        int groupCount = app.groups().getCount();
        app.groups().removeGroups();

        int newGroupCount = app.groups().getCount();
        Assertions.assertEquals(groupCount - 1, newGroupCount);
    }

    @Test
    void canRemoveAllGroups() {
        if (app.groups().getCount() == 0) {
            app.groups().createGroup(new GroupData("Group name", "Group header", "Group footer"));
        }

        app.groups().removeAllGroups();
        Assertions.assertEquals(0, app.groups().getCount());
    }
}
