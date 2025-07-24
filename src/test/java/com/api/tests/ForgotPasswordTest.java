package com.api.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.api.base.AuthService;
import com.api.models.request.SignUpRequest;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class ForgotPasswordTest {

	@Test(description = "Verify If ForgotPassword API is working .... ")
	public void forgotPasswordTest() {
		
		  // Send the request
	    AuthService authService = new AuthService();
	    Response response = authService.forgotPassword("manish2@gmail.com");
	    String responseBody = response.getBody().asString();

	    // Print the response for debugging
	    System.out.println("Response: " + responseBody);

	    // Parse the response JSON
	    JsonPath jsonPath = new JsonPath(responseBody);
	    String actualMessage = jsonPath.getString("message");

	 // Validate known possible messages
	    if ("If your email exists in our system, you will receive reset instructions.".equals(actualMessage)) {
	        Assert.assertEquals(actualMessage, "If your email exists in our system, you will receive reset instructions.");
	    } else if ("An unexpected error occurred".equals(actualMessage)) {
	        Assert.fail("API returned unexpected error: " + actualMessage);
	    } else {
	        Assert.fail("Unexpected response message: " + actualMessage);
	    }
	
	}
}
