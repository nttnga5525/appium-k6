package test;

import driver.DriverFactory;
import driver.Platforms;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;

import java.util.List;

public class Lab16_1_FormFilling {
    public static void main(String[] args) {
        // Get appium driver
        AppiumDriver<MobileElement> driver = DriverFactory.getDriver(Platforms.android);
        try{
            // Find and click on nav login button
            MobileElement navFormsBntElem = driver.findElement(MobileBy.AccessibilityId("Forms"));
            navFormsBntElem.click();

            // Find and fill text field
            MobileElement textInputFieldElem = driver.findElement(MobileBy.AccessibilityId("text-input"));
            textInputFieldElem.sendKeys("Hello! How are you?");

            // Find and get text field
            MobileElement textResultFieldElem = driver.findElement(MobileBy.AccessibilityId("input-text-result"));
            System.out.println("Result: "+ textResultFieldElem.getText());

            // Find and click switch button
            MobileElement switchBtnElem = driver.findElement(MobileBy.AccessibilityId("switch"));
            switchBtnElem.click();

            //Because the device is large, there is no switch part
            // Find and click dropdown
            MobileElement dropdownElem = driver.findElement(MobileBy
                    .AndroidUIAutomator("new UiSelector().textContains(\"Select an item\")"));
            dropdownElem.click();

            // Find all item of dropdown list
            List<MobileElement> dropdownListElem = driver.findElements(MobileBy.id("android:id/text1"));
            final int WEB_DRIVER_IO_IS_AWESOME = 1;
            dropdownListElem.get(WEB_DRIVER_IO_IS_AWESOME).click();

            // Find and click active button
            MobileElement activeBtnElem = driver.findElement(MobileBy.AccessibilityId("button-Active"));
            activeBtnElem.click();

            // Find and click OK button
            MobileElement okBtnElem = driver.findElement(MobileBy.id("android:id/button1"));
            okBtnElem.click();

            Thread.sleep(3000);

        }catch (Exception e){
            e.printStackTrace();
        }finally {
            // Quit appium server
            driver.quit();
        }
    }
}
