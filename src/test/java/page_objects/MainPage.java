package page_objects;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

//https://qa-scooter.praktikum-services.ru/


public class MainPage {

    private WebDriver driver;
    public MainPage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }
    //заголовок "Вопросы о важном"
    @FindBy(xpath = ".//div[@class='Home_SubHeader__zwi_E' and text()='Вопросы о важном'] ")
    private WebElement headerFAQ;
    //все стрелочки под заголовком
    @FindBy(className = "accordion__button")
    private List<WebElement> accordion ;
    @FindBy(className = "Button_Button__ra12g")
    private WebElement upperOrderButton ;
    @FindBy(xpath = ".//button[@class='Button_Button__ra12g Button_UltraBig__UU3Lp']")
    private WebElement lowerOrderButton;
    @FindBy(id = "rcc-confirm-button")
    private WebElement cookieButton;
    @FindBy(className = "Header_LogoScooter__3lsAR")
    private WebElement samokatLogo;
    @FindBy(className = "Header_LogoYandex__3TSOI")
    private WebElement yandexLogo;
    @FindBy(className = "Header_Link__1TAG7")
    private WebElement trackButton;
    @FindBy(xpath = ".//button[@class='Button_Button__ra12g Header_Button__28dPO']")
    private WebElement goTrackButton;
    @FindBy(xpath = ".//input[@class='Input_Input__1iN_Z Header_Input__xIoUq']")
    private WebElement inputTrack;


    //Методы:
    //нажатие на стрелку
    public void pressQuestion(int n){
        By question = By.id("accordion__heading-" + n);
        driver.findElement(question).click();
    }
    //проверить открытие текста под катом
    public boolean isEnabledAnswer(int n){
        By answer = By.id("accordion__panel-" + n);
        return driver.findElement(answer).isEnabled();
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
        lowerOrderButton.click(); //раскоментить когда пофиксю lowerOrderButton
    }

    public List<WebElement> getAccordion() {
        return accordion;
    }

    public By getQuestion(int n){
        return By.id("accordion__heading-" + n);
    }
    public By getAnswer(int n){
        return By.id("accordion__panel-" + n);
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
    public boolean isEnabledGoTrackButton(){
        return goTrackButton.isEnabled();
    }

    public boolean isDisplayedGoTrackButton(){
        return goTrackButton.isDisplayed();
    }
    public void setInputTrack(String trackNumber){
        inputTrack.sendKeys(trackNumber);
    }
}
