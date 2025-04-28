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
    //Total tests run: 2, Passes: 2, Failures: 0, Skips: 0
    //===============================================
    @Test
    public void testCorrectDataLoginAPI() throws IOException, InterruptedException {
        // Body data with correct password and email
        Map<String, String> formData = Map.of(
                "lwa", "1",
                "log", System.getenv("MY_EMAIL"),
                "pwd", System.getenv("MY_PASSWORD"),
                "lwa_profile_link", "https://intellipaat.com",
                "login-with-ajax", "login",
                "redirect_to", "https://intellipaat.com/?loggedin"
        );

        // Encode the form data
        String encodedForm = encodeFormData(formData);

        Map<String, String> headers = new HashMap<>();

        headers.put("Content-Type","application/x-www-form-urlencoded; charset=UTF-8"); // header for req

        var response = postReq(loginUrl,encodedForm,headers);

        var response_body = response.body(); // getting response body for finding result
        System.out.println(response.body());

        Assert.assertEquals(response.statusCode(),200); // check if req was succeeded
        Assert.assertTrue(getResult(response_body)); // check if login attempt is succeeded
    }

    @Test
    public void testIncorrectDataLoginAPI() throws IOException, InterruptedException {

        // Body data with incorrect password
        Map<String, String> formDataPassword = Map.of(
                "lwa", "1",
                "log", System.getenv("MY_EMAIL"),
                "pwd", System.getenv("MY_PASSWORD") + "1",
                "lwa_profile_link", "https://intellipaat.com",
                "login-with-ajax", "login",
                "redirect_to", "https://intellipaat.com/?loggedin"
        );

        // Body data with incorrect email
        Map<String, String> formDataEmail = Map.of(
                "lwa", "1",
                "log", "1" + System.getenv("MY_EMAIL"),
                "pwd", System.getenv("MY_PASSWORD"),
                "lwa_profile_link", "https://intellipaat.com",
                "login-with-ajax", "login",
                "redirect_to", "https://intellipaat.com/?loggedin"
        );

        // Encode the form data
        String encodedFormPassword = encodeFormData(formDataPassword);
        String encodedFormEmail = encodeFormData(formDataEmail);

        Map<String, String> headers = new HashMap<>();

        headers.put("Content-Type","application/x-www-form-urlencoded; charset=UTF-8"); // header for req

        var responsePassword = postReq(loginUrl,encodedFormPassword,headers);
        var responseEmail= postReq(loginUrl,encodedFormEmail,headers);

        var response_bodyPassword = responsePassword.body(); // getting response body for finding result
        var response_bodyEmail = responseEmail.body(); // getting response body for finding result
        System.out.println(responsePassword.body());
        System.out.println(responseEmail.body());

        Assert.assertEquals(responsePassword.statusCode(),200); // check if req was succeeded
        Assert.assertEquals(responseEmail.statusCode(),200); // check if req was succeeded

        Assert.assertFalse(getResult(response_bodyPassword)); // check if login attempt is succeeded
        Assert.assertFalse(getResult(response_bodyEmail)); // check if login attempt is succeeded
    }
}
