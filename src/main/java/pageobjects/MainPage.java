package pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

//https://qa-scooter.praktikum-services.ru/


public class MainPage {

    WebDriver driver;
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
    @FindBy(className = "Home_FinishButton__1_cWm")
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
    private WebElement goTrackButton; // Это кнопка GO для проверки номера заказа
    @FindBy(xpath = ".//input[@class='Input_Input__1iN_Z Header_Input__xIoUq']")
    private WebElement inputTrackNumber; // Это поле ввода номера заказа для проверки
    private final String[] expectedText = { //Это массив с ожидаемым текстом ответов на вопросы
            "Сутки — 400 рублей. Оплата курьеру — наличными или картой.",
            "Пока что у нас так: один заказ — один самокат. Если хотите покататься с друзьями, можете просто сделать несколько заказов — один за другим.",
            "Допустим, вы оформляете заказ на 8 мая. Мы привозим самокат 8 мая в течение дня. Отсчёт времени аренды начинается с момента, когда вы оплатите заказ курьеру. Если мы привезли самокат 8 мая в 20:30, суточная аренда закончится 9 мая в 20:30.",
            "Только начиная с завтрашнего дня. Но скоро станем расторопнее.",
            "Пока что нет! Но если что-то срочное — всегда можно позвонить в поддержку по красивому номеру 1010.",
            "Самокат приезжает к вам с полной зарядкой. Этого хватает на восемь суток — даже если будете кататься без передышек и во сне. Зарядка не понадобится.",
            "Да, пока самокат не привезли. Штрафа не будет, объяснительной записки тоже не попросим. Все же свои.",
            "Да, обязательно. Всем самокатов! И Москве, и Московской области.",
    };


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
        boolean result = factText.equals(expectedText[n]);
        return result;
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
    public void clickOrderButton(boolean isUpperButton){
        if (isUpperButton){
            upperOrderButton.click();
        }else{
            lowerOrderButton.click();
        }
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
