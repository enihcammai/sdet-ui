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

    @FindBy(partialLinkText = "Free lifetime access to 20+")
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

}
