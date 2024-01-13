package tests;

import io.qameta.allure.Allure;
import model.GroupData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class GroupRemovalTests extends TestBase {

    @Test
    void canRemoveGroup() {
        Allure.step("Checking_precondition", step -> {
            if (app.hbm().getGroupCount() == 0) {
                app.hbm().createGroup(new GroupData("", "Group name", "Group header", "Group footer"));
            }
        });
        List<GroupData> oldGroups = app.hbm().getGroupList();
        int index = new Random().nextInt(oldGroups.size());
        app.groups().removeGroups(oldGroups.get(index));
        List<GroupData> newGroups = app.hbm().getGroupList();
        List<GroupData> expectedList = new ArrayList<>(oldGroups);
        expectedList.remove(index);
        Allure.step("Validating_results", step -> {
            Assertions.assertEquals(newGroups, expectedList);
        });
    }

    @Test
    void canRemoveAllGroups() {
        if (app.hbm().getGroupCount() == 0) {
            app.hbm().createGroup(new GroupData("", "Group name", "Group header", "Group footer"));
        }
        app.groups().removeAllGroups();
        Assertions.assertEquals(0, app.hbm().getGroupCount());
    }
}
