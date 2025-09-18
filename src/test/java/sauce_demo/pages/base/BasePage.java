package sauce_demo.pages.base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * The BasePage class represents the base page for the Sauce Demo application.
 * It provides functionalities for initializing WebDriver and PageFactory.
 */
public abstract class BasePage {

    private static final Logger logger = LoggerFactory.getLogger(BasePage.class);
    protected WebDriver driver;

    /**
     * Constructs a BasePage with the provided WebDriver.
     *
     * @param driver The WebDriver instance to be used by the page.
     */
    public BasePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }
}
