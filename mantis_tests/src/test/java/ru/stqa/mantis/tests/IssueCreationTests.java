package ru.stqa.mantis.tests;

import common.CommonFunctions;
import org.junit.jupiter.api.Test;
import ru.stqa.mantis.model.IssueData;

public class IssueCreationTests extends TestBase {

    @Test
    void canCreateIssue() {
        app.soap().createIssue(new IssueData()
               .withSummary(CommonFunctions.randomString(10))
               .withDescription(CommonFunctions.randomString(10))
               .withProject(1L));

    }
}
