package sauce_demo.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import sauce_demo.models.Product;
import sauce_demo.pages.base.NonLoginBasePage;

/**
 * The ProductDetailsPage class represents the page displaying details of a specific product in the Sauce Demo application.
 */
public class ProductDetailsPage extends NonLoginBasePage {

    private static final Logger logger = LoggerFactory.getLogger(ProductDetailsPage.class);

    @FindBy(css = "div.inventory_details_name")
    WebElement title;

    @FindBy(css = "div.inventory_details_desc")
    WebElement description;

    @FindBy(css = "div.inventory_details_price")
    WebElement price;

    /**
     * Constructs a ProductDetailsPage object with the provided WebDriver.
     *
     * @param driver The WebDriver instance to be used by the ProductDetailsPage.
     */
    public ProductDetailsPage(WebDriver driver) {
        super(driver);
    }

    /**
     * Retrieves the data of the product displayed on the page.
     *
     * @return A Product object representing the data of the displayed product.
     */
    public Product getProductData() {
        return new Product(title.getText(), description.getText(), price.getText());
    }
}
