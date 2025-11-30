package com.simbirsoft.ui.utils;

import org.testng.annotations.DataProvider;

public class TestDataProvider {

    @DataProvider(name = "registrationTestData")
    public Object[][] getRegistrationTestData() {
        return new Object[][] {
                {1, "Ввод единичного символа в поле имени", "A", "test@example.com"},
                {2, "Ввод 100 символов в поле имени",
                        "VladislavVladislavVladislavVladislavVladislavVladislavVladislavVladislavVladislavVladislavV",
                        "test@example.com"},
                {3, "Ввод специальных символов в поле имени", "Vlad-slav@#", "test@example.com"},
                {4, "Ввод имени вместе с цифрами", "Vladislav12345", "test@example.com"},
                {5, "Ввод email с точкой", "Vladislav", "test.test@example.com"},
                {6, "Ввод email с различными символами", "Vladislav", "test.test.with+symbol@example.com"}
        };
    }
}