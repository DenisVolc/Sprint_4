package pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class YandexPage {
    WebDriver driver;
    public YandexPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }
    @FindBy(xpath = ".//*[@class = 'zen-ui-generic-svg navigation-tab__icon-3k']")
    private WebElement yandexSearchBar;

    public WebElement getYandexSearchBar() {
        return yandexSearchBar;
    }
}
