package com.xenia.qa.fw;

import com.xenia.qa.model.Board;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;

public class BoardHelper extends HelperBase {

    public BoardHelper(WebDriver wd) {
        super(wd);
    }

    public String getTitle() {
        return wd.findElement(By.xpath("//h1")).getText();
    }

    public void confirmBoardCreation() {
        click(By.xpath("//*[@data-test-id='create-board-submit-button']"));
    }

    public void fillBoardCreation(Board board) {
        type(By.xpath("//*[@data-test-id='create-board-title-input']"), board.getBoardName());
    }

    public void selectCreateBoard() {
        click(By.xpath("//*[@data-test-id='header-create-board-button']"));
    }

    public void waitForAddListButtonInTheBoard() {
        waitForElement(By.cssSelector(".open-add-list"), 15);
    }

    public void deleteBoard() {
        if (!isElementPresent(By.cssSelector(".js-open-more"))) {
            click(By.cssSelector(".icon-back"));
        }
        click(By.cssSelector(".js-open-more"));
        click(By.cssSelector(".js-close-board"));
        confirmAction();
        click(By.cssSelector(".js-delete"));
        confirmAction();
    }

    public void openMenu() {
        if (isElementDisplayed(By.cssSelector(".js-show-sidebar"))) {
            click(By.cssSelector(".js-show-sidebar"));
        }
    }

    public int getBoardsCount() {
        return wd.findElements(By.xpath("//*[contains(@class, 'boards-page-board-section-header-icon-default-image')]/../../../..//li")).size() - 1;
    }

    public void selectFirstBoard() {
        click(By.xpath("//*[contains(@class, 'boards-page-board-section-header-icon-default-image')]/../../../..//li"));
    }

    public void clearBoardList() throws InterruptedException {
        int boardsCount = getBoardsCount();
        while (boardsCount > 1) {
            selectFirstBoard();
            openMenu();
            deleteBoard();
            returnToHomePage();
            Thread.sleep(4000);
            boardsCount = getBoardsCount();
        }
    }

    public boolean isThereABoard() {
        return getBoardsCount()>0;
    }

    public void createBoard() {
        clickOnPlusButton();
        selectCreateBoard();
        fillBoardCreation(new Board().withBoardName("experiment"));
        confirmBoardCreation();
        returnToHomePage();
    }

    public void editBoardName(String name) {
        click(By.cssSelector(".mod-board-name"));
        wd.findElement(By.cssSelector(".js-board-name-input")).sendKeys(name+ Keys.ENTER);
    }

    public boolean isOnBoardsPage() {
        return isElementPresent(By.xpath("//*[contains(@class, 'boards-page-board-section-header-icon-default-image')]/../../../..//li"));
    }
}