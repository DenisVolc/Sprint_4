package Tests;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import page_objects.*;


import java.time.Duration;
import java.util.concurrent.TimeUnit;


@RunWith(Parameterized.class)
public class LogoTests {
    WebDriver driver;
    private final String browser;

    private final String mainPageUrl= "https://qa-scooter.praktikum-services.ru/";
    public LogoTests(String browser) {
        this.browser = browser;
    }



    @Parameterized.Parameters // добавили аннотацию
    public static Object[][] orderTestDataSet() {
        return new Object[][]{
                {"chrome"},
                {"mozila"},

        };
    }
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
    public void samokatLogoTestN3(){
        MainPage mainPage = new MainPage(driver);
        driver.get(mainPageUrl);

        mainPage.acceptCookies();
        mainPage.clickLowerOrder();
        mainPage.clickSamokatLogo(); //нажать на лого самоката

        Assert.assertEquals(mainPageUrl,driver.getCurrentUrl()); //проверить url

    }
    @Test
    public void yandexLogoTestN4(){
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
