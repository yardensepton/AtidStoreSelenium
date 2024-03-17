package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CartPage {

    WebDriver driver;
    String baseUrl = "https://atid.store/";
    String cart = "cart-2/";
    By remove = By.className("remove");
    By subtotalElements = By.cssSelector(".product-subtotal");
    By quantity = By.xpath("//tbody//td[@class='product-quantity']//input[@type='number']");
    By updateCart = By.cssSelector("button.button[name='update_cart'][value='Update cart']");
    By productRemoved = By.cssSelector(".woocommerce-message");

    //Constructor that will be automatically called as soon as the object of the class is created
    public CartPage(WebDriver driver) {
        this.driver = driver;
    }

    public boolean isProductTotalPriceEqualsToPriceInCart(double productPrice, int amount) {
        return productPrice * amount == getPriceInCart();
    }

    public double getPriceInCart() {
        List<WebElement> subtotalList = driver.findElements(subtotalElements);
        String subtotalText = subtotalList.get(1).getText();
        String extractedSubtotal = extractNumber(subtotalText);
        return Double.parseDouble(extractedSubtotal);
    }

    public void goToCartPage() {
        driver.get(baseUrl.concat(cart));
    }

    public By productRemoved() {
        return productRemoved;

    }

    public void clickRemove() {
        driver.findElement(remove).click();
    }



    public List<WebElement> getQuantityField() {
        return driver.findElements(quantity);
    }

    public WebElement getUpdateCart() {
        return driver.findElement(updateCart);
    }

    public String extractNumber(String input) {
        // Define a regular expression pattern for extracting numbers with commas and
        // periods
        String regex = "(\\d{1,3}(,\\d{3})*(\\.\\d+)?)|\\d+";

        // Use a regular expression to match the pattern
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(input);

        // Find the first match
        if (matcher.find()) {
            return matcher.group().replace(",", ""); // Remove commas
        }

        // If no match is found, return an empty string
        return "";
    }
}


