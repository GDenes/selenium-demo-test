package sauce_demo.tests;

import io.qameta.allure.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import sauce_demo.pages.InventoryPage;
import sauce_demo.tests.base.TestBase;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

@Story("Login Tests")
public class LoginTests extends TestBase {

    private static final String ERROR_MESSAGE1 = "Epic sadface: Username and password do not match any user in this service";
    private static final String ERROR_MESSAGE2 = "Epic sadface: Username is required";
    private static final String ERROR_MESSAGE3 = "Epic sadface: Password is required";
    private static final String ERROR_MESSAGE4 = "Epic sadface: Sorry, this user has been locked out.";

    @Description("This test tries to log in using valid credentials.")
    @Severity(SeverityLevel.NORMAL)
    @Test
    public void testLoginValidCredentials() {
        InventoryPage inventoryPage = loginPage.login(usernames.get(0), passwords.get(0));

        assertEquals(inventoryPage.getUrl(), driver.getCurrentUrl(), "Failed to login.");
        assertEquals("Products", inventoryPage.getHeaderText(), "Failed to login.");
        assertFalse(inventoryPage.isEmptyImage(), "Failed to login.");
    }

    @Description("This test tries to log in using credentials of a locked user.")
    @Severity(SeverityLevel.NORMAL)
    @Test
    public void testLoginLockedUser() {
        InventoryPage inventoryPage = loginPage.login(usernames.get(1), passwords.get(0));

        assertEquals(ERROR_MESSAGE4, loginPage.getErrorMessage(), "Successful login with locked user.");
        assertNotEquals(inventoryPage.getUrl(), driver.getCurrentUrl(), "Successful login with locked user.");
    }

    @Description("This test tries to log in using invalid credentials.")
    @Severity(SeverityLevel.NORMAL)
    @ParameterizedTest(name = "{2}")
    @MethodSource({"getLoginArguments"})
    public void testLogin(Boolean isValidUsername, Boolean isValidPassword, String errorMessage) {
        String username = loginPage.getUsernameByIndex(isValidUsername);
        String password = loginPage.getPasswordByIndex(isValidPassword);

        InventoryPage inventoryPage = loginPage.login(username, password);

        assertEquals(errorMessage, loginPage.getErrorMessage(), "Successful login with invalid credentials.");
        assertNotEquals(inventoryPage.getUrl(), driver.getCurrentUrl(), "Successful login with invalid credentials.");
    }

    public static Stream<Arguments> getLoginArguments() {
        return Stream.of(
                Arguments.of(false, true, ERROR_MESSAGE1), // true = valid index, false = "invalid", null = ""
                Arguments.of(true, false, ERROR_MESSAGE1),
                Arguments.of(null, null, ERROR_MESSAGE2),
                Arguments.of(null, true, ERROR_MESSAGE2),
                Arguments.of(true, null, ERROR_MESSAGE3)
        );
    }
}
