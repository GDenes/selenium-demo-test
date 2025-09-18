package sauce_demo.tests.base;

import org.junit.jupiter.api.BeforeEach;
import sauce_demo.pages.InventoryPage;

public abstract class TestLoginBase extends TestBase {

    public InventoryPage inventoryPage;

    /**
     * Method executed before each test to set up the login process.
     * It performs login using the first username and password obtained from test data.
     */
    @BeforeEach
    public void setUpLogin() {
        inventoryPage = loginPage.login(usernames.get(0), passwords.get(0));
    }
}
