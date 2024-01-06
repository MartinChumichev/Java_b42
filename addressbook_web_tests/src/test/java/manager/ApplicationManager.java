package manager;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.time.Duration;
import java.util.Properties;

public class ApplicationManager {
    protected WebDriver driver;
    private LoginHelper session;
    private GroupHelper groups;
    private ContactHelper contacts;
    private Properties properties;
    private JDBCHelper jdbc;

    public LoginHelper session() {
        if (session == null) {
            session = new LoginHelper(this);
        }
        return session;
    }

    public GroupHelper groups() {
        if (groups == null) {
            groups = new GroupHelper(this);
        }
        return groups;
    }

    public ContactHelper contacts() {
        if (contacts == null) {
            contacts = new ContactHelper(this);
        }
        return contacts;
    }

    public JDBCHelper jdbc() {
        if (jdbc == null) {
            jdbc = new JDBCHelper(this);
        }
        return jdbc;
    }

    public void init(String browser, Properties properties) {
        this.properties = properties;
        if (driver == null) {
            switch (browser) {
                case "chrome" -> driver = new ChromeDriver();
                case "firefox" -> driver = new FirefoxDriver();
                case "edge" -> driver = new EdgeDriver();
                default -> throw new IllegalArgumentException(String.format("Неизвестный браузер %s", browser));
            }

            Runtime.getRuntime().addShutdownHook(new Thread(driver::quit));
            driver.manage().window().maximize();
            driver.get(properties.getProperty("web.baseURL"));
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
            session().login(properties.getProperty("web.username"), properties.getProperty("web.password"));
        }
    }

    protected boolean isElementPresent(By locator) {
        try {
            driver.findElement(locator);
            return true;
        } catch (NoSuchElementException exception) {
            return false;
        }
    }
}
