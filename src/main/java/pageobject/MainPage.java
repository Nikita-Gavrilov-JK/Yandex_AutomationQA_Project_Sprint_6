package pageobject;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class MainPage {
    private WebDriver driver;

    //Локаторы
    private final By titleFAQ = By.xpath("//div[text()='Вопросы о важном']");
    private  final By buttonTopOrder = By.xpath("//button[text()='Заказать' and @class = 'Button_Button__ra12g']");
    private final By buttonDownOrder = By.xpath("//button[text()='Заказать' and @class = 'Button_Button__ra12g Button_Middle__1CSJM']");
    private final By scooterLogo = By.xpath("//a[contains(@class,'Header_LogoScooter')]");
    private final By yandexLogo = By.xpath("//a[contains(@class,'Header_LogoYandex')]");
    private final By statusButton = By.xpath("//button[text()='Статус заказа']");
    private final By inputField = By.xpath("//input[@placeholder='Введите номер заказа']");
    private final By goButton = By.xpath("//button[text()='Go!']");

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
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        By answerLocator = By.id("accordion__panel-" + index);
        WebElement answerElement = wait.until(ExpectedConditions.visibilityOfElementLocated(answerLocator));
        return answerElement.getText();
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
    public void clickScooterLogo() {
        driver.findElement(scooterLogo).click();
    }

    public void clickYandexLogo() {
        driver.findElement(yandexLogo).click();
    }

    public void clickStatusButtonEnterTextGo() {
        driver.findElement(statusButton).click();
        driver.findElement(inputField).sendKeys("7");
        driver.findElement(goButton).click();
    }
}
