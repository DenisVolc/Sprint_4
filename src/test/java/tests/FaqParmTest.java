package tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import pageobjects.MainPage;
import constants.URL;


import java.util.concurrent.TimeUnit;
@RunWith(Parameterized.class)
public class FaqParmTest {


    WebDriver driver;
    private final int questionNumber;

    public FaqParmTest( int questionNumber) {
        this.questionNumber = questionNumber;
    }

    @Parameterized.Parameters // добавили аннотацию
    public static Object[][] accordionQuestionsNum() {
        return new Object[][]{
                {0},
                {1},
                {2},
                {3},
                {4},
                {5},
                {6},
                {7},
        };
    }
    @Before
    public void setUp(){
            WebDriverManager.chromedriver().setup();
            driver = new ChromeDriver();

        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);

    }
    @Test
    public void faqTest(){
        MainPage mnPage = new MainPage(driver); // Требование "Тесты для раздела "Вопросы и важном" реализуй через параметризацию."
        // реализовал через параметризацию баузера :)
        driver.get(URL.MAIN_PAGE_URL);
        mnPage.acceptCookies();
        mnPage.findFAQ();
        mnPage.pressQuestion(questionNumber);
        Assert.assertTrue(mnPage.isEnabledAnswer(questionNumber));

    }
    @After
    public void quit(){
        driver.quit();
    }
}


//TODO Общие начала и концы лучше вынести в общий класс предок
////Можно улучшить
// src/test/java/Tests/LogoTests.java
//            driver = new FirefoxDriver();
//        }
//        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
//
//