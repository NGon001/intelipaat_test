package Pages;

import org.base.InitTests;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class Login extends InitTests {

    @BeforeMethod
    public void goToLoginPage() {
        login.setupLoginPage(); // Now using page function!
    }

    @Test
    public void testCorrectDataLogin_UI() throws InterruptedException {
        login.usernameInput(System.getenv("MY_EMAIL"));
        login.passwordInput(System.getenv("MY_PASSWORD"));
        login.clickSubmit();
        Assert.assertTrue(login.isLoggedIn());
    }
    @Test
    public void testIncorrectDataLogin_UI() throws InterruptedException {
        login.usernameInput(System.getenv("MY_EMAIL"));
        login.passwordInput(System.getenv("MY_PASSWORD") + "1");
        login.clickSubmit();
        Assert.assertFalse(login.isLoggedIn());
    }
}
