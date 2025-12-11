package com.simbirsoft.ui.tests;

import com.simbirsoft.ui.pages.RegistrationPage;
import com.simbirsoft.ui.utils.TestDataProvider;
import io.qameta.allure.*;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

@Epic("Сайт Way2Automation")
@Feature("Форма регистрации")
public class RegistrationTest extends BaseTest {

    private RegistrationPage buildRegPage(){
        return new RegistrationPage(driver);
    }

    @Test(priority = 1, description = "UI-04: Успешная проверка работы формы регистрации")
    @Story("Открытие формы")
    @Severity(SeverityLevel.CRITICAL)
    public void testRegistrationFormFunctionality() {
        assertTrue(buildRegPage().openRegistrationForm()
                .testFormWithData("Vladislav", "test@example.com")
                .isVerificationFormVisible(), "Форма подтверждения не открылась");
    }


    @Test(priority = 2, description = "UI-05: Успешная проверка валидности данных формы регистрации",
            dataProvider = "registrationTestData", dataProviderClass = TestDataProvider.class)
    @Story("Регистрация с валидными учетными данными")
    @Severity(SeverityLevel.CRITICAL)
    public void testRegistrationFormFieldsValidity(String fullName, String email) {
        assertTrue(buildRegPage()
                .openRegistrationForm()
                .testFormWithData(fullName, email)
                .isVerificationFormVisible(), "Форма подтверждения не открылась");
    }

    @Test(priority = 5, description = "Проверка текста сообщений об ошибках",
            dataProvider = "errorValidationData", dataProviderClass = TestDataProvider.class)
    @Story("Получение предусмотренных ошибок")
    @Severity(SeverityLevel.NORMAL)
    public void testErrorMessages(String fullName, String email, String expectedNameError, String expectedEmailError) {
        String errorMessageText = buildRegPage()
                .openRegistrationForm()
                .testFormWithData(fullName,email)
                .getErrorMessageText();

        assertTrue(errorMessageText.contains(expectedNameError)
                && errorMessageText.contains((expectedEmailError)));
    }

}
