package sauce_demo.interfaces;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.RemoteWebDriver;

public interface WebDriverFactory {

    WebDriver createDriver(boolean isHeadless);
    RemoteWebDriver createRemoteDriver(boolean isHeadless, String remoteUrl);
}
