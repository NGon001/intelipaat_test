package org.base;
import org.openqa.selenium.Dimension;


import org.Pages.Blog;
import org.Pages.Login;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

public class InitTests {
    protected Base base = new Base();
    protected Blog blog = new Blog();
    protected Login login = new Login();
    protected RemoteWebDriver driver;

    @BeforeTest
    public void setUp() throws MalformedURLException {
        ChromeOptions browserOptions = new ChromeOptions();
        browserOptions.setPlatformName("Windows 10");
        browserOptions.setBrowserVersion("latest");
        Map<String, Object> sauceOptions = new HashMap<>();
        sauceOptions.put("username", System.getenv("MY_SAUCE_USERNAME"));
        sauceOptions.put("accessKey", System.getenv("MY_SAUCE_KEY"));
        sauceOptions.put("screenResolution", "1920x1080");
        sauceOptions.put("extendedDebugging", "true");
        browserOptions.setCapability("sauce:options", sauceOptions);

        // start the session
        URL url = new URL("https://ondemand.us-west-1.saucelabs.com:443/wd/hub");
        driver = new RemoteWebDriver(url, browserOptions);
        //driver = new ChromeDriver(base.chromeOptions);
        base.setDriver(driver);
        base.setWait(new WebDriverWait(driver, Duration.ofSeconds(5)));
        driver.manage().window().setSize(new Dimension(1920, 1080));
    }


    @AfterTest
    public void tearDown(){
        driver.quit();
    }
}
