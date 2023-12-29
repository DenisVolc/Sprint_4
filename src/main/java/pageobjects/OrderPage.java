package pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

//https://qa-scooter.praktikum-services.ru/order

public class OrderPage {
    //Локаторы
    WebDriver driver;
    public OrderPage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }
    @FindBy (className = "Order_Header__BZXOb")
    private WebElement orderHeader;
    @FindBy(xpath = ".//input[@placeholder='* Имя']")
    private WebElement name;//    Имя
    @FindBy(xpath = ".//input[@placeholder='* Фамилия']")
    private WebElement secondName; //    Фамилия
    @FindBy(xpath = ".//input[@placeholder='* Адрес: куда привезти заказ']")
    private WebElement address ;//    Адрес
    @FindBy(className = "select-search__input")
    private WebElement metroStations;//    станция метро
    @FindBy(xpath = ".//input[@placeholder='* Телефон: на него позвонит курьер']")
    private WebElement phoneNumber;//    Телефон
    @FindBy(xpath = ".//*[@class='Button_Button__ra12g Button_Middle__1CSJM']")
    private WebElement nextButton;//  //  Кнопка "Далее"
    @FindBy(xpath = ".//input[@placeholder='* Когда привезти самокат']")
    private WebElement date;
    @FindBy(className = "Dropdown-control")
    private WebElement duration;
    @FindBy(className = "Checkbox_Input__14A2w")
    private List<WebElement> checkBox;
    @FindBy(xpath = ".//input[@placeholder='Комментарий для курьера']")
    private WebElement commentInput;
    @FindBy(xpath = ".//*[@class='Button_Button__ra12g Button_Middle__1CSJM' and text()='Да']")
    private WebElement confirmYesButton ;
    @FindBy(className = "App_App__15LM-")
    private WebElement root;
    @FindBy(className = "Order_Text__2broi")
    private WebElement orderConfirmed; //заказ подтвержден
    @FindBy(xpath = ".//div[text()='Введите корректное имя']")
    private WebElement nameError;
    @FindBy(xpath = ".//div[text()='Введите корректную фамилию']")
    private WebElement secondNameError;
    @FindBy(xpath = ".//div[text()='Введите корректный адрес']")
    private WebElement addressError;
    @FindBy(xpath = ".//div[text()='Выберите станцию']")
    private WebElement metroStationsError;
    @FindBy(xpath = ".//div[text()='Введите корректный номер']")
    private WebElement phoneNumberError;

    //Методы

    public void clickNextButton(){
        nextButton.click();
    }
    public void inputName(String text){//    заполнить имя
    name.sendKeys(text);
    }
    public void inputSecondName(String text){//    заполнить Фамилию
        secondName.sendKeys(text);
    }
    public void inputAddress(String text){//    заполнить адрес
        address.sendKeys(text);
    }
    public void inputPhoneNumber(String text){//    заполнить номер
        phoneNumber.sendKeys(text);
    }
    public void chooseMetroStation(int n){//    заполнить номер
        metroStations.click();
        driver.findElement(By.xpath(".//li[@class='select-search__row' and @data-index='"+n+"']")).click();//n
    }
    public void setDate(String date){
        this.date.sendKeys(date);
        root.click();
    }
    public void chooseDuration(int n){
        duration.click();
        driver.findElements(By.className("Dropdown-option")).get(n).click();
    }
    public void writeComment(String comment){
        commentInput.sendKeys(comment);
    }
    public void clickCheckBox(int i){
        checkBox.get(i).click();
    }
    public void clickYesButton(){
        confirmYesButton.click();
    }
    public boolean isAppearOrderConfirmed(){
        return !(orderConfirmed.getText().equals(" "));
    }
    public boolean isAppearNameError(){
        return nameError.isDisplayed();
    }
    public boolean isAppearSecondNameError(){
        return secondNameError.isDisplayed();
    }
    public boolean isAppearAddressError(){
        return addressError.isDisplayed();
    }
    public boolean isAppearMetroStationError(){
        return metroStationsError.isDisplayed();
    }
    public boolean isAppearPhoneNumberError(){
        return phoneNumberError.isDisplayed();
    }

    public boolean isDisplayedOrderHeader() {
        return orderHeader.isDisplayed();
    }
}
