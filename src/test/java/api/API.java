package api;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class API extends org.api.API {

    // 2 tests total, 1 sec 58 ms
    //===============================================
    //Default Suite
    //Total tests run: 4, Passes: 4, Failures: 0, Skips: 0
    //===============================================
    @Test
    public void testCorrectDataLoginAPI() throws IOException, InterruptedException {
        Assert.assertTrue(login(System.getenv("MY_EMAIL"),System.getenv("MY_PASSWORD"))); // check if login attempt is succeeded
    }

    @Test
    public void testIncorrectEmailLoginAPI() throws IOException, InterruptedException {
        Assert.assertFalse(login(("1" + System.getenv("MY_EMAIL")),System.getenv("MY_PASSWORD"))); // check if login attempt is succeeded
    }

    @Test
    public void testIncorrectPasswordLoginAPI() throws IOException, InterruptedException {
        Assert.assertFalse(login(System.getenv("MY_EMAIL"),System.getenv("MY_PASSWORD") + "1")); // check if login attempt is succeeded
    }
    @Test
    public void testIncorrectPasswordAndEmailLoginAPI() throws IOException, InterruptedException {
        Assert.assertFalse(login("1" + System.getenv("MY_EMAIL"),System.getenv("MY_PASSWORD") + "1")); // check if login attempt is succeeded
    }
}
