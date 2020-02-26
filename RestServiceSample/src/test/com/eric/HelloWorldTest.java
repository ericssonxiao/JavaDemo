package com.eric;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;
import org.testng.annotations.Test;

public class HelloWorldTest {

    @Test
    void getHelloTest() {
        //The base URI to be used
        RestAssured.baseURI = "http://localhost:8080/rest/hello";

        //Define the specification of request. Server is specified by baseURI above.
        RequestSpecification httpRequest = RestAssured.given();

        //Makes calls to the server using Method type.
        Response response = httpRequest.request(Method.GET, "?name=alex");

        //Checks the Status Code
        int statusCode = response.getStatusCode();
        Assert.assertEquals(statusCode, 200);
    }
}
