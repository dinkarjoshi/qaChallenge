package com.myStore.selenium.initialize;

import com.aventstack.extentreports.ExtentReports;

import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import javax.swing.plaf.synth.SynthTextAreaUI;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.Date;

public class ExtentManager {

    private static ExtentReports extent;

    public static ExtentReports getInstance() throws FileNotFoundException {
        if (extent == null)
            extent= createInstance();
        return extent;
    }

    private static ExtentReports createInstance() throws FileNotFoundException {

        String fileName = getReportName().replace(":","-");
        System.out.println("Report Name "+ fileName);
        String path = System.getProperty("user.dir")+"/reports/"+fileName;
        ExtentSparkReporter htmlReporter =  new ExtentSparkReporter(path);


        htmlReporter.config().setDocumentTitle(path.toString());
        htmlReporter.config().setEncoding("utf-8");
        htmlReporter.config().setReportName(fileName);
        htmlReporter.config().setTheme(Theme.DARK);
        htmlReporter.config().setDocumentTitle("GMA_Automation");

        extent = new ExtentReports();
        extent.attachReporter(htmlReporter);
        extent.setSystemInfo("CI&T","QAProject");
        extent.setSystemInfo("User","Dinkar");
        return extent;
    }

    public static String getReportName(){
        Date date = new Date();
        return "AutomationReport_"+date.toString().replace(" ","_")+".html";
    }

}