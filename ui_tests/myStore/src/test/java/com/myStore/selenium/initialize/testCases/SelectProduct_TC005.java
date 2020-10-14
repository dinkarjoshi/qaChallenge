package com.myStore.selenium.initialize.testCases;

import com.myStore.selenium.initialize.Getter;
import com.myStore.selenium.initialize.Initializer;
import com.myStore.selenium.initialize.MyGetter;
import com.myStore.selenium.initialize.Utility.Comparisions;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

public class SelectProduct_TC005 {

    private Getter getter;
    private static final String myClassName = "SelectProduct_TC005";
    private Comparisions comparisions;


    public SelectProduct_TC005() {
        getter = new MyGetter();
        getter.setMyClassName(myClassName);
    }

    @Test(priority = 1)
    public void selectProduct() {
        String homelink = "homelink";
        String dressSelect = "dressSelect";
        Initializer.getDriver().findElement(By.xpath("//div[3]/div[1]/div[1]/div[1]/a[1]/img[1]")).click();
        SearchProducts_TC004 searchProducts_tc004 = new SearchProducts_TC004();
        searchProducts_tc004.searchProduct();
        getter.getElement(dressSelect).click();

    }

    @Test(dependsOnMethods = "selectProduct")
    public void verifyPrice(){
        String priceExpected = "priceExpected";
        String price = "price";
        Assert.assertTrue(getter.getElement(price).getText().equalsIgnoreCase(getter.getExpectedMessage(priceExpected)));
    }


}
