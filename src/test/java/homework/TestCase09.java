package homework;


import POM.CartPage;
import POM.CheckoutPage;
import POM.LoginPage;
import driver.driverFactory;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.AssertJUnit;
import org.testng.annotations.Test;

import java.io.File;
import java.util.List;
import java.util.Locale;

/*--------------TESTCASE08-------------------------

    *  Verify you are able to change or reorder previously added product

    *  Test Data = QTY = 10

    Test Steps:

    1. Go to http://live.techpanda.org/

    2. Click on my account link

    3. Login in application using previously created credential

    4. Click on 'REORDER' link , change QTY & click Update

    5. Verify Grand Total is changed

    6. Complete Billing & Shipping Information

    7. Verify order is generated and note the order number

    Expected outcomes:

    1) Grand Total is Changed

    2) Order number is generated
*/
public class TestCase09 {
    WebDriver driver = driverFactory.getChromeDriver();


    @Test
    public void TestCase09(){
        WebDriver driver = driverFactory.getChromeDriver();

        String COUPON_CODE = "GURU50";

        try {
            // Step 1
            driver.get("http://live.techpanda.org/");

            /**
             *  Step 2: Click on �MOBILE� menu
             */
            WebElement moblieLink = driver.findElement(new By.ByCssSelector("body > div:nth-child(1) > div:nth-child(2) > header:nth-child(2) > div:nth-child(1) > div:nth-child(4) > nav:nth-child(1) > ol:nth-child(1) > li:nth-child(1) > a:nth-child(1)"));
            moblieLink.click();

            WebElement iphone = driver.findElement(By.cssSelector(".item:first-child .actions > .btn-cart"));
            iphone.click();

            String priceBefore = driver.findElement(By.cssSelector("#shopping-cart-totals-table tfoot .price"))
                    .getText();

            // remove the $ sign and the ',' sign

            Float priceBeforeFloat = Float.parseFloat(priceBefore.substring(1).replace(",", ""));

            System.out.println("Price before: " + priceBeforeFloat);

            WebElement couponCode = driver.findElement(By.cssSelector("#coupon_code"));

            couponCode.sendKeys(COUPON_CODE);

            driver.findElement(By.cssSelector(".discount-form .button-wrapper button:first-child")).click();

            String priceDiscount = driver
                    .findElement(By.cssSelector("#shopping-cart-totals-table tbody tr:last-child .price")).getText();
            // remove the -$ sign and the ',' sign
            priceDiscount = priceDiscount.substring(2).replace(",", "");

            Float priceDiscountFloat = Float.parseFloat(priceDiscount);

            System.out.println("Price discount: " + priceDiscountFloat);

            Float expectedPrice = priceBeforeFloat * 0.05f;

            // check the discount price 5%
            Assert.assertEquals(priceDiscountFloat, expectedPrice);

            // check the grand total after discount
            String priceAfter = driver.findElement(By.cssSelector("#shopping-cart-totals-table tfoot .price")).getText();

            System.out.println("priceBefore: " + priceBefore);
            System.out.println("priceAfter: " + priceAfter);

            Assert.assertNotEquals(priceBefore, priceAfter);

            // screenshot the result
            File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
            String png = ("D:\\FPT\\Kì 5\\SWT\\SWT301-SeleniumWebdriver-Ecommerce-main\\SWT301-SeleniumWebdriver-Ecommerce-main\\src\\test\\java\\homework\\TestCase09.png");
            FileUtils.copyFile(scrFile, new File(png));

        } catch (Exception e) {
            Assert.fail("Test case TC09 is failed:" + e.getMessage());
        }

        driver.quit();
    }
}