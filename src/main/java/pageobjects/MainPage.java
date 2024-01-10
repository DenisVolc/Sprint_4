package pageobjects;

import constants.Constants;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;


//https://qa-scooter.praktikum-services.ru/


public class MainPage {

    WebDriver driver;
    public MainPage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = ".//div[@class='Home_SubHeader__zwi_E' and text()='Вопросы о важном'] ")
    private WebElement headerFAQ;//заголовок "Вопросы о важном"
    @FindBy(className = "Button_Button__ra12g")
    private WebElement upperOrderButton ;//верхняя кнопка "Заказать"
    @FindBy(className = "Home_FinishButton__1_cWm")
    private WebElement lowerOrderButton;//нижняя кнопка "Заказать"
    @FindBy(id = "rcc-confirm-button")
    private WebElement cookieButton;//
    @FindBy(className = "Header_LogoScooter__3lsAR")
    private WebElement samokatLogo;
    @FindBy(className = "Header_LogoYandex__3TSOI")
    private WebElement yandexLogo;
    @FindBy(className = "Header_Link__1TAG7")
    private WebElement trackButton;// Кнопка "Статус заказа"
    @FindBy(xpath = ".//button[@class='Button_Button__ra12g Header_Button__28dPO']")
    private WebElement goTrackButton; // Это кнопка GO для проверки номера заказа
    @FindBy(xpath = ".//input[@class='Input_Input__1iN_Z Header_Input__xIoUq']")
    private WebElement inputTrackNumber; // Это поле ввода номера заказа для проверки


    //Методы:
    //нажатие на стрелку
    public void pressQuestion(int n){
        By question = By.id("accordion__heading-" + n);
        driver.findElement(question).click();
    }
    //проверить открытие текста под катом
    public boolean isEnabledAnswer(int n){
        By element =  By.xpath(".//*[@id='accordion__panel-"+n+"']/p");
        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.elementToBeClickable(element));
        String factText = driver.findElement(element).getText();
        return factText.equals(Constants.EXPECTED_TEXT[n]);
    }
    //скролл до FAQ
    public void findFAQ(){
        WebElement element = headerFAQ;
        ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();", element);
    }
    //нажать на кнопку "заказать"
    public void clickUpperOrder (){
        upperOrderButton.click();
    }
    public void clickLowerOrder (){
        lowerOrderButton.click();
    }
    public void acceptCookies(){
       cookieButton.click();
    }
    public void clickSamokatLogo(){
        samokatLogo.click();
    }
    public void clickYandexLogo(){
        yandexLogo.click();
    }
    public void clickTrackButton(){
        trackButton.click();
    }
    public void clickGoTrackButton(){
        goTrackButton.click();
    }
    public WebElement getGoTrackButton() {
        return goTrackButton;
    }

    public void setInputTrackNumber(String number) {
        inputTrackNumber.sendKeys(number);
    }
}
