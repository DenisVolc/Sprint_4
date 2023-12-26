package page_objects;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import java.util.Collection;
import java.util.List;

//https://qa-scooter.praktikum-services.ru/
//TODO заменить локаторы By на WebElemеnt

public class MainPage {

    private WebDriver driver;
    public MainPage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }
    //заголовок "Вопросы о важном"
    private final By headerFAQ = By.xpath(".//div[@class='Home_SubHeader__zwi_E' and text()='Вопросы о важном'] ");
    //все стрелочки под заголовком
    private final By accordion = By.className("accordion__button");
    private final By upperOrderButton = By.className("Button_Button__ra12g");
//    private final By lowerOrderButton = By.className("Button_Button__ra12g Button_Middle__1CSJM");//TODO заменить className на Xpath
    private final By cookieButton =By.id("rcc-confirm-button");
    //текст под катом
//    private final By answers = By.className("accordion__panel");

    //Методы:
    //нажатие на стрелку
    public void pressQuestion(int n){
        By question = By.id("accordion__heading-" + n);
        driver.findElement(question).click();
    }
    //проверить открытие текста под катом
    public boolean isAnswerAppear(int n){
        By answer = By.id("accordion__panel-" + n);
        return driver.findElement(answer).isEnabled();
    }
    //скролл до FAQ
    public void findFAQ(){
        WebElement element = driver.findElement(headerFAQ);
        ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();", element);
    }
    //нажать на кнопку "заказать"
    public void clickUpperOrder (){
        driver.findElement(upperOrderButton).click();
    }
//    public void clickLowerOrder (){
//        driver.findElement(lowerOrderButton).click(); //раскоментить когда пофиксю lowerOrderButton
//    }

    public List<WebElement> getAccordion() {
        return driver.findElements(accordion);
    }

    public By getQuestion(int n){
        return By.id("accordion__heading-" + n);
    }
    public By getAnswer(int n){
        return By.id("accordion__panel-" + n);
    }
    public void acceptCookies(){
       driver.findElement(cookieButton).click();
    }
}
