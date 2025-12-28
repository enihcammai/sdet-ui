package com.simbirsoft.ui.tests;

import com.simbirsoft.ui.pages.TabsPage;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class TabTest extends BaseTest {

    private TabsPage buildTabPage() {
        return new TabsPage(driver);
    }

    @Test
    public void testTabsSwitch() {
        buildTabPage().openTabPage()
                .switchToFrame()
                .clickOpenNewTabButton()
                .switchToNewTab(mainWindowHandle)
                .clickOpenNewTabButton();
        assertEquals(driver.getWindowHandles().size(), 3);
    }
}