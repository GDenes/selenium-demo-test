package sauce_demo.utils;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Set;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static java.lang.System.currentTimeMillis;

/**
 * The Utils class provides utility methods for handling WebDriver operations in the Sauce Demo application.
 */
public class Utils {

    private static final String filePath = "target/allure-results/environment.properties";
    private static final Logger logger = LoggerFactory.getLogger(Utils.class);

    /**
     * Switches the WebDriver to a newly opened tab.
     *
     * @param driver The WebDriver instance to switch tabs.
     */
    public static void switchToNewTab(WebDriver driver) {
        String newWindow = new ArrayList<>(driver.getWindowHandles()).getLast();
        Set<String> windowHandles = driver.getWindowHandles();
        for (String windowHandle : windowHandles) {
            if (windowHandle.equals(newWindow)) {
                driver.switchTo().window(windowHandle);
                break;
            }
        }
    }

    /**
     * Takes a screenshot of the whole page. Saves the image at ./screenshots.
     *
     * @param driver The WebDriver instance
     */
    public static byte[] takeScreenshot(WebDriver driver) {
        TakesScreenshot takesScreenshot = (TakesScreenshot) driver;
        File file = takesScreenshot.getScreenshotAs(OutputType.FILE);
        try {
            FileUtils.copyFile(file, new File("./target/screenshots/" + currentTimeMillis() + ".png"));
        } catch (IOException e) {
            System.out.println("Failed to take screenshot.");
        }
        return takesScreenshot.getScreenshotAs(OutputType.BYTES);
    }

    /**
     * Generates an Allure environment file with specified browser, headless mode, remote execution status, and URL.
     *
     * @param browser  The browser information.
     * @param headless Whether the browser operates in headless mode.
     * @param remote   Whether the execution is remote or local.
     * @param url      The URL for remote execution.
     */
    public static void generateAllureEnvironmentFile(String browser, String headless, String remote, String url) {
        String content = String.format(
                """
                        browser=%s
                        headless=%s
                        remote=%s
                        url=%s
                               """,
                browser, headless, remote, url
        );

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            writer.write(content);
            logger.info("Content has been written to the file.");
        } catch (IOException e) {
            logger.error("An error occurred while writing to the file: " + e.getMessage());
        }
    }
}
