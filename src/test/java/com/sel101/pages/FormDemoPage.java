package com.sel101.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class FormDemoPage {

    private static WebDriver driver = null;

    public FormDemoPage(WebDriver driver){
        FormDemoPage.driver = driver;
    }

    static By header = By.xpath("//div[@class='wrapper']//h1");
    public By submit = By.xpath("//button[text()='Submit']");
    static By successMsg = By.className("success-msg");
    static By getInputBox(String labelText){
        return By.xpath("//div[@class='container']//input[@placeholder='"+labelText+"']");
    }
    static By getSelect(String labelText){
        return By.xpath("//div[@class='container']//select[preceding-sibling::label[contains(text(),'"+labelText+"')]]");
    }

    public boolean fillInputField(String labelText, String value){
        try{
            WebElement inputBox = FormDemoPage.driver.findElement(getInputBox(labelText));
            inputBox.clear();
            inputBox.sendKeys(value);
            return true;
        }catch(Exception e){
            System.out.println("ERROR: An exception occurred while filling input field '"+labelText+"' - " + e.getMessage());
            return false;
        }
    }

    public boolean selectDropdownOption(String labelText, String option){
        try{
            Select select = new Select(FormDemoPage.driver.findElement(getSelect(labelText)));
            select.selectByVisibleText(option);
            return true;
        }catch(Exception e){
            System.out.println("ERROR: An exception occurred while selecting dropdown option '"+option+"' for '"+labelText+"' - " + e.getMessage());
            return false;
        }
    }

    public void submitForm(){
        FormDemoPage.driver.findElement(submit).click();
    }

    public String getValidationMessage(String labelText){
        String validationMessage = null;
        try{
            WebElement inputBox = FormDemoPage.driver.findElement(getInputBox(labelText));
            validationMessage = inputBox.getAttribute("validationMessage");
            return validationMessage;
        }catch (Exception e) {
            System.out.println("ERROR: An exception occurred while getting validation message for '" + labelText + "' - " + e.getMessage());
            return "";
        }
    }

    public String getSuccessMessage(){
        return FormDemoPage.driver.findElement(successMsg).getText();
    }

    public boolean isHeaderVisible(){
        return FormDemoPage.driver.findElement(header).isDisplayed();
    }

    public boolean isPageLoaded(){
        try{
            String currentURL = FormDemoPage.driver.getCurrentUrl();
            if(!currentURL.contains("/input-form-demo"))
                return false;
            else {
                if(isHeaderVisible())
                    return FormDemoPage.driver.findElement(header).getText().equals("Form Demo");
                else return false;
            }
        }catch(Exception e){
            System.out.println("ERROR: An exception occurred while loading Form Demo Page - " + e.getMessage());
            return false;
        }
    }
}
