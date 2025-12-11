package com.simbirsoft.ui.tests;

import com.simbirsoft.ui.pages.HomePage;
import io.qameta.allure.*;
import org.testng.annotations.Test;

import static org.testng.Assert.assertTrue;


@Epic("Сайт Way2Automation")
@Feature("Переходы по ресурсу")
public class NavigationTest extends BaseTest {

    @Test(priority = 1, description = "UI-06: Успешная проверка переходов по ресурсу")
    @Story("Загрузка страницы DevOps")
    @Severity(SeverityLevel.CRITICAL)
    public void testNavigationToDevOps() {
        HomePage homePage = new HomePage(driver);
        assertTrue(homePage
                .navigateToCourses()
                .clickDevOpsSubMenuOption()
                .isDevOpsPageLoaded(), "Страница DevOps не загрузилась.");
    }

    @Test(priority = 1, description = "UI-07: Успешная проверка перехода на другой ресурс с исходного")
    @Story("Переход на сторонний ресурс")
    @Severity(SeverityLevel.CRITICAL)
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
