package sauce_demo.pages.base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import sauce_demo.pages.CartPage;
import sauce_demo.pages.base.BasePage;

import java.util.List;

public abstract class NonLoginBasePage extends BasePage {

    @FindBy(css = "a.shopping_cart_link")
    WebElement cartButton;

    @FindBy(css = ".social a")
    List<WebElement> footerLinks;

    public NonLoginBasePage(WebDriver driver) {
        super(driver);
    }

    /**
     * Navigates to the cart page by clicking on the cart button.
     *
     * @return An instance of CartPage representing the cart page.
     */
    public CartPage goToCart() {
        cartButton.click();
        return new CartPage(this.driver);
    }

    /**
     * Clicks on a footer element based on the specified index.
     *
     * @param index The index of the footer element to be clicked.
     */
    public void clickOnFooterElement(int index) {
        footerLinks.get(index).click();
    }
}
