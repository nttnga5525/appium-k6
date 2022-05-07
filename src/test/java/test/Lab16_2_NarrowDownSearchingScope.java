package test;

import driver.DriverFactory;
import driver.Platforms;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.touch.offset.PointOption;
import org.openqa.selenium.Dimension;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Lab16_2_NarrowDownSearchingScope {
    public static void main(String[] args) {
        // Get appium driver
        AppiumDriver<MobileElement> driver = DriverFactory.getDriver(Platforms.android);
        try{

            //Get mobile window size
            Dimension windowSize = driver.manage().window().getSize();
            int screenHeight = windowSize.getHeight();
            int screenWidth = windowSize.getWidth();

            // Calculate Touch points
            int xStartPoint = 50 * screenWidth/100;
            int xEndPoint = 50 * screenWidth/100;

            int yStartPoint = 0 * screenHeight/100;
            int yEndPoint = 50 * screenHeight/100;

            // Convert coordinates -> PointOption
            PointOption startPoint = new PointOption().withCoordinates(xStartPoint, yStartPoint);
            PointOption endPoint = new PointOption().withCoordinates(xEndPoint, yEndPoint);

            // Using TouchAction to swipe
            TouchAction touchAction = new TouchAction(driver);
            touchAction
                    .press(startPoint)
                    .moveTo(endPoint)
                    .release()
                    .perform();

            Map<String, String> notifications = new HashMap<String, String>();
            List<MobileElement> notificationElems = driver.findElements(MobileBy.id("android:id/status_bar_latest_event_content"));
            for (MobileElement notificationElem : notificationElems) {
                MobileElement titleElem = notificationElem.findElement(MobileBy.id("android:id/app_name_text"));
                String titleText = titleElem.getText();
                MobileElement contentElem = notificationElem.findElement(MobileBy.id("android:id/title"));
                String contentText = contentElem.getText();
                notifications.put(titleText, contentText);

            }

            if (notifications.keySet().isEmpty()){
                throw new RuntimeException("[ERR] there is no notification to test!");
            } else {
                for (String title : notifications.keySet()) {
                    System.out.println("Title: " + title);
                    System.out.println("Content: " + notifications.get(title));
                }
            }

            // Swipe up to dismiss notification bar
            touchAction
                    .longPress(endPoint)
                    .moveTo(startPoint)
                    .release()
                    .perform();

            Thread.sleep(3000);

        }catch (Exception e){
            e.printStackTrace();
        }finally {
            // Quit appium server
            driver.quit();
        }
    }
}
