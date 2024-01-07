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
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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

    private Object generateData(Supplier<Object> dataSupplier) {
        return Stream.generate(dataSupplier).limit(count).collect(Collectors.toList());
    }

    private Object generateGroups() {
        return generateData(() ->
               new GroupData()
                      .withName(randomString(10))
                      .withHeader(randomString(10))
                      .withFooter(randomString(10)));
    }

    private Object generateContacts() {
        return generateData(() ->
               new ContactData().contactWithNames(
                             "",
                             CommonFunctions.randomString(10),
                             CommonFunctions.randomString(10))
                      .contactWithPhoto(randomFile("src/test/resources/images/")));
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
