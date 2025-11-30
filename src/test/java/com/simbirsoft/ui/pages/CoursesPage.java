package com.simbirsoft.ui.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class CoursesPage extends BasePage {

    public CoursesPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(id = "menu-item-27580")
    private WebElement allCoursesLink;

    @FindBy(id = "menu-item-27592")
    private WebElement devOpsLink;

    @FindBy(className = "elementor-widget-container")
    private WebElement DepOpsHeading;

    @FindBy(xpath = "//*[@id=\"post-26269\"]/div/div/section[6]/div/div/div/section[2]/div/div/div/div/div/p[2]/a[1]")
    private WebElement freeAccessToCoursesLink;

    public void clickDevOpsSubMenuOption() {
        clickSubMenuOption(allCoursesLink, devOpsLink);
    }

    public boolean isDevOpsPageLoaded() {
        return driver.getCurrentUrl().toLowerCase().contains("devops");
    }

    public void clickFreeAccessToCoursesLink() {
        scrollToElement(freeAccessToCoursesLink);
        click(freeAccessToCoursesLink);
    }

    public void switchToNewTab() {
        String originalWindow = driver.getWindowHandle();

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        for (String windowHandle : driver.getWindowHandles()) {
            if (!originalWindow.equals(windowHandle)) {
                driver.switchTo().window(windowHandle);
                break;
            }
        }
    }

    public boolean isSeleniumTutorialUrl() {
        String currentUrl = driver.getCurrentUrl();
        return currentUrl.contains("selenium-tutorial.com");
    }

    public void closeCurrentTabAndSwitchBack(String originalWindow) {
        driver.close();
        driver.switchTo().window(originalWindow);
    }

}
