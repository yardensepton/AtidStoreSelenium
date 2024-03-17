package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StorePage {

    WebDriver driver;
    By productListLocator = By.cssSelector("ul.products li.product");
    By addToCartButton = By.className("single_add_to_cart_button");
    By outOfStock = By.className("stock");
    By input = By.className("input-text");
    By price = By.cssSelector(".woocommerce-Price-amount");
    By salePrice = By.cssSelector(".price ins .woocommerce-Price-amount");
    By alertAddedToCart = By.xpath("//*[@id=\"main\"]/div/div[1]/div/a");
    By byProductLink = By.cssSelector("a.woocommerce-LoopProduct-link");
    By arrow = By.xpath("//*[@id=\"main\"]/div/nav[2]/ul/li[4]/a");


    //Constructor that will be automatically called as soon as the object of the class is created
    public StorePage(WebDriver driver) {
        this.driver = driver;
    }


    public Map<String, String> getProductsInHashMap(int size) {
        List<WebElement> firstPageProducts = driver.findElements(productListLocator);
        Map<String, String> productDetailsMap = new HashMap<>();

        // Iterate through each product in the first page
        for (int i = 0; i < size; i++) {
            WebElement priceElement = firstPageProducts.get(i).findElement(price);

            // Check if there is a sale price
            List<WebElement> saleElements = firstPageProducts.get(i).findElements(salePrice);

            if (!saleElements.isEmpty()) {
                // Sale price found, use the sale price
                priceElement = saleElements.get(0);
            }

            String productPrice = extractNumber(priceElement.getText().trim());
            String productLink = firstPageProducts.get(i).findElement(byProductLink).getAttribute("href");
            productDetailsMap.put(productLink, productPrice);
        }
        return productDetailsMap;
    }

    public int getSize() {
        List<WebElement> firstPageProducts = driver.findElements(productListLocator);
        return firstPageProducts.size();
    }


    public boolean outOfStockCanBeAddedToCart() {
        List<WebElement> outOfStockElement = driver.findElements(outOfStock);
        List<WebElement> inputButtons = driver.findElements(input);
        return !outOfStockElement.isEmpty() && !inputButtons.isEmpty();
    }


    public boolean productInStock() {
        List<WebElement> outOfStockElement = driver.findElements(outOfStock);
        return outOfStockElement.isEmpty();
    }

    public boolean arrowElementPresent() {
        List<WebElement> arrowElements = driver.findElements(arrow);
        return arrowElements.isEmpty();
    }

    public boolean alertAddedToCartPresent() {
        List<WebElement> addedToCartAlertButton = driver.findElements(alertAddedToCart);
        return !addedToCartAlertButton.isEmpty();
    }


    public void sendAmountToInputButton(int amount) {
        List<WebElement> inputButtons = driver.findElements(input);
        for (WebElement inputButton : inputButtons) {
            inputButton.clear();
            inputButton.sendKeys(Integer.toString(amount));
        }
    }

    public void goToLink(String link) {
        driver.get(link);
    }

    public void clickAddToCart() {
        driver.findElement(addToCartButton).click();
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
