import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.firefox.FirefoxDriver;
import page_objects.*;


import java.util.concurrent.TimeUnit;
//TODO параметризацию для браузеров

public class Tests {
    WebDriver driver;
    public final String mainPageUrl= "https://qa-scooter.praktikum-services.ru/";
    @Before
    public void setUp(){
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();

//        WebDriverManager.firefoxdriver().setup();
//        driver = new FirefoxDriver();

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
    public void orderPageTestN2(){
        MainPage mainPage = new MainPage(driver);
        driver.get(mainPageUrl);
        mainPage.acceptCookies();

//        нажать на кнопку заказа(верхнюю или нижнюю)
        mainPage.clickUpperOrder();
        OrderPage orderPage = new OrderPage(driver);

//        заполнить форму заказа

        //Перва форма
        orderPage.inputName("Иван");
        orderPage.inputSecondName("Иванов");
        orderPage.inputAddress("Проспект Ленина д.1");
        orderPage.chooseMetroStation(0);
        orderPage.inputPhoneNumber("89151234567");
        orderPage.clickNextButton();

        //Вторая форма
        orderPage.setDate("01.01.2021");
        orderPage.chooseDuration(0);
        orderPage.clickCheckBox(0);
        orderPage.writeComment("Hello World");
        orderPage.clickNextButton();

//        проверить всплывающее окно
        orderPage.clickYesButton();
        Assert.assertTrue("Не отображается подтверждение заказа",orderPage.isAppearOrderConfirmed());
    }
    @Test
    public void samokatLogoTestN3(){
        MainPage mainPage = new MainPage(driver);
        driver.get(mainPageUrl);
        mainPage.acceptCookies();

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
        Assert.assertTrue(driver.getCurrentUrl().contains("dzen.ru")); //проверить url//проверить url
    }

    @Test
    public void orderErrorsTestN5(){

//        Проверить ошибки для всех полей формы заказа.

    }

    @Test
    public void wrongOrderTestN6(){
        MainPage mainPage = new MainPage(driver);
        TrackPage trackPage = new TrackPage(driver);
        driver.get(mainPageUrl);
        mainPage.acceptCookies();
//        Проверить: если ввести неправильный номер заказа, попадёшь на страницу статуса заказа
//        На ней должно быть написано, что такого заказа нет.
        mainPage.clickTrackButton();//Кликнуть "Статус заказа"
//        mainPage.setInputTrack("1");//ввести неверный номер заказа
        mainPage.setInputTrack("952951");// правильный номер заказа;
        mainPage.clickGoTrackButton();//нажать Го
        boolean result = trackPage.isDisplayedNotFoundImg();
        Assert.assertTrue(result);//проверить запись "Такого заказа нет"
        //TODO Если вводить правильный номер, то isDisplayedNotFoundImg() через раз возвращаяет true, но должен всегда false
    }

    @After
    public void quit(){
        driver.quit();
    }
}
