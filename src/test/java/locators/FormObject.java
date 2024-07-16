package locators;

import com.scooter.OrderFormTest;
import org.junit.jupiter.params.provider.Arguments;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class FormObject {
    private WebDriver driver;
    private WebDriverWait wait;


    // Верхняя кнопка "Заказать"
    private By firstButton = By.cssSelector("button.Button_Button__ra12g");

    // Нижняя кнопка "Заказать"
    private By secondButton = By.cssSelector("div.Home_FinishButton__1_cWm");

    // Поле Имя
    private By nameInput = By.cssSelector("div.Input_InputContainer__3NykH [placeholder='* Имя']");
    // Поле Фамилия
    private By surnameInput = By.cssSelector("div.Input_InputContainer__3NykH [placeholder='* Фамилия']");
    // Поле Адрес
    private By addressInput = By.cssSelector("div.Input_InputContainer__3NykH [placeholder='* Адрес: куда привезти заказ']");
    // Поле Станция метро
    private By metroStation = By.cssSelector("div.select-search__value [placeholder='* Станция метро']");
    private By metroStations = By.cssSelector("div.select-search__select div");
    // Поле Телефон
    private By phoneInput = By.cssSelector("div.Input_InputContainer__3NykH [placeholder='* Телефон: на него позвонит курьер']");
    // Кнопка Далее
    private By nextButton = By.cssSelector("button.Button_Button__ra12g.Button_Middle__1CSJM");
    // Поле Когда привезти самокат
    private By dateField = By.cssSelector("div.react-datepicker__input-container [placeholder='* Когда привезти самокат']");
    // Срок аренды
    private By control = By.cssSelector("div.Dropdown-control");
    private By rentPeriod = By.cssSelector("div.Dropdown-menu div");
    // Цвет скутера
    private By checkbox = By.cssSelector("div.Order_Checkboxes__3lWSI input[type='checkbox']");
    // Поле Комментарии
    private By comment = By.cssSelector("div.Input_InputContainer__3NykH [placeholder='Комментарий для курьера']");
    // Кнопка заказа
    private By buttonsOrder = By.cssSelector("button.Button_Button__ra12g.Button_Middle__1CSJM");
    // Кнопка подтверждения заказа
    private By buttonsYes = By.cssSelector("button.Button_Button__ra12g.Button_Middle__1CSJM");
    // Модальное окно
    private By modalDialog = By.className("Order_Modal__YZ-d3");

    public FormObject(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(20));
    }

    ////
    // Заполнение поля
    private void fillInputField(By locator, String value) {
        WebElement inputField = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        inputField.click();
        inputField.clear();
        inputField.sendKeys(value);
    }

    //Клик по первой кнопке "Заказать"
    public void clickFirstButton() {
        WebElement firstButton = wait.until(ExpectedConditions.elementToBeClickable(this.firstButton));
        firstButton.click();
    }
    //Клик по второй кнопке "Заказать"
    public void  clickSecondButton() {
        WebElement element = driver.findElement(this.secondButton);

        // Прокрутка к элементу до его отображения
        ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView(true);", element);

        // Использование Actions для безопасного перемещения к элементу и кликания по нему
        Actions actions = new Actions(driver);
        actions.moveToElement(element).click().perform();

    }
    // Заполнение поля Имя
    public void setNameInput(String name) {
        fillInputField(this.nameInput, name);
    }
    // Заполнение поля Фамилия
    public void setSurnameInput(String surname) {
        fillInputField(this.surnameInput, surname);
    }
    // Заполнение поля Адрес
    public void setAddressInput(String address) {
        fillInputField(this.addressInput, address);
    }
    // Заполнение поля Станция метро
    public void setMetroStation(int metro) {
        driver.findElement(this.metroStation).click();
        List<WebElement> metroStations = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(this.metroStations));
        metroStations.get(metro).click();
    }
    // Заполнение поля Телефон
    public void setPhoneInput(String phone) {
        fillInputField(this.phoneInput, phone);
    }
    // Переход ко второй части формы
    public void nextButton() {
        WebElement nextButton = driver.findElement(this.nextButton);
        nextButton.click();
    }
    // Заполнение поля Когда привезти самокат
    public void setDateField(String date) {
        WebElement dateField = wait.until(ExpectedConditions.visibilityOfElementLocated(this.dateField));
        dateField.sendKeys(date);
        dateField.sendKeys(Keys.ENTER);;
    }
    // Выбор срока аренды
    public void controlClick() {
        driver.findElement(this.control).click();
        WebElement rentPeriod = wait.until(ExpectedConditions.elementToBeClickable(this.rentPeriod));
        rentPeriod.click();
    }
    // Выбор цвета скутера
    public void checkboxClick() {
        WebElement checkbox = driver.findElement(this.checkbox);
        if (!checkbox.isSelected()) {
            checkbox.click();
        }
    }
    // Заполнение поля Комментарии
    public void setComment(String comment) {
        driver.findElement(this.comment).sendKeys(comment);
    }
    // Кнопка заказа
    public void buttonsOrderClick() {
        List<WebElement> buttonsOrder = driver.findElements(this.buttonsOrder);
        for (WebElement button : buttonsOrder) {
            if (button.getText().equals("Заказать")) {

                WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(100));
                wait.until(ExpectedConditions.elementToBeClickable(button));
                button.click();
                break;
            }
        }
    }
    // Кнопка подтверждения заказа
    public void buttonsYesClick() {
        List<WebElement> buttonsYes = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(this.buttonsYes));
        for (WebElement button : buttonsYes) {
            if (button.getText().equals("Да")) {

                WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(100));
                wait.until(ExpectedConditions.elementToBeClickable(button));
                button.click();
                break;
            }
        }
    }
    // Проверка модального окна
    public void modalDialog() {
        WebElement modalDialog = wait.until(ExpectedConditions.visibilityOfElementLocated(this.modalDialog));

        // Проверка что текст начинается с "Заказ оформлен"
        String modalText = modalDialog.getText();
        assertTrue(modalText.startsWith("Заказ оформлен"), "Текст не начинается с 'Заказ оформлен': " + modalText);
    }
}
