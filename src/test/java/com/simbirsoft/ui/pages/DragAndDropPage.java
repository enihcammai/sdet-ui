package com.simbirsoft.ui.pages;

import com.simbirsoft.ui.utils.PropertyService;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class DragAndDropPage extends BasePage {

    private static final String DRAG_AND_DROP_PAGE_URL = "http://way2automation.com/way2auto_jquery/droppable.php";

    public DragAndDropPage(WebDriver driver){
        super(driver);
    }

    @FindBy(css = "iframe[src='droppable/default.html")
    public WebElement iframeDroppableDefault;

    @FindBy(id = "draggable")
    public WebElement draggableElement;

    @FindBy(id = "droppable")
    public WebElement droppableElement;

    @FindBy(css = "#droppable > p")
    public WebElement droppableText;


    @Step("Открыть страницу Drag'n'Drop")
    public DragAndDropPage openPage(){
        driver.get(DRAG_AND_DROP_PAGE_URL);
        return this;
    }

    @Step("Перенести элемент в зону назначения")
    public void dragAndDrop(){
        actions.dragAndDrop(draggableElement, droppableElement).build().perform();
    }
}