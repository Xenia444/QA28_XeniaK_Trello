package com.xenia.qa;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class BoardCreationTest extends TestBase {

    @BeforeMethod
    public void precondition() throws InterruptedException {
        if (!isElementPresent(By.xpath("//*[@data-test-id='header-member-menu-button']"))) {
            login();
        }
    }

    @Test
    public void boardCreationTest() throws InterruptedException {
        String boardName = "Board" + System.currentTimeMillis();
        click(By.xpath("//*[@data-test-id='header-create-menu-button']"));
        click(By.xpath("//*[@data-test-id='header-create-board-button']"));
        type(By.xpath("//*[@data-test-id='create-board-title-input']"),boardName);
        click(By.xpath("//*[@data-test-id='create-board-submit-button']"));
        Thread.sleep(2000);
        String title = wd.findElement(By.xpath("//h1")).getText();

        Assert.assertEquals(title, boardName);
    }
}


