package com.scooter;

import com.scooter.factory.ChromeDriverProvider;
import com.scooter.factory.FirefoxDriverProvider;
import com.scooter.factory.WebDriverProvider;
import com.util.Constants;
import locators.PageObject;
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
    private final String url = Constants.URL;
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

        List<WebElement> cookiePopups = driver.findElements(PageObject.cookieOkButton);

        if (!cookiePopups.isEmpty()) {
            WebElement cookiePopup = cookiePopups.get(0);
            WebElement acceptButton = cookiePopup.findElement(By.tagName("button"));
            acceptButton.click();
        }

    }

    private void testAccordion(WebDriver driver) {
        driver.manage().window().maximize();
        driver.get(url);

        handleCookiePopup(driver);


        WebElement button0 = driver.findElement(PageObject.buttonQuestion0);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", button0);

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(button0)).click();

        WebElement panel0 = wait.until(ExpectedConditions.visibilityOfElementLocated(PageObject.textAnswer0));
        assertTrue(panel0.isDisplayed(), "Панель 1 не открылась");

        // Нажимаем на 2 кнопку аккордеона и проверяем открытие второй панели
        WebElement button1 = driver.findElement(PageObject.buttonQuestion1);
        wait.until(ExpectedConditions.elementToBeClickable(button1)).click();

        WebElement panel1 = wait.until(ExpectedConditions.visibilityOfElementLocated(PageObject.textAnswer1));
        assertTrue(panel1.isDisplayed(), "Панель 2 не открылась");

        // Нажимаем на 3 кнопку аккордеона и проверяем открытие второй панели
        WebElement button2 = driver.findElement(PageObject.buttonQuestion2);
        wait.until(ExpectedConditions.elementToBeClickable(button2)).click();

        WebElement panel2 = wait.until(ExpectedConditions.visibilityOfElementLocated(PageObject.textAnswer2));
        assertTrue(panel2.isDisplayed(), "Панель 3 не открылась");

        // Нажимаем на 4 кнопку аккордеона и проверяем открытие второй панели
        WebElement button3 = driver.findElement(PageObject.buttonQuestion3);
        wait.until(ExpectedConditions.elementToBeClickable(button3)).click();

        WebElement panel3 = wait.until(ExpectedConditions.visibilityOfElementLocated(PageObject.textAnswer3));
        assertTrue(panel3.isDisplayed(), "Панель 4 не открылась");

        // Нажимаем на 5 кнопку аккордеона и проверяем открытие второй панели
        WebElement button4 = driver.findElement(PageObject.buttonQuestion4);
        wait.until(ExpectedConditions.elementToBeClickable(button4)).click();

        WebElement panel4 = wait.until(ExpectedConditions.visibilityOfElementLocated(PageObject.textAnswer4));
        assertTrue(panel4.isDisplayed(), "Панель 5 не открылась");

        // Нажимаем на 6 кнопку аккордеона и проверяем открытие второй панели
        WebElement button5 = driver.findElement(PageObject.buttonQuestion5);
        wait.until(ExpectedConditions.elementToBeClickable(button5)).click();

        WebElement panel5 = wait.until(ExpectedConditions.visibilityOfElementLocated(PageObject.textAnswer5));
        assertTrue(panel5.isDisplayed(), "Панель 6 не открылась");

        // Нажимаем на 7 кнопку аккордеона и проверяем открытие второй панели
        WebElement button6 = driver.findElement(PageObject.buttonQuestion6);
        wait.until(ExpectedConditions.elementToBeClickable(button6)).click();

        WebElement panel6 = wait.until(ExpectedConditions.visibilityOfElementLocated(PageObject.textAnswer6));
        assertTrue(panel6.isDisplayed(), "Панель 7 не открылась");

        // Нажимаем на 8 кнопку аккордеона и проверяем открытие второй панели
        WebElement button7 = driver.findElement(PageObject.buttonQuestion7);
        wait.until(ExpectedConditions.elementToBeClickable(button7)).click();

        WebElement panel7 = wait.until(ExpectedConditions.visibilityOfElementLocated(PageObject.textAnswer7));
        assertTrue(panel7.isDisplayed(), "Панель 8 не открылась");


    }

    @ParameterizedTest
    @MethodSource("browserProvider")
    @Tag("browser")
    public void testAccordionOnBrowsers(WebDriverProvider browserProvider) {
        driver = browserProvider.createDriver();
            testAccordion(driver);
    }

    @AfterEach
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
