package com.simbirsoft.ui.tests;

import com.simbirsoft.ui.helpers.CookieHelper;
import com.simbirsoft.ui.pages.SqlExAuthorization;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.File;

import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

public class SqlExAuthorizationTest extends BaseTest {

    private SqlExAuthorization buildSqlExPage() {
        return new SqlExAuthorization(driver);
    }

    @BeforeMethod(onlyForGroups = {"saveCookies"})
    public void beforeSaveCookiesGroup() {
        CookieHelper.deleteCookiesFile();
        driver.manage().deleteAllCookies();
        assertFalse(new File("cookies.ser").exists());
    }

    @Test(groups = "saveCookies")
    public void testAuthorizationAndSaveCookies() {
        assertFalse(new File("cookies.ser").exists());
            assertTrue(buildSqlExPage()
                    .openPage()
                    .fillLogin("vladiusx2")
                    .fillPassword("rec!D4EBvClX7")
                    .clickLoginBtn()
                    .isUserLoggedIn());

        CookieHelper.saveCookies(driver);
    }

    @BeforeMethod(onlyForGroups = {"usageCookies"})
    public void beforeUsageCookiesGroup() {
        File cookiesFile = new File("cookies.ser");
        if (!cookiesFile.exists()) {
            assertTrue(buildSqlExPage().openPage()
                    .fillLogin("vladiusx2")
                    .fillPassword("rec!D4EBvClX7")
                    .clickLoginBtn()
                    .isUserLoggedIn());
            CookieHelper.saveCookies(driver);
            assertTrue(cookiesFile.exists());
        }
    }

    @Test(groups = "usageCookies")
    public void testUseCookies() {
        buildSqlExPage().openPage();
        driver.manage().deleteAllCookies();
        assertTrue(CookieHelper.loadCookies(driver));
        driver.navigate().refresh();
        assertTrue(buildSqlExPage().isUserLoggedIn());
    }
}
