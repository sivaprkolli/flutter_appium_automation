package com.flutter.tests;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.flutter.FlutterDriverOptions;
import io.appium.java_client.flutter.ios.FlutterIOSDriver;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.interactions.Sequence;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.Collections;
public class FlutterIOSAutomation {

    FlutterIOSDriver flutterAndroidDriver;

    @Test
    public void testFlutterApp() throws MalformedURLException, InterruptedException {
        FlutterDriverOptions flutterDriverOptions = new FlutterDriverOptions();
        UiAutomator2Options uiAutomator2Options = new UiAutomator2Options();
        flutterDriverOptions.setAutomationName("FlutterIntegration");
        flutterDriverOptions.setPlatformVersion("18.1");
        flutterDriverOptions.setPlatformName("iOS");
        flutterDriverOptions.setCapability("deviceName","iPhone 16 Plus");
        flutterDriverOptions.setCapability("appium:flutterServerLaunchTimeout", 24000);
        flutterDriverOptions.setCapability("appium:app", System.getProperty("user.dir")+"/src/main/resources/Runner.app");
       // flutterDriverOptions.setCapability("appium:bundleId", "com.example.flutterApplicationCounter");
        flutterDriverOptions.setUiAutomator2Options(uiAutomator2Options);
        flutterDriverOptions.setCapability("appium:app", System.getProperty("user.dir")+"/src/main/resources/Runner.app");
        flutterAndroidDriver = new FlutterIOSDriver(new URL("http://127.0.0.1:4723"), flutterDriverOptions);
        flutterAndroidDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
        Thread.sleep(5000);
        WebDriverWait wait = new WebDriverWait(flutterAndroidDriver, Duration.ofSeconds(20));

        String message = flutterAndroidDriver.findElement(AppiumBy.flutterText("You have pushed the button this many times:")).getText();
        System.out.println(message);
    }
}

