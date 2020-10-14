package com.myStore.selenium.initialize;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class Initializer {

    static private String url;
    static private final Properties properties = new Properties();
    static final private String userDirectory = System.getProperty("user.dir");
    static final private HashMap<String, String> classNameWorksheetMap = new HashMap<>();



    static {
        WebDriverManager.chromedriver().setup();
        try (FileInputStream fis = new FileInputStream(userDirectory + "\\src\\test\\resources\\global.Properties")) {
            properties.load(fis);
            url = properties.getProperty("url");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    static private final File workbook = new File(userDirectory + properties.getProperty("excelFilePath") + properties.getProperty("excelFileName"));

    static private XSSFWorkbook excelWorkbook;

    static {
        try {
            FileInputStream fileInputStream = new FileInputStream(workbook);
             excelWorkbook = new XSSFWorkbook(fileInputStream);
            //excelWorkbook = (XSSFWorkbook) WorkbookFactory.create(workbook, properties.getProperty("password"));

        } catch (IOException e) {
            e.printStackTrace();
        }
        for (String key : properties.stringPropertyNames()) {
            String value = properties.getProperty(key);
            classNameWorksheetMap.put(key, value);
        }
    }
    private final static WebDriver webDriver = new ChromeDriver();
    static {
        webDriver.get(url);
        webDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

    }

    public static WebDriver getDriver() {
        return webDriver;
    }
    public static HashMap<String, String> getClassNameWorksheetMap() {
        return classNameWorksheetMap;
    }
    public static XSSFWorkbook getWorkBook() {
        return excelWorkbook;
    }

}
