package com.xenia.qa;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

public class LoginTrelloTest extends TestBase{

    @Test
    public void loginTest() throws InterruptedException {

        clickOnLoginButton();
        fillLoginForm("xeniya.kraeva+1@mail.ru", "97531.mail");
        confirmLogin();
      //  waitForElement(By.xpath("//ul[@class='boards-page-board-section-list']"), 30);

        Assert.assertTrue(isAvatarPresentWait(), "Avatar is not present");

        Assert.assertTrue(waitForElementPresentTryCatch(By.xpath("//ul[@class='boards-page-board-section-list']"), 30));

        logout();
        Assert.assertTrue(checkUserLoggedOut());
    }




}
