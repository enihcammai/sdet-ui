package com.simbirsoft.ui.tests;

import com.simbirsoft.ui.pages.BasicAuthPage;
import org.testng.annotations.Test;

import static org.testng.Assert.assertTrue;

public class BasicAuthTest extends BaseTest{

    private BasicAuthPage buildBasicAuthPage(){
        return new BasicAuthPage(driver);
    }

    @Test
    public void testBasicAuth(){
        assertTrue(buildBasicAuthPage().openPage()
                .clickDisplayImageButton()
                .enterAuthData()
                .isAuthenticatedImageDisplayed());
    }
}
