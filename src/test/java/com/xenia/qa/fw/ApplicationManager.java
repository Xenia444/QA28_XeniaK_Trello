package com.xenia.qa.fw;

import com.xenia.qa.tests.TestBase;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.BrowserType;
import org.openqa.selenium.support.events.EventFiringWebDriver;

import java.util.concurrent.TimeUnit;

public class ApplicationManager{
    EventFiringWebDriver wd;
    // WebDriver wd;
    BoardHelper board;
    SessionHelper session;
    ListHelper list;
    CardHelper card;
    String browser;

    public ApplicationManager(String browser) {
        this.browser = browser;
    }

    public void init() {
        if(browser.equals(BrowserType.CHROME)) {
            wd = new EventFiringWebDriver(new ChromeDriver());
        }else if(browser.equals(BrowserType.FIREFOX)) {
            wd = new EventFiringWebDriver(new FirefoxDriver());
        }
        wd.register(new TestBase.MyListener());
        wd.manage().window().maximize();
        wd.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
        openSite("https://trello.com/");
        board = new BoardHelper(wd);
        session = new SessionHelper(wd);
        list = new ListHelper(wd);
        card = new CardHelper(wd);
    }


    public void openSite(String url) {
        wd.get(url);
    }

    public BoardHelper board() {
        return board;
    }

    public SessionHelper session() {
        return session;
    }

    public ListHelper list() {
        return list;
    }

    public CardHelper card() {
        return card;
    }

    public void stop() {
        wd.quit();
    }

}
