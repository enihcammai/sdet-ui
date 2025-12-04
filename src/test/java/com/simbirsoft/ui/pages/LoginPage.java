package com.simbirsoft.ui.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends BasePage {

    private static final String LOGIN_PAGE_URL = "https://sso.teachable.com/secure/673/identity/login/otp";

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(className = "uni-ml-4 troubleShootBtn")
    private WebElement signUpLink;

    @FindBy(className = "m-auto-xs")
    private WebElement loginForm;


    public LoginPage clickSignUp() {
        return (LoginPage) click(signUpLink);
    }

    public RegistrationPage navigateToRegistration() {
        clickSignUp();
        return new RegistrationPage(driver);
    }
}
