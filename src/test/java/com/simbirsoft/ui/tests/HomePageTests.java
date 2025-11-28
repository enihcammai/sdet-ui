package com.simbirsoft.ui.tests;

import com.simbirsoft.ui.pages.HomePage;
import com.simbirsoft.ui.pages.LoginPage;
import com.simbirsoft.ui.pages.RegistrationPage;
import org.testng.annotations.Test;
import static org.testng.Assert.*;

public class HomePageTests extends BaseTest {

    @Test(priority = 1, description = "UI-01: Успешная проверка загрузки и работы главной страницы")
    public void testHomePageLoading() {
        HomePage homePage = new HomePage(driver);
        homePage.scrollToFooter();
        String currentUrl = driver.getCurrentUrl();

        assertTrue(homePage.isHomePageLoaded() && currentUrl.contains("way2automation.com"),
                "URL страницы не соответствует ожидаемому. Фактический: " + currentUrl);
    }

    @Test(priority = 2, description = "UI-02: Успешная проверка работы кнопки возврата в начало страницы")
    public void testScrollToTopButton() {
        HomePage homePage = new HomePage(driver);
        if(homePage.isHomePageLoaded()) {
            homePage.scrollToFooter();

            homePage.clickScrollTopButton();

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }

        assertTrue(homePage.isLogoVisible(),"Страница не вернулась в начало после нажатия кнопки");
    }

    @Test(priority = 3, description = "UI-03: Успешная проверка открытия формы регистрации")
    public void testRegistrationFormOpening() {
        HomePage homePage = new HomePage(driver);
        LoginPage loginPage = new LoginPage(driver);
        RegistrationPage registrationPage = null;

        if(homePage.isMemberLoginButtonVisible()) {
            homePage.clickMemberLogin();
            if(loginPage.isLoginFormVisible()){
                 registrationPage = loginPage.clickSignUpAndGoToRegistration();
            }
        }

        if (registrationPage != null) {
            assertTrue(registrationPage.isRegistrationFormVisible(),
                    "Форма регистрации не открылась после нажатия на кнопку Sign Up");
        }

    }

}
