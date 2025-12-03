package com.simbirsoft.ui.tests;

import com.simbirsoft.ui.pages.RegistrationPage;
import com.simbirsoft.ui.utils.TestDataProvider;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class RegistrationTest extends BaseTest {

    private RegistrationPage buildRegPage(){
        return new RegistrationPage(driver);
    }

    @Test(priority = 1, description = "UI-04: Успешная проверка работы формы регистрации")
    public void testRegistrationFormFunctionality() {
        assertTrue(buildRegPage().openRegistrationForm()
                .testFormWithData("Vladislav", "test@example.com")
                .isVerificationFormVisible(), "Форма подтверждения не открылась");
    }


    @Test(priority = 2, description = "UI-05: Успешная проверка валидности данных формы регистрации",
            dataProvider = "registrationTestData", dataProviderClass = TestDataProvider.class)
    public void testRegistrationFormFieldsValidity(String fullName, String email) {
        assertTrue(buildRegPage()
                .openRegistrationForm()
                .testFormWithData(fullName, email)
                .isVerificationFormVisible(), "Форма подтверждения не открылась");
    }

    @Test(priority = 5, description = "Проверка текста сообщений об ошибках",
            dataProvider = "errorValidationData", dataProviderClass = TestDataProvider.class)
    public void testErrorMessages(String fullName, String email, String expectedNameError, String expectedEmailError) {
        String errorMessageText = buildRegPage()
                .openRegistrationForm()
                .testFormWithData(fullName,email)
                .getErrorMessageText();

        assertTrue(errorMessageText.contains(expectedNameError)
                && errorMessageText.contains((expectedEmailError)));
    }

}
