package com.api.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class LoginAPITest {

	@Test(description = "Verify If Login API is working .... ")
	public void loginTest()
	{
		RestAssured.baseURI ="http://64.227.160.186:8080";
		RequestSpecification rs = RestAssured.given();
		RequestSpecification rsh = rs.header("Content-Type", "application/json");
		RequestSpecification rsb = rsh.body("{\"username\": \"Manish Kumar\",\"password\": \"Magic@123\"}");
		Response response = rsb.post("/api/auth/login");
		System.out.println(response.asPrettyString());
		Assert.assertEquals(response.statusCode(), 200);		
	}
}
