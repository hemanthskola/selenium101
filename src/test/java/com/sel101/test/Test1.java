package com.sel101.test;

import com.sel101.base.BaseTest;
import com.sel101.pages.SimpleFormDemoPage;
import com.sel101.pages.HomePage;

import java.net.MalformedURLException;

public class Test1 extends BaseTest {

    public void test1() {
        try {
            navigateToBaseURL();
            HomePage hp = new HomePage(getDriver());
            if (!hp.isPageLoaded()) {
                markStatus("failed", "Home Page did not load correctly", getDriver());
                return;
            }

            hp.navigateToOption("Simple Form Demo");
            SimpleFormDemoPage simpleFormDemoPage = new SimpleFormDemoPage(getDriver());
            if (!simpleFormDemoPage.isPageLoaded()) {
                markStatus("failed", "Simple Form Demo Page did not load correctly", getDriver());
                return;
            }

            String testMessage = "Welcome to LambdaTest";
            simpleFormDemoPage.enterMessage(testMessage);
            //simpleFormDemoPage.submitForm();
            jsClick(getDriver().findElement(simpleFormDemoPage.getCheckedValueButton));
            String displayedMessage = simpleFormDemoPage.getMessageText();
            if (displayedMessage.equals(testMessage))
                markStatus("passed", "The displayed message matches the input message", getDriver());
            else
                markStatus("failed", "The displayed message does not match the input message", getDriver());

        } catch (Exception e) {
            markStatus("failed", "Test encountered an exception: " + e.getMessage(), getDriver());
        }
    }

    public static void main(String[] args) {
        try {
            Test1 test = new Test1();
            setupLambdaTest();
            //setupLocalDriver();
            test.test1();
            tearDown();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }
}