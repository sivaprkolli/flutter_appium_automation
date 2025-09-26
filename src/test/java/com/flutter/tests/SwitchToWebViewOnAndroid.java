package com.flutter.tests;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
import io.appium.java_client.android.options.UiAutomator2Options;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.Set;

public class SwitchToWebViewOnAndroid {

    AndroidDriver androidDriver;
    @Test
    public void launchApp() throws MalformedURLException, InterruptedException {
        UiAutomator2Options uiAutomator2Options = new UiAutomator2Options();
        uiAutomator2Options.setDeviceName("Google Pixel");
        uiAutomator2Options.setPlatformName("Android");
        uiAutomator2Options.setPlatformVersion("16.0");
        uiAutomator2Options.setAutomationName("UiAutomator2");
        uiAutomator2Options.setApp(System.getProperty("user.dir")+"/src/main/resources/GeneralStore.apk");
        androidDriver = new AndroidDriver(new URL("http://127.0.0.1:4723"),uiAutomator2Options);

        androidDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        //Avoid writing xpath
        androidDriver.findElement(AppiumBy.xpath("//*[ends-with(@resource-id,'nameField')]")).sendKeys("Test");
        androidDriver.findElement(AppiumBy.xpath("//*[ends-with(@resource-id,'btnLetsShop')]")).click();

        androidDriver.findElement(AppiumBy.androidUIAutomator("new UiSelector().text(\"ADD TO CART\").instance(0)")).click();

        androidDriver.findElement(AppiumBy.xpath("//android.widget.FrameLayout/android.widget.ImageButton")).click();

        //Thread.sleep(5000);
        androidDriver.findElement(AppiumBy.id("com.androidsample.generalstore:id/btnProceed")).click();
        Thread.sleep(5000);
        Set<String> contexts = androidDriver.getContextHandles();
        System.out.println("contexts :: " +contexts);
        for (String context: contexts){
            //Thread.sleep(1000);
            if (context.contains("WEBVIEW")){
                androidDriver.context(context);
            }
        }

        androidDriver.findElement(AppiumBy.cssSelector("textarea[aria-label=\"Google Search\"]")).sendKeys("Appium");

        androidDriver.pressKey(new KeyEvent(AndroidKey.BACK));

        androidDriver.context("NATIVE_APP");

        boolean b = androidDriver.findElement(AppiumBy.xpath("//*[ends-with(@resource-id,'btnLetsShop')]")).isDisplayed();
        System.out.println(b);

    }
}
