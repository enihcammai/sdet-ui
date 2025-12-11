package com.simbirsoft.ui.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.ArrayList;
import java.util.List;

public class RegistrationPage extends BasePage {
    private static final String REG_PAGE_URL = "https://sso.teachable.com/secure/673/identity/sign_up/otp";

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

    @FindBy(id = "my-error-id")
    private WebElement emailErrorMessage;


    @Step("Ввод имени: '{fullName}'")
    public RegistrationPage fillFullName(String fullName) {
        return (RegistrationPage) sendKeys(fullNameField, fullName);
    }


    @Step("Открытие формы регистрации")
    public RegistrationPage openRegistrationForm(){
        driver.get(REG_PAGE_URL);
        return this;
    }

    @Step("Ввод email: '{email}'")
    public RegistrationPage fillEmail(String email) {
       return (RegistrationPage) sendKeys(emailField, email);
    }

    @Step("Заполнение формы: Имя='{fullName}', e-mail='{email}'")
    public RegistrationPage fillRegistrationForm(String fullName, String email) {
        return fillFullName(fullName)
                .fillEmail(email);
    }

    @Step("Установить флажок согласия с рассылкой")
    public RegistrationPage setAgreeCheckbox() {
        return (RegistrationPage) click(agreeCheckbox);
    }

    @Step("Нажатие кнопки 'Send code'")
    public RegistrationPage clickSendCode() {
        return (RegistrationPage) click(sendCodeButton);
    }

    @Step("Проверка отображение формы верификации")
    public boolean isVerificationFormVisible() {
        return isElementVisible(verificationForm);
    }

    @Step("Проверка отображение формы регистрации")
    public boolean isRegistrationFormVisible() {
        return isElementVisible(registrationForm);
    }

    @Step("Проверка отображение ошибки для поля 'Full Name'")
    public boolean isNameErrorMessageVisible() {
        return isElementVisible(nameErrorMessage);
    }

    @Step("Проверка отображение ошибки для поля 'Email'")
    public boolean isEmailErrorMessageVisible() {
        return isElementVisible(emailErrorMessage);
    }

    @Step("Получение текста всех сообщений об ошибках")
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

    @Step("Заполнение и отправка формы регистрации с данными: Имя='{fullName}', e-mail='{email}'")
    public RegistrationPage testFormWithData(String fullName, String email) {
        return fillRegistrationForm(fullName, email)
                .setAgreeCheckbox()
                .clickSendCode();
    }

}
