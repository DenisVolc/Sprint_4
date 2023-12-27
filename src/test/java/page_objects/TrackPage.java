package page_objects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

//https://qa-scooter.praktikum-services.ru/track?t=
public class TrackPage {
    private WebDriver driver;
    public TrackPage(WebDriver driver){
        this.driver=driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy (xpath = ".//*[@alt='Not found']")
    private WebElement notFoundImg;

    public boolean isDisplayedNotFoundImg(){
        return notFoundImg.isDisplayed();
    }
}
