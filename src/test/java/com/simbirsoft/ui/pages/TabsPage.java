package com.simbirsoft.ui.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.Set;

public class TabsPage extends BasePage {

    private static final String TABS_PAGE_URL = "http://way2automation.com/way2auto_jquery/frames-and-windows.php";

    public TabsPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(css = "iframe[src='frames-windows/defult1.html")
    public WebElement iframeWindowsDefault;

    @FindBy(css = "a[target = '_blank']")
    public WebElement newBrowserTabBtn;

    @Step("Открыть страницу с вкладками")
    public TabsPage openTabPage() {
        driver.get(TABS_PAGE_URL);
        return this;
    }

    @Step("Нажать на кнопку перехода на новую вкладку")
    public TabsPage clickOpenNewTabButton() {
        click(newBrowserTabBtn);
        return this;
    }

    @Step("Сместить фокус на новую вкладку")
    public TabsPage switchToNewTab(String mainWindowHandle) {
        Set<String> allHandles = driver.getWindowHandles();
        for (String handle : allHandles) {
            if (!handle.equals(mainWindowHandle)) {
                driver.switchTo().window(handle);
                return this;
            }
        }
        return null;
    }

    @Step("Перейти на IFrame")
    public TabsPage switchToFrame() {
        driver.switchTo().frame(iframeWindowsDefault);
        return this;
    }
}
