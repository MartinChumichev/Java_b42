package tests;

import com.github.javafaker.Faker;
import manager.ApplicationManager;
import org.junit.jupiter.api.BeforeEach;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.Properties;
import java.util.Random;

public class TestBase {
    public static ApplicationManager app;
    static Faker faker = new Faker();

    @BeforeEach
    public void setUp() throws IOException {
        var properties = new Properties();
        properties.load(new FileReader(System.getProperty("target", "local.properties")));
        if (app == null) {
            app = new ApplicationManager();
        }
        app.init(System.getProperty("browser", "chrome"), properties);
    }

    public static String randomFile(String dir) {
        var fileNames = new File(dir).list();
        var random = new Random();
        var index = random.nextInt(fileNames.length);
        return Paths.get(dir, fileNames[index]).toString();
    }

    public static String generateRandomEmail() {
        return faker.internet().emailAddress();
    }

    public static String generateRandomPhoneNumber() {
        return faker.phoneNumber().phoneNumber();
    }

}
