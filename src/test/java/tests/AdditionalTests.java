package tests;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import pageobject.MainPage;
import pageobject.OrderPage;
import pageobject.StatusPage;

import java.util.concurrent.TimeUnit;

public class AdditionalTests {
    private WebDriver driver;
    @Before
    public void setUp() {
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        driver.get("https://qa-scooter.praktikum-services.ru/");
    }

    @Test
    public void testClickScooterLogoToMainPage() {
        driver.get("https://qa-scooter.praktikum-services.ru/order");

        MainPage mainPage = new MainPage(driver);
        mainPage.clickScooterLogo();

        Assert.assertEquals("https://qa-scooter.praktikum-services.ru/", driver.getCurrentUrl());
    }
    @Test
    public void testClickYandexLogoToYandexTab() {
        MainPage mainPage = new MainPage(driver);

        String originalWindow = driver.getWindowHandle();

        mainPage.clickYandexLogo();

        for (String windowHandle : driver.getWindowHandles()) {
            if (!windowHandle.equals(originalWindow)) {
                driver.switchTo().window(windowHandle);
                break;
            }
        }

        String currentUrl = driver.getCurrentUrl();
        Assert.assertTrue("URL не содержит главной страницы Яндекса, текущая: " + currentUrl,
                currentUrl.contains("yandex"));

        driver.close();
        driver.switchTo().window(originalWindow);
    }

    //Находит 4 из 5 так как на странице поле Адрес не показывает ошибку хотя
    @Test
    public void checkErrorsAllFieldsFormOrder() {
        OrderPage orderPage = new OrderPage(driver);
        driver.get("https://qa-scooter.praktikum-services.ru/order");
        orderPage.clickNextButton();

        int errorsCount = orderPage.getNumberOfErrorFields();

        Assert.assertEquals("Ожидалось 5 обязательных полей с ошибками, но найдено: " + errorsCount, 5, errorsCount);
    }
    @Test
    public void checkInvalidOrderNumber() {
        MainPage mainPage = new MainPage(driver);
        mainPage.clickStatusButtonEnterTextGo();
        StatusPage statusPage = new StatusPage(driver);
        Assert.assertTrue("Ожидалось сообщение Заказ не найден", statusPage.isOrderNotFound());
    }

    @After
    public void tearDown() {
        driver.quit();
    }
}
