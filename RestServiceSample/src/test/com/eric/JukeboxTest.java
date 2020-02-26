package com.eric;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;
import org.testng.annotations.Test;


public class JukeboxTest {

    /**
     * get all Jukeboxes data
     */
    @Test
    void getAllJukeboxTest() {
        //The base URI to be used
        RestAssured.baseURI = "http://localhost:8080/rest/jukeboxes";

        //Define the specification of request. Server is specified by baseURI above.
        RequestSpecification httpRequest = RestAssured.given();

        //Makes calls to the server using Method type.
        Response response = httpRequest.request(Method.GET, "");

        //Checks the Status Code
        int statusCode = response.getStatusCode();
        Assert.assertEquals(statusCode, 200);
    }

    /**
     * get a Jukebox by jukebox's id.
     */
    @Test
    void getJukeboxByIdTest() {
        //The base URI to be used
        RestAssured.baseURI = "http://localhost:8080/rest/jukeboxes/";

        //Define the specification of request. Server is specified by baseURI above.
        RequestSpecification httpRequest = RestAssured.given();

        //Makes calls to the server using Method type.
        Response response = httpRequest.request(Method.GET, "5ca94a8ac470d3e47cd4713c");

        //Checks the Status Code
        int statusCode = response.getStatusCode();
        Assert.assertEquals(statusCode, 200);
    }

    /**
     * get a Jukebox by settingId.
     */
    @Test
    void getJukeboxBySettingIdTest() {
        //The base URI to be used
        RestAssured.baseURI = "http://localhost:8080/rest/jukeboxes/";

        //Define the specification of request. Server is specified by baseURI above.
        RequestSpecification httpRequest = RestAssured.given();

        //Makes calls to the server using Method type.
        Response response = httpRequest.request(Method.GET, "?settingId=2321763c-8e06-4a31-873d-0b5dac2436da");

        //Checks the Status Code
        int statusCode = response.getStatusCode();
        Assert.assertEquals(statusCode, 200);
    }

}
