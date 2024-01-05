package generator;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;
import model.GroupData;

import java.util.ArrayList;
import java.util.List;

import static common.CommonFunctions.randomString;

public class Generator {

    @Parameter(names = {"--type", "-t"})
    String type;
    @Parameter(names = {"--output", "-o"})
    String output;
    @Parameter(names = {"--format", "-f"})
    String format;
    @Parameter(names = {"--count", "-n"})
    int count;


    public static void main(String[] args) {
        var generator = new Generator();
        JCommander.newBuilder()
               .addObject(generator)
               .build()
               .parse(args);
        generator.run();
    }

    private void run() {
        var data = generate();
        save(data);
    }

    private Object generate() {
        if ("groups".equals(type)) {
            return generateGroups();
        } else if (("contacts".equals(type))) {
            return generateContacts();
        } else {
            throw new IllegalArgumentException("Неизвестный тип данных " + type);
        }
    }

    private Object generateGroups() {
        List<GroupData> list = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            list.add(new GroupData()
                   .withName(randomString(i))
                   .withHeader(randomString(i))
                   .withFooter(randomString(i)));
        }
        return list;
    }

    private Object generateContacts() {
        return null;
    }

    private void save(Object data) {
    }
}
