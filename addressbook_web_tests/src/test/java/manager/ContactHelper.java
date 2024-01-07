package manager;

import model.ContactData;
import model.GroupData;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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

    public void createContactWithPhoto(ContactData contact) {
        initContactCreation();
        fillForm(contact);
        attachPhoto(contact);
        submitContactCreation();
        goToHomePage();
    }

    public void createContact(ContactData contact) {
        initContactCreation();
        fillForm(contact);
        submitContactCreation();
        goToHomePage();
    }

    public void createContact(ContactData contact, GroupData group) {
        initContactCreation();
        fillForm(contact);
        selectGroup(group);
        submitContactCreation();
        goToHomePage();
    }

    public void addContactInGroup(ContactData contact, GroupData group) {
        goToHomePage();
        selectContact(contact);
        selectGroupToAdd(group);
        submitGroupAddingToContact();
    }

    public void removeContactFromGroup(ContactData contact, GroupData group) {
        goToHomePage();
        selectGroupFromMainPage(group);
        selectContact(contact);
        removeGroupContact();
        goToHomePage();
        selectAllContactsFromDropList();
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

    private void selectAllContactsFromDropList() {
        new Select(manager.driver.findElement(By.name("group"))).selectByVisibleText("[all]");
    }

    private void removeGroupContact() {
        click(By.name("remove"));
    }

    private void selectGroup(GroupData group) {
        new Select(manager.driver.findElement(By.name("new_group"))).selectByValue(group.id());
    }

    private void selectGroupFromMainPage(GroupData group) {
        new Select(manager.driver.findElement(By.name("group"))).selectByValue(group.id());
    }

    private void selectGroupToAdd(GroupData group) {
        new Select(manager.driver.findElement(By.name("to_group"))).selectByValue(group.id());
    }

    private void initContactModification(ContactData contact) {
        click(By.xpath(String.format("//input[@value='%s']/ancestor::tr/td[8]/a", contact.getId())));
    }

    private void submitContactModification() {
        click(By.name("update"));
    }

    private void submitGroupAddingToContact() {
        click(By.name("add"));
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

    private void attachPhoto(ContactData contact) {
        attach(By.name("photo"), contact.getPhoto());
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
            var lastName = span.findElement(By.xpath(".//td[2]")).getText();
            var firstName = span.findElement(By.xpath(".//td[3]")).getText();
            contacts.add(new ContactData().contactWithNames(id, firstName, lastName));
        }
        return contacts;
    }

    public String getPhonesFromMainPage(ContactData contact) {
        return manager.driver.findElement(By.xpath(
               String.format("//input[@id='%s']/../../td[6]", contact.getId()))).getText();
    }

    public String getEmailsFromMainPage(ContactData contact) {
        return manager.driver.findElement(By.xpath(
               String.format("//input[@id='%s']/../../td[5]", contact.getId()))).getText();
    }

    public String getAddressFromMainPage(ContactData contact) {
        return manager.driver.findElement(By.xpath(
               String.format("//input[@id='%s']/../../td[4]", contact.getId()))).getText();
    }

    public ContactData infoFromEditForm(ContactData contact) {
        editContactById(Integer.parseInt(contact.getId()));
        String email = manager.driver.findElement(By.name("email")).getAttribute("value");
        String email2 = manager.driver.findElement(By.name("email2")).getAttribute("value");
        String email3 = manager.driver.findElement(By.name("email3")).getAttribute("value");
        String homeNumber = manager.driver.findElement(By.name("home")).getAttribute("value");
        String mobileNumber = manager.driver.findElement(By.name("mobile")).getAttribute("value");
        String workNumber = manager.driver.findElement(By.name("work")).getAttribute("value");
        String secondaryNumber = manager.driver.findElement(By.name("phone2")).getAttribute("value");
        String address = manager.driver.findElement(By.name("address")).getAttribute("value");
        manager.driver.navigate().back();
        return new ContactData()
               .contactWithEmails(email, email2, email3)
               .contactWithPhones(homeNumber, mobileNumber, workNumber, secondaryNumber)
               .contactWithAddress(address);
    }

    private void editContactById(int id) {
        manager.driver.findElement(By.cssSelector(String.format("a[href='edit.php?id=%s']", id))).click();
    }

    public String getAllPhones(ContactData contact) {
        return Stream.of(contact.getHomePhone(), contact.getMobilePhone(), contact.getWorkPhone(), contact.getSecondaryPhone())
               .filter(s -> s != null && !s.equals(""))
               .collect(Collectors.joining("\n"));
    }

    public String getAllEmails(ContactData contact) {
        return Stream.of(contact.getFirstEmail(), contact.getSecondEmail(), contact.getThirdEmail())
               .filter(s -> s != null && !s.equals(""))
               .collect(Collectors.joining("\n"));
    }
}
