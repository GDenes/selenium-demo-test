package sauce_demo.tests;

import io.qameta.allure.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import sauce_demo.tests.base.TestLoginBase;
import sauce_demo.utils.Utils;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

@Story("Footer Tests")
public class FooterTests extends TestLoginBase {

    @Description("These tests check if the footers redirect to the correct page.")
    @Severity(SeverityLevel.NORMAL)
    @ParameterizedTest(name = "footerLinksTests {1}")
    @MethodSource({"getFooterArguments"})
    public void testFooterElements(int footer, String url) {
        inventoryPage.clickOnFooterElement(footer);
        Utils.switchToNewTab(driver);

        assertEquals(url, driver.getCurrentUrl(), "Failed to navigate to a footer page.");
    }

    public static Stream<Arguments> getFooterArguments() {
        return Stream.of(
                Arguments.of(0, "https://twitter.com/saucelabs"),
                Arguments.of(1, "https://www.facebook.com/saucelabs"),
                Arguments.of(2, "https://www.linkedin.com/company/sauce-labs/")
        );
    }
}
