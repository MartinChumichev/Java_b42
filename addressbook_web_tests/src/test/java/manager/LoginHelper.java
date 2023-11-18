package manager;

import org.openqa.selenium.By;

public class LoginHelper extends HelperBase {

    public LoginHelper(ApplicationManager manager) {
        super(manager);
    }

    void login(String name, String password) {
        fillField(By.name("user"), name);
        fillField(By.name("pass"), password);
        click(By.xpath("//input[@value=\'Login\']"));
    }
}
