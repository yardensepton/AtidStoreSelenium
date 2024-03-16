package Pages;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;

import java.util.Random;

public class CartPage {
    private Logger logger;
    private WebDriver driver;

    public CartPage(WebDriver d){
        this.driver=d;
    }

    public void setup() {
        driver.get("https://atid.store/store/");
        driver.manage().window().setSize(new Dimension(1024, 670));

        logger.info("---------------------ADD PRODUCT TEST---------------------");

        // Log information about opening the first store page
        logger.info("Opening the first store page - GOOD");

        Random rand = new Random();


        String cart = "cart-2/";
    }


}

