package sauce_demo.tests;

import io.qameta.allure.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import sauce_demo.tests.base.TestBase;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

@Story("Permission Tests")
public class PermissionTests extends TestBase {

    @Description("These tests check the permissions of the pages.")
    @Severity(SeverityLevel.NORMAL)
    @ParameterizedTest(name = "{0}")
    @MethodSource({"getPageArguments"})
    public void testInventoryPermission(String page) {
        driver.get("https://www.saucedemo.com/" + page + ".html");
        String errorMessage = "Epic sadface: You can only access '/" + page + ".html' when you are logged in.";

        assertEquals(errorMessage, loginPage.getErrorMessage(), "Successful login without credentials.");
    }

    public static Stream<Arguments> getPageArguments() {
        return Stream.of(
                Arguments.of("inventory"),
                Arguments.of("cart"),
                Arguments.of("checkout-step-one"),
                Arguments.of("checkout-step-two"),
                Arguments.of("checkout-complete")
        );
    }
}
