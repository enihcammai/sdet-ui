package com.simbirsoft.ui.tests;

import com.simbirsoft.ui.helpers.CookieHelper;
import com.simbirsoft.ui.pages.SqlExAuthorization;
import org.testng.annotations.Test;

import static org.testng.Assert.assertTrue;

public class SqlExAuthorizationTest extends BaseTest{

    private SqlExAuthorization buildSqlExPage(){
        return new SqlExAuthorization(driver);
    }

    @Test
    public void testAuthorizationAndSaveCookies() throws Exception {
        assertTrue(buildSqlExPage().openPage()
                .fillLogin("vladiusx2")
                .fillPassword("rec!D4EBvClX7")
                .clickLoginBtn()
                .isUserLoggedIn());

        CookieHelper.saveCookies(driver);
    }

    @Test
    public void testUseCookies() throws Exception {
        buildSqlExPage().openPage();
        driver.manage().deleteAllCookies();
        assertTrue(CookieHelper.loadCookies(driver));

        driver.navigate().refresh();
        assertTrue(buildSqlExPage().isUserLoggedIn());
    }
}
