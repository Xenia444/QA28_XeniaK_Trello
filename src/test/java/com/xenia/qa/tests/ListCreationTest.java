package com.xenia.qa.tests;

import com.xenia.qa.model.List;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class ListCreationTest extends TestBase {
    @BeforeMethod
    public void preconditions() throws InterruptedException {
        if (!app.session().isAvatarPresent()) {
            app.session().login("xeniya.kraeva+1@mail.ru", "97531.mail");
        }
        if(!app.board().isOnBoardsPage()) {
            app.board().returnToHomePage();
        }
        if(!app.board().isThereABoard()){
            app.board().createBoard();
        }
    }

    @Test
    public void testListCreation() {
        app.board().selectFirstBoard();
        app.list().addNew();
        app.list().typeName(new List().withName("autoTest"));
        app.list().saveEdit();

    }
}
