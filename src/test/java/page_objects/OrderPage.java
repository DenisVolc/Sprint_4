package page_objects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

//https://qa-scooter.praktikum-services.ru/order
//TODO заменить локаторы By на WebElemеnt
public class OrderPage {
    //Локаторы
    private WebDriver driver;
    public OrderPage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }
    private final By header = By.className("Order_Header__BZXOb");// заголовок
    private final By name =By.xpath(".//input[@placeholder='* Имя']");//    Имя
    private final By secondName =By.xpath(".//input[@placeholder='* Фамилия']");//    Фамилия
    private final By address =By.xpath(".//input[@placeholder='* Адрес: куда привезти заказ']");//    Адрес
    private final By metroStations =By.className("select-search__input");//    станция метро
    private final By phoneNumber =By.xpath(".//input[@placeholder='* Телефон: на него позвонит курьер']");//    Телефон
    private final By nextButton = By.xpath(".//*[@class='Button_Button__ra12g Button_Middle__1CSJM']");//  //  Кнопка "Далее"

    @FindBy(xpath = ".//input[@placeholder='* Когда привезти самокат']")
    private WebElement date;
    @FindBy(className = "Dropdown-control")
    private WebElement duration;

//    private final By blackCheckBox = By.xpath(".//*[@for='black]");
//    private final By greyCheckBox = By.xpath(".//*[@for='grey]");

    @FindBy(className = "Checkbox_Input__14A2w")
    private List<WebElement> checkBox;
    @FindBy(xpath = ".//input[@placeholder='Комментарий для курьера']")
    private WebElement commentInput;
    @FindBy(xpath = ".//*[@class='Button_Button__ra12g Button_Middle__1CSJM Button_Inverted__3IF-i' and text()='Назад']")
    private WebElement backButton;
    @FindBy(className = "Order_Modal__YZ-d3")
    private WebElement orderConfirm ;//окно подтверждения заказа
    @FindBy(xpath = ".//*[@class='Button_Button__ra12g Button_Middle__1CSJM' and text()='Да']")
    private WebElement confirmYesButton ;
    @FindBy(xpath = ".//*[@class='Button_Button__ra12g Button_Middle__1CSJM' and text()='Нет']")
    private WebElement confirmNoButton ;
    @FindBy(className = "App_App__15LM-")
    private WebElement root;
    @FindBy(className = "Order_ModalHeader__3FDaJ")
    private WebElement orderConfirmed; //заказ подтвержден
    //Методы

    public void clickNextButton(){
        driver.findElement(nextButton).click();
    }
    public void inputName(String text){//    заполнить имя
    driver.findElement(name).sendKeys(text);
    }
    public void inputSecondName(String text){//    заполнить Фамилию
        driver.findElement(secondName).sendKeys(text);
    }
    public void inputAddress(String text){//    заполнить адрес
        driver.findElement(address).sendKeys(text);
    }
    public void inputPhoneNumber(String text){//    заполнить номер
        driver.findElement(phoneNumber).sendKeys(text);
    }
    public void chooseMetroStation(int n){//    заполнить номер
        driver.findElement(metroStations).click();
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
    public By getHeader(){
        return header;
    }
    public void writeComment(String comment){
        commentInput.sendKeys(comment);
    }
    public void clickCheckBox(int i){
        checkBox.get(i).click();
    }
    public void clickBackButton() {
        backButton.click();
    }
    public WebElement orderConfirmation(){
        return orderConfirm;
    }
    public void clickYesButton(){
        confirmYesButton.click();
    }
    public void clickNoButton(){
        confirmNoButton.click();
    }
    public boolean isOrderConfirmedAppear(){
        boolean result = orderConfirmed.isDisplayed();
        return result;
    }
}
