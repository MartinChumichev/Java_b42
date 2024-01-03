package manager;

import model.GroupData;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

public class GroupHelper extends HelperBase {

    public GroupHelper(ApplicationManager manager) {
        super(manager);
    }

    public void modifyGroup(GroupData group, GroupData modifiedGroup) {
        openGroupPage();
        selectGroup(group);
        initGroupModification();
        fillGroupForm(modifiedGroup);
        submitGroupModification();
        returnToGroupsPage();
    }

    public void createGroup(GroupData group) {
        openGroupPage();
        initCreationGroup();
        fillGroupForm(group);
        submitGroupCreation();
        returnToGroupsPage();
    }

    public void removeGroups(GroupData group) {
        openGroupPage();
        selectGroup(group);
        submitRemovalGroup();
        returnToGroupsPage();
    }

    public void removeAllGroups() {
        openGroupPage();
        selectAllGroups();
        submitRemovalGroup();
        returnToGroupsPage();
    }

    private void selectAllGroups() {
        List<WebElement> checkboxes = manager.driver.findElements(By.name("selected[]"));
        for (WebElement checkbox : checkboxes) {
            checkbox.click();
        }
    }

    public int getCount() {
        openGroupPage();
        return manager.driver.findElements(By.name("selected[]")).size();
    }

    private void fillGroupForm(GroupData group) {
        click(By.name("group_name"));
        fillField(By.name("group_name"), group.name());
        click(By.name("group_header"));
        fillField(By.name("group_header"), group.header());
        click(By.name("group_footer"));
        fillField(By.name("group_footer"), group.footer());
    }

    private void submitGroupCreation() {
        click(By.name("submit"));
    }

    private void initGroupModification() {
        click(By.name("edit"));
    }

    private void selectGroup(GroupData group) {
        click(By.xpath(String.format("//input[@value='%s']", group.id())));
    }

    private void submitGroupModification() {
        click(By.name("update"));
    }

    private void initCreationGroup() {
        click(By.name("new"));
    }

    private void submitRemovalGroup() {
        click(By.name("delete"));
    }

    private void returnToGroupsPage() {
        click(By.linkText("group page"));
    }

    public void openGroupPage() {
        if (!manager.isElementPresent(By.name("new"))) {
            click(By.linkText("groups"));
        }
    }

    public List<GroupData> getList() {
        openGroupPage();
        List<GroupData> groups = new ArrayList<>();
        List<WebElement> spans = manager.driver.findElements(By.xpath("//span[@class='group']"));
        for (var span : spans) {
            var name = span.getText();
            var id = span.findElement(By.name("selected[]")).getAttribute("value");
            groups.add(new GroupData().withId(id).withName(name));
        }
        return groups;
    }
}
