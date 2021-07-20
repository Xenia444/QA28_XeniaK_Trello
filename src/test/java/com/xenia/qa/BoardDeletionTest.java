package com.xenia.qa;

import org.openqa.selenium.By;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class BoardDeletionTest extends TestBase{
    @BeforeMethod
    public void precondition() throws InterruptedException {
        if (isAvatarPresent()) {
            login("xeniya.kraeva+1@mail.ru", "97531.mail");
        }
    }

    @Test
    public void testBoardDeletion() {
        int boardCount = getBoardCount();
        while(boardCount > 1) {
            selectFirsBoard();
            openMenu();
            deleteBoard();
            returnOnHomePage();
            boardCount = getBoardCount();
        }
    }

    private void returnOnHomePage() {
        waitForElement(By.cssSelector("[data-test-id*=header-home]"), 30);
        click(By.cssSelector("[data-test-id*=header-home]"));
    }

    private void deleteBoard() {
        click(By.cssSelector(".js-open-more"));
        click(By.cssSelector(".js-close-board"));
        click(By.cssSelector(".js-confirm"));
        click(By.cssSelector(".js-delete"));
        click(By.cssSelector(".js-confirm"));
    }

    private void openMenu() {
        click(By.cssSelector(".js-show-sidebar"));
    }

    private int getBoardCount() {
       return wd.findElements(By.xpath("//*[contains(@class, 'boards-page-board-section-header-icon-default-image')]/../../../..//li")).size()-1;
    }

    private void selectFirsBoard() {
        click(By.xpath("//*[contains(@class, 'boards-page-board-section-header-icon-default-image')]/../../../..//li"));
    }
}
