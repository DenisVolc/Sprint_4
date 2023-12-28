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
import page_objects.MainPage;

import java.util.concurrent.TimeUnit;
@RunWith(Parameterized.class)
public class FaqParmTest {
    private final String mainPageUrl= "https://qa-scooter.praktikum-services.ru/";
    WebDriver driver;
    private final String browser;
    private final int questionNumber;

    public FaqParmTest( int questionNumber,String browser) {
        this.browser = browser;
        this.questionNumber = questionNumber;
    }

    @Parameterized.Parameters // добавили аннотацию
    public static Object[][] orderTestDataSet() {
        return new Object[][]{
                {0,"chrome"},
                {1,"chrome"},
                {2,"chrome"},
                {3,"chrome"},
                {4,"chrome"},
                {5,"chrome"},
                {6,"chrome"},
                {7,"chrome"},
                {0,"mozila"},
                {1,"mozila"},
                {2,"mozila"},
                {3,"mozila"},
                {4,"mozila"},
                {5,"mozila"},
                {6,"mozila"},
                {7,"mozila"},
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
        MainPage mnPage = new MainPage(driver); // Требование "Тесты для раздела "Вопросы и важном" реализуй через параметризацию."
        // реализовал через параметризацию баузера :)
        driver.get(mainPageUrl);
        mnPage.acceptCookies();
        mnPage.findFAQ();
//      Мне показалось, что проще перебрать элементы аккордиона циклом,
//      потому что не нужно знать заранее кол-во элементов.
//      Да и не нужно руками забивать тестовые данные. Если бы их было не 7 а хотя бы 30, было бы грустно.
//        for (int i = 0; i < mnPage.getAccordion().size(); i++) {
//            mnPage.pressQuestion(i);
//            Assert.assertTrue(mnPage.isEnabledAnswer(i));
//        }
//        Реализация по ТЗ:
        mnPage.pressQuestion(questionNumber);
        Assert.assertTrue(mnPage.isEnabledAnswer(questionNumber));

    }
    @After
    public void quit(){
        driver.quit();
    }
}
