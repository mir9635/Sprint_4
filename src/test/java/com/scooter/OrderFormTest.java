package com.scooter;

import com.scooter.factory.ChromeDriverProvider;
import com.scooter.factory.FirefoxDriverProvider;
import com.scooter.factory.WebDriverProvider;
import com.util.Constants;
import io.github.bonigarcia.wdm.WebDriverManager;
import locators.FormObject;
import locators.PageObject;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.TestInfo;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class OrderFormTest {
    private WebDriver driver;
    private WebDriverWait wait;
    private final String url = Constants.URL;




    private static WebDriverProvider browserProvider(String browserName) {
            WebDriverProvider driver;
        switch (browserName.toLowerCase()) {
            case "chrome":
                // Инициализация ChromeDriver
                driver = new ChromeDriverProvider();
                break;
            case "firefox":
                // Инициализация FirefoxDriver
                driver = new FirefoxDriverProvider();
                break;
            default:
                throw new IllegalArgumentException("Неизвестный браузер: " + browserName);
        }
        return driver;
    }

    @BeforeEach
    public void setUp(TestInfo testInfo) {

    }
        static Stream<Arguments> browserProvider() {
        return Stream.of(
                Arguments.of(browserProvider("chrome"), "Михаил", "Соловьев", "Минск1", 2, "+12345678901", "01.01.2024", "Без комментариев"),
                Arguments.of(browserProvider("chrome"), "Степан", "Кузьмин", "Минск2", 3, "+12345678902", "01.10.2024", " "),
                Arguments.of(browserProvider("firefox"), "Андрей", "Краснов", "Минск3", 4, "+12345678903", "01.11.2024", "Без комментариев1"),
                Arguments.of(browserProvider("firefox"), "Алексей", "Лобанов", "Минск4", 5, "+12345678904", "20.01.2024", "")
        );
    }

    // Закрытие окна cookie
    private void handleCookiePopup(WebDriver driver) {

        List<WebElement> cookiePopups = driver.findElements(PageObject.cookieOkButton);

        if (!cookiePopups.isEmpty()) {
            WebElement cookiePopup = cookiePopups.get(0);
            WebElement acceptButton = cookiePopup.findElement(By.tagName("button"));
            acceptButton.click();
        }
    }

    private void fillForm(WebDriver driver, String name, String surname, String address, int metroStationIndex, String phoneNumber, String date, String comment) {
        driver.manage().window().maximize();
        driver.get(url);
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        handleCookiePopup(driver);


        // Верхняя кнопка "Заказать"
        WebElement firstButton = wait.until(ExpectedConditions.elementToBeClickable(FormObject.firstButton));
        firstButton.click();

        // Возвращаемся на главную страницу
        driver.navigate().back();

        //Нижняя кнопка "Заказать"
        WebElement finishButtonDiv = wait.until(ExpectedConditions.elementToBeClickable(FormObject.finishButtonDiv));

        // Прокручиваем элемент в область видимости

        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", finishButtonDiv);
        //int yCoordinate = finishButtonDiv.getLocation().getY();
        //((JavascriptExecutor) driver).executeScript("window.scrollTo(0, " + yCoordinate + ")");
        //finishButtonDiv.click();

        // Нижняя кнопка "Заказать".
        WebElement buttonInsideDiv = wait.until(ExpectedConditions.visibilityOfElementLocated(FormObject.finishButtonDiv));

        buttonInsideDiv.click();

        test(name, surname, address, metroStationIndex, phoneNumber, date, comment);

    }

    public void test(String name, String surname, String address, int metroStationIndex, String phoneNumber, String date, String comment) {
        // Заполнение поля Имя
        WebElement nameInput = wait.until(ExpectedConditions.visibilityOfElementLocated(FormObject.nameInput));
        //WebElement nameInput = driver.findElement(FormObject.nameInput);
        nameInput.click();
        nameInput.sendKeys(name);

        // Заполнение поля Фамилия
        WebElement surnameInput = driver.findElement(FormObject.surnameInput);
        surnameInput.click();
        surnameInput.sendKeys(surname);

        // Заполнение поля Адрес
        WebElement addressInput = driver.findElement(FormObject.addressInput);
        addressInput.click();
        addressInput.sendKeys(address);

        // Заполнение поля Станция метро
        driver.findElement(FormObject.metroStation).click();
        List<WebElement> metroStations = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(FormObject.metroStations));
        metroStations.get(metroStationIndex).click();

        // Заполнение поля Телефон
        WebElement phoneInput = driver.findElement(FormObject.phoneInput);
        phoneInput.click();
        phoneInput.sendKeys(phoneNumber);

        // Переход ко второй части формы
        WebElement nextButton = driver.findElement(FormObject.nextButton);
        nextButton.click();


        // Заполнение поля Когда привезти самокат
        WebElement dateField = wait.until(ExpectedConditions.visibilityOfElementLocated(FormObject.dateField));
       // WebElement dateField = driver.findElement(FormObject.dateField);
        dateField.sendKeys(date);
        dateField.sendKeys(Keys.ENTER);

        // Выбор срока аренды
        driver.findElement(FormObject.control).click();
        WebElement rentPeriod = wait.until(ExpectedConditions.elementToBeClickable(FormObject.rentPeriod));
        rentPeriod.click();

        // Выбор цвета скутера
        WebElement checkbox = driver.findElement(FormObject.checkbox);
        if (!checkbox.isSelected()) {
            checkbox.click();
        }

        // Заполнение поля Комментарии
        driver.findElement(FormObject.comment).sendKeys(comment);

        // Кнопка заказа
        List<WebElement> buttonsOrder = driver.findElements(FormObject.buttonsOrder);
        for (WebElement button : buttonsOrder) {
            if (button.getText().equals("Заказать")) {

                WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
                wait.until(ExpectedConditions.elementToBeClickable(button));
                button.click();
                break;
            }
        }


        // Кнопка подтверждения заказа

        List<WebElement> buttonsYes = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(FormObject.buttonsYes));
        for (WebElement button : buttonsYes) {
            if (button.getText().equals("Да")) {

                // Waiting until the element is clickable
                WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
                wait.until(ExpectedConditions.elementToBeClickable(button));
                button.click();
                break;
            }
        }

        WebElement modalDialog = wait.until(ExpectedConditions.visibilityOfElementLocated(FormObject.modalDialog));

        // Check that the text starts with "Заказ оформлен"
        String modalText = modalDialog.getText();
        assertTrue(modalText.startsWith("Заказ оформлен"), "Текст не начинается с 'Заказ оформлен': " + modalText);
    }



    @ParameterizedTest
    @MethodSource("browserProvider")
    @Tag("browser")
    public void testFormOnBrowsers(WebDriverProvider browserProvider, String name, String surname, String address, int metroStationIndex, String phoneNumber, String date, String comment) {
        driver = browserProvider.createDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(100));
        fillForm(driver, name, surname, address, metroStationIndex, phoneNumber, date, comment);
    }



    @AfterEach
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
