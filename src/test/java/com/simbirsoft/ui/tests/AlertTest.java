package com.simbirsoft.ui.tests;

import com.simbirsoft.ui.pages.AlertPage;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class AlertTest extends BaseTest{

    private AlertPage buildAlertPage(){
        return new AlertPage(driver);
    }

    @Test
    public void testInputInAlert(){
        String inputText = buildAlertPage().openPage()
                .clickInputAlertTab()
                .clickInputBoxButton()
                .inputTextIntoAlertField("Vladislav");
        assertEquals(buildAlertPage().getGreetingsText(), "Hello " + inputText + "! How are you today?");
    }
}
