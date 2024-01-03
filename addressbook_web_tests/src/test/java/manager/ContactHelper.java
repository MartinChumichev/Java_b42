package manager;

import model.ContactData;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

public class ContactHelper extends HelperBase {
    public ContactHelper(ApplicationManager manager) {
        super(manager);
    }

    public void modifyContact(ContactData contact, ContactData testData) {
        goToHomePage();
        initContactModification(contact);
        fillForm(testData);
        submitContactModification();
        goToHomePage();
    }

    public void createContact(ContactData contact) {
        initContactCreation();
        fillForm(contact);
        submitContactCreation();
        goToHomePage();
    }

    public void removeContact(ContactData contact) {
        goToHomePage();
        selectContact(contact);
        submitRemoval();
        acceptWindowAlert();
        goToHomePage();
    }

    public void removeAllContacts() {
        selectAllContacts();
        submitRemoval();
        acceptWindowAlert();
        goToHomePage();
    }

    private void initContactModification(ContactData contact) {
        click(By.xpath(String.format("//input[@value='%s']/ancestor::tr/td[8]/a", contact.getId())));
    }

    private void submitContactModification() {
        click(By.name("update"));
    }

    private void selectAllContacts() {
        List<WebElement> checkboxes = manager.driver.findElements(By.name("selected[]"));
        for (WebElement checkbox : checkboxes) {
            checkbox.click();
        }
    }

    public int getCount() {
        return manager.driver.findElements(By.name("selected[]")).size();
    }

    private void acceptWindowAlert() {
        manager.driver.switchTo().alert().accept();
    }

    private void submitRemoval() {
        click(By.xpath("//input[@value='Delete']"));
    }

    private void selectContact(ContactData contact) {
        click(By.xpath(String.format("//input[@value='%s']", contact.getId())));
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

    public List<ContactData> getList() {
        List<ContactData> contacts = new ArrayList<>();
        List<WebElement> spans = manager.driver.findElements(By.xpath("//tbody//tr[position()>1]"));
        for (var span : spans) {
            var id = span.findElement(By.name("selected[]")).getAttribute("value");
            var lastName = span.findElement(By.xpath("//td[2]")).getText();
            var firstName = span.findElement(By.xpath("//td[3]")).getText();
            contacts.add(new ContactData().contactWithNames(id, firstName, lastName));
        }
        return contacts;
    }
}
