import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import io.github.bonigarcia.wdm.WebDriverManager;
import page_objects.*;

import java.util.concurrent.TimeUnit;
//TODO параметризацию для браузеров

public class Tests {
    WebDriver driver;

    @Before
    public void setUp(){
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);

    }

    @Test
    public void faqTest(){
        MainPage mnPage = new MainPage(driver);
        driver.get("https://qa-scooter.praktikum-services.ru/");
        mnPage.acceptCookies();
        mnPage.findFAQ();

        for (int i = 0; i < mnPage.getAccordion().size(); i++) {
            mnPage.pressQuestion(i);
            Assert.assertTrue(mnPage.isAnswerAppear(i));
        }

    }

    @Test
    public void orderPageTest(){
        MainPage mainPage = new MainPage(driver);


        driver.get("https://qa-scooter.praktikum-services.ru/");
        mainPage.acceptCookies();

//        нажать на кнопку заказа(верхнюю или нижнюю)
        mainPage.clickUpperOrder();
        OrderPage orderPage = new OrderPage(driver);
        driver.findElement(orderPage.getHeader());

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
        orderPage.orderConfirmation();//ждем появление окна подтверждения заказа
        orderPage.clickYesButton();
        //В ХРОМЕ Не могу узнать как выглядит окно подствержденного заказа потому что не могу руками прожать кнопку "да", похоже на дефект
        Assert.assertTrue("Не отображается подтверждение заказа",orderPage.isOrderConfirmedAppear());
    }

    @After
    public void quit(){
        driver.quit();;
    }
}
