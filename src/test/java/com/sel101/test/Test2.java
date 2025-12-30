package com.sel101.test;

import com.sel101.base.BaseTest;
import com.sel101.pages.HomePage;
import com.sel101.pages.SliderDemoPage;

public class Test2 extends BaseTest {

    public void test2() {
        try {
            navigateToBaseURL();
            HomePage hp = new HomePage(getDriver());
            if (!hp.isPageLoaded()) {
                markStatus("failed", "Home Page did not load correctly", getDriver());
                return;
            }

            hp.navigateToOption("Drag & Drop Sliders");
            SliderDemoPage sliderDemoPage = new SliderDemoPage(getDriver());
            if (!sliderDemoPage.isPageLoaded()) {
                markStatus("failed", "Slider Demo Page did not load correctly", getDriver());
                return;
            }

            int sliderValue = 95;
            sliderDemoPage.setSlider15(sliderValue);
            if (sliderValue == Integer.parseInt(sliderDemoPage.getSlider15Value()))
                markStatus("passed", "The value of Slider 15 is set to 95 as expected", getDriver());
            else
                markStatus("failed", "The value of the Slider 15 is not set to 95 instead it is " + sliderDemoPage.getSlider15Value(), getDriver());

        } catch (Exception e) {
            markStatus("failed", "Test encountered an exception: " + e.getMessage(), getDriver());
        }
    }

    public static void main(String[] args) {
        try {
            Test2 test = new Test2();
            setupLambdaTest();
//            setupLocalDriver();
            test.test2();
            tearDown();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}