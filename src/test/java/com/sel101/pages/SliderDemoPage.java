package com.sel101.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

public class SliderDemoPage {

    private static WebDriver driver = null;

    public SliderDemoPage(WebDriver driver){
        SliderDemoPage.driver = driver;
    }

    static By header = By.xpath("//div[@class='wrapper']//h1");
    static By slider15 = By.cssSelector(".sp__range.sp__range-success input");
    static By slider15Value = By.id("rangeSuccess");

    public void setSlider15(int value){
        try{
            WebElement slider = driver.findElement(slider15);

            // Get min, max, and current value
            int min = Integer.parseInt(slider.getAttribute("min"));
            int max = Integer.parseInt(slider.getAttribute("max"));
            int currentValue = Integer.parseInt(slider.getAttribute("value"));

            // Clamp the target value within min and max
            value = Math.max(min, Math.min(value, max));

            // Calculate the slider width
            int sliderWidth = slider.getSize().width;

            // Calculate the offset per unit value
            double pixelsPerUnit = (double)sliderWidth / (max - min);

            // Calculate the offset needed
            //int xOffset = (int)Math.round((value - currentValue-5) * pixelsPerUnit);

            // Use Actions to move the slider
            Actions action = new Actions(driver);
            action.clickAndHold(slider)
                    .moveByOffset((int) pixelsPerUnit, 0)
                    .release()
                    .perform();
            int xOffset = 0;
            while(Integer.parseInt(getSlider15Value()) != value){
                int offsetValue = Integer.parseInt(getSlider15Value()) - currentValue;
                xOffset += (int) ((pixelsPerUnit/(offsetValue-currentValue)) * (value-Integer.parseInt(getSlider15Value())));
                int currentValue_after = Integer.parseInt(getSlider15Value());
                int diff = value - currentValue_after;
                action.clickAndHold(slider)
                        .moveByOffset((diff>30) ? xOffset*5 : (diff>10) ? xOffset*2 : xOffset, 0)
                        .release()
                        .perform();
            }
        }
        catch(Exception e){
            System.out.println("ERROR: An exception occurred while setting slider value - " + e.getMessage());
        }
    }

    public String getSlider15Value(){
        return SliderDemoPage.driver.findElement(slider15Value).getText();
    }

    public boolean isHeaderVisible(){
        return SliderDemoPage.driver.findElement(header).isDisplayed();
    }


    public boolean isPageLoaded(){
        try{
            String currentURL = SliderDemoPage.driver.getCurrentUrl();
            if(!currentURL.contains("/drag-drop-range-sliders-demo"))
                return false;
            else {
                if(isHeaderVisible())
                    return SliderDemoPage.driver.findElement(header).getText().equals("Slider Demo");
                else return false;
            }
        }catch(Exception e){
            System.out.println("ERROR: An exception occurred while loading Slider Demo Page - " + e.getMessage());
            return false;
        }
    }
}
