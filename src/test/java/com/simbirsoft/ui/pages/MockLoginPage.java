package com.simbirsoft.ui.pages;

import com.simbirsoft.ui.tests.MockLoginTest;
import io.qameta.allure.Step;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class MockLoginPage extends BasePage {

    private static final String MOCK_PAGE_TARGET_URL = "https://www.way2automation.com/angularjs-protractor/registeration/#/";

    public MockLoginPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(id = "username")
    private WebElement usernameField;

    @FindBy(css = "div[ng-messages= 'form.username.$error'] div.ng-scope")
    private WebElement usernameFieldError;

    @FindBy(id = "password")
    private WebElement passwordField;

    @FindBy(css = "div[ng-messages= 'form.password.$error'] div.ng-scope")
    private WebElement passwordFieldError;

    @FindBy(css = "ng-form[name='formly_1'] input")
    private WebElement descriptionField;

    @FindBy(css = "ng-form[name='formly_1'] div.has-error")
    private WebElement descriptionFieldError;

    @FindBy(css = "button.btn")
    private WebElement loginBtn;

    @FindBy(css = "div.alert-danger")
    private WebElement errorAlert;

    public boolean isTargetPage() {
        return wait.until(ExpectedConditions.urlToBe(MOCK_PAGE_TARGET_URL));
    }

    public MockLoginPage fillUsername(String username) {
        return (MockLoginPage) sendKeys(usernameField, username);
    }

    public MockLoginPage fillPassword(String password) {
        return (MockLoginPage) sendKeys(passwordField, password);
    }

    public MockLoginPage fillDescription(String description) {
        descriptionField.sendKeys("1");
        descriptionField.clear();
        return (MockLoginPage) sendKeys(descriptionField, description);
    }

    public MockLoginPage clickLoginButton() {
        return (MockLoginPage) click(loginBtn);
    }

    public MockLoginPage clickUsernameField(){
        return (MockLoginPage) click(usernameField);
    }

    public MockLoginPage testFormWithData(String username, String password, String description) {
        return fillUsername(username).fillPassword(password).fillDescription(description);
    }

    public boolean isErrorAlertVisible() {
        return isElementVisible(errorAlert);
    }

    public boolean isUsernameErrorMessageVisible() {
        return isElementVisible(usernameFieldError);
    }

    public boolean isPasswordErrorMessageVisible() {
        return isElementVisible(passwordFieldError);
    }

    public boolean isDescriptionErrorBehaviorVisible() {
        return isElementVisible(descriptionFieldError);
    }

    public String getUsernameErrorMessage() {
        if (isUsernameErrorMessageVisible()) {
            return usernameFieldError.getText();
        }
        return "";
    }

    public String getPasswordErrorMessage() {
        if (isPasswordErrorMessageVisible()) {
            return passwordFieldError.getText();
        }
        return "";
    }

    public String getDescriptionErrorMessage() {
        if (isDescriptionErrorBehaviorVisible()) {
            return "Entered value has an error";
        }
        return "";
    }

    @Step("Убрать фокус из активного элемента с помощью JavaScript")
    public MockLoginPage removeFocusFromInput() {
        return (MockLoginPage) jsExecutor.executeScript(
                "if(document.activeElement) { " +
                        "   document.activeElement.blur(); " +
                        "}"
        );
    }

    @Step("Проверить наличие вертикального скролла на странице")
    public boolean hasVerticalScroll() {
        return (Boolean) jsExecutor.executeScript(
                "return document.documentElement.scrollHeight > document.documentElement.offsetHeight;"
        );
    }

    @Step("Убедиться, что фокус убран с поля ввода имени пользователя")
    public boolean verifyFocusRemovedFromUsernameField(){
        return !isElementFocused(usernameField);
    }

    @Step("Проверить, что поле ввода имени пользователя находится в фокусе")
    public boolean isUsernameFieldFocused(){
        return isElementFocused(usernameField);
    }

    @Step("Проверить, что элемент находится в фокусе")
    public boolean isElementFocused(WebElement element) {
        return (Boolean) jsExecutor.executeScript(
                "return arguments[0] === document.activeElement;",
                element
        );
    }

}
