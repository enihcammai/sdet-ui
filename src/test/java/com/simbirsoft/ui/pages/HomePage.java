package com.simbirsoft.ui.pages;

import com.simbirsoft.ui.utils.PropertyService;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class HomePage extends BasePage {

    private static final String HOME_PAGE_URL = PropertyService.getInstance().getProperty("base_url");

    public HomePage(WebDriver driver) {
        super(driver);
    }

    @FindBy(id = "menu-item-27625")
    private WebElement memberLoginLink;

    @FindBy(id = "ast-scroll-top")
    private WebElement scrollTopBtn;

    @FindBy(xpath = "//*[@id=\"page\"]/div[2]/div/section/div[2]")
    private WebElement footerContainer;

    @FindBy(className = "site-logo-img")
    private WebElement siteLogo;

    @FindBy(id = "menu-item-27580")
    private WebElement allCoursesLink;

    @FindBy(id = "elementor-popup-modal-26600")
    private WebElement popUpWidget;

    @FindBy(className = "eicon-close")
    private WebElement closeWidgetBtn;


    @Step("Видимость футера")
    public boolean isFooterVisible() {
        return isElementVisible(footerContainer);
    }

    @Step("Закрытие виджета")
    public HomePage closeWidget(){
        actions.moveByOffset(200, 100).build().perform();
        wait.until(ExpectedConditions.visibilityOf(popUpWidget));
        return (HomePage) click(closeWidgetBtn);
    }

    @Step("Скролл до футера")
    public HomePage scrollToFooter() {
        return (HomePage) scrollToElement(footerContainer);
    }

    @Step("Переход на страницу All courses")
    public HomePage clickAllCourses() {
        return (HomePage) click(allCoursesLink);
    }

    @Step("Нажатие кнопки перемещения к началу страницы")
    public HomePage clickScrollToTopButton() {
        actions.moveByOffset(200, 100).build().perform();
        if (isScrollToTopButtonVisible()) {
            return (HomePage) click(scrollTopBtn);
        }
        return this;
    }

    @Step("Видимость кнопки перемещения к началу страницы")
    public boolean isScrollToTopButtonVisible() {
        return isElementVisible(scrollTopBtn);
    }

    @Step("Статус загрузки страницы")
    public boolean isHomePageLoaded() {
        return driver.getTitle().contains("Way2Automation") &&
                isElementVisible(memberLoginLink);
    }

    @Step("Нажатие кнопки перехода к авторизации")
    public HomePage clickMemberLogin() {
        return (HomePage) click(memberLoginLink);
    }


    @Step("Видимость лого")
    public boolean isLogoVisible() {
        return isElementVisible(siteLogo);
    }

    @Step("Нажатие на кнопку перехода на страницу авторизации")
    public LoginPage navigateToLogin() {
        clickMemberLogin();
        return new LoginPage(driver);
    }

    @Step("Нажатие на кнопку перехода на страницу All courses")
    public CoursesPage navigateToCourses() {
        clickAllCourses();
        return new CoursesPage(driver);
    }
}
