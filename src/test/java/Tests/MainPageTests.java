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
import page_objects.*;


import java.util.concurrent.TimeUnit;
//TODO параметризацию для браузеров

@RunWith(Parameterized.class)
public class MainPageTests {
    WebDriver driver;
    private final String browser;

    public MainPageTests(String browser) {
        this.browser = browser;
    }

    public final String mainPageUrl= "https://qa-scooter.praktikum-services.ru/";

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
    public void faqTestN1(){
        MainPage mnPage = new MainPage(driver);
        driver.get(mainPageUrl);
        mnPage.acceptCookies();
        mnPage.findFAQ();

        for (int i = 0; i < mnPage.getAccordion().size(); i++) {
            mnPage.pressQuestion(i);
            Assert.assertTrue(mnPage.isEnabledAnswer(i));
        }

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
        Assert.assertTrue(driver.getCurrentUrl().contains("dzen.ru")); //проверить url
    }
    @After
    public void quit(){
        driver.quit();
    }
}
