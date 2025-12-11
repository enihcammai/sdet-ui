package com.simbirsoft.ui.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends BasePage {

    private static final String LOGIN_PAGE_URL = "https://sso.teachable.com/secure/673/identity/login/otp";

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(linkText = "Sign Up")
    private WebElement signUpLink;

    @FindBy(className = "m-auto-xs")
    private WebElement loginForm;


    @Step("Нажатие кнопки перехода к форме регистрации")
    public LoginPage clickSignUp() {
        return (LoginPage) click(signUpLink);
    }

    @Step("Переход к форме регистрации")
    public RegistrationPage navigateToRegistration() {
        clickSignUp();
        return new RegistrationPage(driver);
    }
}
