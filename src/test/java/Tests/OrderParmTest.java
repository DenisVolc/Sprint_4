package Tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import page_objects.MainPage;
import page_objects.OrderPage;

import java.util.concurrent.TimeUnit;
@RunWith(Parameterized.class)
public class OrderParmTest {
    private final String name;
    private final String secondName;
    private final String address;
    private final int metroStations;
    private final String phoneNumber;
    private  String browser;
    private boolean isUpperorderButton;



    public OrderParmTest(String name, String secondName, String address,
                      int metroStations, String phoneNumber, String browser,boolean orderButton) {
        this.name = name;
        this.secondName = secondName;
        this.address = address;
        this.metroStations = metroStations;
        this.phoneNumber = phoneNumber;
        this.browser = browser;
        this.isUpperorderButton = orderButton;
    }
    @Parameterized.Parameters // добавили аннотацию
    public static Object[][] TestDataSet() {
        return new Object[][]{
                {"Иван","Иванов","Проспект Ленина д.1",0,"89151234578","chrome",true},
                {"Иван","Иванов","Проспект Ленина д.1",0,"89151234578","mozila",false},
                {"петр","васильев","ул. Арбат",100,"+79059876521","chrome",false},
                {"петр","васильев","ул. Арбат",100,"+79059876521","mozila",true},
        };
    }

    WebDriver driver;
    final String mainPageUrl= "https://qa-scooter.praktikum-services.ru/";
    @Before
    public void setUp(){
        if(browser.equals("chrome")){
            WebDriverManager.chromedriver().setup();
            driver = new ChromeDriver();
        }
        if(browser.equals("mozila")) {
            WebDriverManager.firefoxdriver().setup();
            driver = new FirefoxDriver();
        }
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);

    }
    @Test
    public void orderPageTestN2() {
        MainPage mainPage = new MainPage(driver);
        driver.get(mainPageUrl);
        mainPage.acceptCookies();

//        нажать на кнопку заказа(верхнюю или нижнюю)
//            mainPage.clickLowerOrder();
        mainPage.clickOrderButton(isUpperorderButton);//нажатие верхней кнопки или нижней кнопки "Заказать"
        OrderPage orderPage = new OrderPage(driver);

//        заполнить форму заказа

        //Перва форма
        orderPage.inputName(name);
        orderPage.inputSecondName(secondName);
        orderPage.inputAddress(address);
        orderPage.chooseMetroStation(metroStations);
        orderPage.inputPhoneNumber(phoneNumber);
        orderPage.clickNextButton();

        //Вторая форма
        orderPage.setDate("01.01.2021");
        orderPage.chooseDuration(0);
        orderPage.clickCheckBox(0);
        orderPage.writeComment("Hello World");
        orderPage.clickNextButton();

//        проверить всплывающее окно
        orderPage.clickYesButton();
        Assert.assertTrue("Не отображается подтверждение заказа",
                orderPage.isAppearOrderConfirmed());
    }
    @After
    public void quit(){
        driver.quit();
    }
}
