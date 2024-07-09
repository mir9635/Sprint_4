package com.scooter;

import com.scooter.factory.ChromeDriverProvider;
import com.scooter.factory.FirefoxDriverProvider;
import com.scooter.factory.WebDriverProvider;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.parallel.Execution;
import org.junit.jupiter.api.parallel.ExecutionMode;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertTrue;

@Execution(ExecutionMode.CONCURRENT)
public class AccordionTest {
    private WebDriver driver;
    private final String url = "https://qa-scooter.praktikum-services.ru/";
    // Подключение chrome и firefox
    //private static String browserList = "chrome,firefox";
    //Подключение chrome
    private static String browserList = "chrome";

    // Список доступных браузеров
    private static List<WebDriverProvider> browserProviders = new ArrayList<>();

    static {
        // Инициализация списка браузеров, основываясь на системных свойствах
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

    @BeforeEach
    public void setUp(TestInfo testInfo) {
    }

    // Метод, который будет предоставлять браузеры для тестов
    static Stream<WebDriverProvider> browserProvider() {
        return browserProviders.stream();
    }

    private void handleCookiePopup(WebDriver driver) {
        try {
            WebElement cookiePopup = driver.findElement(By.className("App_CookieConsent__1yUIN"));
            WebElement acceptButton = cookiePopup.findElement(By.tagName("button"));
            acceptButton.click();
        } catch (NoSuchElementException e) {
            // Игнорируем, если элемент не найден
        }
    }

    private void testAccordion(WebDriver driver) {
        driver.manage().window().maximize();
        driver.get(url);

        handleCookiePopup(driver);

        for (int i = 0; i < 8; i++) {
            // Нажимаем на кнопку
            WebElement button = driver.findElement(By.id("accordion__heading-" + i));

            // Прокручиваем элемент в видимую область
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", button);

            // Используем явное ожидание для того, чтобы дождаться открытия панели
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            wait.until(ExpectedConditions.elementToBeClickable(button));
            button.click();

            WebElement panel = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("accordion__panel-" + i)));

            // Проверяем, что соответствующая панель открылась
            assertTrue(panel.isDisplayed(), "Панель " + i + " не открылась");
        }
    }

    @ParameterizedTest
    @MethodSource("browserProvider")
    @Tag("browser")
    //@DisabledOnBrowser("firefox")
    public void testAccordionOnBrowsers(WebDriverProvider browserProvider) {
        driver = browserProvider.createDriver();
        try {
            testAccordion(driver);
        } finally {
//            if (driver != null) {
//                driver.quit();
//            }
        }
    }

    @AfterEach
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
