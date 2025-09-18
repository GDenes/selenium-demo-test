package sauce_demo.tests;

import io.qameta.allure.*;
import org.junit.jupiter.api.Test;
import sauce_demo.models.Product;
import sauce_demo.pages.CartPage;
import sauce_demo.tests.base.TestLoginBase;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@Story("Cart Tests")
public class CartTests extends TestLoginBase {

    @Description("This test adds a random product of the cart, and checks if it appeared in the cart.")
    @Severity(SeverityLevel.NORMAL)
    @Test
    public void testRandomProductsInCart() {
        inventoryPage.resetAppState();
        List<Product> randomProducts = inventoryPage.addRandomProductsToCart();
        CartPage cartPage = inventoryPage.goToCart();
        List<Product> cartData = cartPage.getWholeCartData();

        assertEquals(randomProducts, cartData, "The added product doesn't match any of the products in the cart.");
    }

    @Description("This test removes a random product of the cart, and checks if the removal worked as expected.")
    @Severity(SeverityLevel.NORMAL)
    @Test
    public void testRemoveFromCart() {
        inventoryPage.addRandomProductsToCart();
        CartPage cartPage = inventoryPage.goToCart();
        Product deletedProduct = cartPage.deleteProductFromCart(0);
        List<Product> cartData = cartPage.getWholeCartData();

        assertFalse(cartData.contains(deletedProduct), "Failed to delete product from the cart.");
    }
}