package sauce_demo.utils;

import io.qameta.allure.Allure;
import org.junit.jupiter.api.extension.AfterTestExecutionCallback;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.TestWatcher;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import sauce_demo.tests.base.TestBase;

import java.io.ByteArrayInputStream;

public class TestResultLoggerExtension implements TestWatcher, AfterTestExecutionCallback {

    private static final Logger logger = LoggerFactory.getLogger(TestResultLoggerExtension.class);

    @Override
    public void afterTestExecution(ExtensionContext context) {
        TestBase testBase = (TestBase) context.getRequiredTestInstance();
        if (context.getExecutionException().isPresent()) {
            byte[] screenshot = Utils.takeScreenshot(testBase.driver);
            Allure.addAttachment("Screenshot", new ByteArrayInputStream(screenshot));
            Allure.link("URL WHEN FAILED", testBase.driver.getCurrentUrl());
        }
        testBase.driver.manage().deleteAllCookies();
    }
}
