package pageobject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class StatusPage {
    private WebDriver driver;


    private final By notFoundText = By.className("Track_NotFound__6oaoY");

    public StatusPage(WebDriver driver){
        this.driver = driver;
    }

    public boolean isOrderNotFound(){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(4));
        return wait.until(ExpectedConditions.visibilityOfElementLocated(notFoundText)).isDisplayed();
    }

}
