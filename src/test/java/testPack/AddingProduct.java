package testPack;

import Pages.CartPage;
import Pages.StorePage;
import org.junit.Test;
import org.junit.internal.TextListener;
import org.junit.runner.JUnitCore;
import org.junit.Before;
import org.junit.After;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.Dimension;


import java.io.IOException;
import java.util.*;


import org.apache.logging.log4j.*;

public class AddingProduct {
    private WebDriver driver;
    private Logger logger;
    private final int AMOUNT_MAX = 10;

    @Before
    public void setUp() throws IOException {
        driver = BaseTest.initializeDriver();
        logger = LogManager.getLogger(AddingProduct.class);
    }

    @After
    public void tearDown() {
        // driver.quit();
    }

    @Test
    public void runTest() throws InterruptedException {
        // Open the Atid Store website
        driver.get("https://atid.store/store/");
        driver.manage().window().setSize(new Dimension(1024, 670));

        logger.info("---------------------ADD PRODUCT TEST---------------------");

        // Log information about opening the first store page
        logger.info("Opening the first store page - GOOD");

        Random rand = new Random();
        StorePage storePage = new StorePage(driver);
        CartPage cartPage = new CartPage(driver);

       int productAmount = storePage.getSize();

        Map<String, String> productDetailsMap = storePage.getProductsInHashMap(productAmount);


        for (Map.Entry<String, String> entry : productDetailsMap.entrySet()) {

            String productLink = entry.getKey();
            String productPrice = entry.getValue();

            driver.get(productLink);
            if (storePage.outOfStockCanBeAddedToCart()) {
                logger.info("Product is out of stock and can be added to cart - BAD");
            } else if (storePage.productInStock()) {
                int amount = rand.nextInt(15) + 1;
                storePage.sendAmountToInputButton(amount);
                storePage.clickAddToCart();

                // Check if the amount is not valid and the alert button is present
                if (storePage.alertAddedToCartPresent()) {
                    if (amount > AMOUNT_MAX) {
                        logger.info("Product " + extractProductName(productLink) + " was added  " + amount + " times - BAD");
                        ///cart////
                        cartPage.goToCartPage();

                    } else {
                        logger.info("Product " + extractProductName(productLink) + " was added  " + amount + " times - GOOD");
                        cartPage.goToCartPage();

                        double productPriceDouble = Double.parseDouble(productPrice);

                        logger.info("product price is " + productPriceDouble + ", price in the cart is " + cartPage.getPriceInCart());

                        Thread.sleep(1000);
                        if (cartPage.isProductTotalPriceEqualsToPriceInCart(productPriceDouble,amount)) {
                            logger.info("price in the cart matches product price - GOOD");
                        } else {
                            logger.info("price in the cart doesn't match product price - BAD");
                        }

                    }
                    cartPage.clickRemove();
                    logger.info("-------------------------------");


                }
            }
        }
    }


    public static String extractProductName(String url) {
        return url.replaceAll(".*/product/(.*?)/.*", "$1");
    }

    public static void main(String args[]) {
        JUnitCore junit = new JUnitCore();
        junit.addListener(new TextListener(System.out));
        org.junit.runner.Result result = junit.run(AddingProduct.class);

        if (result.getFailureCount() > 0) {
            System.out.println("Test failed.");
            System.exit(1);
        } else {
            System.out.println("Test finished successfully.");
            System.exit(0);
        }
    }
}