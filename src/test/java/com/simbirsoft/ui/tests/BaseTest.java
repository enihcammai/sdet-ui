package com.simbirsoft.ui.tests;

import com.simbirsoft.ui.utils.PropertyService;
import com.simbirsoft.ui.utils.ScreenshotUtil;
import io.qameta.allure.Allure;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import java.time.Duration;

public class BaseTest {
    protected WebDriver driver;
    protected WebDriverWait wait;

    @BeforeMethod
    @Step("Запуск")
    public void setup() {
        System.setProperty(PropertyService.getInstance().getProperty("driver_name"), PropertyService.getInstance().getProperty("driver_path"));

        driver = new EdgeDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.get(PropertyService.getInstance().getProperty("base_url"));
    }

    @AfterMethod
    @Step("Выход")
    public void tearDown(ITestResult result) {

        ScreenshotUtil.takeScreenshotOnFailure(driver, result);

        if (result.getStatus() == ITestResult.FAILURE) {
            String errorMessage = result.getThrowable().getMessage();
            Allure.addAttachment("Ошибка теста",
                    "text/plain",
                    errorMessage);
        }

        if (driver != null) {
            driver.quit();
        }
    }
}
