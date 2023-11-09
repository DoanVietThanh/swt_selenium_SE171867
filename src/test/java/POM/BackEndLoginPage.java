package POM;

import com.sun.jna.WString;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class BackEndLoginPage {
    WebDriver driver;
    private String userName = "user01";
    private String password = "guru99com";
    By UserId = By.id("username");
    By passwordInput = By.id("login");
    By loginButton = By.xpath("//input[@title='Login']");

    public BackEndLoginPage(WebDriver driver)
    {
        this.driver = driver;
    }

    public void enterUserId(){driver.findElement(UserId).sendKeys(userName);}

    public void enterPassword(){driver.findElement(passwordInput).sendKeys(password);}

    public  void clickLoginButton(){driver.findElement(loginButton).click();}
}