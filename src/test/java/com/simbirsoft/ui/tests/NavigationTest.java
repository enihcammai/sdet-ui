package com.simbirsoft.ui.tests;

import com.simbirsoft.ui.pages.CoursesPage;
import com.simbirsoft.ui.pages.HomePage;
import org.testng.annotations.Test;
import static org.testng.Assert.assertTrue;
import static org.testng.Assert.fail;

public class NavigationTest extends BaseTest {

    @Test(priority = 1, description = "UI-06: Успешная проверка переходов по ресурсу")
    public void testNavigationToDevOps() {
        HomePage homePage = new HomePage(driver);
        CoursesPage coursesPage = new CoursesPage(driver);

        if(homePage.isHomePageLoaded()) {
            coursesPage.clickDevOpsSubMenuOption();
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }

        assertTrue(coursesPage.isDevOpsPageLoaded(),
                "Страница DevOps не загрузилась. " +
                        "URL: " + driver.getCurrentUrl() + ", " +
                        "Заголовок: " + driver.getTitle());

        System.out.println("UI-06 пройден: Успешно перешли на страницу DevOps");
        System.out.println("Текущий URL: " + driver.getCurrentUrl());
        System.out.println("Заголовок страницы: " + driver.getTitle());
    }

    @Test(priority = 1, description = "UI-07: Успешная проверка перехода на другой ресурс с исходного")
    public void testNavigationToExternalResource() {
        HomePage homePage = new HomePage(driver);
        CoursesPage coursesPage = new CoursesPage(driver);
        String originalWindow = driver.getWindowHandle();

        try {
            if(homePage.isHomePageLoaded()) {
                coursesPage.clickDevOpsSubMenuOption();
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }

            if(coursesPage.isDevOpsPageLoaded()) {
                coursesPage.clickFreeAccessToCoursesLink();
                coursesPage.switchToNewTab();
                Thread.sleep(3000);

                String newTabUrl = driver.getCurrentUrl();
                System.out.println("New tab URL: " + newTabUrl);

                assertTrue(coursesPage.isSeleniumTutorialUrl(),
                        "Not on selenium-tutorial.com. Actual URL: " + newTabUrl);
            }

            coursesPage.closeCurrentTabAndSwitchBack(originalWindow);

        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            fail("Test interrupted: " + e.getMessage());
        } catch (Exception e) {
            try {
                driver.switchTo().window(originalWindow);
            } catch (Exception ignored) {

            }
            fail("UI-07 failed: " + e.getMessage());
        }
    }
}
