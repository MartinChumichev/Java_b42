package tests;

import model.GroupData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class GroupCreationTests extends TestBase {

    @ParameterizedTest
    @MethodSource("groupProvider")
    void carCreateMultiplyGroups(GroupData group) {
        List<GroupData> oldGroups = app.groups().getList();
        app.groups().createGroup(group);
        List<GroupData> newGroups = app.groups().getList();
        Comparator<GroupData> compareById = (o1, o2) -> {
            return Integer.compare(Integer.parseInt(o1.id()), Integer.parseInt(o2.id()));
        };
        newGroups.sort(compareById);
        List<GroupData> expectedList = new ArrayList<>(oldGroups);
        expectedList.add(group.withId(newGroups.get(newGroups.size() - 1).id()).withHeader("").withFooter(""));
        expectedList.sort(compareById);
        Assertions.assertEquals(newGroups, expectedList);
    }

    @ParameterizedTest
    @MethodSource("negativeGroupProvider")
    void carNotCreateGroups(GroupData group) {
        List<GroupData> oldGroups = app.groups().getList();
        app.groups().createGroup(group);
        List<GroupData> newGroups = app.groups().getList();
        Assertions.assertEquals(oldGroups, newGroups);
    }

    public static List<GroupData> groupProvider() {
        List<GroupData> list = new ArrayList<>();
        for (String name : List.of("", "Group name")) {
            for (String header : List.of("", "group header")) {
                for (String footer : List.of("", "group footer")) {
                    list.add(new GroupData().withName(name).withHeader(header).withFooter(footer));
                }
            }
        }
        for (int i = 0; i < 5; i++) {
            list.add(new GroupData()
                   .withName(randomString(i))
                   .withHeader(randomString(i))
                   .withFooter(randomString(i)));
        }
        return list;
    }

    public static List<GroupData> negativeGroupProvider() {
        return new ArrayList<>(List.of(
               new GroupData("", "Group name'", "", "")));
    }
}
