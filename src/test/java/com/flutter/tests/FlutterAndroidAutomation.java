package com.flutter.tests;


import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.flutter.android.FlutterAndroidDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;

public class FlutterAndroidAutomation {
    FlutterAndroidDriver androidDriver;

    @BeforeTest
    public void launchApplication() throws MalformedURLException, InterruptedException {
        UiAutomator2Options uiAutomator2Options = new UiAutomator2Options();
        uiAutomator2Options.setAutomationName("FlutterIntegration");
        uiAutomator2Options.setPlatformVersion("16.0");
        uiAutomator2Options.setPlatformName("Android");
        uiAutomator2Options.setDeviceName("SamSung");
        uiAutomator2Options.setCapability("appium:flutterServerLaunchTimeout", 10000);
        uiAutomator2Options.setApp("/Users/shiva/Documents/Appium2Updates/src/main/resources/app-debug.apk");
        androidDriver = new FlutterAndroidDriver(new URL("http://127.0.0.1:4723"), uiAutomator2Options);
        androidDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
    }

    public void verifyCounterIncrement() throws InterruptedException {
        Thread.sleep(5000);
        String message = androidDriver.findElement(AppiumBy.flutterText("You have pushed the button this many times:")).getText();
        System.out.println(message);
        //androidDriver.findElement(AppiumBy.flutterText("Increment")).click();
        androidDriver.findElement(AppiumBy.className("android.widget.Button")).click();
        Thread.sleep(3000);
        // String counterNumber = androidDriver.findElement(AppiumBy.xpath("//android.widget.Button/preceding-sibling::*[string-length(@content-desc)=1]")).getAttribute("content-desc");
        String counterNumber = androidDriver.findElement(AppiumBy.flutterText("1")).getText();
        System.out.println(counterNumber);
        Assert.assertEquals(counterNumber, "1");
        //androidDriver.findElement(AppiumBy.flutterKey("id")).click();
        //androidDriver.findElement(AppiumBy.flutterSemanticsLabel("label")).click();
        // androidDriver.findElement(AppiumBy.flutterType("Let's begin")).click();
    }
}

