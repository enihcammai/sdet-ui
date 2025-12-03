package com.simbirsoft.ui.tests;

import com.simbirsoft.ui.pages.HomePage;
import org.testng.annotations.Test;

import static org.testng.Assert.assertTrue;

public class NavigationTest extends BaseTest {

    @Test(priority = 1, description = "UI-06: Успешная проверка переходов по ресурсу")
    public void testNavigationToDevOps() {
        HomePage homePage = new HomePage(driver);
        assertTrue(homePage
                .navigateToCourses()
                .clickDevOpsSubMenuOption()
                .isDevOpsPageLoaded(), "Страница DevOps не загрузилась.");
    }

    @Test(priority = 1, description = "UI-07: Успешная проверка перехода на другой ресурс с исходного")
    public void testNavigationToExternalResource() {
        HomePage homePage = new HomePage(driver);
        homePage.navigateToCourses()
                .clickDevOpsSubMenuOption()
                .clickFreeAccessToCoursesLink()
                .switchToNewTab();

        assertTrue(driver.getCurrentUrl().contains("selenium-tutorial.com"),
                "Не произошел переход на selenium-tutorial.com");
    }
}
