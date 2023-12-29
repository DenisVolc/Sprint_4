package tests;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pageobjects.*;


import java.time.Duration;
import java.util.concurrent.TimeUnit;



public class LogoTests {
    WebDriver driver;


    static final String mainPageUrl= "https://qa-scooter.praktikum-services.ru/";





    @Before
    public void setUp(){

            WebDriverManager.chromedriver().setup();
            driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);

    }


    @Test
    public void samokatLogoTestN3(){
        MainPage mainPage = new MainPage(driver);
        driver.get(mainPageUrl);

        mainPage.acceptCookies();
        mainPage.clickLowerOrder();
        mainPage.clickSamokatLogo(); //нажать на лого самоката

        Assert.assertEquals(mainPageUrl,driver.getCurrentUrl()); //проверить url

    }
    @Test
    public void yandexLogoTest(){
        MainPage mainPage = new MainPage(driver);
        driver.get(mainPageUrl);
        mainPage.acceptCookies();
        mainPage.clickYandexLogo();//нажать на лого yandex
        for(String tab : driver.getWindowHandles()){
            driver.switchTo().window(tab);
        }
        YandexPage dzen = new YandexPage(driver);
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.elementToBeClickable(dzen.getYandexSearchBar()));
        Assert.assertTrue(driver.getCurrentUrl().contains("dzen.ru")); //проверить url
    }
    @After
    public void quit(){
        driver.quit();
    }
}
