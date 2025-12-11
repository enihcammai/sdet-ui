package com.simbirsoft.ui.utils;

import org.testng.annotations.DataProvider;

public class TestDataProvider {

    @DataProvider(name = "registrationTestData")
    public Object[][] getRegistrationTestData() {
        return new Object[][]{
                {"A", "test@example.com"},
                {"VladislavVladislavVladislavVladislavVladislavVladislavVladislavVladislavVladislavVladislavV",
                        "test@example.com"},
                {"Vlad-slav@#", "test@example.com"},
                {"Vladislav12345", "test@example.com"},
                {"Vladislav", "test.test@example.com"},
                {"Vladislav", "test.test.with+symbol@example.com"}
                };
    }


    @DataProvider(name = "errorValidationData")
    public Object[][] getErrorValidationData() {
        return new Object[][] {
                {"", "test@example.com", "Required field", ""},
                {"Vladislav", "", "", "Cannot be blank" },
                {"Vladislav", "invalid", "", "Invalid email"}
        };
    }

    @DataProvider(name = "mockLoginPageValidData")
    public Object[][] getMockLoginPageTestData() {
        return new Object[][] {
                {"angular", "password", "test"}
        };
    }

    @DataProvider(name = "mockLoginPageAlertMessageInvalidData")
    public Object[][] getInvalidData() {
        return new Object[][] {
                {"$$$$$", "password", "invalid"},
                {"android", "%%%%", "invalid"},
                {"Andre.3000", "Пароль", "invalid"},
                {"XD)))))", "....", "invalid"},
                {"Эпос", "pepsi", "invalid"}
        };
    }

    @DataProvider(name = "mockLoginPageErrorMessage")
    public Object[][] getErrorMessage() {
        return new Object[][] {
                {"", "password", "invalid", "You did not enter a username", "", ""},
                {"A", "password", "invalid", "Your username must be between 3 and 50 characters long", "", ""},
                {"An", "password", "invalid", "Your username must be between 3 and 50 characters long", "", ""},
                {" ".repeat(50), "password", "invalid", "You did not enter a username", "", ""},
                {"Andre", "", "invalid", "", "You did not enter a username", ""},
                {"Andre", "$", "invalid", "", "Your username must be between 3 and 100 characters long", ""},
                {"Andre", "$$", "invalid", "", "Your username must be between 3 and 100 characters long", ""},
                {"Andre", "password", "", "", "", "Entered value has an error"},
                {"Andre", "password", " ".repeat(10), "", "", "Entered value has an error"},
                {"Andre", "password", "1", "", "", "Entered value has an error"},
                {"Andre", "password", "12", "", "", "Entered value has an error"},
                {"Andre", "password", "1".repeat(51), "", "", "Entered value has an error"}
        };
    }

}