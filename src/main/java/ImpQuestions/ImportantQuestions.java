package ImpQuestions;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;

public class ImportantQuestions {
    private WebDriver driver;
    private By importantQuestionsHeader = By.xpath(".//div[text()='Вопросы о важном']");

    public ImportantQuestions(WebDriver driver){
        this.driver = driver;
    }

    public String checkTextInDropdown(String text) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", driver.findElement(importantQuestionsHeader));
        driver.findElement(By.xpath(".//div[@class='accordion']//div[contains(text(),'" + text + "')]")).click();
        return driver.findElement(By.xpath(".//div[contains(text(), '" + text + "')]/parent::div/parent::div/div[last()]/p")).getText();
    }
}
