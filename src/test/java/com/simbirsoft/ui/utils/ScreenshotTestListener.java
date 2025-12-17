package com.simbirsoft.ui.utils;

import io.qameta.allure.Allure;
import org.openqa.selenium.WebDriver;
import org.testng.ITestListener;
import org.testng.ITestResult;
import ru.yandex.qatools.ashot.AShot;
import ru.yandex.qatools.ashot.Screenshot;
import ru.yandex.qatools.ashot.shooting.ShootingStrategies;

import javax.imageio.ImageIO;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;

public class ScreenshotTestListener implements ITestListener {

    @Override
    public void onTestFailure(ITestResult result) {
        System.out.println("Тест упал: " + result.getName());

        WebDriver driver = WebDriverManager.getDriver();
        if (driver != null) {
            ScreenshotTestListener.takeScreenshot(driver,
                    "Скриншот при падении теста: " + result.getName());
        }

        if (result.getThrowable() != null) {
            Allure.addAttachment("Ошибка теста",
                    "text/plain",
                    result.getThrowable().getMessage());
        }
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        System.out.println("Тест пропущен: " + result.getName());

        WebDriver driver = WebDriverManager.getDriver();
        if (driver != null) {
            ScreenshotTestListener.takeScreenshot(driver,
                    "Скриншот при пропуске теста: " + result.getName());
        }
    }


    public static void takeScreenshot(WebDriver driver, String screenshotName) {
        try {
            System.out.println("Создание скриншота: " + screenshotName);

            Screenshot screenshot = new AShot()
                    .shootingStrategy(ShootingStrategies.viewportPasting(1000))
                    .takeScreenshot(driver);

            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ImageIO.write(screenshot.getImage(), "PNG", baos);
            byte[] screenshotBytes = baos.toByteArray();

            Allure.addAttachment(screenshotName,
                    "image/png",
                    new ByteArrayInputStream(screenshotBytes),
                    "png");

        } catch (Exception e) {
            System.err.println("Не удалось сделать скриншот: " + e.getMessage());
        }
    }
}
