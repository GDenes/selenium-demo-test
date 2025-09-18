package sauce_demo.factories;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import sauce_demo.interfaces.WebDriverFactory;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;

public class EdgeFactory implements WebDriverFactory {

    @Override
    public WebDriver createDriver(boolean isHeadless) {
        EdgeOptions options = checkAndSetHeadless(isHeadless);
        return new EdgeDriver(options);
    }

    @Override
    public RemoteWebDriver createRemoteDriver(boolean isHeadless, String remoteUrl) {
        try {
            EdgeOptions options = checkAndSetHeadless(isHeadless);
            return new RemoteWebDriver(new URI(remoteUrl).toURL(), options);
        } catch (MalformedURLException | URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Checks the system property for headless mode in EdgeOptions.
     * If the system property 'headless' is set to 'true', it configures EdgeOptions for headless mode.
     *
     * @return EdgeOptions instance with headless mode configured based on the system property.
     */
    private EdgeOptions checkAndSetHeadless(boolean isHeadless) {
        EdgeOptions options = new EdgeOptions().addArguments("--start-maximized");
        if (isHeadless) {
            options.addArguments("--headless");
        }
        return options;
    }
}
