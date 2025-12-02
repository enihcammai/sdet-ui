package com.simbirsoft.ui.utils;

import org.testng.annotations.DataProvider;

public class TestDataProvider {

    @DataProvider(name = "registrationTestData")
    public Object[][] getRegistrationTestData() {
        return new Object[][]{
                {"Ввод единичного символа в поле имени", "A", "test@example.com"},
                {"Ввод 100 символов в поле имени",
                        "VladislavVladislavVladislavVladislavVladislavVladislavVladislavVladislavVladislavVladislavV",
                        "test@example.com"},
                {"Ввод специальных символов в поле имени", "Vlad-slav@#", "test@example.com"},
                {"Ввод имени вместе с цифрами", "Vladislav12345", "test@example.com"},
                {"Ввод email с точкой", "Vladislav", "test.test@example.com"},
                {"Ввод email с различными символами", "Vladislav", "test.test.with+symbol@example.com"}
                };
    }


    @DataProvider(name = "errorValidationData")
    public Object[][] getErrorValidationData() {
        return new Object[][] {
                {"Пустое имя", "", "test@example.com", "Required field"},
                {"Пустой email", "Vladislav", "", "Cannot be blank"},
                {"Неверный email формат", "Vladislav", "invalid", "Invalid email"}
        };
    }

}