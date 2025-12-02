package com.simbirsoft.ui.tests;

import com.simbirsoft.ui.pages.CoursesPage;
import com.simbirsoft.ui.pages.HomePage;
import org.testng.annotations.Test;

import static org.testng.Assert.assertTrue;

public class NavigationTest extends BaseTest {

    @Test(priority = 1, description = "UI-06: Успешная проверка переходов по ресурсу")
    public void testNavigationToDevOps() {
        HomePage homePage = new HomePage(driver);
        CoursesPage coursesPage = new CoursesPage(driver);

        assertTrue(homePage.isHomePageLoaded());
        coursesPage.clickDevOpsSubMenuOption();

        assertTrue(coursesPage.isDevOpsPageLoaded(),
                "Страница DevOps не загрузилась. " +
                        "URL: " + driver.getCurrentUrl() + ", " +
                        "Заголовок: " + driver.getTitle());
    }

    @Test(invocationCount = 3, priority = 1, description = "UI-07: Успешная проверка перехода на другой ресурс с исходного")
    public void testNavigationToExternalResource() {
        HomePage homePage = new HomePage(driver);
        CoursesPage coursesPage = new CoursesPage(driver);

        assertTrue(homePage.isHomePageLoaded());
        coursesPage.clickDevOpsSubMenuOption();

        assertTrue(coursesPage.isDevOpsPageLoaded());
        coursesPage.clickFreeAccessToCoursesLink();
        coursesPage.switchToNewTab();

        assertTrue(coursesPage.isSeleniumTutorialUrl(),
                "Not on selenium-tutorial.com. Actual URL: " + driver.getCurrentUrl());

    }
}
