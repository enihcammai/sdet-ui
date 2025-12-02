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


    public void fillFullName(String fullName) {
        sendKeys(fullNameField, fullName);
    }

    public void fillEmail(String email) {
        sendKeys(emailField, email);
    }

    public void fillRegistrationForm(String fullName, String email) {
        fillFullName(fullName);
        fillEmail(email);
    }

    public void setAgreeCheckbox() {
        click(agreeCheckbox);
    }

    public void clickSendCode() {
        click(sendCodeButton);
    }

    public boolean isVerificationFormVisible() {
        return isElementVisible(verificationForm);
    }

    public boolean isRegistrationFormVisible() {
        return isElementVisible(registrationForm);
    }

    public boolean isSendCodeButtonEnabled() {
        return sendCodeButton.isEnabled();
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

        return errors.isEmpty() ? "" : String.join("; ", errors);
    }

    public boolean canSubmitForm() {
        return isSendCodeButtonEnabled() && !isNameErrorMessageVisible() && !isEmailErrorMessageVisible();
    }

    public void fillFormWithValidation(String fullName, String email) {
        fillFullName(fullName);
        fillEmail(email);
        setAgreeCheckbox();
    }

    public TestResult testFormWithData(String fullName, String email) {
        fillFormWithValidation(fullName, email);

        boolean canSubmit = canSubmitForm();
        clickSendCode();
        String error = getErrorMessageText();

        return new TestResult(fullName, email, canSubmit, error);
    }

    public static class TestResult {
        private final String fullName;
        private final String email;
        private final boolean accepted;
        private final String errorMessage;

        public TestResult(String fullName, String email, boolean accepted, String errorMessage) {
            this.fullName = fullName;
            this.email = email;
            this.accepted = accepted;
            this.errorMessage = errorMessage;
        }

        public String getErrorMessage() {
            return errorMessage;
        }

        @Override
        public String toString() {
            return String.format("Имя: %s, Email: %s, Данные приняты: %s, Ошибка: %s",
                    fullName, email, accepted, errorMessage);
        }
    }
}
