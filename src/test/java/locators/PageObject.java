package locators;


import org.openqa.selenium.By;

public class PageObject {
//    шапка сайта
//
//    Для логотипа Yandex
//    By logoYandexAlt = By.cssSelector("img[alt='Yandex']");

    //    Куки кнопка Ok
    public static By cookieOkButton = By.className("App_CookieConsent__1yUIN");
//
//    Для логотипа Scooter
//    By logoScooterAlt = By.cssSelector("img[alt='Scooter']");
//
//    Для ссылки на Yandex
//    By linkYandex = By.cssSelector("a[target='_blank'][href='//yandex.ru']");
//
//    Для ссылки на Scooter
//    By linkScooter = By.cssSelector("a[href='/']");
//
//    Для элемента "Учебный тренажер"
//    By disclaimerElement = By.cssSelector(".Header_Disclaimer__3VEni");
//
//    Для кнопки "Заказать"
//    By buttonStatusOrder = By.cssSelector(".Header_Link__1TAG7");
//
//    Для кнопки "Статус заказа"
//    By buttonStatusOrder = By.cssSelector(".Header_Link__1TAG7");
//
//
//    блок инфо
//
//    Для изображения самоката
//    By imageScooterBlueprint = By.cssSelector("img[alt='Scooter blueprint']");
//
//    Для заголовка "Самокат на пару дней"
//    By headerScooter = By.cssSelector(".Home_Header__iJKdX");
//
//    Для текста "Привезём его прямо к вашей двери"
//    By buttonWill = By.cssSelector(".Home_SubHeader__zwi_E");
//
//    Для текста "Он лёгкий и с мощными колёсами— самое то, чтобы ехать по набережной и нежно барабанить пальцами по рулю"
//    By secondSubHeaderElement = By.cssSelector(".Home_SubHeader__zwi_E:nth-of-type(2)");
//
//    Для заголовка таблицы "Toxic PRO"
//    By modelName = By.cssSelector(".Home_Column__xlKDK:nth-child(1)");
//
//    Для строки с максимальной скоростью
//    By maxSpeedRow = By.xpath("//div[@class='Home_Column__xlKDK' and text()='Максимальная скорость']/parent::div");
//
//    Для значения максимальной скорости
//    By maxSpeedValue = By.xpath("//div[@class='Home_Column__xlKDK' and text()='Максимальная скорость']/following-sibling::div");
//
//    Для строки с пробегом без подзарядки
//    By maxRangeRow = By.xpath("//div[@class='Home_Column__xlKDK' and text()='Проедет без поздарядки']/parent::div");
//
//    Для значения пробега без подзарядки
//    By maxRangeValue = By.xpath("//div[@class='Home_Column__xlKDK' and text()='Проедет без поздарядки']/following-sibling::div");
//
//    Для строки с максимальным весом
//    By maxWeightRow = By.xpath("//div[@class='Home_Column__xlKDK' and text()='Выдерживает вес']/parent::div");
//
//    Для значения максимального веса
//    By maxWeightValue = By.xpath("//div[@class='Home_Column__xlKDK' and text()='Выдерживает вес']/following-sibling::div");
//
//
//    Блока Как это работает
//
//    Для блока "Как это работает"
//    By howItWorksBlock = By.cssSelector(".Home_ThirdPart__LSTEE");
//
//    Для подзаголовка "Как это работает"
//    By subHeader = By.cssSelector(".Home_SubHeader__zwi_E");
//
//    Для номера шага 1
//    By step1Circle = By.xpath("//div[@class='Home_StatusCircle__3_aTp' and text()='1']");

    //
//    Для описания шага 1
//    By step1Description = By.xpath("//div[@class='Home_StatusCircle__3_aTp' and text()='1']/following-sibling::div/div[@class='Home_StatusDescription__3WGl5']");
//
//    Для номера шага 2
//    By step2Circle = By.xpath("//div[@class='Home_StatusCircle__3_aTp' and text()='2']");
//
//    Для описания шага 2
//    By step2Description = By.xpath("//div[@class='Home_StatusCircle__3_aTp' and text()='2']/following-sibling::div[@class='Home_StatusInfo__HrjoZ']/div[@class='Home_StatusDescription__3WGl5']");
//
//    Для номера шага 3
//    By step3Circle = By.xpath("//div[@class='Home_StatusCircle__3_aTp' and text()='3']");
//
//    Для описания шага 3
//    By step3Description = By.xpath("//div[@class='Home_StatusCircle__3_aTp' and text()='3']/following-sibling::div[@class='Home_StatusInfo__HrjoZ']/div[@class='Home_StatusDescription__3WGl5']");
//
//    Для  номера шага 4
//    By step4Circle = By.xpath("//div[@class='Home_StatusCircle__3_aTp Home_Lineless__2-Rxp' and text()='4']");
//
//    Для описания шага 4
//    By step4Description = By.xpath("//div[@class='Home_StatusCircle__3_aTp Home_Lineless__2-Rxp' and text()='4']/following-sibling::div[@class='Home_StatusInfo__HrjoZ']/div[@class='Home_StatusDescription__3WGl5']");
//
//    Для кнопки "Заказать"
//    By orderButton = By.xpath("//div[@class='Home_FAQ__3uVm4']//button[contains(text(),'Заказать')]");
//
//
//    Блок Вопросы о важном
//
//    Для заголовка Вопросы о важном
//    By blockTitle = By.cssSelector("div.Home_SubHeader__zwi_E");
//
//    Для Сколько это стоит? И как оплатить?
    // public static By buttonQuestion0 = By.xpath("//div[@id='accordion__heading-0']");
    public static By buttonQuestion0 = By.id("accordion__heading-0");
    //    Для раскрывающегося списка 0 элемента
    public static By textAnswer0 = By.id("accordion__panel-0");
    //    Для Хочу сразу несколько самокатов! Так можно?
    public static By buttonQuestion1 = By.id("accordion__heading-1");
    //    Для раскрывающегося списка 2 элемента
    public static By textAnswer1 = By.id("accordion__panel-1");
    //
//    Для Как рассчитывается время аренды?
    public static By buttonQuestion2 = By.id("accordion__heading-2");
    //
//    Для раскрывающегося списка 2 элемента
    public static By textAnswer2 = By.id("accordion__panel-2");
    //
//    Для Можно ли заказать самокат прямо на сегодня?
    public static By buttonQuestion3 = By.id("accordion__heading-3");
    //
//    Для раскрывающегося списка 3 элемента
    public static By textAnswer3 = By.id("accordion__panel-3");
    //
//    Для Можно ли продлить заказ или вернуть самокат раньше?
    public static By buttonQuestion4 = By.id("accordion__heading-4");
    //
//    Для раскрывающегося списка 4 элемента
    public static By textAnswer4 = By.id("accordion__panel-4");
    //
//    Для Вы привозите зарядку вместе с самокатом?
    public static By buttonQuestion5 = By.id("accordion__heading-5");
    //
//    Для раскрывающегося списка 5 элемента
    public static By textAnswer5 = By.id("accordion__panel-5");
    //
//    Для Можно ли отменить заказ?
    public static By buttonQuestion6 = By.id("accordion__heading-6");
    //
//    Для раскрывающегося списка 6 элемента
    public static By textAnswer6 = By.id("accordion__panel-6");
    //
//    Для Я жизу за МКАДом, привезёте?
    public static By buttonQuestion7 = By.id("accordion__heading-7");
    //
//    Для раскрывающегося списка 7 элемента
    public static By textAnswer7 = By.id("accordion__panel-7");

}
