package ru.netology.qa;

import io.appium.java_client.android.AndroidDriver;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.remote.DesiredCapabilities;
import ru.netology.qa.screens.MainActivityPage;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

public class MyActivityTest {

    private AndroidDriver driver;
    private String textToSet = "Check";

    @BeforeEach
    public void setUp() throws MalformedURLException {
        DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
        desiredCapabilities.setCapability("platformName", "Android");
        desiredCapabilities.setCapability("appium:deviceName", "some name");
        desiredCapabilities.setCapability("appium:appPackage", "ru.netology.testing.uiautomator");
        desiredCapabilities.setCapability("appium:appActivity", "ru.netology.testing.uiautomator.MainActivity");

        URL remoteUrl = new URL("http://127.0.0.1:4723/wd/hub");

        driver = new AndroidDriver(remoteUrl, desiredCapabilities);
    }

    @Test
    public void emptyFieldTest() {
        MainActivityPage mainActivityPage = new MainActivityPage(driver);
        mainActivityPage.textToBeChanged.isDisplayed();
        mainActivityPage.buttonChange.click();
        mainActivityPage.anotherTextToBeChanged.isDisplayed();
        Assertions.assertEquals(mainActivityPage.textToBeChanged.getText(), mainActivityPage.anotherTextToBeChanged.getText());

    }

    @Test
    public void anotherActivityTest() {
        MainActivityPage mainActivityPage = new MainActivityPage(driver);
        mainActivityPage.userInput.click();
        mainActivityPage.userInput.sendKeys(textToSet);
        mainActivityPage.buttonActivity.click();
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
        mainActivityPage.textLine.isDisplayed();
        Assertions.assertEquals(textToSet,mainActivityPage.textLine.getText() );

    }
    @AfterEach
    public void tearDown() {
        driver.quit();
    }
}
