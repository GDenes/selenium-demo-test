package sauce_demo.tests;

import io.qameta.allure.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import sauce_demo.models.Product;
import sauce_demo.pages.*;
import sauce_demo.tests.base.TestLoginBase;

import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static sauce_demo.enums.PriceType.*;

@Story("Checkout Tests")
public class CheckoutTests extends TestLoginBase {

    CartPage cartPage;

    @BeforeEach
    public void setUpCheckout() {
        inventoryPage.addRandomProductsToCart();
        cartPage = inventoryPage.goToCart();
    }

    @Description("This test runs through the whole checkout process.")
    @Severity(SeverityLevel.NORMAL)
    @Test
    public void testFullCheckout() {
        List<Product> cartData = cartPage.getWholeCartData();
        CheckoutYourInformationPage checkout = cartPage.goToCheckout();
        checkout.fillUserData("Adam", "Lastname", "24220");
        CheckoutOverviewPage checkoutOverviewPage = checkout.continueCheckout();
        List<Product> checkoutData = checkoutOverviewPage.getWholeCheckoutData();

        double itemTotal = checkoutOverviewPage.getPrice(ITEM_TOTAL);
        double tax = checkoutOverviewPage.getPrice(TAX);
        double totalPrice = checkoutOverviewPage.getPrice(TOTAL_PRICE);

        double calculatedItemTotal = checkoutOverviewPage.getCalculatedItemTotal();

        assertEquals(cartData, checkoutData, "The checkout products don't match the cart products.");
        assertEquals(calculatedItemTotal, itemTotal, 0.0001, "Item total is calculated incorrectly.");
        assertEquals(itemTotal * 0.08, tax, 0.01, "Tax is calculated incorrectly.");
        assertEquals(calculatedItemTotal + tax, totalPrice, 0.0001, "Total is calculated incorrectly.");

        CheckoutFinishPage checkoutFinishPage = checkoutOverviewPage.finishCheckout();

        assertTrue(checkoutFinishPage.isMessagesAppear(), "The messages didn't appear.");
    }

    @Description("This test checks the user input on the checkout page.")
    @Severity(SeverityLevel.NORMAL)
    @ParameterizedTest(name = "{index} {3}")
    @MethodSource({"getUserDataArguments"})
    public void testCheckout(String firstName, String lastName, String postalCode, String message) {
        CheckoutYourInformationPage checkout = cartPage.goToCheckout();
        checkout.fillUserData(firstName, lastName, postalCode);
        checkout.continueCheckout();

        assertEquals(message, checkout.getErrorMessage(), "Invalid error message.");
    }

    public static Stream<Arguments> getUserDataArguments() {
        return Stream.of(
                Arguments.of("", "", "", "Error: First Name is required"),
                Arguments.of("Adam", "", "", "Error: Last Name is required"),
                Arguments.of("Adam", "Lastname", "", "Error: Postal Code is required")
        );
    }
}
