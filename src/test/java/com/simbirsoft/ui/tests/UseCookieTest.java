package com.simbirsoft.ui.tests;

import com.simbirsoft.ui.helpers.CookieHelper;
import com.simbirsoft.ui.pages.SqlExAuthorization;
import org.testng.annotations.Test;
import java.io.File;

import static org.testng.Assert.assertTrue;

public class UseCookieTest extends BaseTest{

    private SqlExAuthorization buildSqlExPage(){
        return new SqlExAuthorization(driver);
    }

    @Test
    public void testUseCookies(){
        assertTrue(new File("cookies.ser").exists());

        buildSqlExPage().openPage();
        driver.manage().deleteAllCookies();
        assertTrue(CookieHelper.loadCookies(driver));

        driver.navigate().refresh();
        assertTrue(buildSqlExPage().isUserLoggedIn());
    }
}
