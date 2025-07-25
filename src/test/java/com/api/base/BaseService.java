package com.api.base;

import static io.restassured.RestAssured.given;

import com.api.filters.LoggingFilter;
import com.api.models.request.LoginRequest;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class BaseService { //Wrapper class for Rest Assured
	//Handle Base URI
	//Creating the Request
	//Handling the Response

	//Constant always written in Upper case - Base_URL
	//Anything constant will mark with final keyword
	/*If anything become final then it has to be static. 
	because final will never come alone. Final will come with static or variables*/
	private static final String BASE_URL = "http://64.227.160.186:8080";
	private RequestSpecification requestSpecification;
	
	static {
		RestAssured.filters(new LoggingFilter()); //Call the LogginFilter class to add the log
	}
	public BaseService() {//Job of default constructor - To initialize the instant variable
		requestSpecification = given().baseUri(BASE_URL);
	}
	
	protected void setAuthToken(String token) {
		requestSpecification.header("Authorization", "Bearer " + token);
	}
	
	protected Response postRequest(Object payload, String endpoint){
		return requestSpecification.contentType(ContentType.JSON).body(payload).post(endpoint);
	}
	
	protected Response putRequest(Object payload, String endpoint){
		return requestSpecification.contentType(ContentType.JSON).body(payload).put(endpoint);
	}
	
	//UserManagement API method
	protected Response getRequest(String endPoint) {
		return requestSpecification.get(endPoint);
	}
	
	protected Response postRequest(String baseURL, Object payload, String endpoint){
		return requestSpecification.baseUri(baseURL).contentType(ContentType.JSON).body(payload).post(endpoint);
	}
}
