package com.simbirsoft.ui.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class CoursesPage extends BasePage {

    private static final String COURSES_DEVOPS_URL = "https://www.way2automation.com/devops-training/";

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


    @Step("Нажатие на пункт меню 'DevOps' в подменю курсов")
    public CoursesPage clickDevOpsSubMenuOption() {
       return (CoursesPage) clickSubMenuOption(allCoursesLink, devOpsLink);
    }


    @Step("Проверка загрузки страницы DevOps курсов")
    public boolean isDevOpsPageLoaded() {
        return driver.getCurrentUrl().toLowerCase().contains("devops");
    }


    @Step("Нажатие на ссылку 'Free access to courses...'")
    public CoursesPage clickFreeAccessToCoursesLink() {
        return (CoursesPage) scrollToElement(freeAccessToCoursesLink)
                .click(freeAccessToCoursesLink);
    }

    @Step("Переключение на новую вкладку браузера")
    public void switchToNewTab() {
        String originalWindow = driver.getWindowHandle();
        for (String windowHandle : driver.getWindowHandles()) {
            if (!originalWindow.equals(windowHandle)) {
                driver.switchTo().window(windowHandle);
                break;
            }
        }
    }

}
