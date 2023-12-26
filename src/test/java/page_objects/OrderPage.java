package page_objects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

//https://qa-scooter.praktikum-services.ru/order
public class OrderPage {
    //Поля
    private WebDriver driver;
    private final By name =By.xpath(".//input[@placeholder='* Имя']");//    Имя
    private final By secondName =By.xpath(".//input[@placeholder='* Фамилия']");//    Фамилия
    private final By address =By.xpath(".//input[@placeholder='* Адрес: куда привезти заказ']");//    Адрес
    private final By metroStations =By.className("select-search__input");//    станция метро
    private final By phoneNumber =By.xpath(".//input[@placeholder='* Телефон: на него позвонит курьер']");//    Телефон
    private final By nextButton = By.className("Button_Button__ra12g Button_Middle__1CSJM");//    Кнопка "Далее"

    public OrderPage(WebDriver driver){
        this.driver = driver;
    }
    //Методы
//    Нажать кнопку далее
    public void clickNextButton(){
        driver.findElement(nextButton).click();
    }

    public void inputName(String text){//    заполнить имя
    driver.findElement(name).sendKeys(text);
    }
    public void inputSecondName(String text){//    заполнить Фамилию
        driver.findElement(secondName).sendKeys(text);
    }
    public void inputAdress(String text){//    заполнить адрес
        driver.findElement(address).sendKeys(text);
    }
    public void inputPhoneNumber(String text){//    заполнить номер
        driver.findElement(phoneNumber).sendKeys(text);
    }
    public void chooseMetroStation(int n){//    заполнить номер
        driver.findElement(metroStations).click();
        driver.findElement(By.xpath(".//li[@class='select-search__row' and @data='"+n+"']"));//n
    }

}
