package com.lambdaTest.pages;

import com.lambdaTest.base.BaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class SimpleFormDemoPage{

    private static WebDriver driver = null;

    public SimpleFormDemoPage(WebDriver driver){
        SimpleFormDemoPage.driver = driver;
    }

    static By header = By.xpath("//div[@class='wrapper']//h1");
    static By inputBox = By.xpath("//input[contains(@placeholder,'Please enter your Message')]");
    static By getCheckedValueButton = By.xpath("//button[text()='Get Checked Value']");
    static By message = By.cssSelector("p#message");

    public void enterMessage(String msg){
        SimpleFormDemoPage.driver.findElement(inputBox).clear();
        SimpleFormDemoPage.driver.findElement(inputBox).sendKeys(msg);
    }

    public void submitForm(){
        SimpleFormDemoPage.driver.findElement(getCheckedValueButton).click();
    }

    public String getMessageText(){
        return SimpleFormDemoPage.driver.findElement(message).getText();
    }

    public boolean isHeaderVisible(){
        return SimpleFormDemoPage.driver.findElement(header).isDisplayed();
    }


    public boolean isPageLoaded(){
        try{
            String currentURL = SimpleFormDemoPage.driver.getCurrentUrl();
            if(!currentURL.contains("/simple-form-demo"))
                return false;
            else {
                if(isHeaderVisible())
                    return SimpleFormDemoPage.driver.findElement(header).getText().equals("Simple Form Demo");
                else return false;
            }
        }catch(Exception e){
            System.out.println("ERROR: An exception occurred while loading Simple Form Demo Page - " + e.getMessage());
            return false;
        }
    }


}
