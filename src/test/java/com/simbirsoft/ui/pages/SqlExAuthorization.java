package com.simbirsoft.ui.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class SqlExAuthorization extends BasePage {

    private static final String SQL_EX_URL = "https://www.sql-ex.ru/";

    public SqlExAuthorization(WebDriver driver) {
        super(driver);
    }

    @FindBy(css = "input[name='login']")
    private WebElement loginField;

    @FindBy(css = "input[name='psw']")
    private WebElement passwordField;

    @FindBy(css = "input[name='subm1']")
    private WebElement loginBtn;

    @FindBy(css = "a[href='/logout.php']")
    private WebElement logoutBtn;


    public SqlExAuthorization openPage() {
        driver.get(SQL_EX_URL);
        return this;
    }

    public SqlExAuthorization fillLogin(String login) {
        return (SqlExAuthorization) sendKeys(loginField, login);
    }

    public SqlExAuthorization fillPassword(String password) {
        return (SqlExAuthorization) sendKeys(passwordField, password);
    }

    public SqlExAuthorization clickLoginBtn(){
        return (SqlExAuthorization) click(loginBtn);
    }

    public boolean isUserLoggedIn(){
        return isElementVisible(logoutBtn);
    }

}
