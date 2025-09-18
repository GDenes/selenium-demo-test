package sauce_demo.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import sauce_demo.enums.PriceType;
import sauce_demo.models.Product;
import sauce_demo.pages.base.NonLoginBasePage;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * The CheckoutOverviewPage class represents the page displaying the overview of items in the cart
 * during the checkout process in the Sauce Demo application.
 */
public class CheckoutOverviewPage extends NonLoginBasePage {

    private static final Logger logger = LoggerFactory.getLogger(CheckoutOverviewPage.class);

    @FindBy(css = "div.inventory_item_name")
    List<WebElement> titles;

    @FindBy(css = "div.inventory_item_desc")
    List<WebElement> descriptions;

    @FindBy(css = "div.inventory_item_price")
    List<WebElement> prices;

    @FindBy(css = "div.summary_subtotal_label")
    WebElement itemTotal;

    @FindBy(css = "div.summary_tax_label")
    WebElement tax;

    @FindBy(css = "div.summary_total_label")
    WebElement priceTotal;

    @FindBy(css = "button[data-test='finish']")
    WebElement finishButton;

    /**
     * Constructs a CheckoutOverviewPage object with the provided WebDriver.
     *
     * @param driver The WebDriver instance to be used by the CheckoutOverviewPage.
     */
    public CheckoutOverviewPage(WebDriver driver) {
        super(driver);
    }

    /**
     * Retrieves the data of all items displayed on the checkout page.
     *
     * @return A list of Products in the checkout.
     */
    public List<Product> getWholeCheckoutData() {
        logger.info("Retrieving the data of all items displayed on the Checkout Page");
        List<Product> checkoutProducts = new ArrayList<>();
        for (int i = 0; i < titles.size(); i++) {
            checkoutProducts.add(new Product(titles.get(i).getText(), descriptions.get(i).getText(), prices.get(i).getText()));
        }
        return checkoutProducts;
    }

    /**
     * Retrieves the price based on the specified PriceType.
     *
     * @param type The PriceType enum specifying the type of price to retrieve.
     * @return The price value based on the specified PriceType.
     */
    public double getPrice(PriceType type) {
        double price;
        price = switch (type) {
            case ITEM_TOTAL -> parsePrice(itemTotal.getText());
            case TAX -> parsePrice(tax.getText());
            case TOTAL_PRICE -> parsePrice(priceTotal.getText());
        };
        return price;
    }

    /**
     * Parses the price from the given string.
     *
     * @param price The string containing the price value.
     * @return The parsed double value of the price.
     */
    public double parsePrice(String price) {
        return Double.parseDouble(price.substring(price.indexOf("$") + 1));
    }

    /**
     * Calculates the total price of items on the checkout page.
     *
     * @return The calculated total price of items in the checkout.
     */
    public double getCalculatedItemTotal() {
        logger.info("Calculating the total price of the items on the Checkout Page.");
        List<Product> checkoutProducts = getWholeCheckoutData();
        double sum = 0;
        for (Product product : checkoutProducts) {
            sum += parsePrice(product.getPrice());
        }
        DecimalFormat decimalFormat = new DecimalFormat("##.00");
        return Double.parseDouble(decimalFormat.format(Math.round(sum * 100.0) / 100.0));
    }

    /**
     * Clicks the finish button to complete the checkout process and navigates to CheckoutFinishPage.
     *
     * @return An instance of CheckoutFinishPage representing the completion of the checkout process.
     */
    public CheckoutFinishPage finishCheckout() {
        logger.info("Navigating to the Checkout Finish Page.");
        finishButton.click();
        return new CheckoutFinishPage(driver);
    }
}
