package com.xenia.qa;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class LoginTrelloTest extends TestBase{

    @Test
    public void loginTest() throws InterruptedException {
     //   wd.findElement(By.xpath("//a[@href='/login']")).click();
        click(By.xpath("//a[@href='/login']"));

     //   click(By.xpath("//*[@name='user']"));
     //   wd.findElement(By.xpath("//*[@name='user']")).clear();
     //   wd.findElement(By.xpath("//*[@name='user']")).sendKeys("rochman.elena@gmail.com");
        type(By.xpath("//*[@name='user']"), "xeniya.kraeva+1@mail.ru");

        Thread.sleep(2000);
     //   wd.findElement(By.xpath("//input[@id='login']")).click();
        click(By.xpath("//input[@id='login']"));

//        click(By.xpath("//*[@name='password']"));
//        wd.findElement(By.xpath("//*[@name='password']")).clear();
//        wd.findElement(By.xpath("//*[@name='password']")).sendKeys("12345.com");
        type(By.xpath("//*[@name='password']"), "97531.mail");

        click(By.xpath("//button[@id='login-submit']"));

        new WebDriverWait(wd, 30)
                .until(ExpectedConditions.presenceOfElementLocated
                        (By.xpath("//ul[@class='boards-page-board-section-list']")));
    }

}
