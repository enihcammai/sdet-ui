package com.simbirsoft.ui.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class BasicAuthPage extends BasePage{

    private static final String BASIC_AUTH_PAGE_URL = "https://www.httpwatch.com/httpgallery/authentication/#showExample10";
    private final String USERNAME = "httpwatch";
    private final String PASSWORD = "httpwatch";

    public BasicAuthPage(WebDriver driver){
        super(driver);
    }

    @FindBy(css = "input[class='button2 buttonspace']")
    public WebElement displayImageButton;

    @FindBy(css = "img")
    public WebElement authenticatedImage;

    @Step("Открыть страницу аутентификации")
    public BasicAuthPage openPage(){
        driver.get(BASIC_AUTH_PAGE_URL);
        return this;
    }

    @Step("Нажать кнопку Display Image")
    public BasicAuthPage clickDisplayImageButton(){
        displayImageButton.click();
        return this;
    }

    @Step("Ввод учетных данных через URL")
    public BasicAuthPage enterAuthData(){
        driver.get("http://"+ USERNAME + ":" + PASSWORD + "@www.httpwatch.com/httpgallery/authentication/authenticatedimage/default.aspx");
        return this;
    }

    @Step("Проверить отображение картинки успешной авторизации")
    public boolean isAuthenticatedImageDisplayed(){
        return isElementVisible(authenticatedImage);
    }
}
