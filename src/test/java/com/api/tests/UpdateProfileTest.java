package com.api.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.api.base.AuthService;
import com.api.base.UserProfileManagementService;
import com.api.models.request.LoginRequest;
import com.api.models.request.ProfileUpdateRequest;
import com.api.models.response.LoginResponse;
import com.api.models.response.UserProfileResponse;

import io.restassured.response.Response;

public class UpdateProfileTest {
	
	@Test(description = "Verify If Update User Profile API is working .... ")
	public void updateProfileInfoTest()
	{
		AuthService authService = new AuthService();
		Response response = authService.login(new LoginRequest("Manish Kumar","Magic@123"));
		LoginResponse loginResponse = response.as(LoginResponse.class);
		System.out.println(loginResponse.getToken());
		System.out.println(response.asPrettyString());
		
		System.out.println("----------------------------------------------------------");
		
		UserProfileManagementService userProfileManagementService = new UserProfileManagementService();
		response = userProfileManagementService.getProfile(loginResponse.getToken());
		System.out.println(response.asPrettyString());
		UserProfileResponse userProfileResponse = response.as(UserProfileResponse.class);
		System.out.println(userProfileResponse.getUsername());
		Assert.assertEquals(userProfileResponse.getUsername(), "Manish Kumar");
		
		System.out.println("----------------------------------------------------------");
		
		ProfileUpdateRequest profileUpdateRequest = new ProfileUpdateRequest.Builder()
				.firstName("Sohan")
				.lastName("Singh")
				.email("sohan@gmail.com")
				.mobileNumber("5465645665")
				.build();
		
		response = userProfileManagementService.updateProfile(loginResponse.getToken(), profileUpdateRequest);
		System.out.println(response.asPrettyString());
	}

}
