package tests;

import model.GroupData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Random;

public class GroupModificationTests extends TestBase {

    @Test
    void canModifyGroup() {
        if (app.groups().getCount() == 0) {
            app.groups().createGroup(new GroupData("", "name", "header", "footer"));
        }
        List<GroupData> oldGroups = app.groups().getList();
        int index = new Random().nextInt(oldGroups.size());
        GroupData testData = new GroupData().withName("modify_name");
        app.groups().modifyGroup(oldGroups.get(index), testData);
        List<GroupData> newGroups = app.groups().getList();
        List<GroupData> expectedList = new ArrayList<>(oldGroups);
        expectedList.set(index, testData.withId(oldGroups.get(index).id()));
        Comparator<GroupData> compareById = (o1, o2) -> {
            return Integer.compare(Integer.parseInt(o1.id()), Integer.parseInt(o2.id()));
        };
        newGroups.sort(compareById);
        expectedList.sort(compareById);
        Assertions.assertEquals(newGroups, expectedList);
    }
}
