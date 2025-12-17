package com.simbirsoft.ui.tests;

import com.simbirsoft.ui.helpers.CookieHelper;
import com.simbirsoft.ui.pages.SqlExAuthorization;
import org.testng.annotations.Test;
import java.io.File;

import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

public class SqlExAuthorizationTest extends BaseTest{

    private SqlExAuthorization buildSqlExPage(){
        return new SqlExAuthorization(driver);
    }

    @Test(priority = 1)
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

    @Test(priority = 2)
    public void testUseCookies(){
        assertTrue(new File("cookies.ser").exists());

        buildSqlExPage().openPage();
        driver.manage().deleteAllCookies();
        assertTrue(CookieHelper.loadCookies(driver));

        driver.navigate().refresh();
        assertTrue(buildSqlExPage().isUserLoggedIn());
    }
}
