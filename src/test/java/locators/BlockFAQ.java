package locators;

import org.junit.jupiter.params.provider.Arguments;
import org.openqa.selenium.By;

import java.util.stream.Stream;

public class BlockFAQ {

    // Для Сколько это стоит? И как оплатить?
    private By buttonQuestion0 = By.id("accordion__heading-0");
    //    Для раскрывающегося списка 0 элемента
    private By textAnswer0 = By.id("accordion__panel-0");
    //    Для Хочу сразу несколько самокатов! Так можно?
    private By buttonQuestion1 = By.id("accordion__heading-1");
    //    Для раскрывающегося списка 2 элемента
    private By textAnswer1 = By.id("accordion__panel-1");
    //
//    Для Как рассчитывается время аренды?
    private By buttonQuestion2 = By.id("accordion__heading-2");
    //
//    Для раскрывающегося списка 2 элемента
    private By textAnswer2 = By.id("accordion__panel-2");
    //
//    Для Можно ли заказать самокат прямо на сегодня?
    private By buttonQuestion3 = By.id("accordion__heading-3");
    //
//    Для раскрывающегося списка 3 элемента
    private By textAnswer3 = By.id("accordion__panel-3");
    //
//    Для Можно ли продлить заказ или вернуть самокат раньше?
    private By buttonQuestion4 = By.id("accordion__heading-4");
    //
//    Для раскрывающегося списка 4 элемента
    private By textAnswer4 = By.id("accordion__panel-4");
    //
//    Для Вы привозите зарядку вместе с самокатом?
    private By buttonQuestion5 = By.id("accordion__heading-5");
    //
//    Для раскрывающегося списка 5 элемента
    private By textAnswer5 = By.id("accordion__panel-5");
    //
//    Для Можно ли отменить заказ?
    private By buttonQuestion6 = By.id("accordion__heading-6");
    //
//    Для раскрывающегося списка 6 элемента
    private By textAnswer6 = By.id("accordion__panel-6");
    //
//    Для Я жизу за МКАДом, привезёте?
    private By buttonQuestion7 = By.id("accordion__heading-7");
    //
//    Для раскрывающегося списка 7 элемента
    private By textAnswer7 = By.id("accordion__panel-7");


    public By getButtonQuestion(int i) {
        switch (i) {
            case (0):
                return buttonQuestion0;
            case (1):
                return buttonQuestion1;
            case (2):
                return buttonQuestion2;
            case (3):
                return buttonQuestion3;
            case (4):
                return buttonQuestion4;
            case (5):
                return buttonQuestion5;
            case (6):
                return buttonQuestion6;
            case (7):
                return buttonQuestion7;
            default:
                throw new IllegalArgumentException("Invalid index: " + i);
        }
    }

    public By getTextAnswer(int i) {
        switch (i) {
            case (0):
                return textAnswer0;
            case (1):
                return textAnswer1;
            case (2):
                return textAnswer2;
            case (3):
                return textAnswer3;
            case (4):
                return textAnswer4;
            case (5):
                return textAnswer5;
            case (6):
                return textAnswer6;
            case (7):
                return textAnswer7;
            default:
                throw new IllegalArgumentException("Invalid index: " + i);
        }
    }

}
