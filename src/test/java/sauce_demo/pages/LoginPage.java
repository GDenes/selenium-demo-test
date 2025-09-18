package sauce_demo.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import sauce_demo.pages.base.BasePage;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * The LoginPage class represents the login page of the Sauce Demo application.
 */
public class LoginPage extends BasePage {

    private static final Logger logger = LoggerFactory.getLogger(LoginPage.class);

    @FindBy(css = "input[data-test='username']")
    WebElement usernameInput;

    @FindBy(css = "input[data-test='password']")
    WebElement passwordInput;

    @FindBy(css = "input[data-test='login-button']")
    WebElement loginButton;

    @FindBy(css = "h3[data-test='error']")
    List<WebElement> errorMessage;

    @FindBy(css = "#login_credentials")
    WebElement usernames;

    @FindBy(css = ".login_password")
    WebElement password;

    /**
     * Constructs a LoginPage object with the provided WebDriver.
     *
     * @param driver The WebDriver instance to be used by the LoginPage.
     */
    public LoginPage(WebDriver driver) {
        super(driver);
    }

    /**
     * Retrieves the error message displayed on the login page.
     *
     * @return The error message text displayed on the page.
     */
    public String getErrorMessage() {
        return errorMessage.get(0).getText();
    }

    /**
     * Retrieves the list of usernames available for login.
     *
     * @return A list of valid usernames.
     */
    public List<String> getUsernames() {
        return removeHeader(usernames);
    }

    /**
     * Retrieves the password for login.
     *
     * @return The password string for login.
     */
    public String getPassword() {
        List<String> validList = removeHeader(password);
        return validList.get(0);
    }

    /**
     * Removes the header and retrieves a list of strings from a WebElement.
     *
     * @param element The WebElement containing the text list.
     * @return A list of strings after removing the header.
     */
    public List<String> removeHeader(WebElement element) {
        String[] list = element.getText().split("\\r?\\n");
        List<String> validList = new ArrayList<>(Arrays.asList(list));
        validList.removeFirst();
        return validList;
    }

    /**
     * Performs the login operation using the provided username and password.
     *
     * @param username The username for login.
     * @param password The password for login.
     * @return An instance of InventoryPage representing the logged-in state after successful login.
     */
    public InventoryPage login(String username, String password) {
        logger.info("Logging in.");
        usernameInput.sendKeys(username);
        passwordInput.sendKeys(password);
        loginButton.click();
        return new InventoryPage(this.driver);
    }

    /**
     * Checks if the login form is displayed on the page.
     *
     * @return true if the login form is displayed; otherwise, false.
     */
    public boolean isFormDisplayed() {
        logger.info("Checking if username and password input fields are displayed.");
        return usernameInput.isDisplayed() && passwordInput.isDisplayed();
    }

    /**
     * Checks if an error message is displayed on the login page.
     *
     * @return true if an error message is displayed; otherwise, false.
     */
    public boolean isErrorMessageDisplayed() {
        logger.info("Checking if error message is displayed.");
        return !errorMessage.isEmpty();
    }


    /**
     * Retrieves a username based on the validity flag.
     *
     * @param isValidUsername A Boolean flag indicating the validity of the username.
     *                        If null, an empty string is returned.
     *                        If true, the first username from the stored list is returned.
     *                        If false, returns "invalid".
     * @return A String representing the username based on the provided validity flag.
     */
    public String getUsernameByIndex(Boolean isValidUsername) {
        String username = "";
        if (isValidUsername == null) {
            username = "";
        } else if (isValidUsername.booleanValue() == true) {
            username = getUsernames().get(0);
        } else {
            username = "invalid";
        }
        return username;
    }


    /**
     * Retrieves a password based on the validity flag.
     *
     * @param isValidPassword A Boolean flag indicating the validity of the password.
     *                        If null, an empty string is returned.
     *                        If true, the stored password is returned.
     *                        If false, returns "invalid".
     * @return A String representing the password based on the provided validity flag.
     */
    public String getPasswordByIndex(Boolean isValidPassword) {
        String password = "";
        if (isValidPassword == null) {
            password = "";
        } else if (isValidPassword.booleanValue() == true) {
            password = getPassword();
        } else {
            password = "invalid";
        }
        return password;
    }
}
