package manager;

import model.ContactData;
import org.openqa.selenium.By;

public class ContactHelper extends HelperBase {
    public ContactHelper(ApplicationManager manager) {
        super(manager);
    }

    public void createContact(ContactData contact) {
        initContactCreation();
        fillForm(contact);
        submitContactCreation();
        goToHomePage();
    }

    public void removeContact() {
        goToHomePage();
        selectContact();
        submitRemoval();
        acceptWindowAlert();
    }

    private void acceptWindowAlert() {
        manager.driver.switchTo().alert().accept();
    }

    private void submitRemoval() {
        click(By.xpath("//input[@value='Delete']"));
    }

    private void selectContact() {
        click(By.name("selected[]"));
    }

    private void initContactCreation() {
        click(By.xpath("//li[@class=\"all\"]/a"));
    }

    private void submitContactCreation() {
        click(By.name("submit"));
    }

    private void goToHomePage() {
        click(By.linkText("home"));
    }

    private void fillForm(ContactData contact) {
        click(By.name("firstname"));
        fillField(By.name("firstname"), contact.getFirstName());
        click(By.name("middlename"));
        fillField(By.name("middlename"), contact.getMiddleName());
        click(By.name("lastname"));
        fillField(By.name("lastname"), contact.getLastName());

        click(By.name("home"));
        fillField(By.name("home"), contact.getHomePhone());
        click(By.name("mobile"));
        fillField(By.name("mobile"), contact.getMobilePhone());
        click(By.name("work"));
        fillField(By.name("work"), contact.getWorkPhone());

        click(By.name("email"));
        fillField(By.name("email"), contact.getFirstEmail());
        click(By.name("email2"));
        fillField(By.name("email2"), contact.getSecondEmail());
        click(By.name("email3"));
        fillField(By.name("email3"), contact.getThirdEmail());
    }

    public boolean isContactPresent() {
        return manager.isElementPresent(By.name("selected[]"));
    }

}
