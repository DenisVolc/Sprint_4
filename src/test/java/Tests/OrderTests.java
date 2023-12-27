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
import page_objects.TrackPage;

import java.util.concurrent.TimeUnit;

@RunWith(Parameterized.class)
public class OrderTests {
    private final String name;
    private final String secondName;
    private final String address;
    private final int metroStations;
    private final String phoneNumber;
    private final String browser;



    public OrderTests(String name, String secondName, String address,
                      int metroStations, String phoneNumber, String browser) {
        this.name = name;
        this.secondName = secondName;
        this.address = address;
        this.metroStations = metroStations;
        this.phoneNumber = phoneNumber;
        this.browser = browser;
    }
    @Parameterized.Parameters // добавили аннотацию
    public static Object[][] TestDataSet() {
        return new Object[][]{
                {"Иван","Иванов","Проспект Ленина д.1",0,"89151234578","chrome"},
                {"Петр","Васильев","ул. Арбат",100,"+79059876521","mozila"},
        };
    }

    WebDriver driver;
    public final String mainPageUrl= "https://qa-scooter.praktikum-services.ru/";
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
            mainPage.clickLowerOrder();
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

        @Test
        public void orderErrorsTestN5() {
            MainPage mainPage = new MainPage(driver);
            driver.get(mainPageUrl);
            mainPage.acceptCookies();

//        нажать на кнопку заказа(верхнюю или нижнюю)
            mainPage.clickUpperOrder();
            OrderPage orderPage = new OrderPage(driver);

            orderPage.clickNextButton();
//        Проверить ошибки для всех полей формы заказа.
            Assert.assertTrue(orderPage.isAppearNameError());
            Assert.assertTrue(orderPage.isAppearSecondNameError());
            Assert.assertTrue(orderPage.isAppearMetroStationError());
            Assert.assertTrue(orderPage.isAppearPhoneNumberError());
            Assert.assertTrue(orderPage.isAppearAddressError());
        }

        @Test
        public void wrongOrderTestN6() {
            MainPage mainPage = new MainPage(driver);
            TrackPage trackPage = new TrackPage(driver);
            driver.get(mainPageUrl);
            mainPage.acceptCookies();
//        Проверить: если ввести неправильный номер заказа, попадёшь на страницу статуса заказа
//        На ней должно быть написано, что такого заказа нет.
            mainPage.clickTrackButton();//Кликнуть "Статус заказа"
            mainPage.setInputTrack("1");//ввести неверный номер заказа
//        mainPage.setInputTrack("952951");// правильный номер заказа;
            mainPage.clickGoTrackButton();//нажать Го
            boolean result = trackPage.isDisplayedNotFoundImg();
            Assert.assertTrue(result);//проверить запись "Такого заказа нет"
            //TODO isDisplayedNotFoundImg() через раз возвращаяет то true, то false
        }

    @After
    public void quit(){
        driver.quit();
    }

}
