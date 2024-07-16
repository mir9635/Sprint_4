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
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

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

    private static Stream<Arguments> accordionPanelsProvider() {
        return Stream.of(
                Arguments.of(0, "Панель 1 не открылась"),
                Arguments.of(1, "Панель 2 не открылась"),
                Arguments.of(2, "Панель 3 не открылась"),
                Arguments.of(3, "Панель 4 не открылась"),
                Arguments.of(4, "Панель 5 не открылась"),
                Arguments.of(5, "Панель 6 не открылась"),
                Arguments.of(6, "Панель 7 не открылась"),
                Arguments.of(7, "Панель 8 не открылась")
        );
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

    // Универсальный метод для тестирования отдельного аккордеона
    private void testIndividualAccordion(By buttonQuestion, By textAnswer, String errorMessage) {
        CookiePopup cookiePopup = new CookiePopup(driver);
        driver.manage().window().maximize();
        driver.get(url);
        cookiePopup.handleCookiePopup();

        WebElement button = driver.findElement(buttonQuestion);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", button);

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(50));
        wait.until(ExpectedConditions.elementToBeClickable(button)).click();

        WebElement panel = wait.until(ExpectedConditions.visibilityOfElementLocated(textAnswer));
        assertTrue(panel.isDisplayed(), errorMessage);
    }

    @ParameterizedTest
    @MethodSource("accordionPanelsProvider")
    public void testAccordionPanel(int panelIndex, String panelErrorMessage) {
        BlockFAQ blockFAQ = new BlockFAQ();
        By buttonQuestion = blockFAQ.getButtonQuestion(panelIndex);
        By textAnswer = blockFAQ.getTextAnswer(panelIndex);
        testIndividualAccordion(buttonQuestion, textAnswer, panelErrorMessage);
    }
}

