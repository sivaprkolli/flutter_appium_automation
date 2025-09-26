package com.flutter.tests;


import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.flutter.FlutterDriverOptions;
import io.appium.java_client.flutter.android.FlutterAndroidDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;

public class FlutterBestUIAndroidAutomation {
    FlutterAndroidDriver androidDriver;


    @Test
    public void testFlutterApp() throws MalformedURLException, InterruptedException {
        FlutterDriverOptions flutterDriverOptions = new FlutterDriverOptions();
        flutterDriverOptions.setAutomationName("FlutterIntegration");
        flutterDriverOptions.setPlatformVersion("16.0");
        flutterDriverOptions.setPlatformName("Android");
        flutterDriverOptions.setCapability("deviceName", "Samsung");
        flutterDriverOptions.setCapability("appium:flutterServerLaunchTimeout", 10000);
        flutterDriverOptions.setCapability("appium:app",System.getProperty("user.dir")+"/src/main/resources/bestUIApp.apk");
        androidDriver = new FlutterAndroidDriver(new URL("http://127.0.0.1:4723"), flutterDriverOptions);
        androidDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(8));

        if (androidDriver.findElements(AppiumBy.flutterTextContaining("Home")).size()>0){
            androidDriver.findElement(AppiumBy.xpath("(//*[@content-desc='Sign Out']/following-sibling::android.view.View)[1]")).click();
        }

        androidDriver.findElement(AppiumBy.xpath("(//android.widget.ImageView)[1]")).click();
        androidDriver.findElement(AppiumBy.flutterText("Let's begin")).click();
        androidDriver.findElement(AppiumBy.xpath("(//android.widget.ImageView/following-sibling::android.view.View)")).click();
    }
}

