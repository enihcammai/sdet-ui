package com.simbirsoft.ui.tests;

import com.simbirsoft.ui.pages.HomePage;
import org.testng.annotations.Test;
import static org.testng.Assert.*;

public class HomePageTests extends BaseTest {

    @Test(priority = 2, description = "UI-01: Успешная проверка загрузки и работы главной страницы")
    public void testHomePageLoading() {
        HomePage homePage = new HomePage(driver);

        String pageTitle = driver.getTitle();
        assertTrue(pageTitle.contains("Way2Automation"),
                "Заголовок страницы не содержит 'Way2Automation'. Фактический: " + pageTitle);

        assertTrue(homePage.isHomePageLoaded(),
                "Главная страница не загрузилась корректно");

        homePage.scrollToFooter();

        assertTrue(homePage.isFooterVisible(),
                "Футер не отображается после скролла");

        String currentUrl = driver.getCurrentUrl();
        assertTrue(currentUrl.contains("way2automation.com"),
                "URL страницы не соответствует ожидаемому. Фактический: " + currentUrl);
    }
}
