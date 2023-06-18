import Order.OrderSamokat;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.junit.Assert.assertTrue;

@RunWith(Parameterized.class)
public class OrderTests {
    private final String buttonType;
    private final String firstName;
    private final String lastName;
    private final String metroStation;
    private final String phone;
    private final String address;
    private final String date;
    private final String dropdownValue;
    private final String comment;
    private WebDriver driver;

    public OrderTests(String buttonType, String firstName, String lastName, String address, String metroStation, String phone, String date, String dropdownValue, String comment) {
        this.buttonType = buttonType;
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.metroStation = metroStation;
        this.phone = phone;
        this.date = date;
        this.dropdownValue = dropdownValue;
        this.comment = comment;
    }

    @Parameterized.Parameters
    public static Object[][] genData() {
        return new Object[][] {
                {"Заказать(ввеpху)", "Алиса", "Селезнева", "Москва", "Черкизовская", "89274568522", "17.06.2023", "сутки", "тестовый комментарий" },
                {"Заказать(внизу)","Виктор", "Иванов", "Москва", "Сокольники", "89274118522", "18.06.2023", "двое суток", "тестовый комментарий для Виктора" },
        };
    }

    @Before
    public void startUp() {
        System.setProperty("webdriver.gecko.driver", "C:\\Users\\Selecty\\geckodriver-v0.33.0-win32\\geckodriver.exe");
    }

    @Test
    public void orderTest() {
        driver = new FirefoxDriver();
        driver.get("https://qa-scooter.praktikum-services.ru/");
        OrderSamokat object1 = new OrderSamokat(driver);
        object1.orderButtonClick(buttonType);
        object1.setDataToFormForWhom(firstName, lastName, address, metroStation, phone);
        object1.nextButtonClick();
        new WebDriverWait(driver, 5)
                .until(ExpectedConditions.visibilityOfElementLocated(By.className("Order_Header__BZXOb")));
        object1.setDataToFormAboutArenda(date, dropdownValue, comment);
        object1.orderButtonFormClick();
        object1.yesClick();
        assertTrue(object1.checkSuccessMessage().contains("Заказ оформлен"));
    }
    @After
    public void tearDown(){
        driver.quit();
    }
}