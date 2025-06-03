package tests;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import pageobject.MainPage;
import pageobject.OrderPage;

import java.util.Arrays;
import java.util.Collection;

@RunWith(Parameterized.class)
public class OrderTest {
    private WebDriver driver;
    private final String name;
    private final String surname;
    private final String address;
    private final String metro;
    private final String phone;
    private final String date;
    private final String comment;
    private final boolean useTopButton;

    public OrderTest(String name, String surname, String address, String metro,
                     String phone, String date, String comment, boolean useTopButton) {
        this.name = name;
        this.surname = surname;
        this.address = address;
        this.metro = metro;
        this.phone = phone;
        this.date = date;
        this.comment = comment;
        this.useTopButton = useTopButton;
    }
    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][]{
                {"Иван", "Иванов", "ул. Сезам, д. 13", "Черкизовская", "89991111313", "03.06.2025", "Позвонить в колокол", true},
                {"Аркадий", "Смирнов", "ул. Гоголя, д. 666", "Красные Ворота", "88003535458", "09.09.2025", "Без звонка", false}
        });
    }

    @Before
    public void setUp() {
        driver = new ChromeDriver();
        //driver = new EdgeDriver();
        driver.get("https://qa-scooter.praktikum-services.ru/");
    }

    @Test
    public void testOrderScooter() {
        MainPage mainPage = new MainPage(driver);
        if(useTopButton){
            mainPage.clickTopButtonOrder();
        } else {
            mainPage.scrollToButtonDown();
            mainPage.clickDownButtonOrder();
        }

        OrderPage orderPage = new OrderPage(driver);
        orderPage.fillFirstForm(name, surname, address, metro, phone);
        orderPage.fillSecondForm(date, comment);
        Assert.assertTrue("Заказ не был подтверждён!", orderPage.isOrderDisplayed());
    }

    @After
    public void finish() {
        driver.quit();
    }
}
