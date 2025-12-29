package com.lambdaTest.base;

import org.jspecify.annotations.Nullable;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;


public class BaseTest {

    public static String hubURL = "https://hub.lambdatest.com/wd/hub";
    private static WebDriver driver;

    public static void setupLambdaTest() throws MalformedURLException {

        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("browserName", "Chrome");
        capabilities.setCapability("browserVersion", "latest");
        Map<String, Object> ltOptions = new HashMap<>();
        ltOptions.put("user", System.getenv("LT_USERNAME"));
        ltOptions.put("accessKey", System.getenv("LT_ACCESS_KEY"));
        ltOptions.put("build", "Selenium 4");
        ltOptions.put("name", BaseTest.class.getName());
        ltOptions.put("platformName", "Windows 10");
        ltOptions.put("seCdp", true);
        ltOptions.put("selenium_version", "latest");
        capabilities.setCapability("LT:Options", ltOptions);

        driver = new RemoteWebDriver(new URL(hubURL), capabilities);
        System.out.println(driver);
    }

    public static void setupLocalDriver(){
        BaseTest.driver = new ChromeDriver();
    }

    public static void tearDown() {
        try {
            BaseTest.driver.quit();
        } catch (Exception e) {
            markStatus("failed", "Got exception!", BaseTest.driver);
            e.printStackTrace();
            BaseTest.driver.quit();
        }
    }

    public static void markStatus(String status, String reason, WebDriver driver) {
//        JavascriptExecutor jsExecute = (JavascriptExecutor) driver;
//        jsExecute.executeScript("lambda-status=" + status);
        System.out.println(status+" - "+reason);
    }

    public static WebDriver getDriver() {
        return BaseTest.driver;
    }

    public static void navigateToBaseURL(){
        BaseTest.driver.get("https://www.lambdatest.com/selenium-playground/");
    }
}