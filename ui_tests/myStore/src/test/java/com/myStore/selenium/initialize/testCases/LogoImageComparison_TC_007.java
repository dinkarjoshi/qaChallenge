package com.myStore.selenium.initialize.testCases;

import com.myStore.selenium.initialize.Getter;
import com.myStore.selenium.initialize.Initializer;
import com.myStore.selenium.initialize.MyGetter;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.Test;
import ru.yandex.qatools.ashot.AShot;
import ru.yandex.qatools.ashot.Screenshot;
import ru.yandex.qatools.ashot.comparison.ImageDiff;
import ru.yandex.qatools.ashot.comparison.ImageDiffer;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;


public class LogoImageComparison_TC_007 {


    private Getter getter;
    private static final String myClassName = "LogoImageComparison_TC_007";
    WebDriver driver = Initializer.getDriver();


    public LogoImageComparison_TC_007() {
        getter = new MyGetter();
        getter.setMyClassName(myClassName);
    }

/*Ashot is capturing wrong area of the screenshot, this can be corrected by a shooting strategy method.
* */

    @Test(priority = 1)
    public void verifyLogoImage() throws IOException {
        String logo = "logo";
        Screenshot logoScreenshot = new AShot().takeScreenshot(driver,getter.getElement(logo));
        ImageIO.write(logoScreenshot.getImage(), "png", new File(System.getProperty("user.dir") + "//src//test//resources//logoShot1.png"));
        File file = new File(System.getProperty("user.dir") + "//src//test//resources//logoShot1.png");
        BufferedImage actualmage = ImageIO.read(new File(System.getProperty("user.dir") + "//src//test//resources//logoShot1.png"));
        BufferedImage expectedImage = ImageIO.read(new File(System.getProperty("user.dir") + "//src//test//resources//logo.png"));

        ImageDiffer imageDiffer = new ImageDiffer();
        ImageDiff imageDiff = imageDiffer.makeDiff(actualmage,expectedImage );
        Assert.assertFalse(imageDiff.hasDiff());
    }

}
