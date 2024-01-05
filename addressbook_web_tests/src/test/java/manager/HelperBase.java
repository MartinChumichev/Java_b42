package manager;

import org.openqa.selenium.By;

import java.nio.file.Paths;

public class HelperBase {
    protected final ApplicationManager manager;

    public HelperBase(ApplicationManager manager) {
        this.manager = manager;
    }

    protected void fillField(By locator, String group) {
        manager.driver.findElement(locator).clear();
        manager.driver.findElement(locator).sendKeys(group);
    }

    protected void attach(By locator, String file) {
        manager.driver.findElement(locator).sendKeys(Paths.get(file).toAbsolutePath().toString());
    }

    protected void click(By locator) {
        manager.driver.findElement(locator).click();
    }
}
