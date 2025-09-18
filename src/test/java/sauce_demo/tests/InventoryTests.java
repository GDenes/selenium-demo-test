package sauce_demo.tests;

import io.qameta.allure.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import sauce_demo.enums.SortBy;
import sauce_demo.models.Product;
import sauce_demo.pages.CartPage;
import sauce_demo.pages.ProductDetailsPage;
import sauce_demo.tests.base.TestLoginBase;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;
import static sauce_demo.enums.SortBy.*;

@Story("Inventory Tests")
public class InventoryTests extends TestLoginBase {

    @Description("This test checks the logout functionality.")
    @Severity(SeverityLevel.NORMAL)
    @Test
    public void testLogout() {
        loginPage = inventoryPage.logout();

        assertTrue(loginPage.isFormDisplayed(), "Failed to logout. The form doesn't exist.");
        assertFalse(loginPage.isErrorMessageDisplayed(), "Something happened during logout. Error message is displayed.");
    }

    @Description("These tests check if the product titles had been sorted correctly ")
    @Severity(SeverityLevel.NORMAL)
    @ParameterizedTest(name = "testSortByTitle  ascending: {1}")
    @MethodSource({"getSortTitleArguments"})
    public void testSortTitles(SortBy sortBy, boolean isAsc) {
        List<String> originalTitles = inventoryPage.getTitleTexts();
        inventoryPage.sortItems(sortBy);
        List<String> sortedTitles = inventoryPage.getTitleTexts();

        assertEquals(inventoryPage.sortList(originalTitles, isAsc), sortedTitles, "Failed to sort products.");
    }

    @Description("These tests check if the product prices had been sorted correctly.")
    @Severity(SeverityLevel.NORMAL)
    @ParameterizedTest(name = "testSortByPrice ascending: {1}")
    @MethodSource({"getSortPriceArguments"})
    public void testSortPrices(SortBy sortBy, boolean isAsc) {
        List<Double> originalPrices = inventoryPage.getPriceTexts();
        inventoryPage.sortItems(sortBy);
        List<Double> sortedPrices = inventoryPage.getPriceTexts();

        assertEquals(inventoryPage.sortList(originalPrices, isAsc), sortedPrices, "Failed to sort products.");
    }

    @Description("This test checks if the product details are correct.")
    @Severity(SeverityLevel.NORMAL)
    @Test
    public void testProductDetailsPage() {
        int listIndex = ThreadLocalRandom.current().nextInt(0, inventoryPage.titlesSize());

        Product productData = inventoryPage.getProductData(listIndex);
        ProductDetailsPage productDetailsPage = inventoryPage.goToProductDetails(listIndex);
        Product productDetailsData = productDetailsPage.getProductData();

        assertEquals(productData, productDetailsData, "The product data on the product list and product details page aren't the same.");
    }

    @Description("This test checks if the correct product had been added to the cart.")
    @Severity(SeverityLevel.NORMAL)
    @Test
    public void testCartPage() {
        int listIndex = ThreadLocalRandom.current().nextInt(0, inventoryPage.titlesSize());

        Product productData = inventoryPage.addProductToCart(listIndex);
        CartPage cartPage = inventoryPage.goToCart();
        Product cartData = cartPage.getProductData();

        assertEquals(productData, cartData, "The product data on the product list and product details page aren't the same.");
    }

    public static Stream<Arguments> getSortTitleArguments() {
        return Stream.of(
                Arguments.of(AZ, true),
                Arguments.of(ZA, false)
        );
    }

    public static Stream<Arguments> getSortPriceArguments() {
        return Stream.of(
                Arguments.of(LOW_TO_HIGH, true),
                Arguments.of(HIGH_TO_LOW, false)
        );
    }
}
