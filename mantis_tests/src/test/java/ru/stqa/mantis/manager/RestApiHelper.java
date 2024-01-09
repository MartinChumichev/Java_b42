package ru.stqa.mantis.manager;

import io.swagger.client.ApiClient;
import io.swagger.client.ApiException;
import io.swagger.client.Configuration;
import io.swagger.client.api.IssuesApi;
import io.swagger.client.api.UserApi;
import io.swagger.client.auth.ApiKeyAuth;
import io.swagger.client.model.AccessLevel;
import io.swagger.client.model.Identifier;
import io.swagger.client.model.Issue;
import io.swagger.client.model.User;
import ru.stqa.mantis.model.IssueData;
import ru.stqa.mantis.model.UserData;

public class RestApiHelper extends HelperBase {
    public RestApiHelper(ApplicationManager manager) {
        super(manager);
        ApiClient defaultClient = Configuration.getDefaultApiClient();
        ApiKeyAuth Authorization = (ApiKeyAuth) defaultClient.getAuthentication("Authorization");
        Authorization.setApiKey(manager.property("apiKey"));
    }

    public void createIssue(IssueData issueData) {
        Issue issue = new Issue();
        issue.setSummary(issueData.summary());
        issue.setDescription(issueData.description());
        var categoryId = new Identifier();
        categoryId.setId(issueData.category());
        issue.setCategory(categoryId);
        var projectId = new Identifier();
        projectId.setId(issueData.project());
        issue.setProject(projectId);

        IssuesApi apiInstance = new IssuesApi();
        try {
            apiInstance.issueAdd(issue);
        } catch (ApiException e) {
            throw new RuntimeException(e);
        }
    }

    public void startUserRegistration(UserData userData) {
        User user = new User();
        AccessLevel leveData = new AccessLevel();
        user.setUsername(userData.username());
        user.setPassword(userData.password());
        user.setRealName(userData.real_name());
        user.setEmail(userData.email());
        user.setAccessLevel(leveData.name("developer"));
        user.setEnabled(true);
        user.setProtected(false);

        UserApi apiInstance = new UserApi();
        try {
            apiInstance.userAdd(user);
        } catch (ApiException e) {
            throw new RuntimeException(e);
        }
    }
}
