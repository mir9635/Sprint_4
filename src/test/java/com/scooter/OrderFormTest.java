package com.scooter;

import io.github.bonigarcia.wdm.WebDriverManager;
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
import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class OrderFormTest {
    private WebDriver driver;
    private WebDriverWait wait;
    private final String url = "https://qa-scooter.praktikum-services.ru/";

    @BeforeEach
    public void setUp(TestInfo testInfo) {

    }



    static Stream<Arguments> browserProvider() {
        return Stream.of(
                Arguments.of("chrome", "Михаил", "Соловьев", "Минск1", 2, "+12345678901", "01.01.2024", "Без комментариев"),
                Arguments.of("chrome", "Степан", "Кузьмин", "Минск2", 3, "+12345678902", "01.10.2024", " "),
                Arguments.of("firefox", "Андрей", "Краснов", "Минск3", 4, "+12345678903", "01.11.2024", "Без комментариев1"),
                Arguments.of("firefox", "Алексей", "Лобанов", "Минск4", 5, "+12345678904", "20.01.2024", "")
        );
    }

    // Закрытие окна cookie
    private void handleCookiePopup(WebDriver driver) {
        try {
            WebElement cookiePopup = driver.findElement(By.className("App_CookieConsent__1yUIN"));
            WebElement acceptButton = cookiePopup.findElement(By.tagName("button"));
            acceptButton.click();
        } catch (NoSuchElementException e) {
        }
    }

    private void fillForm(String name, String surname, String address, int metroStationIndex, String phoneNumber, String date, String comment) {
        driver.manage().window().maximize();
        driver.get(url);

        handleCookiePopup(driver);


        // Верхняя кнопка "Заказать".
        WebElement firstButton = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("button.Button_Button__ra12g")));
        firstButton.click();

        // Возвращаемся на главную страницу
        driver.navigate().back();


        WebElement finishButtonDiv = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("div.Home_FinishButton__1_cWm")));

        // Прокручиваем элемент в область видимости
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", finishButtonDiv);

        // Нижняя кнопка "Заказать".
        WebElement buttonInsideDiv = wait.until(ExpectedConditions.visibilityOfElementLocated(By.tagName("button")));

        //WebElement buttonInsideDiv = wait.until(ExpectedConditions.visibilityOf(finishButtonDiv.findElement(By.tagName("button"))));
        buttonInsideDiv.click();

        test(name, surname, address, metroStationIndex, phoneNumber, date, comment);

    }

    public void test(String name, String surname, String address, int metroStationIndex, String phoneNumber, String date, String comment) {
        // Заполнение поля Имя
        WebElement nameInput = driver.findElement(By.cssSelector("div.Input_InputContainer__3NykH [placeholder='* Имя']"));
        nameInput.click();
        nameInput.sendKeys(name);

        // Заполнение поля Фамилия
        WebElement surnameInput = driver.findElement(By.cssSelector("div.Input_InputContainer__3NykH [placeholder='* Фамилия']"));
        surnameInput.click();
        surnameInput.sendKeys(surname);

        // Заполнение поля Адрес
        WebElement addressInput = driver.findElement(By.cssSelector("div.Input_InputContainer__3NykH [placeholder='* Адрес: куда привезти заказ']"));
        addressInput.click();
        addressInput.sendKeys(address);

        // Заполнение поля Станция метро
        driver.findElement(By.cssSelector("div.select-search__value [placeholder='* Станция метро']")).click();
        List<WebElement> metroStations = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.cssSelector("div.select-search__select div")));
        metroStations.get(metroStationIndex).click();

        // Заполнение поля Телефон
        WebElement phoneInput = driver.findElement(By.cssSelector("div.Input_InputContainer__3NykH [placeholder='* Телефон: на него позвонит курьер']"));
        phoneInput.click();
        phoneInput.sendKeys(phoneNumber);

        // Переход ко второй части формы
        WebElement nextButton = driver.findElement(By.cssSelector("button.Button_Button__ra12g.Button_Middle__1CSJM"));
        nextButton.click();


        // Заполнение поля Когда привезти самокат
        WebElement dateField = driver.findElement(By.cssSelector("div.react-datepicker__input-container [placeholder='* Когда привезти самокат']"));
        dateField.sendKeys(date);
        dateField.sendKeys(Keys.ENTER);

        // Выбор срока аренды
        driver.findElement(By.cssSelector("div.Dropdown-control")).click();
        WebElement rentPeriod = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("div.Dropdown-menu div")));
        rentPeriod.click();

        // Выбор цвета скутера
        WebElement checkbox = driver.findElement(By.cssSelector("div.Order_Checkboxes__3lWSI input[type='checkbox']"));
        if (!checkbox.isSelected()) {
            checkbox.click();
        }

        // Заполнение поля Комментарии
        driver.findElement(By.cssSelector("div.Input_InputContainer__3NykH [placeholder='Комментарий для курьера']")).sendKeys(comment);

        // Кнопка заказа
        List<WebElement> buttonsOrder = driver.findElements(By.cssSelector("button.Button_Button__ra12g.Button_Middle__1CSJM"));
        for (WebElement button : buttonsOrder) {
            if (button.getText().equals("Заказать")) {

                // Waiting until the element is clickable
                WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
                wait.until(ExpectedConditions.elementToBeClickable(button));
                button.click();
                break;
            }
        }


        // Кнопка подтверждения заказа
        List<WebElement> buttonsYes = driver.findElements(By.cssSelector("button.Button_Button__ra12g.Button_Middle__1CSJM"));
        for (WebElement button : buttonsYes) {
            if (button.getText().equals("Да")) {

                // Waiting until the element is clickable
                WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
                wait.until(ExpectedConditions.elementToBeClickable(button));
                button.click();
                break;
            }
        }

        WebElement modalDialog = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("Order_Modal__YZ-d3")));

        // Check that the text starts with "Заказ оформлен"
        String modalText = modalDialog.getText();
        assertTrue(modalText.startsWith("Заказ оформлен"), "Текст не начинается с 'Заказ оформлен': " + modalText);
    }


    @ParameterizedTest
    @MethodSource("browserProvider")
    @Tag("chrome")
    @Tag("firefox")
    public void testOrderForm(String browser, String name, String surname, String address, int metroStationIndex, String phoneNumber, String date, String comment) {
        if ("chrome".equals(browser)) {
            WebDriverManager.chromedriver().setup();
            driver = new ChromeDriver();
            wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        } else if ("firefox".equals(browser)) {
            WebDriverManager.firefoxdriver().setup();
            driver = new FirefoxDriver();
            wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        }

        fillForm(name, surname, address, metroStationIndex, phoneNumber, date, comment);
    }

    @AfterEach
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
