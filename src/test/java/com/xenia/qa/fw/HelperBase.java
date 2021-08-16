package com.xenia.qa.fw;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.util.NoSuchElementException;


public class HelperBase {
    WebDriver wd;

    public HelperBase(WebDriver wd) {
        this.wd = wd;
    }

    public void click(By locator) {
        wd.findElement(locator).click();
    }
    public void waitForElementAndClick(By locator, int time) {
        new WebDriverWait(wd, time).until(ExpectedConditions.elementToBeClickable(locator)).click();
        // wd.findElement(locator).click();
    }

    public void type(By locator, String text) {
        if(text!=null){
            click(locator);
            wd.findElement(locator).clear();
            wd.findElement(locator).sendKeys(text);
        }
    }

    public boolean isElementPresent(By locator) {
        return wd.findElements(locator).size() > 0;
    }

    public boolean isElementDisplayed(By locator) {
        return wd.findElement(locator).isDisplayed();
    }

    public boolean waitForElementPresent(By locator, int timeOut) {
        return new WebDriverWait(wd, timeOut)
                .until(ExpectedConditions.presenceOfAllElementsLocatedBy(locator)).size() > 0;
    }

    public boolean waitForElementPresentTryCatch(By locator, int timeOut) {
        try {
            waitForElement(locator, timeOut);
            return true;
        } catch (NoSuchElementException e) {
            return false;
        }

    }

    public void waitForElement(By locator, int timeOut) {
        new WebDriverWait(wd, timeOut)
                .until(ExpectedConditions.presenceOfElementLocated(locator));
    }

    public void clickOnPlusButton() {
        click(By.xpath("//*[@data-test-id='header-create-menu-button']"));
    }

    public void returnToHomePage() {
        waitForElement(By.cssSelector("[data-test-id*=header-home]"), 30);
        click(By.cssSelector("[data-test-id*=header-home]"));
    }

    public void confirmAction() {
        click(By.cssSelector(".js-confirm"));
    }

    public void attachFile(By locator, File file) {
        // if(file.!=null){
        wd.findElement(locator).sendKeys(file.getAbsolutePath());
        //   }
    }
}
