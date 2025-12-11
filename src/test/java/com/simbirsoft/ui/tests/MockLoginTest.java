package com.simbirsoft.ui.tests;

import com.simbirsoft.ui.pages.MockLoginPage;
import com.simbirsoft.ui.utils.TestDataProvider;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class MockLoginTest extends BaseTest {

    private MockLoginPage buildMockLoginPage() {
        return new MockLoginPage(driver);
    }

    @Test(description = "UI-08: Успешная проверка работы запасной формы регистрации",
            dataProvider = "mockLoginPageValidData", dataProviderClass = TestDataProvider.class)
    public void testValidData(String username, String password, String description) {
        assertTrue(buildMockLoginPage()
                .testFormWithData(username, password, description)
                .clickLoginButton()
                .isTargetPage());
    }


    @Test(description = "UI-09: Успешная проверка работы запасной формы регистрации",
            dataProvider = "mockLoginPageAlertMessageInvalidData", dataProviderClass = TestDataProvider.class)
    public void testLoginFormAlert(String username, String password, String description) {

        assertTrue(buildMockLoginPage()
                .testFormWithData(username, password, description)
                .clickLoginButton()
                .isErrorAlertVisible());
    }


    @Test(description = "UI-10: Проверка сообщений об ошибках",
            dataProvider = "mockLoginPageErrorMessage", dataProviderClass = TestDataProvider.class)
    public void testInvalidData(String username, String password, String description,
                                String expectedUsernameError, String expectedPasswordError,
                                String expectedDescriptionError) {

        String errorMessageText = buildMockLoginPage()
                .testFormWithData(username, password, description)
                .getErrorMessageText();


        assertTrue(errorMessageText.contains(expectedUsernameError)
                && errorMessageText.contains((expectedPasswordError))
                && errorMessageText.contains((expectedDescriptionError)));
    }

}
