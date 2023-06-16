package Order;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;

public class OrderSamokat {
    private WebDriver driver;
    private By orderButtonUp = By.xpath(".//div[@class='Header_Nav__AGCXC']/button[text()='Заказать']");
    private By orderButtonDown = By.xpath(".//div[@class='Home_FinishButton__1_cWm']/button[text()='Заказать']");
    private By orderButtonForm = By.xpath(".//div[@class='Order_Buttons__1xGrp']/button[text()='Заказать']");
    private By firstNameField = By.xpath(".//input[@placeholder='* Имя']");
    private By lastNameField = By.xpath(".//input[@placeholder='* Фамилия']");
    private By addressField = By.xpath(".//input[@placeholder='* Адрес: куда привезти заказ']");
    private By metroStationField = By.xpath(".//input[@placeholder='* Станция метро']");
    private By phoneField = By.xpath(".//input[@placeholder='* Телефон: на него позвонит курьер']");
    private By nextButton = By.xpath(".//button[text()='Далее']");
    private By yesButton = By.xpath(".//button[text()='Да']");
    private By dateField = By.xpath(".//input[@placeholder='* Когда привезти самокат']");
    private By dropdownField = By.className("Dropdown-arrow");
    private By colorCheckbox = By.id("black");
    private By commentField = By.xpath(".//input[@placeholder='Комментарий для курьера']");

    public OrderSamokat(WebDriver driver){
        this.driver = driver;
    }

    public void orderButtonClick(String buttonName) {
        if (buttonName == "Заказать(ввеpху)") {
            driver.findElement(orderButtonUp).click();
        } else {
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", driver.findElement(orderButtonDown));
            driver.findElement(orderButtonDown).click();
        }
    }

    //заполнить форму заказа для кого
    public void setDataToFormForWhom(String firstName, String lastName, String address, String metroStation, String phone) {
        driver.findElement(firstNameField).sendKeys(firstName);
        driver.findElement(lastNameField).sendKeys(lastName);
        driver.findElement(addressField).sendKeys(address);
        driver.findElement(metroStationField).click();
        driver.findElement(By.xpath(".//button/div[contains(text(),'" + metroStation + "')]")).click();
        driver.findElement(phoneField).sendKeys(phone);
    }
    ///заполнить форму заказа про аренду
    public void setDataToFormAboutArenda(String date, String dropdownValue, String comment) {
        driver.findElement(dateField).sendKeys(date);
        driver.findElement(dropdownField).click();
        driver.findElement(By.xpath(".//div[@role='option' and text() = '" + dropdownValue + "']")).click();
        driver.findElement(colorCheckbox).click();
        driver.findElement(commentField).sendKeys(comment);
    }
    public void yesClick() {
        driver.findElement(yesButton).click();
    }
    public void nextButtonClick() {
        driver.findElement(nextButton).click();
    }
    public void orderButtonFormClick() {
        driver.findElement(orderButtonForm).click();
    }
    public String checkSuccessMessage() {
        return driver.findElement(By.xpath(".//div[@class='Order_ModalHeader__3FDaJ']")).getText();

    }
}


