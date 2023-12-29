package tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import pageobjects.MainPage;
import pageobjects.OrderPage;

import java.util.concurrent.TimeUnit;


public class OrderErrorTests {

    WebDriver driver;
    static final String mainPageUrl= "https://qa-scooter.praktikum-services.ru/";
    @Before
    public void setUp(){
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
        MainPage mainPage = new MainPage(driver);
        driver.get(mainPageUrl);
        mainPage.acceptCookies();
//        нажать на кнопку заказа(верхнюю или нижнюю)
        mainPage.clickUpperOrder();

    }


        @Test
        public void appearNameErrorTest() {
            OrderPage orderPage = new OrderPage(driver);
            orderPage.clickNextButton();
            Assert.assertTrue(orderPage.isAppearNameError());
        }
    @Test
    public void appearSecondNameErrorTest(){
            OrderPage orderPage = new OrderPage(driver);
            orderPage.clickNextButton();
            Assert.assertTrue(orderPage.isAppearSecondNameError());
    }
    @Test
    public void appearMetroStationErrorTest(){
        OrderPage orderPage = new OrderPage(driver);
        orderPage.clickNextButton();
        Assert.assertTrue(orderPage.isAppearMetroStationError());
    }
    @Test
    public void appearPhoneNumberErrorTest(){
        OrderPage orderPage = new OrderPage(driver);
        orderPage.clickNextButton();
        Assert.assertTrue(orderPage.isAppearPhoneNumberError());
    }
    @Test
    public void appearAddressErrorTest(){
        OrderPage orderPage = new OrderPage(driver);
        orderPage.clickNextButton();
        Assert.assertTrue(orderPage.isAppearAddressError());
    }


    @After
    public void quit(){
        driver.quit();
    }



}
