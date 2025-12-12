package com.simbirsoft.ui.tests;

import com.simbirsoft.ui.pages.HomePage;
import io.qameta.allure.*;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

@Epic("Сайт Way2Automation")
@Feature("Главная страница")
public class HomePageTests extends BaseTest {

    private HomePage buildHomePage() {
        return new HomePage(driver);
    }

    @Test(priority = 1, description = "UI-01: Успешная проверка загрузки и работы главной страницы")
    @Story("Загрузка главной страницы")
    @Severity(SeverityLevel.BLOCKER)
    public void testHomePageLoading() {
        assertTrue(buildHomePage()
                        .scrollToFooter()
                        .isFooterVisible(),
                "Футер не отображается после скролла");
    }

    @Test(priority = 2, description = "UI-02: Успешная проверка работы кнопки возврата в начало страницы")
    @Story("Работа кнопки возврата в начало")
    @Severity(SeverityLevel.MINOR)
    public void testScrollToTopButton() {
        assertTrue(buildHomePage().closeWidget()
                        .scrollToFooter()
                        .clickScrollToTopButton()
                        .isLogoVisible(),
                "Страница не вернулась в начало после нажатия кнопки");
    }

    @Test(priority = 3, description = "UI-03: Успешная проверка открытия формы регистрации")
    @Story("Переход на форму регистрации")
    @Severity(SeverityLevel.CRITICAL)
    public void testRegistrationFormOpening() {
        assertTrue(buildHomePage()
                .navigateToLogin()
                .navigateToRegistration()
                .isRegistrationFormVisible(),
                "Форма регистрации не открылась.");
    }

    // ПАДАЮЩИЕ ТЕСТЫ
    @Test(priority = 4, description = "Проверка несуществующего элемента")
    @Story("Проверка несуществующего элемента")
    @Severity(SeverityLevel.NORMAL)
    public void testNonExistentElement() {
        assertTrue(buildHomePage().isNonExistingElementVisible());
    }

    @Test(priority = 5, description = "Проверка неверного URL")
    @Story("Неправильный адрес")
    @Severity(SeverityLevel.NORMAL)
    public void testWrongUrl() {
        assertEquals(driver.getCurrentUrl(), "https://google.com/");
    }
}
