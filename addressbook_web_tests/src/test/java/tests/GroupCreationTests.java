package tests;

import model.GroupData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.ArrayList;
import java.util.List;

public class GroupCreationTests extends TestBase {

    @ParameterizedTest
    @MethodSource("groupProvider")
    void carCreateMultiplyGroups(GroupData group) {
        int groupCount = app.groups().getCount();
        app.groups().createGroup(group);
        int newGroupCount = app.groups().getCount();
        Assertions.assertEquals(groupCount + 1, newGroupCount);
    }

    @ParameterizedTest
    @MethodSource("negativeGroupProvider")
    void carNotCreateGroups(GroupData group) {
        int groupCount = app.groups().getCount();
        app.groups().createGroup(group);
        int newGroupCount = app.groups().getCount();
        Assertions.assertEquals(groupCount, newGroupCount);
    }

    public static List<GroupData> groupProvider() {
        List<GroupData> list = new ArrayList<>();
        for (String name : List.of("", "Group name")) {
            for (String header : List.of("", "group header")) {
                for (String footer : List.of("", "group footer")) {
                    list.add(new GroupData(name, header, footer));
                }
            }
        }
        for (int i = 0; i < 5; i++) {
            list.add(new GroupData(randomString(i), randomString(i), randomString(i)));
        }
        return list;
    }

    public static List<GroupData> negativeGroupProvider() {
        return new ArrayList<>(List.of(
               new GroupData("Group name'", "", "")));
    }
}
