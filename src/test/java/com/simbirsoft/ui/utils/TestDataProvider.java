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

}