package com.simbirsoft.ui.tests;

import com.simbirsoft.ui.pages.HomePage;
import com.simbirsoft.ui.pages.LoginPage;
import com.simbirsoft.ui.pages.RegistrationPage;
import com.simbirsoft.ui.utils.TestDataProvider;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class RegistrationTest extends BaseTest {

    @Test(priority = 1, description = "UI-04: Успешная проверка работы формы регистрации")
    public void testRegistrationFormFunctionality() {
        HomePage homePage = new HomePage(driver);
        LoginPage loginPage = new LoginPage(driver);

        homePage.clickMemberLogin();
        RegistrationPage registrationPage = loginPage.clickSignUpAndGoToRegistration();

        registrationPage.fillRegistrationForm("Vladislav", "test@example.com");
        registrationPage.setAgreeCheckbox();
        registrationPage.clickSendCode();

        assertTrue(registrationPage.isVerificationFormVisible(),
                "Форма подтверждения не открылась");
    }


    @Test(priority = 2, description = "UI-05: Успешная проверка валидности полей формы регистрации",
            dataProvider = "registrationTestData", dataProviderClass = TestDataProvider.class)
    public void testRegistrationFormFieldsValidity(String testCase, String fullName, String email) {
        HomePage homePage = new HomePage(driver);
        LoginPage loginPage = new LoginPage(driver);
        RegistrationPage registrationPage;

        homePage.clickMemberLogin();
        registrationPage = loginPage.clickSignUpAndGoToRegistration();
        RegistrationPage.TestResult result = registrationPage.testFormWithData(fullName, email);

        assertTrue(registrationPage.isVerificationFormVisible(),
                String.format("Форма не приняла валидные данные. Тест: %s, Имя: %s, Email: %s, Ошибка: %s",
                        testCase, fullName, email, result.getErrorMessage()));
    }

    @Test(priority = 5, description = "Проверка текста сообщений об ошибках",
            dataProvider = "errorValidationData", dataProviderClass = TestDataProvider.class)
    public void testErrorMessages(String testCase, String fullName, String email, String expectedError) {
        HomePage homePage = new HomePage(driver);
        LoginPage loginPage = new LoginPage(driver);

        homePage.clickMemberLogin();
        RegistrationPage registrationPage = loginPage.clickSignUpAndGoToRegistration();

        RegistrationPage.TestResult result = registrationPage.testFormWithData(fullName, email);

        assertNotNull(result.getErrorMessage(), "Должно быть сообщение об ошибке");
        assertTrue(result.getErrorMessage().contains(expectedError),
                String.format("Ожидалась ошибка: '%s', получено: '%s'. Тест: %s",
                        expectedError, result.getErrorMessage(), testCase));
    }

}
