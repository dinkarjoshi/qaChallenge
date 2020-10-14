package com.myStore.selenium.initialize;


import com.google.common.collect.ImmutableMap;
import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;
import org.apache.poi.ss.usermodel.Sheet;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;

import javax.print.DocFlavor;
import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

public class Utilities {

    private static final WebDriver driver = Initializer.getDriver();
    private static final HashMap<String, String> classWorkSheetMap = Initializer.getClassNameWorksheetMap();


    public static void killDriver() {
        driver.quit();
    }

    public static String getCurrentURL(){
        return driver.getCurrentUrl();
    }

    public static Sheet getWorkSheet(String workSheetName) {
        return Initializer.getWorkBook().getSheet(workSheetName);
    }


    public static String getWorkSheetName(String className) {
        return classWorkSheetMap.get(className);
    }



    public static Dimension getDimension() {
        return driver.manage().window().getSize();
    }


    public static void scrollVertical(int fromY, int toY) {
        //Dimension dimension = getDimension();
        /*fromY =(int) (dimension.getHeight()*0.9);
        toY =(int) (dimension.getHeight()*0.1);
*/
        //new TouchAction<>(driver).press(PointOption.point(0,fromY)).waitAction(2000).moveTo(0,toY).release().perform();
        //  new TouchAction<>(driver).longPress(PointOption.point(0,fromY)).waitAction(WaitOptions.waitOptions(Duration.ofSeconds(5))).moveTo(PointOption.point(0,toY)).release().perform();


    }

    public static boolean containsIgnoreCase(String str, String searchStr) {
        if (str == null || searchStr == null) return false;

        final int length = searchStr.length();
        if (length == 0)
            return true;

        for (int i = str.length() - length; i >= 0; i--) {
            if (str.regionMatches(true, i, searchStr, 0, length))
                return true;
        }
        return false;    }



}
