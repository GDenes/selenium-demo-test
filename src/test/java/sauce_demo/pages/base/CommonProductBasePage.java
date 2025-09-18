package sauce_demo.pages.base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import sauce_demo.models.Product;

import java.util.List;

/**
 * The CommonProductBasePage class serves as a base page for pages containing lists of products
 * in the Sauce Demo application.
 */
public abstract class CommonProductBasePage extends NonLoginBasePage {
    @FindBy(css = "div.inventory_item_name")
    List<WebElement> titles;

    @FindBy(css = "div.inventory_item_desc")
    List<WebElement> descriptions;

    @FindBy(css = "div.inventory_item_price")
    List<WebElement> prices;

    @FindBy(css = "div.bm-burger-button")
    WebElement hamburgerButton;

    @FindBy(css = "a#reset_sidebar_link")
    WebElement resetStateButton;

    public List<WebElement> getTitles() {
        return titles;
    }

    public List<WebElement> getDescriptions() {
        return descriptions;
    }

    public List<WebElement> getPrices() {
        return prices;
    }

    /**
     * Constructs a CommonProductBasePage object with the provided WebDriver.
     *
     * @param driver The WebDriver instance to be used by the CommonProductBasePage.
     */
    public CommonProductBasePage(WebDriver driver) {
        super(driver);
    }

    /**
     * Retrieves the data of the first product displayed on the page.
     *
     * @return A Product object representing the data of the first product.
     */
    public Product getProductData() {
        return new Product(titles.get(0).getText(), descriptions.get(0).getText(), prices.get(0).getText());
    }

    /**
     * Retrieves the data of a product based on the specified index.
     *
     * @param index The index of the product to retrieve data from.
     * @return A Product object representing the data of the specified product index.
     */
    public Product getProductData(int index) {
        return new Product(titles.get(index).getText(), descriptions.get(index).getText(), prices.get(index).getText());
    }

    /**
     * Retrieves the number of titles present on the inventory page.
     *
     * @return The count of title elements.
     */
    public int titlesSize() {
        return getTitles().size();
    }

    public void resetAppState() {
        hamburgerButton.click();
        resetStateButton.click();
        driver.navigate().refresh();
    }
}
