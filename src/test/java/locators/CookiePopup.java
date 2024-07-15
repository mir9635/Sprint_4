package locators;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class CookiePopup {
    private WebDriver driver;

    // Окно куки
    private By cookiePopup = By.className("App_CookieConsent__1yUIN");
    // Куки кнопка Ok
    private  By cookieOkButton = By.tagName("button");

    public CookiePopup(WebDriver driver) {
        this.driver = driver;
    }


    public List<WebElement> getCookiePopup() {
        return   driver.findElements(cookiePopup);
    }

    public WebElement getCookieOkButton() {
        return getCookiePopup().get(0).findElement(cookieOkButton);
    }

    public void clickCookieOkButton() {
        getCookieOkButton().click();
    }


}
