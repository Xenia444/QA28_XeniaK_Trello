package com.xenia.qa.tests;

import com.xenia.qa.model.Board;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class BoardCreationTests extends TestBase {
    @BeforeMethod
    public void preconditions() throws InterruptedException {
        if (!app.session().isAvatarPresent()) {
            app.session().login("xeniya.kraeva+1@mail.ru", "97531.mail");
        }
    }

    @DataProvider
    public Iterator<Object[]> validBoards(){
        List<Object[]> list = new ArrayList<>();
        list.add(new Object[]{"a"});
        list.add(new Object[]{"123456789123456789"});
        list.add(new Object[]{"^%#@"});
        list.add(new Object[]{"h h h"});
        return list.iterator();
    }


    @DataProvider
    public Iterator<Object[]> validBoardsCSV() throws IOException {
        List<Object[]> list = new ArrayList<>();
        BufferedReader reader = new BufferedReader(new FileReader(new File("src/test/resources/boards.csv")));
        String line = reader.readLine();
        while(line != null) {
            String[] split = line.split(",");
            list.add(new Object[]{new Board().withBoardName(split[0])});
            line = reader.readLine();
        }

        return list.iterator();
    }

    @Test(dataProvider = "validBoards")
    public void boardCreationTestDataProvider1(String boardName) throws InterruptedException {
       // String boardName = "qa28Board_" + System.currentTimeMillis();
        Thread.sleep(4000);
        int before = app.board().getBoardsCount();

        app.board().clickOnPlusButton();
        app.board().selectCreateBoard();
        app.board().fillBoardCreation(new Board().withBoardName(boardName));
        app.board().confirmBoardCreation();
        app.board().waitForAddListButtonInTheBoard();
        String title = app.board().getTitle();
        Assert.assertEquals(title, boardName);

        app.board().returnToHomePage();
        Thread.sleep(4000);
        int after = app.board().getBoardsCount();
        Assert.assertEquals(after, before + 1);
    }

    @Test(dataProvider = "validBoardsCSV")
    public void boardCreationTestDataProvider1(Board board) throws InterruptedException {
        // String boardName = "qa28Board_" + System.currentTimeMillis();
        Thread.sleep(4000);
        int before = app.board().getBoardsCount();

        app.board().clickOnPlusButton();
        app.board().selectCreateBoard();
        app.board().fillBoardCreation(board);
        app.board().confirmBoardCreation();
        app.board().waitForAddListButtonInTheBoard();
        String title = app.board().getTitle();
     //   Assert.assertEquals(title, board);

        app.board().returnToHomePage();
        Thread.sleep(4000);
        int after = app.board().getBoardsCount();
        Assert.assertEquals(after, before + 1);
    }

    @Test
    public void boardCreationTest() throws InterruptedException {
        String boardName = "qa28Board_" + System.currentTimeMillis();
        Thread.sleep(4000);
        int before = app.board().getBoardsCount();

        app.board().clickOnPlusButton();
        app.board().selectCreateBoard();
        app.board().fillBoardCreation(new Board().withBoardName(boardName));
        app.board().confirmBoardCreation();
        app.board().waitForAddListButtonInTheBoard();
        String title = app.board().getTitle();
        Assert.assertEquals(title, boardName);

        app.board().returnToHomePage();
        Thread.sleep(4000);
        int after = app.board().getBoardsCount();
        Assert.assertEquals(after, before + 1);
    }

}