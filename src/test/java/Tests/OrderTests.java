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
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import page_objects.MainPage;
import page_objects.OrderPage;
import page_objects.TrackPage;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

@RunWith(Parameterized.class)
public class OrderTests {

    private final String browser;



    public OrderTests( String browser) {

        this.browser = browser;

    }
    @Parameterized.Parameters // добавили аннотацию
    public static Object[][] TestDataSet() {
        return new Object[][]{
                {"chrome"},
                {"mozila"},
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
            //На второй странце заказа нет сообщений об ошибках и не понятно какие у них локаторы,
            // поэтому эти тесты я не реализовал
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
            new WebDriverWait(driver, Duration.ofSeconds(5))
                    .until(ExpectedConditions.elementToBeClickable(mainPage.getGoTrackButton()));
//            mainPage.setInputTrack("952951");// ввести правильный номер заказа для отладки; Почему-то в хроме этот тест будет проходить
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
