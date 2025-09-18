package sauce_demo.tests.base;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.TestInfo;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import sauce_demo.factories.BrowserFactory;
import sauce_demo.pages.LoginPage;
import sauce_demo.utils.TestResultLoggerExtension;
import sauce_demo.utils.Utils;

import java.time.Duration;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@ExtendWith(TestResultLoggerExtension.class)
public abstract class TestBase {

    private static final Logger logger = LoggerFactory.getLogger(TestBase.class);
    protected static LoginPage loginPage;
    protected static List<String> usernames;
    protected static List<String> passwords;
    public static WebDriver driver;
    private static final String URL_ROOT = "https://www.saucedemo.com";

    @BeforeAll
    public static void setUpAll() {
        driver = BrowserFactory.getDriver();
    }

    @BeforeEach
    public void setUp() {
        if (driver == null) {
            driver = BrowserFactory.getDriver();
        }
    }

    @AfterAll
    public static void tearDownAll() {
        if (driver != null) {
            logger.info("Quitting driver.");
            logger.info("----------------------------------");
            driver.quit();
        }
        Map<String, String> systemVariables = BrowserFactory.getSystemVariables();
        Utils.generateAllureEnvironmentFile(systemVariables.get("browser"), systemVariables.get("headless"), systemVariables.get("remote"), systemVariables.get("remote_url "));
    }

    /**
     * Method executed before each test to set up the testing environment.
     * It navigates to the login page, retrieves login credentials, and initializes necessary variables.
     */
    @BeforeEach
    public void setUp(TestInfo testinfo) {
        loginPage = navigateToLogin();
        usernames = getLoginCredentials().get("usernames");
        passwords = getLoginCredentials().get("passwords");
        logger.info("Starting test: " + testinfo.getDisplayName());
    }

    /**
     * Navigates to the login page of the Sauce Demo application using a WebDriver.
     *
     * @return An instance of LoginPage representing the login page.
     */
    public LoginPage navigateToLogin() {
        driver.get(URL_ROOT);
        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(500));
        return new LoginPage(driver);
    }

    /**
     * Retrieves login credentials consisting of usernames and passwords.
     *
     * @return A HashMap containing usernames and passwords.
     */
    public HashMap<String, List<String>> getLoginCredentials() {
        List<String> usernames = loginPage.getUsernames();
        String password = loginPage.getPassword();
        HashMap<String, List<String>> loginCredentials = new HashMap<>();
        loginCredentials.put("usernames", usernames);
        loginCredentials.put("passwords", new ArrayList<>(List.of(password)));
        return loginCredentials;
    }
}
