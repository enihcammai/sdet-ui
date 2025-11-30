package com.simbirsoft.ui.tests;

import com.simbirsoft.ui.pages.HomePage;
import com.simbirsoft.ui.pages.LoginPage;
import com.simbirsoft.ui.pages.RegistrationPage;
import com.simbirsoft.ui.utils.TestDataProvider;
import org.testng.annotations.Test;
import static org.testng.Assert.*;

public class RegistrationTest extends BaseTest{

    @Test(priority = 1, description = "UI-04: Успешная проверка работы формы регистрации")
    public void testRegistrationFormFunctionality() {
        HomePage homePage = new HomePage(driver);
        LoginPage loginPage = new LoginPage(driver);

        homePage.clickMemberLogin();
        RegistrationPage registrationPage = loginPage.clickSignUpAndGoToRegistration();

        registrationPage.fillRegistrationForm("Vladislav", "test@example.com");
        registrationPage.setAgreeCheckbox(true);
        registrationPage.clickSendCode();

        assertTrue(registrationPage.isVerificationFormVisible(),
                "Форма подтверждения не открылась");
    }


    @Test(priority = 2, description = "UI-05: Успешная проверка валидности полей формы регистрации",
            dataProvider = "registrationTestData", dataProviderClass = TestDataProvider.class)
    public void testRegistrationFormFieldsValidity(int testNumber, String testCase, String fullName, String email) {
        System.out.println("\nЗапускаем тест " + testNumber + ": " + testCase);
        System.out.println("Вводимое имя: " + fullName);
        System.out.println("Вводимый email: " + email);

        HomePage homePage = new HomePage(driver);
        LoginPage loginPage = new LoginPage(driver);
        RegistrationPage registrationPage;

        try {
            homePage.clickMemberLogin();
            Thread.sleep(1000);
            registrationPage = loginPage.clickSignUpAndGoToRegistration();
            Thread.sleep(1000);
            RegistrationPage.TestResult result = registrationPage.testFormWithData(fullName, email);

            assertTrue(result.isAccepted(),
                    String.format("Форма не приняла валидные данные. Тест: %s, Имя: %s, Email: %s, Ошибка: %s",
                            testCase, fullName, email, result.getErrorMessage()));

            System.out.println("Тест " + testNumber + " пройден - данные приняты формой");

        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            fail("Тестирование прервано: " + e.getMessage());
        } catch (Exception e) {
            fail("Тест " + testNumber + " не пройден: " + e.getMessage());
        }
    }

}
