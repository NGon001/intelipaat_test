package org.base;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Base {
    public static WebDriver driver;
    public static WebDriverWait wait;
    public ChromeOptions chromeOptions = new ChromeOptions();

    public void setDriver(WebDriver driver){
        Base.driver = driver;
    }

    public void setWait(WebDriverWait wait){
        Base.wait = wait;
    }

    protected WebElement find(By locator){
        return driver.findElement(locator);
    }

    protected WebElement findWithWait(By locator){
        return wait.until(ExpectedConditions.presenceOfElementLocated(locator));
    }

    public void click(By locator){
        find(locator).click();
    }

    public void input(By locator,String text){
        find(locator).sendKeys(text);
    }
}
