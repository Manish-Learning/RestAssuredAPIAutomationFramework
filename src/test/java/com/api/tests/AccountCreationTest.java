package com.api.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.api.base.AuthService;
import com.api.models.request.SignUpRequest;

import io.restassured.response.Response;

public class AccountCreationTest {

	@Test(description = "Verify If SignUp API is working .... ")
	public void createAccountTest() {
		
		//We Can do it via using constructor but if the parameter lenght 
		//will be more than it's hard to remember the variable position. 
		//So to solve this we can follow like below
		// Create test data
		SignUpRequest signUpRequest = new SignUpRequest.Builder()
		.userName("Manish2")
		.password("manish@123")
		.email("manish3@gmail.com")
		.firstName("Manish")
		.lastName("kumar")
		.mobileNumber("8978445544")
		.build();
		
		 // Send the request
		AuthService authService = new AuthService();
		Response response = authService.signUp(signUpRequest);
		String responseBody = response.getBody().asString();

		    // Print the response for debugging
		    System.out.println("Response: " + responseBody);

		    // Validate the response
		    if (responseBody.contains("Username is already taken!")) {
		        Assert.assertEquals(responseBody, "Error: Username is already taken!");
		        Assert.assertEquals(response.getStatusCode(), 400);
		    } else if (responseBody.contains("Email is already in use!")) {
		        Assert.assertEquals(responseBody, "Error: Email is already in use!");
		        Assert.assertEquals(response.getStatusCode(), 400);
		    } else {
		        Assert.assertEquals(responseBody, "User registered successfully!");
		        Assert.assertEquals(response.getStatusCode(), 200); // or 400 for validation errors

		    }
		}
		
	
}
