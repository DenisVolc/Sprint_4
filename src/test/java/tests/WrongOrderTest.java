package tests;

import constants.Constants;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pageobjects.MainPage;
import pageobjects.TrackPage;

import java.time.Duration;
import java.util.concurrent.TimeUnit;


public class WrongOrderTest {

    WebDriver driver;

    @Before
    public void setUp(){
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
    }


        @Test
        public void wrongOrderTest() {
            MainPage mainPage = new MainPage(driver);
            TrackPage trackPage = new TrackPage(driver);
            driver.get(Constants.MAIN_PAGE_URL);
            mainPage.acceptCookies();
//        Проверить: если ввести неправильный номер заказа, попадёшь на страницу статуса заказа
//        На ней должно быть написано, что такого заказа нет.
            mainPage.clickTrackButton();//Кликнуть "Статус заказа"
            new WebDriverWait(driver, Duration.ofSeconds(5))
                    .until(ExpectedConditions.elementToBeClickable(mainPage.getGoTrackButton()));
            mainPage.setInputTrackNumber("1");
            mainPage.clickGoTrackButton();//нажать Го
            new WebDriverWait(driver, Duration.ofSeconds(10))
                    .until(ExpectedConditions.elementToBeClickable(trackPage.getLookAtButton()));
            Assert.assertTrue(trackPage.isDisplayedNotFoundImg());//проверить запись "Такого заказа нет"
        }


    @After
    public void quit(){
        driver.quit();
    }



}
