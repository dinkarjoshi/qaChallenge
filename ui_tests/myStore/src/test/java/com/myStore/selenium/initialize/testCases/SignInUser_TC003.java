package com.myStore.selenium.initialize.testCases;

import com.myStore.selenium.initialize.Getter;
import com.myStore.selenium.initialize.MyGetter;
import com.myStore.selenium.initialize.Utility.Comparisions;
import org.testng.Assert;
import org.testng.annotations.Test;

public class SignInUser_TC003 {

    private Getter getter;
    private static final String myClassName = "SignInUser_TC003";
    private Comparisions comparisions;


    public SignInUser_TC003() {
        getter = new MyGetter();
        getter.setMyClassName(myClassName);
    }

    @Test(priority = 1)
    public void navigateSignInPage() {
        String signUpLink = "signUpLink";
        getter.getElement(signUpLink).click();
    }

    @Test(dependsOnMethods = "navigateSignInPage")
    public void enterEmailAndPassword() {
        String emailSignIn = "emailSignIn";
        String password = "password";
        String cliclSignInButton = "cliclSignInButton";
        getter.getElement(emailSignIn).sendKeys("whale1@gmail.com");
        getter.getElement(password).sendKeys("Password");
        getter.getElement(cliclSignInButton).click();
    }
    @Test(dependsOnMethods = "enterEmailAndPassword" )
    public void verifyWelcomeMessage(){
        String welcomeMessageLink = "welcomeMessageLink";
        String welcomeMessageText = "welcomeMessageText";
        Assert.assertEquals(getter.getElement(welcomeMessageLink).getText(),getter.getExpectedMessage(welcomeMessageText));
    }
}
