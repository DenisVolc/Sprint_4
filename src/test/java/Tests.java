import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import io.github.bonigarcia.wdm.WebDriverManager;
import page_objects.*;

import java.util.List;
import java.util.concurrent.TimeUnit;

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
        mnPage.findFAQ();

        for (int i = 0; i < mnPage.getAccordion().size(); i++) {
//            i = 7; // это для отладки, коментить для прода
            mnPage.pressQuestion(i);
            Assert.assertTrue(mnPage.isAnswerAppear(i));
        }

    }

    @Test
    public void orderPageTest(){

    }

    @After
    public void quit(){
        driver.quit();;
    }
}
