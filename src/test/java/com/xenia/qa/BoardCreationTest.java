package com.xenia.qa;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class BoardCreationTest extends TestBase {

    @BeforeMethod
    public void precondition() throws InterruptedException {
        if (isAvatarPresent()) {
            login("xeniya.kraeva+1@mail.ru", "97531.mail");
        }
    }

    @Test
    public void boardCreationTest() throws InterruptedException {
        String boardName = "Board" + System.currentTimeMillis();
        clickOnPlusButton();
        selectCreateBoard();
        fillBoardCreationForm(boardName);
        confirmBoardCreation();
        waitForAddListButtonInTheBoard();
        String title = getTitle();

        Assert.assertEquals(title, boardName);
    }
}


