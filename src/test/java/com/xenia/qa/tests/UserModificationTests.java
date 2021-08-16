package com.xenia.qa.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class UserModificationTests extends TestBase{

    @BeforeMethod
    public void preconditions() throws InterruptedException {
        if (!app.session().isAvatarPresent()) {
            app.session().login("xeniya.kraeva+1@mail.ru", "97531.mail");
        }
    }

    @Test
    public void testChangeUserAvatar() throws InterruptedException {
        app.session().clickOnAvatar();
        app.session().openUserProfile();
        app.session().goToAtlassianAccount();
        Thread.sleep(7000);
        String url = app.getURL();
        Assert.assertEquals(url, "https://id.atlassian.com/manage-profile/profile-and-visibility", "Wrong URL!!! " + app.getURL());

        app.atlassian().initChangePhoto();
        app.atlassian().uploadPhoto();

        app.session().returnToTrelloFromAtlassian();
        String currUrl = app.getURL();
        Assert.assertTrue(currUrl.contains("https://trello.com"), "Current URL is " + app.getURL());
    }
}
