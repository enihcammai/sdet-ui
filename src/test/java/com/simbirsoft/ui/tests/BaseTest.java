package com.simbirsoft.ui.tests;

import com.simbirsoft.ui.utils.PropertyService;
import com.simbirsoft.ui.utils.WebDriverManager;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;

public class BaseTest {
    protected WebDriver driver;
    protected WebDriverWait wait;

    @BeforeMethod
    @Parameters({"headless", "useGrid", "gridUrl"})
    @Step("Запуск")
    public void setUp(@Optional("false") boolean headless,
                      @Optional("false") boolean useGrid,
                      @Optional("http://localhost:4444/wd/hub") String gridUrl) {

        if (useGrid) {
            setupRemoteDriver(gridUrl);
        } else {
            setupLocalDriver();
        }
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.get(PropertyService.getInstance().getProperty("base_url"));
    }

    @Step("Запуск удаленного драйвера")
    private void setupRemoteDriver(String gridUrl) {
        try {
            DesiredCapabilities capabilities = new DesiredCapabilities();
            capabilities.setBrowserName("MicrosoftEdge");
            EdgeOptions edgeOptions = new EdgeOptions();
            edgeOptions.addArguments("--headless");
            edgeOptions.merge(capabilities);
            driver = new RemoteWebDriver(new URL(gridUrl), capabilities);
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
    }

    @Step("Запуск локального драйвера")
    public void setupLocalDriver() {
        System.setProperty(PropertyService.getInstance().getProperty("driver_name"), PropertyService.getInstance().getProperty("driver_path"));

        driver = new EdgeDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.get(PropertyService.getInstance().getProperty("base_url"));

        WebDriverManager.setDriver(driver);
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
