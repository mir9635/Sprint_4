package locators;

import org.openqa.selenium.By;

public class FormObject {

    // Верхняя кнопка "Заказать"
    public static By firstButton = By.cssSelector("button.Button_Button__ra12g");

    // Нижняя кнопка "Заказать"
    public static By finishButtonDiv = By.cssSelector("div.Home_FinishButton__1_cWm");

    // Поле Имя
    public static By nameInput = By.cssSelector("div.Input_InputContainer__3NykH [placeholder='* Имя']");
    // Поле Фамилия
    public static By surnameInput = By.cssSelector("div.Input_InputContainer__3NykH [placeholder='* Фамилия']");
    // Поле Адрес
    public static By addressInput = By.cssSelector("div.Input_InputContainer__3NykH [placeholder='* Адрес: куда привезти заказ']");
    // Поле Станция метро
    public static By metroStation = By.cssSelector("div.select-search__value [placeholder='* Станция метро']");
    public static By metroStations = By.cssSelector("div.select-search__select div");
    // Поле Телефон
    public static By phoneInput = By.cssSelector("div.Input_InputContainer__3NykH [placeholder='* Телефон: на него позвонит курьер']");
    // Кнопка Далее
    public static By nextButton = By.cssSelector("button.Button_Button__ra12g.Button_Middle__1CSJM");
    // Поле Когда привезти самокат
    public static By dateField = By.cssSelector("div.react-datepicker__input-container [placeholder='* Когда привезти самокат']");
    // Срок аренды
    public static By control = By.cssSelector("div.Dropdown-control");
    public static By rentPeriod = By.cssSelector("div.Dropdown-menu div");
    // Цвет скутера
    public static By checkbox = By.cssSelector("div.Order_Checkboxes__3lWSI input[type='checkbox']");
    // Поле Комментарии
    public static By comment = By.cssSelector("div.Input_InputContainer__3NykH [placeholder='Комментарий для курьера']");
    // Кнопка заказа
    public static By buttonsOrder = By.cssSelector("button.Button_Button__ra12g.Button_Middle__1CSJM");
    // Кнопка подтверждения заказа
    public static By buttonsYes = By.cssSelector("button.Button_Button__ra12g.Button_Middle__1CSJM");
    // Модальное окно
    public static By modalDialog = By.className("Order_Modal__YZ-d3");

}
