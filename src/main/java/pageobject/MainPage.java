package pageobject;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class MainPage {
    private WebDriver driver;

    //Локаторы
    private final By titleFAQ = By.xpath("//div[text()='Вопросы о важном']");
    private  final By buttonTopOrder = By.xpath("//button[text()='Заказать' and @class = 'Button_Button__ra12g']");
    private final By buttonDownOrder = By.xpath("//button[text()='Заказать' and @class = 'Button_Button__ra12g Button_Middle__1CSJM']");

    public MainPage(WebDriver driver) {
        this.driver = driver;
    }

    public void scrollToFAQ() {
        WebElement faqScroll = driver.findElement(titleFAQ);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", faqScroll);
    }
    // Кликаем на первый вопрос
    public void clickQuestionByIndex(int index) {
        driver.findElement(By.id("accordion__heading-" + index)).click();
    }

    public String getAnswerTextByIndex(int index) {
        return driver.findElement(By.id("accordion__panel-" + index)).getText();
    }

    public void clickTopButtonOrder() {
        driver.findElement(buttonTopOrder).click();
    }

    public void clickDownButtonOrder() {
        driver.findElement(buttonDownOrder).click();
    }
    public void scrollToButtonDown() {
        WebElement buttonScroll = driver.findElement(buttonDownOrder);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", buttonScroll);
    }
}
