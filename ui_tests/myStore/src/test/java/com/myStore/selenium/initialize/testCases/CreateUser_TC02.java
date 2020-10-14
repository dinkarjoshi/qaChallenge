package com.myStore.selenium.initialize.testCases;

import com.myStore.selenium.initialize.Getter;
import com.myStore.selenium.initialize.Initializer;
import com.myStore.selenium.initialize.MyGetter;
import com.myStore.selenium.initialize.Utility.Comparisions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;

public class CreateUser_TC02 {
    private Getter getter;
    private static final String myClassName = "CreateUser_TC02";
    private Comparisions comparisions;
    WebDriver driver = Initializer.getDriver();

    public CreateUser_TC02() {
        getter = new MyGetter();
        getter.setMyClassName(myClassName);
        comparisions = new Comparisions();
    }

    @Test(priority = 1)
    public void createAccount() {
        String signInLink = "signInLink";
        getter.getElement(signInLink).click();
    }

    @Test(dependsOnMethods = "createAccount")
    public void createAccountIsDisplayed() {
        String createAccount = "createAccount";
        getter.getElement(createAccount).isDisplayed();
    }

    /*DAta provider can also be used or excel sheet can also be used to provide data instead of hardcoding, however due to lack of time hardcoding the value of email address*/
    @Test(dependsOnMethods = "createAccountIsDisplayed")
    public void enterEmailAddress() {
        String createEmail = "createEmail";
        getter.getElement(createEmail).sendKeys("whale8@gmail.com");
    }

    @Test(dependsOnMethods = "enterEmailAddress")
    public void clickCreateAccount() {
        String createAccountButton = "createAccountButton";
        getter.getElement(createAccountButton).click();
    }

    @Test(dependsOnMethods = "clickCreateAccount")
    public void createAccountPageisDisplayed() {
        String createAccountURL = "createAccountURL";
        System.out.println(getter.getCurrentURL());
        System.out.println(getter.getElement(createAccountURL)+"getter.Getelememnt");
        //  Assert.assertEquals(getter.getExpectedMessage(createAccountURL),getter.getCurrentURL());
    }


    /*
    Using the driver instance to speed up the data entry process, if time permits can be used via excel, like other fileds.
    * */
    @Test(dependsOnMethods = "createAccountPageisDisplayed")
    public void enterAccountDetails() {
        System.out.println(driver.getCurrentUrl());
        driver.findElement(By.xpath("//input[@id='id_gender1']")).click();
        driver.findElement(By.xpath("//input[@id='customer_firstname']")).sendKeys("Dinkar");
        driver.findElement(By.xpath("//input[@id='customer_lastname']")).sendKeys("Joshi");
        driver.findElement(By.xpath("//input[@id='passwd']")).sendKeys("Password");
        driver.findElement(By.xpath("//select[@id='days']")).click();
        Select day = new Select(driver.findElement(By.xpath("//select[@id='days']")));
        day.selectByValue("21");
        driver.findElement(By.xpath("//select[@id='months']")).click();
        Select month = new Select(driver.findElement(By.xpath("//select[@id='months']")));
        month.selectByIndex(5);
        driver.findElement(By.xpath("//select[@id='years']")).click();
        Select year = new Select(driver.findElement(By.xpath("//select[@id='years']")));
        year.selectByValue("1985");
        driver.findElement(By.id("optin")).click();
        driver.findElement(By.xpath("//input[@id='address2']")).sendKeys("Address Line 2");
        driver.findElement(By.xpath("//input[@id='address1']")).sendKeys("Address Line 1");
        driver.findElement(By.xpath("//input[@id='city']")).sendKeys("City");
        driver.findElement(By.xpath("//select[@id='id_state']")).click();
        new Select(driver.findElement(By.xpath("//select[@id='id_state']"))).selectByIndex(22);
        driver.findElement(By.xpath("//input[@id='postcode']")).sendKeys("12345");
        driver.findElement(By.xpath("//input[@id='phone_mobile']")).sendKeys("123456789");
        driver.findElement(By.xpath("//body/div[@id='page']/div[2]/div[1]/div[3]/div[1]/div[1]/form[1]/div[4]/button[1]/span[1]")).click();

    }

    @Test(dependsOnMethods = "enterAccountDetails")
    public void verifyUserCreated() {
        String accountCreatedMessage = "accountCreatedMessage";
        String accountCreatedXpath  = "accountCreatedXpath";
        Assert.assertTrue(getter.getElement(accountCreatedXpath).getText().equalsIgnoreCase(getter.getExpectedMessage(accountCreatedMessage)));
    }

    @AfterClass
    public void logOutUser(){
        String signOutUser = "signOutUser";
        getter.getElement(signOutUser).click();
    }

}
