package tests;

import constants.URL;
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

    @Before
    public void setUp(){
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
        MainPage mainPage = new MainPage(driver);
        driver.get(URL.MAIN_PAGE_URL);
        mainPage.acceptCookies();
//        нажать на кнопку заказа(верхнюю или нижнюю)
        mainPage.clickUpperOrder();

    }


        @Test
        public void appearNameErrorTest() {
            OrderPage orderPage = new OrderPage(driver);
            orderPage.clickNextButton();
            Assert.assertTrue("Нет ошибки имени",orderPage.isAppearNameError());
        }
    @Test
    public void appearSecondNameErrorTest(){
            OrderPage orderPage = new OrderPage(driver);
            orderPage.clickNextButton();
            Assert.assertTrue("Нет ошибки фамилии",orderPage.isAppearSecondNameError());
    }
    @Test
    public void appearMetroStationErrorTest(){
        OrderPage orderPage = new OrderPage(driver);
        orderPage.clickNextButton();
        Assert.assertTrue("Нет ошибки станци метро",orderPage.isAppearMetroStationError());
    }
    @Test
    public void appearPhoneNumberErrorTest(){
        OrderPage orderPage = new OrderPage(driver);
        orderPage.clickNextButton();
        Assert.assertTrue("Нет ошибки номера",orderPage.isAppearPhoneNumberError());
    }
    @Test
    public void appearAddressErrorTest(){
        OrderPage orderPage = new OrderPage(driver);
        orderPage.clickNextButton();
        Assert.assertTrue("Нет ошибки адреса",orderPage.isAppearAddressError());
    }


    @After
    public void quit(){
        driver.quit();
    }



}
