package tests;

import model.GroupData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class GroupRemovalTests extends TestBase {

    @Test
    void canRemoveGroup() {
        if (app.groups().getCount() == 0) {
            app.groups().createGroup(new GroupData("", "Group name", "Group header", "Group footer"));
        }
        List<GroupData> oldGroups = app.groups().getList();
        int index = new Random().nextInt(oldGroups.size());
        app.groups().removeGroups(oldGroups.get(index));
        List<GroupData> newGroups = app.groups().getList();
        List<GroupData> expectedList = new ArrayList<>(oldGroups);
        expectedList.remove(index);
        Assertions.assertEquals(newGroups, expectedList);
    }

    @Test
    void canRemoveAllGroups() {
        if (app.groups().getCount() == 0) {
            app.groups().createGroup(new GroupData("", "Group name", "Group header", "Group footer"));
        }

        app.groups().removeAllGroups();
        Assertions.assertEquals(0, app.groups().getCount());
    }
}
