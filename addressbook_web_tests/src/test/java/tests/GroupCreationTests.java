package tests;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import common.CommonFunctions;
import model.GroupData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class GroupCreationTests extends TestBase {

    @ParameterizedTest
    @MethodSource("singleRandomGroup")
    void canCreateGroup(GroupData group) {
        List<GroupData> oldGroups = app.hbm().getGroupList();
        app.hbm().createGroup(group);
        List<GroupData> newGroups = app.hbm().getGroupList();
        Comparator<GroupData> compareById = (o1, o2) -> {
            return Integer.compare(Integer.parseInt(o1.id()), Integer.parseInt(o2.id()));
        };
        newGroups.sort(compareById);
        var maxId = newGroups.get(newGroups.size() - 1).id();
        List<GroupData> expectedList = new ArrayList<>(oldGroups);
        expectedList.add(group.withId(maxId));
        expectedList.sort(compareById);
        Assertions.assertEquals(newGroups, expectedList);
    }

    @ParameterizedTest
    @MethodSource("negativeGroupProvider")
    void carNotCreateGroups(GroupData group) {
        List<GroupData> oldGroups = app.hbm().getGroupList();
        app.groups().createGroup(group);
        List<GroupData> newGroups = app.hbm().getGroupList();
        Assertions.assertEquals(oldGroups, newGroups);
    }

    public static List<GroupData> groupProvider() throws IOException {
        List<GroupData> list = new ArrayList<>();
        for (String name : List.of("", "Group name")) {
            for (String header : List.of("", "group header")) {
                for (String footer : List.of("", "group footer")) {
                    list.add(new GroupData().withName(name).withHeader(header).withFooter(footer));
                }
            }
        }
        ObjectMapper mapper = new ObjectMapper();
        var value = mapper.readValue(new File("groups.json"), new TypeReference<List<GroupData>>() {
        });
        list.addAll(value);
        return list;
    }

    public static List<GroupData> singleRandomGroup() {
        return List.of(new GroupData()
               .withName(CommonFunctions.randomString(9))
               .withFooter(CommonFunctions.randomString(8))
               .withHeader(CommonFunctions.randomString(7)));
    }

    public static List<GroupData> negativeGroupProvider() {
        return new ArrayList<>(List.of(
               new GroupData("", "Group name'", "", "")));
    }
}
