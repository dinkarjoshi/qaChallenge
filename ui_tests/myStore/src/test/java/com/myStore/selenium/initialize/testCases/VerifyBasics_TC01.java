package com.myStore.selenium.initialize.testCases;

import com.myStore.selenium.initialize.Getter;
import com.myStore.selenium.initialize.Initializer;
import com.myStore.selenium.initialize.MyGetter;
import com.myStore.selenium.initialize.Utility.Comparisions;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

public class VerifyBasics_TC01 {
    private final Getter getter;
    private static final String myClassName = "VerifyBasics_TC01";
    private final Comparisions comparisions;

    public VerifyBasics_TC01() {
        getter = new MyGetter();
        getter.setMyClassName(myClassName);
        comparisions = new Comparisions();
    }

    /*
    To verify the url of the page is correct or not
    The program reads the expected value from the excel sheet and compares against the actual url
    */
    @Test(priority = 1)
    public void verifyURLName() {
        Assert.assertEquals(getter.getExpectedMessage("url"), getter.getCurrentURL());
    }

    /*
    The method reads the value of the text of the Contact button and compares with the actual from the web page
     */
    @Test(dependsOnMethods = "verifyURLName")
    public void verifyContactLink() {
        String contactLink = "contactUsLink";
        String contactUsMessage = "contactUsMessage";
        Assert.assertEquals(getter.getExpectedMessage(contactUsMessage), getter.getElement(contactLink).getText());
    }
    /*
        Verify the signin page URL , the method reads data from excel sheet and compares with the actual
     */
    @Test(dependsOnMethods = "verifyContactLink")
    public void verifySignInURL(){
        String signInLink = "signInLink";
        String signInURL = "signInURL";
        Assert.assertEquals( getter.getExpectedMessage(signInURL), getter.getElement(signInLink).getAttribute("href"));
    }

}
