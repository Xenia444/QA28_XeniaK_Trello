package com.xenia.qa.tests;
import com.google.common.io.Files;
import com.xenia.qa.fw.ApplicationManager;
import org.openqa.selenium.*;
import org.openqa.selenium.remote.BrowserType;
import org.openqa.selenium.support.events.AbstractWebDriverEventListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;

public class TestBase {

    protected static ApplicationManager app = new ApplicationManager(System.getProperty("browser", BrowserType.CHROME));

    Logger logger = LoggerFactory.getLogger(TestBase.class);

    public static class MyListener extends AbstractWebDriverEventListener {
        Logger logger = LoggerFactory.getLogger(MyListener.class);

        @Override
        public void beforeFindBy(By by, WebElement element, WebDriver driver) {
            logger.info("Start search element" + by + ".");
        }

        @Override
        public void afterFindBy(By by, WebElement element, WebDriver driver) {
            logger.info("Element" + by + "found.");
        }

        @Override
        public void onException(Throwable throwable, WebDriver driver) {
            logger.error("!!!!!TEST FAILED!!!!!\n", throwable);
            long l = System.currentTimeMillis();
            String pathName = "screenshots/screen-" + l + ".png";
            File tmp = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
            File screenshot = new File(pathName);

            try {
                Files.copy(tmp, screenshot);
            } catch (IOException e) {
                e.printStackTrace();
            }
            logger.error("screenshot " + pathName);
        }
    }

    @BeforeSuite
    public void setUp() throws IOException {
        // Capabilities caps = new DesiredCapabilities();
        app.init();
    }

    @AfterSuite(alwaysRun = true)
    public void tearDown() throws InterruptedException {
        app.board().clearBoardList();
        app.stop();
    }

    @BeforeMethod
    public void startLogger(Method m) {
        logger.info("start test" + m.getName());
    }
    @AfterMethod(alwaysRun = true)
    public void stopLogger(Method m) {
        logger.info("stop test" + m.getName());
    }
}