package ru.stqa.mantis.tests;

import common.CommonFunctions;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class UserRegistrationTests extends TestBase {

    @Test
    void canRegisterUser() {
        String name = CommonFunctions.randomString(5);
        String email = String.format("%s@localhost", name);
        String password = "password";

        app.jamesCli().addUser(email, password);
        app.user().startUserRegistration(name, email);
        String link = app.mail().getLinkFromEmail(email, password);
        app.user().finishUserRegistration(link, password);
        app.http().login(name, password);
        Assertions.assertTrue(app.http().isLoggedIn());
    }
}
