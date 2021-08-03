package com.xenia.qa.tests;

import com.xenia.qa.model.Card;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class CardCreationTest extends TestBase{
    @BeforeMethod
    public void preconditions() throws InterruptedException {
        if (!app.session().isAvatarPresent()) {
            app.session().login("xeniya.kraeva+1@mail.ru", "97531.mail");
        }
        if(!app.board().isOnBoardsPage()) {
            app.board().returnToHomePage();
        }
        Thread.sleep(3000);
        if(!app.board().isThereABoard()){
            app.board().createBoard();
        }
    }

    @Test
    public void testListCreation(){
        app.board().selectFirstBoard();
        app.card().addNew();
        app.card().fillForm(new Card().withName("withLable").withColor("yellow"));
        app.card().confirmCreation();

        //  app.card().addNew();
        app.card().fillForm(new Card().withName("withoutLable"));
        app.card().confirmCreation();

    }
}
