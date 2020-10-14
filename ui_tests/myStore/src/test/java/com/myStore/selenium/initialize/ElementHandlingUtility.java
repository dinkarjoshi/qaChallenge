package com.myStore.selenium.initialize;


import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;


public class ElementHandlingUtility {

    private final WebDriver driver = Initializer.getDriver();

    WebElement getElement(String locator, String elementLocatorStringValue) {
        if (locator.equalsIgnoreCase("Id")) return getElementById(elementLocatorStringValue);
        if (locator.equalsIgnoreCase("HardCode")) return getElementByIdHardCode(elementLocatorStringValue);

        return getElementByXpath(elementLocatorStringValue);
    }


    int getLocatorColoumnIndex(Sheet sheet) {
        return getColumnIndex(getRow(0, sheet), "LocatorType");
    }


    int getValueColumnIndex(Sheet sheet) {
        return getColumnIndex(getRow(0, sheet), "ElementLocatorStringValue");
    }

    int getElementNameColumnIndex(Sheet sheet) {
        return getColumnIndex(getRow(0, sheet), "ElementName");
    }


    int getExpectedMessageColumnIndex(Sheet sheet) {
        return getColumnIndex(getRow(0, sheet), "ExpectedMessage");
    }

    int getExpectedMessageFieldNameColumnIndex(Sheet sheet) {
        return getColumnIndex(getRow(0, sheet), "ExpectedMessageFieldName");
    }

    String getExpectedMessage(String expectedMessageFieldName, Sheet sheet, int expectedMessageColumnIndex) {
        return getCellValue(expectedMessageFieldName, sheet, expectedMessageColumnIndex);
    }


    String getLocatorType(String elementName, Sheet sheet, int locatorColoumnIndex) {
        return getCellValue(elementName, sheet, locatorColoumnIndex);

    }

    String getElementLocatorStringValue(String elementName, Sheet sheet, int elementLocatorStringValueIndex) {
        return getCellValue(elementName, sheet, elementLocatorStringValueIndex);
    }

    private WebElement getElementById(String id) {
        //System.out.println(driver.findElementById(appPackage + ":id/" + id));
        return driver.findElement(By.id(id));
    }

    private WebElement getElementByIdHardCode(String id) {
        //System.out.println(driver.findElementById(appPackage + ":id/" + id));
        return driver.findElement(By.id(id));
    }


    public List<WebElement> getElements(String locator, String elementLocatorStringValue) {
        if (locator.equalsIgnoreCase("Id")) return getElementsById(elementLocatorStringValue);
        return getElementsByXpath(elementLocatorStringValue);
    }

    private List<WebElement> getElementsById(String elementLocatorStringValue) {
        return driver.findElements(By.id(elementLocatorStringValue));
    }

    private List<WebElement> getElementsByXpath(String elementLocatorStringValue) {
        return driver.findElements(By.xpath(elementLocatorStringValue));
    }

    public void clickAndSendKeys(WebElement mobileElement, String keys) {
        click(mobileElement);
        sendKeys(mobileElement, keys);
    }

    public String getPageSource() {
        return driver.getPageSource();
    }

    public Dimension getDimension() {
        return driver.manage().window().getSize();
    }

    private String getCellValue(String toFind, Sheet sheet, int coloumnIndex) {
        String elementToReturn = "";
        for (Row row : sheet) {
            for (Cell cell : row) {
                if (cell.getStringCellValue().equalsIgnoreCase(toFind))
                    elementToReturn = row.getCell(coloumnIndex).getStringCellValue();
            }
        }
        return elementToReturn;
    }

    private void click(WebElement mobileElement) {
        mobileElement.click();
    }

    private void sendKeys(WebElement mobileElement, String keys) {
        mobileElement.sendKeys(keys);
    }


    private WebElement getElementByXpath(String locator) {
        return driver.findElement(By.xpath(locator));
    }

    private Row getRow(int rowNumber, Sheet sheet) {
        return sheet.getRow(rowNumber);
    }

    private int getColumnIndex(Row row, String stringToFind) {
        int coloumnIndex = 0;
        for (Cell cell : row) {
            if (cell.getStringCellValue().equalsIgnoreCase(stringToFind))
                coloumnIndex = cell.getColumnIndex();
        }
        return coloumnIndex;
    }

}
