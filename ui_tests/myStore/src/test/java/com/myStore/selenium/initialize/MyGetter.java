package com.myStore.selenium.initialize;

import io.appium.java_client.MobileElement;
import org.apache.poi.ss.usermodel.Sheet;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebElement;

import java.util.List;

public class MyGetter implements Getter {
    private Sheet sheet;
    private int locatorColoumnIndex;
    private int elementLocatorStringValueColoumnIndex;
    private int expectedMessageColumnIndex;
    private  ElementHandlingUtility elementHandlingUtility;
    private int elementColoumnIndex;
    private int expectedMessageFiledNameColumnIndex;
    private String servingClassName;
    private String getterClassName;


    private void setGetterClassName(String getterClassName) {
        this.getterClassName = getterClassName;
    }


    public MyGetter() {
        elementHandlingUtility = new ElementHandlingUtility();
        setGetterClassName(this.getClass().getName());
    }


    private void setLocators() {
        setSheet();

        setElementLocatorStringValueColoumnIndex();
        setElementColoumnIndex();
        setExpectedMessageColumnIndex();
        setExpectedMessageColumnIndex();
        setLocatorColoumnIndex();
    }

    @Override
    public void setMyClassName(String myClassname) {
        this.servingClassName = myClassname;
        setLocators();
    }

    public WebElement getElement(String elementName) {
        WebElement element = null;
        try {
            element = elementHandlingUtility.getElement(elementHandlingUtility.getLocatorType(elementName, sheet, locatorColoumnIndex), elementHandlingUtility.getElementLocatorStringValue(elementName, sheet, elementLocatorStringValueColoumnIndex));

        } catch (Exception e) {
            System.out.println("This is from Getter, element " + elementName + " could not be found, hence element is null");
            System.out.println(e.getMessage());
        }
        return element;
    }

    public String getExpectedMessage(String expectedMessageFieldName) {
        return elementHandlingUtility.getExpectedMessage(expectedMessageFieldName, sheet, expectedMessageColumnIndex);
    }

    @Override
    public String getServingClassName() {
        return servingClassName;
    }

    @Override
    public String getSheetName() {
        return sheet.getSheetName();
    }

    @Override
    public String getPageSource() {
        return elementHandlingUtility.getPageSource();
    }

    @Override
    public String getCurrentURL() {
       return Utilities.getCurrentURL();
    }

    @Override
    public List<WebElement> getElements(String elementName) {
        List<WebElement> element = null;

        try {
            element = elementHandlingUtility.getElements(elementHandlingUtility.getLocatorType(elementName, sheet, locatorColoumnIndex), elementHandlingUtility.getElementLocatorStringValue(elementName, sheet, elementLocatorStringValueColoumnIndex));
        } catch (Exception e) {
            System.out.println("This is from Getter, element " + elementName + " could not be found, hence element is null");
            System.out.println(e.getMessage());
        }
        return element;
    }

    @Override
    public Dimension getDimension() {
        return elementHandlingUtility.getDimension();
    }

    @Override
    public String getValueByAttribue(WebElement element, String attribute) {
        return element.getAttribute(attribute);
    }

    @Override
    public Getter getGetterObject() {
        return Getter.getGetterObject(getterClassName);
    }

    public String toString() {
        return servingClassName;
    }

    private void setSheet() {
        this.sheet = Utilities.getWorkSheet(Utilities.getWorkSheetName(servingClassName));
    }

    private void setElementLocatorStringValueColoumnIndex() {
        elementLocatorStringValueColoumnIndex = elementHandlingUtility.getValueColumnIndex(sheet);
    }

    private void setElementColoumnIndex() {
        elementColoumnIndex = elementHandlingUtility.getElementNameColumnIndex(sheet);
    }

    private void setExpectedMessageFiledNameColumnIndex() {
        expectedMessageFiledNameColumnIndex = elementHandlingUtility.getExpectedMessageFieldNameColumnIndex(sheet);
    }

    private void setExpectedMessageColumnIndex() {
        expectedMessageColumnIndex = elementHandlingUtility.getExpectedMessageColumnIndex(sheet);
    }

    private void setLocatorColoumnIndex() {
        locatorColoumnIndex = elementHandlingUtility.getLocatorColoumnIndex(sheet);
    }

}
