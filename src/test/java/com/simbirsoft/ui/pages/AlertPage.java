package com.simbirsoft.ui.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class AlertPage extends BasePage{

    private static final String ALERT_PAGE_URL = " http://way2automation.com/way2auto_jquery/alert.php";

    public AlertPage(WebDriver driver){
        super(driver);
    }

    @FindBy(css = "a[href='#example-1-tab-2']")
    public WebElement inputAlertTab;

    @FindBy(css = "iframe[src='alert/input-alert.html")
    public WebElement iframeInputAlert;

    @FindBy(css = "button[onclick='myFunction()']")
    public WebElement inputBoxButton;

    @FindBy(css = "p[id='demo']")
    public WebElement greetingsLabel;

    @Step("Открыть страницу алерта")
    public AlertPage openPage(){
        driver.get(ALERT_PAGE_URL);
        return this;
    }

    @Step("Перейти на вкладку Input Alert")
    public AlertPage clickInputAlertTab(){
        inputAlertTab.click();
        return this;
    }

    @Step("Нажать кнопку открытия alert")
    public AlertPage clickInputBoxButton(){
        driver.switchTo().frame(iframeInputAlert);
        inputBoxButton.click();
        return this;
    }

    @Step("Ввести и подтвердить текст в alert")
    public String inputTextIntoAlertField(String inputText){
        driver.switchTo().alert().sendKeys(inputText);
        driver.switchTo().alert().accept();
        return inputText;
    }

    @Step("Получить текст приветствия после заполнения alert")
    public String getGreetingsText(){
        return greetingsLabel.getText();
    }
}
