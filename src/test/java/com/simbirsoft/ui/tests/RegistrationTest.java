package com.simbirsoft.ui.tests;

import com.simbirsoft.ui.pages.HomePage;
import com.simbirsoft.ui.pages.LoginPage;
import com.simbirsoft.ui.pages.RegistrationPage;
import org.testng.annotations.Test;
import static org.testng.Assert.assertTrue;

public class RegistrationTest extends BaseTest{

    @Test(priority = 1, description = "UI-04: Verify registration form functionality")
    public void testRegistrationFormFunctionality() {
        HomePage homePage = new HomePage(driver);
        LoginPage loginPage = new LoginPage(driver);

        homePage.clickMemberLogin();
        RegistrationPage registrationPage = loginPage.clickSignUpAndGoToRegistration();

        registrationPage.fillRegistrationForm("Vladislav", "test@example.com");
        registrationPage.setAgreeCheckbox(true);
        registrationPage.clickSendCode();

        assertTrue(registrationPage.isVerificationFormVisible(),
                "Verification form did not open after clicking Send Code");
    }
}
