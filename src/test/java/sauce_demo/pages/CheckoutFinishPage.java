package sauce_demo.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import sauce_demo.pages.base.NonLoginBasePage;

import java.util.List;

/**
 * The CheckoutFinishPage class represents the page displayed after completing the checkout process in the Sauce Demo application.
 */
public class CheckoutFinishPage extends NonLoginBasePage {

    private static final Logger logger = LoggerFactory.getLogger(CheckoutFinishPage.class);

    @FindBy(css = "h2.complete-header")
    List<WebElement> header;

    @FindBy(css = "div.complete-text")
    List<WebElement> description;

    @FindBy(css = "button.btn_primary")
    List<WebElement> backButton;

    /**
     * Constructs a CheckoutFinishPage object with the provided WebDriver.
     *
     * @param driver The WebDriver instance to be used by the CheckoutFinishPage.
     */
    public CheckoutFinishPage(WebDriver driver) {
        super(driver);
    }

    /**
     * Checks whether certain messages or elements appear on the checkout finish page.
     *
     * @return true if the header, description, and back button elements are present; otherwise, false.
     */
    public boolean isMessagesAppear() {
        logger.info("Checking if messages appear on the page.");
        return (!header.isEmpty() && !description.isEmpty() && !backButton.isEmpty());
    }
}
