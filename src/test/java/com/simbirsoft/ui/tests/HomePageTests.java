package com.simbirsoft.ui.tests;

import com.simbirsoft.ui.pages.HomePage;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class HomePageTests extends BaseTest {

    @Test(priority = 1, description = "UI-01: Успешная проверка загрузки и работы главной страницы")
    public void testHomePageLoading() {
        HomePage homePage = new HomePage(driver);
        assertTrue(homePage.isHomePageLoaded());
        assertTrue(homePage.scrollToFooter().isFooterVisible(), "Футер не отображается после скролла");
    }

    @Test(priority = 2, description = "UI-02: Успешная проверка работы кнопки возврата в начало страницы")
    public void testScrollToTopButton() {
        HomePage homePage = new HomePage(driver);
        homePage.scrollToFooter()
                .clickScrollToTopButton();

        assertTrue(homePage.isLogoVisible(), "Страница не вернулась в начало после нажатия кнопки");
    }

    @Test(priority = 3, description = "UI-03: Успешная проверка открытия формы регистрации")
    public void testRegistrationFormOpening() {
        HomePage homePage = new HomePage(driver);
        assertTrue(homePage
                .navigateToLogin()
                .navigateToRegistration()
                .isRegistrationFormVisible(), "Форма регистрации не открылась.");

    }

}
