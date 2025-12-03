package com.simbirsoft.ui.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.ArrayList;
import java.util.List;

public class RegistrationPage extends BasePage {

    public RegistrationPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(id = "name")
    private WebElement fullNameField;

    @FindBy(id = "email")
    private WebElement emailField;

    @FindBy(id = "allowMarketingEmails")
    private WebElement agreeCheckbox;

    @FindBy(id = "otp-login-btn")
    private WebElement sendCodeButton;

    @FindBy(xpath = "//*[@id=\"otp-signup\"]/div/div[1]")
    private WebElement verificationForm;

    @FindBy(id = "otp-signup")
    private WebElement registrationForm;

    @FindBy(css = "[data-testid='name-input-error']")
    private WebElement nameErrorMessage;

    @FindBy(css = "data-testid='email-input-error']")
    private WebElement emailErrorMessage;


    public RegistrationPage fillFullName(String fullName) {
        return (RegistrationPage) sendKeys(fullNameField, fullName);
    }

    public RegistrationPage openRegistrationForm(){
        driver.get("https://sso.teachable.com/secure/673/identity/sign_up/otp");
        return this;
    }

    public RegistrationPage fillEmail(String email) {
       return (RegistrationPage) sendKeys(emailField, email);
    }

    public RegistrationPage fillRegistrationForm(String fullName, String email) {
        return fillFullName(fullName)
                .fillEmail(email);
    }

    public RegistrationPage setAgreeCheckbox() {
        return (RegistrationPage) click(agreeCheckbox);
    }

    public RegistrationPage clickSendCode() {
        return (RegistrationPage) click(sendCodeButton);
    }

    public boolean isVerificationFormVisible() {
        return isElementVisible(verificationForm);
    }

    public boolean isRegistrationFormVisible() {
        return isElementVisible(registrationForm);
    }

    public boolean isNameErrorMessageVisible() {
        return isElementVisible(nameErrorMessage);
    }

    public boolean isEmailErrorMessageVisible() {
        return isElementVisible(emailErrorMessage);
    }

    public String getErrorMessageText() {
        List<String> errors = new ArrayList<>();

        if (isNameErrorMessageVisible()) {
            errors.add(nameErrorMessage.getText());
        }
        if (isEmailErrorMessageVisible()) {
            errors.add(emailErrorMessage.getText());
        }

        return errors.isEmpty() ? null : String.join("; ", errors);
    }

    public RegistrationPage testFormWithData(String fullName, String email) {
        return fillRegistrationForm(fullName, email)
                .setAgreeCheckbox()
                .clickSendCode();
    }

}
