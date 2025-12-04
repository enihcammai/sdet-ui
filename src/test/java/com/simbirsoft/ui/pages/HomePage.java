package com.simbirsoft.ui.pages;

import com.simbirsoft.ui.utils.PropertyService;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

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


    public boolean isFooterVisible() {
        return isElementVisible(footerContainer);
    }

    public HomePage scrollToFooter(){
        return (HomePage) scrollToElement(footerContainer);
    }

    public HomePage clickAllCourses() {
        return (HomePage) click(allCoursesLink);
    }

    public HomePage clickScrollToTopButton(){
        actions.moveByOffset(200,100).build().perform();
        if (isScrollToTopButtonVisible()) {
             return (HomePage) click(scrollTopBtn);
        }
        return this;
    }

    public boolean isScrollToTopButtonVisible(){
        return isElementVisible(scrollTopBtn);
    }

    public boolean isHomePageLoaded() {
        return driver.getTitle().contains("Way2Automation") &&
                isElementVisible(memberLoginLink);
    }

    public HomePage clickMemberLogin() {
        return (HomePage) click(memberLoginLink);
    }


    public boolean isLogoVisible() {
        return isElementVisible(siteLogo);
    }

    public LoginPage navigateToLogin() {
        clickMemberLogin();
        return new LoginPage(driver);
    }

    public CoursesPage navigateToCourses() {
        clickAllCourses();
        return new CoursesPage(driver);
    }
}
