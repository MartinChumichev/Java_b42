package ru.stqa.mantis.manager;

import org.openqa.selenium.By;

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
}
