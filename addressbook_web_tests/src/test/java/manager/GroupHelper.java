package manager;

import model.GroupData;
import org.openqa.selenium.By;

public class GroupHelper extends HelperBase{

    public GroupHelper(ApplicationManager manager) {
        super(manager);
    }

    public void modifyGroup(GroupData group) {
        openGroupPage();
        selectGroup();
        initGroupModification();
        fillGroupForm(group);
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

    public void removeGroup() {
        openGroupPage();
        selectGroup();
        submitRemovalGroup();
        returnToGroupsPage();
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

    private void selectGroup() {
        click(By.name("selected[]"));
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

    public boolean isGroupPresent() {
        return manager.isElementPresent(By.name("selected[]"));
    }
}
