package com.simbirsoft.ui.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.ArrayList;
import java.util.List;

public class MockLoginPage extends BasePage {

    private static final String MOCK_PAGE_URL = "https://www.way2automation.com/angularjs-protractor/registeration/#/login";

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

    @FindBy(xpath = "/html/body/div[1]/div/div/div/form/ng-form/div[1]/div/div/input")
    private WebElement descriptionField;

    @FindBy(css = "input[name= 'formly_2_input_username_0'].ng-valid-required.ng-invalid-maxlength")
    private WebElement descriptionFieldMaxLengthError;

    @FindBy(xpath = "//input[@id='formly_2_input_username_0' and contains(@class, 'ng-valid-required') and contains(@class, 'ng-invalid-minlength')]")
    private WebElement descriptionFieldMinLengthError;

    @FindBy(css = "input[name='formly_2_input_username_0'].ng-invalid-required.ng-valid-minlength")
    private WebElement descriptionRequiredFieldError;

    @FindBy(css = "div.form-group.has-error")
    private WebElement descriptionFieldError;

    @FindBy(css = "button.btn")
    private WebElement loginBtn;

    @FindBy(xpath = "//div[contains(text(), 'Username or password is incorrect')]")
    private WebElement errorAlert;


    public boolean isTargetPage() {
        return wait.until(ExpectedConditions.urlToBe("https://www.way2automation.com/angularjs-protractor/registeration/#/"));
    }

    public MockLoginPage fillUsername(String username) {
        return (MockLoginPage) sendKeys(usernameField, username);
    }

    public MockLoginPage fillPassword(String password) {
        return (MockLoginPage) sendKeys(passwordField, password);
    }

    public MockLoginPage fillDescription(String description) {
        click(descriptionField);
        descriptionField.sendKeys("1");
        descriptionField.clear();
        return (MockLoginPage) sendKeys(descriptionField, description);
    }

    public MockLoginPage clickLoginButton() {
        return (MockLoginPage) click(loginBtn);
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

    public String getErrorMessageText() {
        List<String> errors = new ArrayList<>();

        if (isUsernameErrorMessageVisible()) {
            errors.add(usernameFieldError.getText());
        }
        if (isPasswordErrorMessageVisible()) {
            errors.add(passwordFieldError.getText());
        }

        if (isDescriptionErrorBehaviorVisible()) {
            errors.add("Entered value has an error");
        }

        if (!loginBtn.isEnabled()) {
            return String.join("; ", errors);
        }

        return null;
    }

}
