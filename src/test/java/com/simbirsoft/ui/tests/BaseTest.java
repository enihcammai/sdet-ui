package com.simbirsoft.ui.tests;

import com.simbirsoft.ui.utils.DriverFactory;
import com.simbirsoft.ui.utils.PropertyService;
import com.simbirsoft.ui.utils.WebDriverManager;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import java.time.Duration;

public class BaseTest {
    protected WebDriver driver;
    protected WebDriverWait wait;

    @BeforeMethod
    @Parameters({"browser", "headless", "useGrid", "gridUrl"})
    @Step("Запуск")
    public void setUp(@Optional("edge") String browser,
                      @Optional("false") boolean headless,
                      @Optional("false") boolean useGrid,
                      @Optional("http://localhost:4444") String gridUrl) {
        driver = DriverFactory.createDriver(browser, headless, useGrid, gridUrl);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        if(!useGrid){
            driver.manage().window().maximize();
        }
        driver.get(PropertyService.getInstance().getProperty("base_url"));
    }

    @AfterMethod
    @Step("Выход")
    public void tearDown(ITestResult result) {
        WebDriver driver = WebDriverManager.getDriver();
        if (driver != null) {
            driver.quit();
        }
        WebDriverManager.removeDriver();
    }
}
