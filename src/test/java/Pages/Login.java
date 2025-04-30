package Pages;

import org.base.InitTests;
import org.openqa.selenium.JavascriptExecutor;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class Login extends InitTests {

    @BeforeMethod
    public void goToLoginPage() {
        login.setupLoginPage(); // Now using page function!
    }
    //new Object(){}.getClass().getEnclosingMethod().getName();
    //
    //        ((JavascriptExecutor) driver).executeScript("sauce:job-result=" + jobStatus);
    //
    //        // Set a meaningful test name and build dynamically
    //        ((JavascriptExecutor) driver).executeScript("sauce:job-name=Login Page Title Verification");
    //        ((JavascriptExecutor) driver).executeScript("sauce:job-build=Build_2025_04_29");

    @Test
    public void testCorrectDataLogin_UI() throws InterruptedException {
        String methodName = new Object(){}.getClass().getEnclosingMethod().getName();
        login.usernameInput(System.getenv("MY_EMAIL"));
        login.passwordInput(System.getenv("MY_PASSWORD"));
        login.clickSubmit();
        boolean status = login.isLoggedIn();
        String jobStatus = status ? "passed" : "failed";
        ((JavascriptExecutor) driver).executeScript("sauce:job-result=" + jobStatus);
        ((JavascriptExecutor) driver).executeScript("sauce:job-name=" + methodName);
        ((JavascriptExecutor) driver).executeScript("sauce:job-build=_" + methodName + "_Build_2025_04_29");
        Assert.assertTrue(status);
    }
    @Test
    public void testIncorrectDataLogin_UI() throws InterruptedException {
        String methodName = new Object(){}.getClass().getEnclosingMethod().getName();
        login.usernameInput(System.getenv("MY_EMAIL"));
        login.passwordInput(System.getenv("MY_PASSWORD") + "1");
        login.clickSubmit();

        boolean status = login.isLoggedIn();
        String jobStatus = status ? "failed" : "passed";
        ((JavascriptExecutor) driver).executeScript("sauce:job-result=" + jobStatus);
        ((JavascriptExecutor) driver).executeScript("sauce:job-name=" + methodName);
        ((JavascriptExecutor) driver).executeScript("sauce:job-build=_" + methodName + "_Build_2025_04_29");
        Assert.assertFalse(status);
    }
}
