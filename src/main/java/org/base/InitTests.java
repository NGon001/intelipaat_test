package org.base;

import org.Pages.Blog;
import org.Pages.Login;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;

import java.time.Duration;

public class InitTests {
    protected Base base = new Base();
    protected Blog blog = new Blog();
    protected Login login = new Login();
    protected WebDriver driver;

    @BeforeTest
    public void setUp(){
        //base.chromeOptions.addArguments("--headless");
       // base.chromeOptions.addArguments("--disable-gpu");
        base.chromeOptions.addArguments("--start-maximized");
        base.chromeOptions.addArguments("--disable-extensions");
        base.chromeOptions.addArguments("--incognito");
        driver = new ChromeDriver(base.chromeOptions);
        base.setDriver(driver);
        base.setWait(new WebDriverWait(driver, Duration.ofSeconds(5)));
    }


    @AfterTest
    public void tearDown(){
        driver.quit();
    }
}
