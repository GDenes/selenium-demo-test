package sauce_demo.factories;

import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import sauce_demo.enums.Browser;

import java.util.HashMap;
import java.util.Map;

public class BrowserFactory {

    private static final Logger logger = LoggerFactory.getLogger(BrowserFactory.class);

    static final String DEFAULT_URL = "http://192.168.50.69:4444/";
    static final Browser BROWSER = Browser.customValueOf(System.getProperty("browser"));
    static final boolean IS_REMOTE = System.getProperty("remote") != null && System.getProperty("remote").equalsIgnoreCase("true");
    static final boolean IS_HEADLESS = System.getProperty("headless") != null && System.getProperty("headless").equalsIgnoreCase("true");
    static String remoteUrl = System.getProperty("url");
    static final String REMOTE_URL = (remoteUrl != null && !remoteUrl.isEmpty()) ? remoteUrl : DEFAULT_URL;

    /**
     * Retrieves a map of system variables including browser type, headless mode,
     * remote execution status, and remote URL.
     *
     * @return A Map containing system variables as key-value pairs.
     */
    public static Map<String, String> getSystemVariables() {
        Map<String, String> systemVariables = new HashMap<>();
        systemVariables.put("browser", String.valueOf(BROWSER).toLowerCase());
        systemVariables.put("headless", String.valueOf(IS_HEADLESS));
        systemVariables.put("remote", String.valueOf(IS_REMOTE));
        systemVariables.put("remote_url ", REMOTE_URL);
        return systemVariables;
    }

    /**
     * Creates a WebDriver instance based on the specified browser.
     *
     * @return WebDriver instance based on the specified browser
     */
    public static WebDriver getDriver() {
        logger.info("Creating driver with parameters:" +
                "\nBrowser: " + BROWSER +
                "\nHeadless: " + IS_HEADLESS +
                "\nRemote: " + IS_REMOTE +
                "\nURL: " + REMOTE_URL);

        if (IS_REMOTE) {
            return switch (BROWSER) {
                case EDGE -> new EdgeFactory().createRemoteDriver(IS_HEADLESS, REMOTE_URL);
                case FIREFOX -> new FirefoxFactory().createRemoteDriver(IS_HEADLESS, REMOTE_URL);
                default -> new ChromeFactory().createRemoteDriver(IS_HEADLESS, REMOTE_URL);
            };
        }

        return switch (BROWSER) {
            case EDGE -> new EdgeFactory().createDriver(IS_HEADLESS);
            case FIREFOX -> new FirefoxFactory().createDriver(IS_HEADLESS);
            default -> new ChromeFactory().createDriver(IS_HEADLESS);
        };
    }


}
