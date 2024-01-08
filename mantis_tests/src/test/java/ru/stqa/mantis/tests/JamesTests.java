package ru.stqa.mantis.tests;

import common.CommonFunctions;
import org.junit.jupiter.api.Test;

public class JamesTests extends TestBase{

    @Test
    void canCreateUser() {
        app.jamesCli().addUser(String.format("%s@localhost", CommonFunctions.randomString(10)), "password");
    }
}
