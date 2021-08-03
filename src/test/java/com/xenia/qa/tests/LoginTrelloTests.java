package com.xenia.qa.tests;


import com.xenia.qa.model.User;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class LoginTrelloTests extends TestBase {
    @BeforeMethod
    public void preconditions(){
        if(app.session().isAvatarPresent()){
            app.session().logout();
        }
    }

    @Test
    public void loginTest() throws InterruptedException {

        app.session().clickOnLoginButton();
        //    app.getSession().fillLoginForm(new User ("rochman.elena@gmail.com", "12345.com"));
        app.session().fillLoginForm(new User()
                .withEmail("xeniya.kraeva+1@mail.ru")
                .withPassword("97531.mail"));
        app.session().confirmLogin();

        Assert.assertTrue(app.session().isAvatarPresentWait());
    }

    @Test
    public void LogoutTest() throws InterruptedException {
        app.session().logout();
        Thread.sleep(10);
        Assert.assertTrue(app.session().checkUserLoggedOut());
    }

    @Test
    public void negativeLoginWithoutPasswordTest() throws InterruptedException {

        app.session().clickOnLoginButton();
        //    app.getSession().fillLoginForm(new User ("rochman.elena@gmail.com", "12345.com"));
        app.session().fillLoginForm(new User()
                .withEmail("xeniya.kraeva+1@mail.ru"));
        app.session().confirmLogin();
        Assert.assertTrue(app.session().isErrorPresent(), "Error is not present");


    }


}