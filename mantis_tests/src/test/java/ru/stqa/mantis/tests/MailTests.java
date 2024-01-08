package ru.stqa.mantis.tests;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.stqa.mantis.model.MailMessage;

import java.time.Duration;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MailTests extends TestBase {

    @Test
    void canDrainInbox() {
        app.mail().drain("user1@localhost", "password");
    }

    @Test
    void canReceiveEmail() {
        List<MailMessage> messages = app.mail().receive("user1@localhost", "password", Duration.ofSeconds(10));
        Assertions.assertEquals(1, messages.size());
        System.out.println(messages);
    }

    @Test
    void canExtractUrl() {
        List<MailMessage> messages = app.mail().receive("user1@localhost", "password", Duration.ofSeconds(10));
        String text = messages.get(0).content();
        Pattern pattern = Pattern.compile("http://\\S*");
        Matcher matcher = pattern.matcher(text);
        if (matcher.find()) {
            var url = text.substring(matcher.start(), matcher.end());
            System.out.println(url);
        }
    }
}
