package com.simbirsoft.ui.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

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

    public void fillFullName(String fullName) {
        type(fullNameField, fullName);
    }

    public void fillEmail(String email) {
        type(emailField, email);
    }

    public void fillRegistrationForm(String fullName, String email) {
        fillFullName(fullName);
        fillEmail(email);
    }

    public void setAgreeCheckbox(boolean agree) {
        if (agree) {
            if (!agreeCheckbox.isSelected()) {
                click(agreeCheckbox);
            }
        } else {
            if (agreeCheckbox.isSelected()) {
                click(agreeCheckbox);
            }
        }
    }

    public void clickSendCode() {
        click(sendCodeButton);
    }

    public boolean isVerificationFormVisible() {
        try {
            Thread.sleep(1500); // Wait for form transition
            return isElementVisible(verificationForm);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            return false;
        }
    }

    public boolean isRegistrationFormVisible() {
        return isElementVisible(registrationForm);
    }

    public boolean areFormFieldsVisible() {
        return isElementVisible(fullNameField) &&
                isElementVisible(emailField) &&
                isElementVisible(agreeCheckbox);
    }

    public boolean isSendCodeButtonEnabled() {
        return sendCodeButton.isEnabled();
    }
}
