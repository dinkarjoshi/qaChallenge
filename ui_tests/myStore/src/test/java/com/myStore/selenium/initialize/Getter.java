package com.myStore.selenium.initialize;



import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebElement;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.List;

public interface Getter {

    public void setMyClassName(String myClassname);

    public WebElement getElement(String elementName);

    public String getExpectedMessage(String expectedMessageFieldName);

    String getServingClassName();

    String getSheetName();

    String getPageSource();
    String getCurrentURL();

    List<WebElement> getElements(String elementName);

    Dimension getDimension();

    String getValueByAttribue(WebElement element, String attribute);

    Getter getGetterObject();

    static Getter getGetterObject(String getterClassName) {
        Getter myGetter = null;

        try {
            Class<?> getterClass = Class.forName(getterClassName);
            try {
                Constructor<?> constructor = getterClass.getConstructor();
                try {
                    myGetter = (Getter) constructor.newInstance();

                } catch (InstantiationException | InvocationTargetException | IllegalAccessException e) {
                    e.printStackTrace();
                }
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return myGetter;
    }

}

