package com.lambdaTest.pages;

import com.lambdaTest.base.BaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class HomePage{

    private static WebDriver driver = null;

    public HomePage(WebDriver driver){
        HomePage.driver = driver;
    }

    static By header = By.cssSelector("div.wrapper h1");

    public static By getOptionLocator(String optionName){
        return By.xpath("//div[@class='container__selenium']//li//a[text()='"+optionName+"']");
    }

    public boolean isHeaderVisible(){
        return driver.findElement(header).isDisplayed();
    }

    public boolean navigateToOption(String optionName){
        try {
            driver.findElement(getOptionLocator(optionName)).click();
            return true;
        }catch(Exception e){
            return false;
        }
    }

    public boolean isPageLoaded(){
        try{
            String currentURL = driver.getCurrentUrl();
            if(!currentURL.contains("/selenium-playground/"))
                return false;
            else {
                if(isHeaderVisible())
                    return driver.findElement(header).getText().equals("Selenium Playground");
                else return false;
            }
        }catch(Exception e){
            System.out.println("ERROR: An exception occurred while loading Home Page - " + e.getMessage());
            return false;
        }
    }

}
