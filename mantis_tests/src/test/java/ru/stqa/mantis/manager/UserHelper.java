package ru.stqa.mantis.manager;

import org.openqa.selenium.By;
import ru.stqa.mantis.model.DeveloperMailUser;

public class UserHelper extends HelperBase {
    public UserHelper(ApplicationManager manager) {
        super(manager);
    }

    public void startUserRegistration(String username, String email) {
        manager.driver().get(manager.property("web.baseURL") + "/signup_page.php");
        fillField(By.name("username"), username);
        fillField(By.name("email"), email);
        click(By.xpath("//input[@value='Signup']"));
    }

    public void finishUserRegistration(String confirmationLink, String password) {
        manager.driver().get(confirmationLink);
        fillField(By.name("password"), password);
        fillField(By.name("password_confirm"), password);
        click(By.xpath("//button[@type='submit']"));
    }

    public void startCreation(String user, String email) {
        if(!manager.session().isLoggedIn()) {
            manager.session().login(manager.property("web.username"), manager.property("web.password"));
        }
        manager.driver().get(String.format("%s/manage_user_create_page.php", manager.property("web.baseURL")));
        fillField(By.name("username"), user);
        fillField(By.name("realname"), user);
        fillField(By.name("email"), email);
        click(By.cssSelector("input[type='submit']"));
    }

    public void finishCreation(String link, String password) {
        if(!manager.session().isLoggedIn()) {
            manager.session().login(manager.property("web.username"), manager.property("web.password"));
        }
        manager.driver().get(link);
        fillField(By.name("password"), password);
        fillField(By.name("password_confirm"), password);
        click(By.xpath("//button[@type='submit']"));
    }
}
