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
import java.util.List;
import java.util.Set;
import java.util.function.Supplier;
import java.util.stream.Stream;

public class GroupCreationTests extends TestBase {

    @ParameterizedTest
    @MethodSource("singleRandomGroup")
    void canCreateGroup(GroupData group) {
        List<GroupData> oldGroups = app.hbm().getGroupList();
        app.hbm().createGroup(group);
        List<GroupData> newGroups = app.hbm().getGroupList();

        var extraGroup = newGroups.stream().filter(g -> !oldGroups.contains(g)).toList();
        var newId = extraGroup.get(0).id();

        List<GroupData> expectedList = new ArrayList<>(oldGroups);
        expectedList.add(group.withId(newId));

        Assertions.assertEquals(Set.copyOf(newGroups), Set.copyOf(expectedList));
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

    public static Stream<GroupData> singleRandomGroup() {
        Supplier<GroupData> randomGroupData = () ->
               new GroupData()
                      .withName(CommonFunctions.randomString(9))
                      .withFooter(CommonFunctions.randomString(8))
                      .withHeader(CommonFunctions.randomString(7));
        return Stream.generate(randomGroupData).limit(2);
    }

    public static List<GroupData> negativeGroupProvider() {
        return new ArrayList<>(List.of(
               new GroupData("", "Group name'", "", "")));
    }
}
