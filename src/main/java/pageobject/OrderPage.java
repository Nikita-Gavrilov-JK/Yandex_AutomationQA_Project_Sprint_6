package pageobject;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;

public class OrderPage {
    private WebDriver driver;
    private final By firstName = By.xpath("//input[@type='text' and @placeholder='* Имя']");
    private final By lastName = By.xpath("//input[@type='text' and @placeholder='* Фамилия']");
    private final By address = By.xpath("//input[@type='text' and @placeholder='* Адрес: куда привезти заказ']");
    private final By metroStation = By.xpath("//input[@class='select-search__input' and @placeholder='* Станция метро']");
    private final By phoneNumber = By.xpath("//input[@type='text' and @placeholder='* Телефон: на него позвонит курьер']");
    private final By buttonNext = By.xpath("//button[text()='Далее']");
    private final By firstSelectSearch = By.className("select-search__select");
    private final By bringScooter = By.xpath("//input[@placeholder='* Когда привезти самокат']");
    private final By rentDuration = By.className("Dropdown-root");
    private final By rentOption = By.xpath("//div[@class='Dropdown-option' and text()='двое суток']");
    private final By colorScooter = By.xpath("//label[@for='black']");
    private final By comment = By.xpath("//input[@placeholder='Комментарий для курьера']");
    private final By orderButton = By.xpath("//button[text()='Заказать' and @class='Button_Button__ra12g Button_Middle__1CSJM']");
    private final By confirmButton = By.xpath("//button[text()='Да']");
    private final By succsesful = By.xpath("//*[contains(text(),'Заказ оформлен')]");
    public OrderPage(WebDriver driver) {
        this.driver = driver;
    }

    public void fillFirstForm(String name, String surname, String addr, String station, String phone) {
        driver.findElement(firstName).sendKeys(name);
        driver.findElement(lastName).sendKeys(surname);
        driver.findElement(address).sendKeys(addr);
        driver.findElement(metroStation).sendKeys(station);
        driver.findElement(firstSelectSearch).click();
        driver.findElement(phoneNumber).sendKeys(phone);
        driver.findElement(buttonNext).click();
    }

    public void fillSecondForm(String deliveryDate, String commentText) {
        driver.findElement(bringScooter).sendKeys(deliveryDate + Keys.ESCAPE);
        driver.findElement(rentDuration).click();
        driver.findElement(rentOption).click();
        driver.findElement(colorScooter).click();
        driver.findElement(comment).sendKeys(commentText);
        driver.findElement(orderButton).click();
        driver.findElement(confirmButton).click();
    }
    public boolean isOrderDisplayed() {
        return driver.findElement(succsesful).isDisplayed();
    }
}
