package homework;


import POM.*;
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

/*--------------TESTCASE10-------------------------


*/
public class TestCase10 {
    WebDriver driver = driverFactory.getChromeDriver();

    @Test
    public  void TestCase10()
    {
        try{
            driver.get("http://live.techpanda.org/index.php/backendlogin");

            BackEndLoginPage BELoginPage = new BackEndLoginPage(driver);
            OrderManagementPage orderPage = new OrderManagementPage(driver);
            BELoginPage.enterUserId();
            BELoginPage.enterPassword();
            BELoginPage.clickLoginButton();


            driver.findElement(By.xpath("//span[normalize-space()='close']")).click();
            driver.findElement(By.xpath("//span[normalize-space()='Sales']")).click();
            driver.findElement(By.xpath("//span[normalize-space()='Orders']")).click();
            orderPage.enterFromInput();
            orderPage.enterToInput();
            orderPage.enterOrderID();
            orderPage.clickSearch();

            Thread.sleep(1000);
            File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
            String png = ("D:\\FPT\\Kì 5\\SWT\\SWT301-SeleniumWebdriver-Ecommerce-main\\SWT301-SeleniumWebdriver-Ecommerce-main\\src\\test\\java\\homework\\TestCase10.png");
            FileUtils.copyFile(scrFile, new File(png));

        }catch (Exception ex){
            ex.printStackTrace();
        }
        driver.quit();
    }
}