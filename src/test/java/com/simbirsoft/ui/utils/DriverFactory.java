package com.simbirsoft.ui.utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.ie.InternetExplorerOptions;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public class DriverFactory {
    public static WebDriver createLocalDriver(String browser, boolean headless) {
        switch (browser.toLowerCase()) {
            case "chrome":
                return createChromeDriver(headless);
            case "firefox":
                return createFirefoxDriver(headless);
            case "edge":
                return createEdgeDriver(headless);
            case "ie":
                return createIEDriver();
            default:
                throw new IllegalArgumentException("Unsupported browser: " + browser);
        }
    }

    public static WebDriver createRemoteDriver(String browser, String gridUrl, boolean headless) {
        try {
            URL url = new URL(gridUrl);

            switch (browser.toLowerCase()) {
                case "chrome":
                    return new RemoteWebDriver(url, getChromeOptions(headless));
                case "firefox":
                    return new RemoteWebDriver(url, getFirefoxOptions(headless));
                case "edge":
                    return new RemoteWebDriver(url, getEdgeOptions(headless));
                case "ie":
                    return new RemoteWebDriver(url, getIEOptions());
                default:
                    return new RemoteWebDriver(url, getChromeOptions(headless));
            }
        } catch (MalformedURLException e) {
            throw new RuntimeException("Invalid Grid URL: " + gridUrl, e);
        }
    }

    public static WebDriver createDriver(String browser, boolean headless,
                                         boolean useGrid, String gridUrl) {
        if (useGrid && gridUrl != null && !gridUrl.isEmpty()) {
            return createRemoteDriver(browser, gridUrl, headless);
        } else {
            return createLocalDriver(browser, headless);
        }
    }

    private static WebDriver createChromeDriver(boolean headless) {
        System.setProperty("webdriver.chrome.driver", getDriverPath("chrome"));
        return new ChromeDriver(getChromeOptions(headless));
    }

    private static ChromeOptions getChromeOptions(boolean headless) {
        ChromeOptions options = new ChromeOptions();

        if (headless) {
            options.addArguments("--headless=new");
        }
        options.addArguments("--start-maximized");
        options.addArguments("--disable-notifications");
        options.addArguments("--disable-infobars");
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--no-sandbox");
        options.addArguments("--remote-allow-origins=*");
        options.setExperimentalOption("excludeSwitches", new String[]{"enable-automation"});

        return options;
    }

    private static WebDriver createFirefoxDriver(boolean headless) {
        System.setProperty("webdriver.gecko.driver", getDriverPath("firefox"));
        return new FirefoxDriver(getFirefoxOptions(headless));
    }

    private static FirefoxOptions getFirefoxOptions(boolean headless) {
        FirefoxOptions options = new FirefoxOptions();

        if (headless) {
            options.addArguments("--headless");
        }
        options.addArguments("--start-maximized");
        options.addArguments("--disable-notifications");
        options.setCapability("acceptInsecureCerts", true);

        return options;
    }

    private static WebDriver createEdgeDriver(boolean headless) {
        System.setProperty("webdriver.edge.driver", getDriverPath("edge"));
        return new EdgeDriver(getEdgeOptions(headless));
    }

    private static EdgeOptions getEdgeOptions(boolean headless) {
        EdgeOptions options = new EdgeOptions();

        if (headless) {
            options.addArguments("--headless=new");
        }
        options.addArguments("--start-maximized");
        options.addArguments("--disable-notifications");
        options.addArguments("--disable-features=VizDisplayCompositor");
        options.addArguments("--remote-allow-origins=*");

        return options;
    }

    private static WebDriver createIEDriver() {
        System.setProperty("webdriver.ie.driver", getDriverPath("ie"));

        InternetExplorerOptions options = getIEOptions();
        return new InternetExplorerDriver(options);
    }

    private static InternetExplorerOptions getIEOptions() {
        InternetExplorerOptions options = new InternetExplorerOptions();
        options.setCapability("ignoreZoomSetting", true);
        options.setCapability("ignoreProtectedModeSettings", true);
        options.setCapability("requireWindowFocus", false);
        options.setCapability("enablePersistentHover", false);
        options.setCapability("nativeEvents", false);
        options.setCapability("ignoreProtectedModeSettings", true);
        options.setCapability("disable-popup-blocking", true);
        options.setCapability("enableElementCacheCleanup", true);
        options.setCapability("requireWindowFocus", true);
        options.setCapability("browserAttachTimeout", 15000);
        options.setCapability("ie.ensureCleanSession", true);
        options.setCapability("ie.usePerProcessProxy", true);

        return options;
    }

    private static String getDriverPath(String browser) {
        String driversDir = System.getProperty("user.dir") + "/drivers/";
        Map<String, String> drivers = new HashMap<>();
        drivers.put("chrome", driversDir + "chromedriver.exe");
        drivers.put("firefox", driversDir + "geckodriver.exe");
        drivers.put("edge", driversDir + "msedgedriver.exe");
        drivers.put("ie", driversDir + "IEDriverServer.exe");

        return drivers.get(browser);
    }
}
