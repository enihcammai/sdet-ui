package com.simbirsoft.ui.tests;

import com.simbirsoft.ui.pages.DragAndDropPage;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class DragAndDropTest extends BaseTest{

    private DragAndDropPage buildDragAndDropPage(){
        return new DragAndDropPage(driver);
    }

    @Test
    public void testDragAndDrop(){
        driver.switchTo().frame(buildDragAndDropPage().openPage().iframeDroppableDefault);
        buildDragAndDropPage().dragAndDrop();
        assertEquals(buildDragAndDropPage().droppableText.getText(), "Dropped!");
    }
}
