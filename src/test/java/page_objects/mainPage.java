package page_objects;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

//https://qa-scooter.praktikum-services.ru/
public class mainPage {

    private WebDriver driver;
    private int n;
    //заголовок "Вопросы о важном"
    private final By headerFAQ = By.className("Home_SubHeader__zwi_E");
    //все стрелочки под заголовком
    private final By accordion = By.className("accordion__button");
    //текст под катом
    private final By answers = By.className("accordion__panel");
    private final By orderButton = By.className("Button_Button__ra12g");
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
    public void clickOrder (){
        driver.findElement(orderButton).click();
    }
}
