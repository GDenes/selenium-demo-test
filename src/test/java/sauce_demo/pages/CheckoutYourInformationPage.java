package sauce_demo.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import sauce_demo.pages.base.NonLoginBasePage;

/**
 * The CheckoutYourInformationPage class represents the page where user information is entered
 * during the checkout process in the Sauce Demo application.
 */
public class CheckoutYourInformationPage extends NonLoginBasePage {

    private static final Logger logger = LoggerFactory.getLogger(CheckoutYourInformationPage.class);

    @FindBy(css = "input[data-test='firstName']")
    WebElement firstNameInput;

    @FindBy(css = "input[data-test='lastName']")
    WebElement lastNameInput;

    @FindBy(css = "input[data-test='postalCode']")
    WebElement postalCodeInput;

    @FindBy(css = "input[data-test='continue']")
    WebElement continueButton;

    @FindBy(css = "h3[data-test='error']")
    WebElement errorMessage;

    /**
     * Constructs a CheckoutYourInformationPage object with the provided WebDriver.
     *
     * @param driver The WebDriver instance to be used by the CheckoutYourInformationPage.
     */
    public CheckoutYourInformationPage(WebDriver driver) {
        super(driver);
    }

    /**
     * Clicks the continue button to proceed with the checkout process and navigates to CheckoutOverviewPage.
     *
     * @return An instance of CheckoutOverviewPage representing the next step in the checkout process.
     */
    public CheckoutOverviewPage continueCheckout() {
        logger.info("Navigating to Checkout Overview Page.");
        continueButton.click();
        return new CheckoutOverviewPage(driver);
    }

    /**
     * Fills user data into the corresponding input fields.
     *
     * @param firstName  The first name of the user.
     * @param lastName   The last name of the user.
     * @param postalCode The postal code of the user.
     */
    public void fillUserData(String firstName, String lastName, String postalCode) {
        logger.info("Filling user data.");
        firstNameInput.sendKeys(firstName);
        lastNameInput.sendKeys(lastName);
        postalCodeInput.sendKeys(postalCode);
    }

    /**
     * Retrieves the error message displayed on the user information page.
     *
     * @return The error message text displayed on the page.
     */
    public String getErrorMessage() {
        logger.info("Retrieving the error message.");
        return errorMessage.getText();
    }
}
