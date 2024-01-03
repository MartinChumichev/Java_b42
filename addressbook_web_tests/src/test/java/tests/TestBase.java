package tests;

import manager.ApplicationManager;
import org.junit.jupiter.api.BeforeEach;

import java.util.Random;

public class TestBase {
    public static ApplicationManager app;

    @BeforeEach
    public void setUp() {
        if (app == null) {
            app = new ApplicationManager();
        }
        app.init(System.getProperty("browser", "chrome"));
    }

    public String randomString(int a) {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < a; i++) {
            result.append((char) ('a' + new Random().nextInt(26)));
        }
        return result.toString();
    }

}
