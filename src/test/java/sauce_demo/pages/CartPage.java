package sauce_demo.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import sauce_demo.models.Product;
import sauce_demo.pages.base.CommonProductBasePage;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * The CartPage class represents the page that displays the shopping cart in the Sauce Demo application.
 */
public class CartPage extends CommonProductBasePage {

    private static final Logger logger = LoggerFactory.getLogger(CartPage.class);

    @FindBy(css = "button.cart_button")
    List<WebElement> removeButton;

    @FindBy(css = "button.checkout_button")
    WebElement checkoutButton;

    /**
     * Constructs a CartPage object with the provided WebDriver.
     *
     * @param driver The WebDriver instance to be used by the CartPage.
     */
    public CartPage(WebDriver driver) {
        super(driver);
    }

    /**
     * Retrieves the details of all products in the cart.
     *
     * @return A list of Product objects representing the products in the cart.
     */
    public List<Product> getWholeCartData() {
        logger.info("Getting whole cart data.");
        return IntStream.range(0, titlesSize())
                .mapToObj(i -> new Product(getTitles().get(i).getText(), getDescriptions().get(i).getText(), getPrices().get(i).getText()))
                .collect(Collectors.toList());
    }

    /**
     * Deletes a product from the cart based on the specified index.
     *
     * @param index The index of the product to be deleted from the cart.
     * @return The Product object representing the deleted product.
     */
    public Product deleteProductFromCart(int index) {
        logger.info("Deleting product from cart.");
        Product deletedProduct = getProductData(index);
        removeButton.get(index).click();
        return deletedProduct;
    }

    /**
     * Clicks on the checkout button and navigates to the CheckoutYourInformationPage.
     *
     * @return An instance of CheckoutFirstPage representing the checkout process.
     */
    public CheckoutYourInformationPage goToCheckout() {
        logger.info("Navigating to Checkout Page.");
        checkoutButton.click();
        return new CheckoutYourInformationPage(driver);
    }
}
