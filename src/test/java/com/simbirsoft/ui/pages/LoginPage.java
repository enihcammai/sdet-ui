package com.simbirsoft.ui.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends BasePage {

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(linkText = "Sign Up")
    private WebElement signUpLink;

    @FindBy(className = "m-auto-xs")
    private WebElement loginForm;


    public void clickSignUp() {
        click(signUpLink);
    }

    public boolean isLoginFormVisible() {
        return isElementVisible(loginForm);
    }

    public RegistrationPage clickSignUpAndGoToRegistration() {
        clickSignUp();
        return new RegistrationPage(driver);
    }
}
