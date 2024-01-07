package tests;

import common.CommonFunctions;
import model.GroupData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Set;

public class GroupModificationTests extends TestBase {

    @Test
    void canModifyGroup() {
        if (app.hbm().getGroupCount() == 0) {
            app.hbm().createGroup(new GroupData("", "Group name", "Group header", "Group footer"));
        }
        List<GroupData> oldGroups = app.hbm().getGroupList();
        int index = new Random().nextInt(oldGroups.size());
        GroupData testData = new GroupData().withName(CommonFunctions.randomString(8));
        app.groups().modifyGroup(oldGroups.get(index), testData);
        List<GroupData> newGroups = app.hbm().getGroupList();
        List<GroupData> expectedList = new ArrayList<>(oldGroups);
        expectedList.set(index, testData.withId(oldGroups.get(index).id()));
        Assertions.assertEquals(Set.copyOf(newGroups), Set.copyOf(expectedList));
    }
}
