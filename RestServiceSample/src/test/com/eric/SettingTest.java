package com.eric;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;
import org.testng.annotations.Test;

public class SettingTest {

    /**
     * get all settings data
     */
    @Test
    void getAllSettingTest() {
        //The base URI to be used
        RestAssured.baseURI = "http://localhost:8080/rest/settings";

        //Define the specification of request. Server is specified by baseURI above.
        RequestSpecification httpRequest = RestAssured.given();

        //Makes calls to the server using Method type.
        Response response = httpRequest.request(Method.GET, "");

        //Checks the Status Code
        int statusCode = response.getStatusCode();
        Assert.assertEquals(statusCode, 200);
    }

    /**
     * get a Setting by setting's id.
     */
    @Test
    void getSettingByIdTest() {
        //The base URI to be used
        RestAssured.baseURI = "http://localhost:8080/rest/settings/";

        //Define the specification of request. Server is specified by baseURI above.
        RequestSpecification httpRequest = RestAssured.given();

        //Makes calls to the server using Method type.
        Response response = httpRequest.request(Method.GET, "2321763c-8e06-4a31-873d-0b5dac2436da");

        //Checks the Status Code
        int statusCode = response.getStatusCode();
        Assert.assertEquals(statusCode, 200);
    }

    /**
     * Get endpoint, which can response jukebox data by settingId
     */
    @Test
    void getJukeboxBySettingIdTest() {
        //The base URI to be used
        RestAssured.baseURI = "http://localhost:8080/rest/settings/2321763c-8e06-4a31-873d-0b5dac2436da/jukeboxes";

        //Define the specification of request. Server is specified by baseURI above.
        RequestSpecification httpRequest = RestAssured.given();

        //Makes calls to the server using Method type.
        Response response = httpRequest.request(Method.GET, "");

        //Checks the Status Code
        int statusCode = response.getStatusCode();
        Assert.assertEquals(statusCode, 200);
    }
}
