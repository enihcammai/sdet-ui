package com.simbirsoft.ui.tests;

import com.simbirsoft.ui.pages.HomePage;
import io.qameta.allure.*;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

@Epic("Сайт Way2Automation")
@Feature("Главная страница")
public class HomePageTests extends BaseTest {

    @Test(priority = 1, description = "UI-01: Успешная проверка загрузки и работы главной страницы")
    @Story("Загрузка главной страницы")
    @Severity(SeverityLevel.BLOCKER)
    public void testHomePageLoading() {
        HomePage homePage = new HomePage(driver);
        assertTrue(homePage.isHomePageLoaded());
        assertTrue(homePage.scrollToFooter().isFooterVisible(), "Футер не отображается после скролла");
    }

    @Test(priority = 2, description = "UI-02: Успешная проверка работы кнопки возврата в начало страницы")
    @Story("Работа кнопки возврата в начало")
    @Severity(SeverityLevel.MINOR)
    public void testScrollToTopButton() {
        HomePage homePage = new HomePage(driver);
        homePage.scrollToFooter()
                .clickScrollToTopButton();

        assertTrue(homePage.isLogoVisible(), "Страница не вернулась в начало после нажатия кнопки");
    }

    @Test(priority = 3, description = "UI-03: Успешная проверка открытия формы регистрации")
    @Story("Загрузка главной страницы")
    @Severity(SeverityLevel.CRITICAL)
    public void testRegistrationFormOpening() {
        HomePage homePage = new HomePage(driver);
        assertTrue(homePage
                .navigateToLogin()
                .navigateToRegistration()
                .isRegistrationFormVisible(), "Форма регистрации не открылась.");

    }

}
