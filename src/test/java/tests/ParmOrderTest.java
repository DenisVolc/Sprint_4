package tests;

import constants.URL;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import pageobjects.MainPage;
import pageobjects.OrderPage;

import java.util.concurrent.TimeUnit;
@RunWith(Parameterized.class)
public class ParmOrderTest {
    private final String name;
    private final String secondName;
    private final String address;
    private final int metroStations;
    private final String phoneNumber;



    public ParmOrderTest(String name, String secondName, String address,
                         int metroStations, String phoneNumber) {
        this.name = name;
        this.secondName = secondName;
        this.address = address;
        this.metroStations = metroStations;
        this.phoneNumber = phoneNumber;
    }
    @Parameterized.Parameters // добавили аннотацию
    public static Object[][] testDataSet() {
        return new Object[][]{
                {"Иван","Иванов","Проспект Ленина д.1",0,"89151234578"},
                {"Иван","Иванов","Проспект Ленина д.1",0,"89151234578"},
                {"петр","васильев","ул. Арбат",100,"+79059876521"},
                {"петр","васильев","ул. Арбат",100,"+79059876521"},
        };
    }

    WebDriver driver;

    @Before
    public void setUp(){
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
        driver.get(URL.MAIN_PAGE_URL);
    }
    @Test
    public void orderPageTest() {
        MainPage mainPage = new MainPage(driver);

        mainPage.acceptCookies();

//        нажать на кнопку заказа(верхнюю или нижнюю)
//            mainPage.clickLowerOrder();
        mainPage.clickUpperOrder();//нажатие верхней кнопки или нижней кнопки "Заказать"
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
        boolean result =orderPage.isAppearOrderConfirmed("Заказ оформлен");
        Assert.assertTrue("Не отображается подтверждение заказа", result);
    }

    @After
    public void quit(){
        driver.quit();
    }
}
