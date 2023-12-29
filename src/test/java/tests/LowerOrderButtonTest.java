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

public class LowerOrderButtonTest {
    WebDriver driver;
    static final String mainPageUrl= "https://qa-scooter.praktikum-services.ru/";
    @Before
    public void setUp(){
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
        driver.get(mainPageUrl);
    }
    @Test
    public void lowerOrderButtonTest(){
        MainPage mainPage = new MainPage(driver);
        mainPage.acceptCookies();
        mainPage.clickLowerOrder();
        OrderPage orderPage = new OrderPage(driver);

        Assert.assertTrue(orderPage.isDisplayedOrderHeader());
    }
    @After
    public void quit(){
        driver.quit();
    }
}
