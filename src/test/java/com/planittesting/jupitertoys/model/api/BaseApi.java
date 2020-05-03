package com.planittesting.jupitertoys.model.api;

import com.planittesting.jupitertoys.support.Settings;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;

public class BaseApi {

    private static String api_endpoint;
    private String resource;
    private RequestSpecification request;
    private String requestMethod;

    public BaseApi(String resource, String requestMethod) {
        api_endpoint = Settings.getApi_endpoint();
        this.resource = resource;
        this.requestMethod = requestMethod;
        RestAssured.baseURI = api_endpoint + "/" + resource;
        request = RestAssured.given();
    }

    public void handleError(Response response) {
        int statusCode = response.getStatusCode();
        switch (requestMethod.toUpperCase()) {
            case "GET":
                Assert.assertEquals(statusCode, 200);
                break;
            case "PUT":
                Assert.assertEquals(statusCode, 200);
                break;
            case "POST":
                Assert.assertTrue((statusCode==200)||(statusCode==201), "Error: request unsuccessful, the status code is " + statusCode);
                break;
            default:
                Assert.assertTrue(false, "Error: invalid request method. Request method is: " + requestMethod);
        }
    }

    
}

