package com.myStore.selenium.initialize.testCases;

import com.myStore.selenium.initialize.Getter;
import com.myStore.selenium.initialize.MyGetter;
import com.myStore.selenium.initialize.Utility.Comparisions;
import org.testng.annotations.Test;

public class AddProductToCart_TC006 {

    private Getter getter;
    private static final String myClassName = "AddProductToCart_TC006";


    public AddProductToCart_TC006() {
        getter = new MyGetter();
        getter.setMyClassName(myClassName);
    }

    @Test(priority = 1)
    public void addProductToCart() {
        String addToCart = "addToCart";
        SelectProduct_TC005 selectProduct_tc005 = new SelectProduct_TC005();
        selectProduct_tc005.selectProduct();
        getter.getElement(addToCart).click();
    }

}
