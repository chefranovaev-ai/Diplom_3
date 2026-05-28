import config.AppConfig;
import api.UserApiSteps;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.UUID;

public class BaseTest {
    protected WebDriver driver;
    protected String fakeName;
    protected String fakeEmail;
    protected final String FAKE_PASSWORD = "securePassword123";

    @Before
    public void setUp() {
        fakeName = "CleanCodeTester";
        fakeEmail = "test_" + UUID.randomUUID().toString().substring(0, 5) + "@yandex.ru";

        String browser = System.getProperty("browser", "chrome");
        if ("chrome".equalsIgnoreCase(browser)) {
            WebDriverManager.chromedriver().setup();
            driver = new ChromeDriver();
        } else if ("yandex".equalsIgnoreCase(browser)) {
            WebDriverManager.chromedriver().driverVersion("146.0").setup();
            ChromeOptions options = new ChromeOptions();
            options.setBinary(AppConfig.YANDEX_BROWSER_BINARY_PATH);
            options.addArguments("--remote-allow-origins=*");
            options.addArguments("--no-sandbox");
            options.addArguments("--disable-dev-shm-usage");
            driver = new ChromeDriver(options);
        }

        driver.manage().window().maximize();
    }

    @After
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }

        UserApiSteps apiSteps = new UserApiSteps();
        apiSteps.deleteUserIfCreated(fakeEmail, FAKE_PASSWORD);
    }
}

