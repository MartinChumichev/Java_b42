package ru.stqa.mantis.tests;

import common.CommonFunctions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import ru.stqa.mantis.model.DeveloperMailUser;

import java.time.Duration;
import java.util.stream.Stream;

public class UserRegistrationTests extends TestBase {

    DeveloperMailUser user;

    @ParameterizedTest
    @MethodSource("randomUserName")
    void canRegisterUserUsingUI(String name) {
        String email = String.format("%s@localhost", name);
        String password = "password";

        app.jamesCli().addUser(email, password);
        app.user().startUserRegistration(name, email);
        String link = app.mail().getLinkFromEmail(email, password);
        app.user().finishUserRegistration(link, password);
        app.http().login(name, password);
        Assertions.assertTrue(app.http().isLoggedIn());
    }

    @Test
    void canRegisterUserUsingDeveloperMail() {
        String password = "password";
        user = app.developerMail().addUser();
        String email = String.format("%s@developermail.com", user.name());

        app.user().startCreation(user.name(), email);

        var link = app.developerMail().receive(user, Duration.ofSeconds(10));

        app.user().finishCreation(link, password);

        app.http().login(user.name(), password);
        Assertions.assertTrue(app.http().isLoggedIn());
    }

    @AfterEach
    void deleteMailUser() {
        app.developerMail().deleteUser(user);
    }

    public static Stream<String> randomUserName() {
        return Stream.of(CommonFunctions.randomString(5)).limit(1);
    }
}
