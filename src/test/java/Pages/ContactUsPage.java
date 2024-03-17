package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class ContactUsPage {

    WebDriver driver;
    By name = By.xpath("//*[@id=\"wpforms-15-field_0\"]");
    By subject = By.xpath("//*[@id=\"wpforms-15-field_5\"]");
    By email = By.xpath("//*[@id=\"wpforms-15-field_4\"]");
    By message = By.xpath("//*[@id=\"wpforms-15-field_2\"]");
    By confirmationElement = By.cssSelector("div.wpforms-confirmation-container#wpforms-confirmation-15");
    By send = By.xpath("//*[@id=\"wpforms-submit-15\"]");

    //Constructor that will be automatically called as soon as the object of the class is created
    public ContactUsPage(WebDriver driver) {
        this.driver = driver;
    }

    public void enterName(String input) {
        driver.findElement(name).sendKeys(input);
    }

    public void enterSubject(String input) {
        driver.findElement(subject).sendKeys(input);
    }

    public void enterEmail(String input) {
        driver.findElement(email).sendKeys(input);
    }

    public void enterMessage(String input) {
        driver.findElement(message).sendKeys(input);
    }

    public void clickSend() {
        driver.findElement(send).click();
    }

    public boolean confirmNotPresent() {
        List<WebElement> confirm = driver.findElements(confirmationElement);
        return confirm.isEmpty();

    }

}
