package com.simbirsoft.ui.tests;

import com.simbirsoft.ui.pages.MockLoginPage;
import com.simbirsoft.ui.utils.TestDataProvider;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class MockLoginTest extends BaseTest {

    private MockLoginPage buildMockLoginPage() {
        return new MockLoginPage(driver);
    }

    @Test(priority = 1, description = "UI-08: Успешная проверка работы запасной формы регистрации",
            dataProvider = "mockLoginPageValidData", dataProviderClass = TestDataProvider.class)
    public void testValidData(String username, String password, String description) {
        assertTrue(buildMockLoginPage()
                .testFormWithData(username, password, description)
                .clickLoginButton()
                .isTargetPage());
    }

    @Test(priority = 2, description = "UI-09: Успешная проверка работы запасной формы регистрации",
            dataProvider = "mockLoginPageAlertMessageInvalidData", dataProviderClass = TestDataProvider.class)
    public void testLoginFormAlert(String username, String password, String description) {
        assertTrue(buildMockLoginPage()
                .testFormWithData(username, password, description)
                .clickLoginButton()
                .isErrorAlertVisible());
    }

    @Test(priority = 3, description = "UI-10: Проверка сообщений об ошибках",
            dataProvider = "mockLoginPageErrorMessage", dataProviderClass = TestDataProvider.class)
    public void testInvalidData(String username,
                                String password,
                                String description,
                                String expectedUsernameError,
                                String expectedPasswordError,
                                String expectedDescriptionError) {
        MockLoginPage loginPage = buildMockLoginPage().testFormWithData(username, password, description);
        assertEquals(expectedUsernameError, loginPage.getUsernameErrorMessage());
        assertEquals(expectedPasswordError, loginPage.getPasswordErrorMessage());
        assertEquals(expectedDescriptionError, loginPage.getDescriptionErrorMessage());
    }

    @Test(description = "Проверка функциональности JSExecutor")
    public void testJSExecutorFunctionality(){
        assertTrue(buildMockLoginPage()
                .clickUsernameField()
                .isUsernameFieldFocused());

        buildMockLoginPage().removeFocusFromInput();
        assertTrue(buildMockLoginPage().verifyFocusRemovedFromUsernameField());
        assertTrue(buildMockLoginPage().hasVerticalScroll());
    }
}
