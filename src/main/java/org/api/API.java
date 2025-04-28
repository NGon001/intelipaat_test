package org.api;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.util.Map;

public class API {
    protected String loginUrl = "https://intellipaat.com/wp-admin/admin-ajax.php?callback=jQuery371039282904034390165_1745443840173";

    public static String encodeFormData(Map<String, String> data) throws UnsupportedEncodingException {
        StringBuilder encodedBody = new StringBuilder();

        for (Map.Entry<String, String> entry : data.entrySet()) { // get key and val for encode in url
            if (!encodedBody.isEmpty()) { // add & after key and val for url
                encodedBody.append("&");
            }
            // Encode key
            encodedBody.append(URLEncoder.encode(entry.getKey(), StandardCharsets.UTF_8));
            encodedBody.append("=");

            // Encode value
            encodedBody.append(URLEncoder.encode(entry.getValue(), StandardCharsets.UTF_8));
        }

        // Get encoded data
        return encodedBody.toString();
    }

    public HttpResponse<String> postReq(String url, String body, Map<String, String> headers) throws IOException, InterruptedException {
        try{
            // build req
            HttpRequest.Builder builder = HttpRequest.newBuilder()
                    .uri(URI.create(url))
                    .POST(HttpRequest.BodyPublishers.ofString(body));

            // check if headers exist, if yes, put it into build req
            if (headers != null) {
                headers.forEach(builder::header);
            }


            HttpRequest request = builder.build();             // final build
            HttpClient client = HttpClient.newHttpClient();             // creating client
            return client.send(request, HttpResponse.BodyHandlers.ofString()); // making req
        }catch (Exception e){
            System.out.println("Exception in req: " + e); // if something wrong with req it will send exception
        }
        return null; // if req got expedition return null string
    }

    //example "jQuery371039282904034390165_1745443840173({"result":true,"message":"Login Successful, redirecting...","redirect":"https:\/\/intellipaat.com\/?loggedin","action":"login"})"
    public boolean getResult(String response_body){
        int start = response_body.indexOf('(') + 1; // starting from {
        int end = response_body.lastIndexOf(')'); // ending in )
        String json = response_body.substring(start, end); // put it into json
        Gson gson = new Gson();
        JsonObject obj = gson.fromJson(json, JsonObject.class); // parsing

        return obj.get("result").getAsBoolean(); // getting ""result":true" from json
    }

}
