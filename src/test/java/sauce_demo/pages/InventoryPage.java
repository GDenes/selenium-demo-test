package sauce_demo.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import sauce_demo.enums.SortBy;
import sauce_demo.models.Product;
import sauce_demo.pages.base.CommonProductBasePage;

import java.util.*;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;

/**
 * The InventoryPage class represents the page displaying product inventory in the Sauce Demo application.
 */
public class InventoryPage extends CommonProductBasePage {

    private static final Logger logger = LoggerFactory.getLogger(InventoryPage.class);

    @FindBy(css = "span.title")
    WebElement header;

    @FindBy(css = "a img.inventory_item_img")
    List<WebElement> image;

    @FindBy(css = "button.btn_inventory")
    List<WebElement> buttonInventory;

    @FindBy(css = "select[data-test='product-sort-container']")
    WebElement productSort;

    @FindBy(css = "button#react-burger-menu-btn")
    WebElement hamburgerButton;

    @FindBy(css = "#logout_sidebar_link")
    WebElement logoutLink;

    /**
     * Retrieves the URL of the inventory page.
     *
     * @return The URL of the inventory page.
     */
    public String getUrl() {
        final String URL_INVENTORY = "https://www.saucedemo.com/inventory.html";
        return URL_INVENTORY;
    }

    /**
     * Constructs an InventoryPage object with the provided WebDriver.
     *
     * @param driver The WebDriver instance to be used by the InventoryPage.
     */
    public InventoryPage(WebDriver driver) {
        super(driver);
    }

    /**
     * Retrieves the text of the header displayed on the inventory page.
     *
     * @return The text content of the header element.
     */
    public String getHeaderText() {
        return header.getText();
    }

    /**
     * Retrieves the text content of all titles displayed on the inventory page.
     *
     * @return A list containing the text content of all title elements.
     */
    public List<String> getTitleTexts() {
        List<String> titleTexts = new ArrayList<>();
        for (WebElement title : getTitles()) {
            titleTexts.add(title.getText());
        }
        return titleTexts;
    }

    /**
     * Retrieves the price values as doubles from the displayed price elements.
     *
     * @return A list containing the price values as doubles.
     */
    public List<Double> getPriceTexts() {
        return getPrices().stream()
                .map(price -> Double.parseDouble(price.getText().substring(1)))
                .collect(Collectors.toList());
    }

    /**
     * Checks if image exists.
     *
     * @return True if the image list is empty; otherwise, false.
     */
    public Boolean isEmptyImage() {
        return image.isEmpty();
    }

    /**
     * Performs logout action by clicking on the hamburger menu and logout link.
     *
     * @return An instance of LoginPage after performing the logout action.
     */
    @Step("Logging out")
    public LoginPage logout() {
        hamburgerButton.click();
        logoutLink.click();

        return new LoginPage(this.driver);
    }

    /**
     * Sorts items on the page based on the specified SortBy parameter.
     *
     * @param sortBy The sorting criteria to apply.
     */
    @Step("Sorting items")
    public void sortItems(SortBy sortBy) {
        Select sortButton = new Select(productSort);
        sortButton.selectByValue(sortBy.toString());
    }

    /**
     * Sorts a given list in ascending or descending order based on the isAsc parameter.
     *
     * @param list  The list to be sorted.
     * @param isAsc True for ascending, false for descending.
     * @param <T>   The type of elements in the list.
     * @return The sorted list.
     */
    public <T extends Comparable<? super T>> List<T> sortList(List<T> list, boolean isAsc) {
        if (isAsc) {
            Collections.sort(list);
        } else {
            list.sort(Collections.reverseOrder());
        }
        return list;
    }

    /**
     * Navigates to the product details page for the product at the specified index.
     *
     * @param titleIndex The index of the title for which product details need to be viewed.
     * @return An instance of ProductDetailsPage representing the product details page.
     */
    @Step("Navigating to product details")
    public ProductDetailsPage goToProductDetails(int titleIndex) {
        logger.info("Clicked on product with index " + titleIndex);
        getTitles().get(titleIndex).click();
        return new ProductDetailsPage(this.driver);
    }

    /**
     * Adds a product to the cart based on the specified index and retrieves its details.
     *
     * @param index The index of the product to be added to the cart.
     * @return The details of the product added to the cart as a Product object.
     */
    @Step("Adding product to cart")
    public Product addProductToCart(int index) {
        logger.info("Product with id " + index + " was added to cart");
        buttonInventory.get(index).click();
        return getProductData(index);
    }

    /**
     * Adds random products to the cart and retrieves details of the added products.
     *
     * @return A list containing details of the randomly added products as Product objects.
     */
    @Step("Adding random products to cart")
    public List<Product> addRandomProductsToCart() {
        List<Integer> randomIndices = getRandomNumbers(titlesSize());
        List<Product> addedProducts = new ArrayList<>();
        for (int i = 0; i < randomIndices.size(); i++) {
            buttonInventory.get(i).click();
            addedProducts.add(getProductData(i));
            logger.info(getProductData(i).getTitle());
        }
        return addedProducts;
    }

    /**
     * Generates a list of random integers within the specified range.
     *
     * @param to The upper limit of the range.
     * @return A list containing random integers.
     */
    private List<Integer> getRandomNumbers(int to) {
        return ThreadLocalRandom.current()
                .ints(1, to)
                .distinct()
                .limit(Math.min(new Random().nextInt(to), to))
                .boxed()
                .collect(Collectors.toList());
    }
}
