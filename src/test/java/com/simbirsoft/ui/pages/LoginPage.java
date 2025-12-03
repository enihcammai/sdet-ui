package com.simbirsoft.ui.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends BasePage {

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

    public boolean isLoginFormVisible() {
        return isElementVisible(loginForm);
    }

    public RegistrationPage navigateToRegistration() {
        clickSignUp();
        return new RegistrationPage(driver);
    }
}
