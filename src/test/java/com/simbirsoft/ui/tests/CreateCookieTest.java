package com.simbirsoft.ui.tests;

import com.simbirsoft.ui.helpers.CookieHelper;
import com.simbirsoft.ui.pages.SqlExAuthorization;
import org.testng.annotations.Test;
import java.io.File;

import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

public class CreateCookieTest extends BaseTest{

    private SqlExAuthorization buildSqlExPage(){
        return new SqlExAuthorization(driver);
    }

    @Test
    public void testAuthorizationAndSaveCookies(){
        CookieHelper.deleteCookiesFile();
        assertFalse(CookieHelper.loadCookies(driver));
        assertTrue(buildSqlExPage().openPage()
                .fillLogin("vladiusx2")
                .fillPassword("rec!D4EBvClX7")
                .clickLoginBtn()
                .isUserLoggedIn());

        CookieHelper.saveCookies(driver);
        assertTrue(new File("cookies.ser").exists());
    }
}
