package com.myStore.selenium.initialize.testCases;

import com.myStore.selenium.initialize.Getter;
import com.myStore.selenium.initialize.Initializer;
import com.myStore.selenium.initialize.MyGetter;
import com.myStore.selenium.initialize.Utility.Comparisions;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;

public class SearchProducts_TC004 {
    private Getter getter;
    private static final String myClassName = "SearchProducts_TC004";
    private Comparisions comparisions;
    WebDriver driver = Initializer.getDriver();


    public SearchProducts_TC004() {
        getter = new MyGetter();
        getter.setMyClassName(myClassName);
    }


    /*Can use data providers for test data or can be imported from excel, however hardcoding to save time*/
    @Test(priority = 1)
    public void searchProduct(){
        String searchField ="searchField" ;
        String searchButton = "searchButton";

        getter.getElement(searchField).sendKeys("Printed");
        getter.getElement(searchButton).click();

    }

    @Test(dependsOnMethods = "searchProduct")
    public void verifySearch(){
        String resultCount = "resultCount";

        System.out.println("The number of searched result " + getter.getElement(resultCount).getText() );
    }

}
