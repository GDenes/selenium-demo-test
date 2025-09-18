package sauce_demo.factories;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import sauce_demo.interfaces.WebDriverFactory;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;

public class ChromeFactory implements WebDriverFactory {

    @Override
    public WebDriver createDriver(boolean isHeadless) {
        ChromeOptions options = checkAndSetHeadless(isHeadless);
        return new ChromeDriver(options);
    }

    @Override
    public RemoteWebDriver createRemoteDriver(boolean isHeadless, String remoteUrl) {
        try {
            ChromeOptions options = checkAndSetHeadless(isHeadless);
            return new RemoteWebDriver(new URI(remoteUrl).toURL(), options);
        } catch (MalformedURLException | URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Checks the system property for headless mode in ChromeOptions.
     * If the system property 'headless' is set to 'true', it configures ChromeOptions for headless mode.
     *
     * @return ChromeOptions instance with headless mode configured based on the system property.
     */
    private ChromeOptions checkAndSetHeadless(boolean isHeadless) {
        ChromeOptions options = new ChromeOptions().addArguments("--start-maximized");
        if (isHeadless) {
            options.addArguments("--headless");
            options.addArguments("--no-sandbox");
            options.addArguments("--remote-allow-origins=*");
            options.addArguments("--disable-dev-shm-usage");
            options.addArguments("--headless");
            options.addArguments("ignore-certificate-errors");
        }
        return options;
    }
}
