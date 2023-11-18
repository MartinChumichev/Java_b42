package tests;

import model.GroupData;
import org.junit.jupiter.api.Test;

public class GroupCreationTests extends TestBase {

    @Test
    public void canCreateGroup() {
        app.groups().createGroup(new GroupData("Group name", "Group header", "Group footer"));
    }

    @Test
    public void canCreateGroupWithEmptyName() {
        app.groups().createGroup(new GroupData("", "", ""));
    }

    @Test
    public void canCreateGroupWithName() {
        app.groups().createGroup(new GroupData().withName("name"));
    }


}
