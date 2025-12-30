package com.sel101.test;

import com.sel101.base.BaseTest;
import com.sel101.pages.FormDemoPage;
import com.sel101.pages.HomePage;

public class Test3 extends BaseTest {

    public void test3() {
        try {
            navigateToBaseURL();
            HomePage hp = new HomePage(getDriver());
            if (!hp.isPageLoaded()) {
                markStatus("failed", "Home Page did not load correctly", getDriver());
                return;
            }

            hp.navigateToOption("Input Form Submit");
            FormDemoPage formDemoPage = new FormDemoPage(getDriver());
            if (!formDemoPage.isPageLoaded()) {
                markStatus("failed", "Input Demo Page did not load correctly", getDriver());
                return;
            }

            //formDemoPage.submitForm();
            jsClick(getDriver().findElement(formDemoPage.submit));
            compareString(formDemoPage.getValidationMessage("Name"),"Please fill out this field.");
            formDemoPage.fillInputField("Name","Test");
            //formDemoPage.submitForm();
            jsClick(getDriver().findElement(formDemoPage.submit));
            compareString(formDemoPage.getValidationMessage("Email"),"Please fill out this field.");
            formDemoPage.fillInputField("Email","Test");
            compareString(formDemoPage.getValidationMessage("Email"),"Please include an '@' in the email address. 'Test' is missing an '@'.");
            formDemoPage.fillInputField("Email","Test@test.com");
            formDemoPage.fillInputField("Password","Test@test.com");
            formDemoPage.fillInputField("Company","Test");
            formDemoPage.fillInputField("Website","Testest.com");
            formDemoPage.fillInputField("City","Test");
            formDemoPage.fillInputField("Address 1","Test");
            formDemoPage.fillInputField("Address 2","Test");
            formDemoPage.fillInputField("State","Test");
            formDemoPage.fillInputField("Zip code","12312");
            formDemoPage.selectDropdownOption("Country","United States");

            //formDemoPage.submitForm();
            jsClick(getDriver().findElement(formDemoPage.submit));

            compareString(formDemoPage.getSuccessMessage(),"Thanks for contacting us, we will get back to you shortly.");
        } catch (Exception e) {
            markStatus("failed", "Test encountered an exception: " + e.getMessage(), getDriver());
        }
    }

    public static void main(String[] args) {
        try {
            Test3 test = new Test3();
            setupLambdaTest();
//            setupLocalDriver();
            test.test3();
            tearDown();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}