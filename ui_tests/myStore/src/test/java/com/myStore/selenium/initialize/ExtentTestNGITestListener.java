package com.myStore.selenium.initialize;


import com.aventstack.extentreports.*;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.Markup;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Arrays;
import java.util.Date;

public class ExtentTestNGITestListener implements ITestListener {


    private static ExtentReports extent;

    static {
        try {
            extent = ExtentManager.getInstance();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    private static final ThreadLocal<ExtentTest> extentTestThreadLocal = new ThreadLocal<>();

    public void onTestStart(ITestResult result) {
        ExtentTest extentTest = extent.createTest(getClassMethodName(result));
        extentTestThreadLocal.set(extentTest);
    }

    public void onTestSuccess(ITestResult result) {
        String logTest = "<b>Test Method" + getMethodName(result) + "Successful</b>";
        Markup markup = MarkupHelper.createLabel(logTest, ExtentColor.GREEN);
        extentTestThreadLocal.get().log(Status.PASS, markup);
    }

    public void onTestSkipped(ITestResult result) {
        String logTest = "<b>Test Method" + getMethodName(result) + "Skipped</b>";
        Markup markup = MarkupHelper.createLabel(logTest, ExtentColor.YELLOW);
        extentTestThreadLocal.get().log(Status.SKIP, markup);
    }

    public void onTestFailure(ITestResult result) {
    /*    String exceptionMessage = Arrays.toString(result.getThrowable().getStackTrace());
        extentTestThreadLocal.get().fail("<details><summary><b><font color=Red>" + " Exception Occured, click here to see the details: " + "</font></b></summary>"
                + exceptionMessage.replaceAll(",", "<br>") + "</details> \n");

        String path = takeScreenShot(getMethodName(result));
        extentTestThreadLocal.get().fail("<b><font color = red>" + "ScreenShot of Failure" + "</font></b>",
                MediaEntityBuilder.createScreenCaptureFromPath(path).build());*/

        String logText = "<b>Test Method " + getMethodName(result) + " Failed</b>";
        Markup markup = MarkupHelper.createLabel(logText, ExtentColor.RED);
        extentTestThreadLocal.get().log(Status.FAIL, markup);
    }

    public void onFinish(ITestContext context) {
        if (extent != null) {
            extent.flush();
        }
    }

   /* private String takeScreenShot(String methodName) {
        String fileName = getScreenShotName(methodName);
        System.out.println("File "+fileName);
        String directory = System.getProperty("user.dir") + "\\reports\\";
        new File(directory).mkdirs();
        String path = directory + fileName;
        System.out.println("path "+path);
        ActionsPerformer actionsPerformer = new ActionsPerformer();
        actionsPerformer.takeScreenShot(path);
        return path;
    }*/

    private String getScreenShotName(String methodName) {
        return methodName + new Date().toString().replace(" ","_").replace(":","_") + ".jpg";
    }


    private String getClassMethodName(ITestResult result) {
        return getClassName(result) + " : " + getMethodName(result);
    }

    private String getClassName(ITestResult result) {
        return result.getTestClass().getName();
    }

    private String getMethodName(ITestResult result) {
        return result.getMethod().getMethodName();
    }

}
