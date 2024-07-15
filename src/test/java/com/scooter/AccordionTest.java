package com.scooter;

import com.scooter.factory.ChromeDriverProvider;
import com.scooter.factory.FirefoxDriverProvider;
import com.scooter.factory.WebDriverProvider;
import com.util.Constants;
import locators.BlockFAQ;
import locators.CookiePopup;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class AccordionTest {
    private final String url = Constants.URL;


    // Подключение chrome,firefox
    private static String browserList = "chrome,firefox";

    // Список доступных браузеров
    private static List<WebDriverProvider> browserProviders = new ArrayList<>();

    static {
        // Инициализация списка браузеров
        String browsers = System.getProperty("browsers", browserList);
        for (String browser : browsers.split(",")) {
            switch (browser.trim().toLowerCase()) {
                case "chrome":
                    browserProviders.add(new ChromeDriverProvider());
                    break;
                case "firefox":
                    browserProviders.add(new FirefoxDriverProvider());
                    break;
                default:
                    throw new IllegalArgumentException("Неподдерживаемый браузер: " + browser);
            }
        }

    }

    private WebDriver driver;

    @BeforeEach
    public void setup() {
        // Берем первый браузер из списка 0 = chrome 1 = firefox
        WebDriverProvider provider = browserProviders.get(0);
        driver = provider.createDriver();
    }

    @AfterEach
    public void teardown() {
        if (driver != null) {
            driver.quit();
        }
    }

    private void handleCookiePopup() {
        CookiePopup cookiePopup = new CookiePopup(driver);
        List<WebElement> cookiePopups = cookiePopup.getCookiePopup();
        if (!cookiePopups.isEmpty()) {
            cookiePopup.clickCookieOkButton();
        }
    }


    // Универсальный метод для тестирования отдельного аккордеона
    private void testIndividualAccordion(By buttonQuestion, By textAnswer, String errorMessage) {
        driver.manage().window().maximize();
        driver.get(url);
        handleCookiePopup();

        WebElement button = driver.findElement(buttonQuestion);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", button);

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(50));
        wait.until(ExpectedConditions.elementToBeClickable(button)).click();

        WebElement panel = wait.until(ExpectedConditions.visibilityOfElementLocated(textAnswer));
        assertTrue(panel.isDisplayed(), errorMessage);
    }

    @Test
    public void testAccordionPanel0() {
        BlockFAQ blockFAQ = new BlockFAQ();
        testIndividualAccordion(blockFAQ.getButtonQuestion0(), blockFAQ.getTextAnswer0(), "Панель 1 не открылась");
    }

    @Test
    public void testAccordionPanel1() {
        BlockFAQ blockFAQ = new BlockFAQ();
        testIndividualAccordion(blockFAQ.getButtonQuestion1(), blockFAQ.getTextAnswer1(), "Панель 2 не открылась");
    }

    @Test
    public void testAccordionPanel2() {
        BlockFAQ blockFAQ = new BlockFAQ();
        testIndividualAccordion(blockFAQ.getButtonQuestion2(), blockFAQ.getTextAnswer2(), "Панель 3 не открылась");
    }

    @Test
    public void testAccordionPanel3() {
        BlockFAQ blockFAQ = new BlockFAQ();
        testIndividualAccordion(blockFAQ.getButtonQuestion3(), blockFAQ.getTextAnswer3(), "Панель 4 не открылась");
    }

    @Test
    public void testAccordionPanel4() {
        BlockFAQ blockFAQ = new BlockFAQ();
        testIndividualAccordion(blockFAQ.getButtonQuestion4(), blockFAQ.getTextAnswer4(), "Панель 5 не открылась");
    }

    @Test
    public void testAccordionPanel5() {
        BlockFAQ blockFAQ = new BlockFAQ();
        testIndividualAccordion(blockFAQ.getButtonQuestion5(), blockFAQ.getTextAnswer5(), "Панель 6 не открылась");
    }

    @Test
    public void testAccordionPanel6() {
        BlockFAQ blockFAQ = new BlockFAQ();
        testIndividualAccordion(blockFAQ.getButtonQuestion6(), blockFAQ.getTextAnswer6(), "Панель 7 не открылась");
    }

    @Test
    public void testAccordionPanel7() {
        BlockFAQ blockFAQ = new BlockFAQ();
        testIndividualAccordion(blockFAQ.getButtonQuestion7(), blockFAQ.getTextAnswer7(), "Панель 8 не открылась");
    }
}

