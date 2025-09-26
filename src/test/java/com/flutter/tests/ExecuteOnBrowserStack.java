package com.flutter.tests;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.flutter.FlutterDriverOptions;
import io.appium.java_client.flutter.android.FlutterAndroidDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.HashMap;

public class ExecuteOnBrowserStack {
    FlutterAndroidDriver flutterAndroidDriver;
    WebDriverWait wait;

    @AfterTest
    public void killSession(ITestResult iTestResult) {
        flutterAndroidDriver.quit();
    }

    @BeforeTest
    public void setUp() throws MalformedURLException, InterruptedException {
        DesiredCapabilities desiredCapabilities = new DesiredCapabilities();

        FlutterDriverOptions uiAutomator2Options = new FlutterDriverOptions();
        uiAutomator2Options.setAutomationName("FlutterIntegration");
        uiAutomator2Options.setPlatformVersion("13.0");
        uiAutomator2Options.setPlatformName("Android");
        uiAutomator2Options.setCapability("deviceName","Google Pixel 7");
        uiAutomator2Options.setCapability("appium:app","bs://ab1a95b597e77417304fda282d87b784b2ce6839");
        uiAutomator2Options.setCapability("newCommandTimeout", 120);
        uiAutomator2Options.setCapability("autoGrantPermissions", true);

        uiAutomator2Options.setCapability("project", "Flutter Mobile Automation Demo");
        uiAutomator2Options.setCapability("build", "Flutter Build V1.1");
        uiAutomator2Options.setCapability("name", "Verify Counter");

        HashMap<String, Object> browserstackOptions = new HashMap<String, Object>();
        browserstackOptions.put("interactiveDebugging", true);
        browserstackOptions.put("browserstack.debug", true);
        browserstackOptions.put("appiumVersion", "2.19.0");
        desiredCapabilities.setCapability("bstack:options", browserstackOptions);

        uiAutomator2Options.merge(desiredCapabilities);
        flutterAndroidDriver = new FlutterAndroidDriver(new URL("https://username:accesskey@hub.browserstack.com/wd/hub"), uiAutomator2Options);
        wait = new WebDriverWait(flutterAndroidDriver, Duration.ofSeconds(10));
    }

    @Test
    public void verifyCounter() throws InterruptedException {
        Thread.sleep(5000);
        String message = flutterAndroidDriver.findElement(AppiumBy.flutterTextContaining("You have pushed the button this many times:")).getText();
        System.out.println(message);
        flutterAndroidDriver.findElement(AppiumBy.className("android.widget.Button")).click();
        Thread.sleep(3000);
        String counterNumber = flutterAndroidDriver.findElement(AppiumBy.flutterText("1")).getText();
        System.out.println(counterNumber);
        Assert.assertEquals(counterNumber,"1");
    }
}
