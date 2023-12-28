package page_objects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

//https://qa-scooter.praktikum-services.ru/track?t=
public class TrackPage {
    WebDriver driver;
    public TrackPage(WebDriver driver){
        this.driver=driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy (xpath = ".//*[@alt='Not found']")
    private WebElement notFoundImg;
    @FindBy(xpath = ".//*[@class='Button_Button__ra12g Button_Middle__1CSJM' and text()='Посмотреть']")
    private WebElement lookAtButton;
    public boolean isDisplayedNotFoundImg(){
         return notFoundImg.isEnabled();


    }

    public WebElement getLookAtButton() {
        return lookAtButton;
    }
}
