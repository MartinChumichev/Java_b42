package generator;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLMapper;
import common.CommonFunctions;
import model.ContactData;
import model.GroupData;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static common.CommonFunctions.randomString;
import static tests.TestBase.randomFile;

public class Generator {

    @Parameter(names = {"--type", "-t"})
    String type;
    @Parameter(names = {"--output", "-o"})
    String output;
    @Parameter(names = {"--format", "-f"})
    String format;
    @Parameter(names = {"--count", "-n"})
    int count;


    public static void main(String[] args) throws IOException {
        var generator = new Generator();
        JCommander.newBuilder()
               .addObject(generator)
               .build()
               .parse(args);
        generator.run();
    }

    private void run() throws IOException {
        var data = generate();
        save(data);
    }

    private Object generate() {
        switch (type) {
            case "groups" -> {
                return generateGroups();
            }
            case "contacts" -> {
                return generateContacts();
            }
            default -> throw new IllegalArgumentException("Неизвестный тип данных " + type);
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
        List<ContactData> list = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            list.add(new ContactData().contactWithNames(
                          "",
                          CommonFunctions.randomString(i),
                          CommonFunctions.randomString(i))
                   .contactWithPhoto(randomFile("src/test/resources/images/")));
        }
        return list;
    }

    private void save(Object data) throws IOException {
        switch (format) {
            case "json" -> {
                ObjectMapper mapper = new ObjectMapper();
                mapper.enable(SerializationFeature.INDENT_OUTPUT);
                mapper.writeValue(new File(output), data);
            }
            case "yaml" -> {
                ObjectMapper mapper = new YAMLMapper();
                mapper.writeValue(new File(output), data);
            }
            case "xml" -> {
                ObjectMapper mapper = new XmlMapper();
                mapper.writeValue(new File(output), data);
            }
            default -> throw new IllegalArgumentException("Unknown format " + format);
        }
    }
}
