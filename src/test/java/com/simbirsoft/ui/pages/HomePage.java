package com.simbirsoft.ui.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePage extends BasePage {

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


    public boolean isFooterVisible() {
        scrollToElement(footerContainer);
        return isElementVisible(footerContainer);
    }


    public void clickScrollToTopButton(){
        actions.moveByOffset(200,100).build().perform();
        if (isScrollToTopButtonVisible()) {
            click(scrollTopBtn);
            try {
                Thread.sleep(1000);
            } catch(InterruptedException ignore){

            }
        }
    }

    public boolean isScrollToTopButtonVisible(){
        return isElementVisible(scrollTopBtn);
    }

    public boolean isHomePageLoaded() {
        return driver.getTitle().contains("Way2Automation") &&
                isElementVisible(memberLoginLink);
    }

    public void clickMemberLogin() {
        click(memberLoginLink);
    }

    public boolean isMemberLoginButtonVisible() {
        return isElementVisible(memberLoginLink);
    }

    public boolean isLogoVisible() {
        return isElementVisible(siteLogo);
    }
}
