package com.scooter;

import com.scooter.factory.ChromeDriverProvider;
import com.scooter.factory.FirefoxDriverProvider;
import com.scooter.factory.WebDriverProvider;
import com.util.Constants;
import io.github.bonigarcia.wdm.WebDriverManager;
import locators.CookiePopup;
import locators.FormObject;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.TestInfo;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.openqa.selenium.*;
import java.util.List;
import java.util.stream.Stream;


public class OrderFormTest {
    private WebDriver driver;
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
        CookiePopup cookiePopup = new CookiePopup(driver);

        List<WebElement> cookiePopups = cookiePopup.getCookiePopup();

        if (!cookiePopups.isEmpty()) {
            cookiePopup.clickCookieOkButton();
        }
    }

    private void fillForm(WebDriver driver, String name, String surname, String address, int metroStationIndex, String phoneNumber, String date, String comment) {
        FormObject formObject = new FormObject(driver);
        driver.manage().window().maximize();
        driver.get(url);

        handleCookiePopup(driver);

        //Клик по первой кнопке "Заказать"
        formObject.clickFirstButton();
        // Возвращаемся на главную страницу
        driver.navigate().back();
        //Клик по второй кнопке "Заказать"
        formObject.clickSecondButton();

        test(formObject, name, surname, address, metroStationIndex, phoneNumber, date, comment);

    }

    public void test(FormObject formObject, String name, String surname, String address, int metroStationIndex, String phoneNumber, String date, String comment) {
        // Заполнение поля Имя
        formObject.setNameInput(name);
        // Заполнение поля Фамилия
        formObject.setSurnameInput(surname);
        // Заполнение поля Адрес
        formObject.setAddressInput(address);
        // Заполнение поля Станция метро
        formObject.setMetroStation(metroStationIndex);
        // Заполнение поля Телефон
        formObject.setPhoneInput(phoneNumber);
        // Переход ко второй части формы
        formObject.nextButton();
        // Заполнение поля Когда привезти самокат
        formObject.setDateField(date);
        // Выбор срока аренды
        formObject.controlClick();
        // Выбор цвета скутера
        formObject.checkboxClick();
        // Заполнение поля Комментарии
        formObject.setComment(comment);
        // Нажатие кнопки "Заказать"
        formObject.buttonsOrderClick();
        // Кнопка подтверждения заказа
        formObject.buttonsYesClick();
        // Проверка модального окна
        formObject.modalDialog();
    }


    @ParameterizedTest
    @MethodSource("browserProvider")
    @Tag("browser")
    public void testFormOnBrowsers(WebDriverProvider browserProvider, String name, String surname, String address, int metroStationIndex, String phoneNumber, String date, String comment) {
        driver = browserProvider.createDriver();
        fillForm(driver, name, surname, address, metroStationIndex, phoneNumber, date, comment);
    }


    @AfterEach
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
