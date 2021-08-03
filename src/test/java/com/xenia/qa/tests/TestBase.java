package com.xenia.qa.tests;
import com.xenia.qa.fw.ApplicationManager;
import org.openqa.selenium.remote.BrowserType;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

public class TestBase {

    protected static ApplicationManager app = new ApplicationManager(System.getProperty("browser", BrowserType.CHROME));

    @BeforeSuite
    public void setUp() {
        // Capabilities caps = new DesiredCapabilities();
        app.init();
    }

    @AfterSuite(alwaysRun = true)
    public void tearDown() throws InterruptedException {
        app.board().clearBoardList();
        app.stop();
    }
}