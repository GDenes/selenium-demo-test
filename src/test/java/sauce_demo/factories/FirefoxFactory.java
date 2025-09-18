package sauce_demo.factories;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import sauce_demo.interfaces.WebDriverFactory;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;

public class FirefoxFactory implements WebDriverFactory {

    @Override
    public WebDriver createDriver(boolean isHeadless) {
        FirefoxOptions options = checkAndSetHeadless(isHeadless);
        WebDriver driver = new FirefoxDriver(options);
        driver.manage().window().maximize();
        return driver;
    }

    @Override
    public RemoteWebDriver createRemoteDriver(boolean isHeadless, String remoteUrl) {
        try {
            FirefoxOptions options = checkAndSetHeadless(isHeadless);
            RemoteWebDriver driver = new RemoteWebDriver(new URI(remoteUrl).toURL(), options);
            driver.manage().window().maximize();
            return driver;
        } catch (MalformedURLException | URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Checks the system property for headless mode in FirefoxOptions.
     * If the system property 'headless' is set to 'true', it configures FirefoxOptions for headless mode.
     *
     * @return FirefoxOptions instance with headless mode configured based on the system property.
     */
    private FirefoxOptions checkAndSetHeadless(boolean isHeadless) {
        FirefoxOptions options = new FirefoxOptions();
        if (isHeadless) {
            options.addArguments("--headless");
        }
        return options;
    }
}
