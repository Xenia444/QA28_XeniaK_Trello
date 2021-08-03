package com.xenia.qa.fw;


import com.xenia.qa.model.User;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class SessionHelper extends HelperBase {
    public SessionHelper(WebDriver wd) {
        super(wd);
    }

    public void login(String email, String password) throws InterruptedException {
        click(By.xpath("//a[@href='/login']"));
        type(By.xpath("//*[@name='user']"), email);
        Thread.sleep(2000);
        click(By.xpath("//input[@id='login']"));
        type(By.xpath("//*[@name='password']"), password);
        click(By.xpath("//button[@id='login-submit']"));
    }

    public void logout() {
        clickOnAvatar();
        selectLogoutFromDropDownList();
        confirmLogout();


    }

    public boolean checkUserLoggedOut() {
        return wd.getCurrentUrl().equals("https://trello.com/logged-out");
    }

    private void confirmLogout() {
        click(By.cssSelector("#logout-submit"));
    }

    public void selectLogoutFromDropDownList() {
        click(By.cssSelector("[data-test-id$=logout]"));
    }

    public void clickOnAvatar() {
        click(By.cssSelector("[data-test-id=header-member-menu-button]"));
    }

    public void confirmLogin() {
        click(By.xpath("//button[@id='login-submit']"));
    }

    public void fillLoginForm(User user) throws InterruptedException {
        type(By.xpath("//*[@name='user']"), user.getEmail());
        Thread.sleep(2000);
        click(By.xpath("//input[@id='login']"));
        type(By.xpath("//*[@name='password']"), user.getPassword());
    }

    public void clickOnLoginButton() {
        click(By.xpath("//a[@href='/login']"));
    }

    public boolean isAvatarPresent() {
        return isElementPresent(By.xpath("//*[@data-test-id='header-member-menu-button']"));
    }

    public boolean isAvatarPresentWait() {
        return waitForElementPresentTryCatch(By.xpath("//ul[@class = 'boards-page-board-section-list']"), 20);
    }

    public boolean isErrorPresent() {
        return isElementPresent(By.cssSelector("#password-error"));
    }
}