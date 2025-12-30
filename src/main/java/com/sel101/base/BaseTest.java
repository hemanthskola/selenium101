package com.sel101.base;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.HashMap;
import java.util.Map;


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
        ltOptions.put("platformName", "macos 26.0");
        ltOptions.put("seCdp", true);
        ltOptions.put("selenium_version", "latest");
        capabilities.setCapability("LT:Options", ltOptions);

        driver = new RemoteWebDriver(new URL(hubURL), capabilities);
        System.out.println(driver);
    }

    public static void setupLocalDriver(){
        BaseTest.driver = new ChromeDriver();
        BaseTest.driver.manage().window().maximize();
        BaseTest.driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
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

    public void compareString(String actual, String expected){
        try{
            if(expected.equals(actual))
                markStatus("passed", "Values matched", BaseTest.driver);
            else
                markStatus("failed", "Values didn't matched", BaseTest.driver);
        }catch(Exception e){
            markStatus("failed", e.getMessage(),BaseTest.driver);
        }
    }

    public static void markStatus(String status, String reason, WebDriver driver) {
        JavascriptExecutor jsExecute = (JavascriptExecutor) driver;
        jsExecute.executeScript("lambda-status=" + status);
        System.out.println(status+" - "+reason);
    }

    public static WebDriver getDriver() {
        return BaseTest.driver;
    }

    public static void navigateToBaseURL(){
        BaseTest.driver.get("https://www.lambdatest.com/selenium-playground/");
    }

    public static boolean jsClick(WebElement ele){
        try{
            JavascriptExecutor jsExecutor = (JavascriptExecutor) getDriver();
            jsExecutor.executeScript("arguments[0].click();", ele);
            return true;
        }catch(Exception e){
            return false;
        }
    }

//    static class ParallelTestTask implements Runnable {
//        private final String browser;
//        private final String browserVersion;
//        private final String platform;
//
//        public ParallelTestTask(String browser, String browserVersion, String platform) {
//            this.browser = browser;
//            this.browserVersion = browserVersion;
//            this.platform = platform;
//        }
//
//        @Override
//        public void run() {
//            WebDriver driver = null;
//            try {
//                DesiredCapabilities capabilities = new DesiredCapabilities();
//                capabilities.setCapability("browserName", browser);
//                capabilities.setCapability("browserVersion", browserVersion);
//                Map<String, Object> ltOptions = new HashMap<>();
//                ltOptions.put("user", System.getenv("LT_USERNAME"));
//                ltOptions.put("accessKey", System.getenv("LT_ACCESS_KEY"));
//                ltOptions.put("build", "Parallel Selenium Run");
//                ltOptions.put("name", "Test on " + browser + " " + browserVersion + " " + platform);
//                ltOptions.put("platformName", platform);
//                capabilities.setCapability("LT:Options", ltOptions);
//
//                driver = new RemoteWebDriver(new URL("https://hub.lambdatest.com/wd/hub"), capabilities);
//                driver.get("https://www.lambdatest.com/selenium-playground/");
//                System.out.println("Running on: " + browser + " " + browserVersion + " " + platform);
//                // Place your test logic here
//
//            } catch (Exception e) {
//                e.printStackTrace();
//            } finally {
//                if (driver != null) {
//                    driver.quit();
//                }
//            }
//        }
//    }
}