package firstRESTpackage;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import org.json.JSONException;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

public class APIPOSTtesting {
	//pasted from toolsqa.com
	@Test
	public void postMethodtesting() throws JSONException
	{
		RestAssured.baseURI ="http://restapi.demoqa.com/customer";
		RequestSpecification request = RestAssured.given();
		
		
		// JSONObject is a class that represents a simple
		// JSON. We can add Key - Value pairs using the put
		// method
		JSONObject requestParams = new JSONObject();
		requestParams.put("FirstName", "sfdsdf"); // Cast
		requestParams.put("LastName", "adfdasf");
		requestParams.put("UserName", "dfg7s6d76fghfgh");
		requestParams.put("Password", "password1");
		requestParams.put("Email",  "32gdds213adfgdsfg@gmail.com");
		//request.body(requestParams.to);
		System.out.println(requestParams.toString());
		
		request.body(requestParams.toString());// adding the json object to the request
		Response response = request.post("/register");
	 
		
		int statusCode = response.getStatusCode();
		
		// Deserialize the Response body into RegistrationSuccessResponse
		RegistrationSuccessResponse responseBody = response.as(RegistrationSuccessResponse.class);
		System.out.println(responseBody.SuccessCode);
		System.out.println(responseBody.Message);
		
		//validating the response
		int expectedstatuscode =201;
		Assert.assertEquals(statusCode,expectedstatuscode);
		
		//getting the particular node from the JSON.
		String successCode = response.jsonPath().get("SuccessCode");
		System.out.println("sdfsdfsd"+successCode);
		Assert.assertEquals( "OPERATION_SUCCESS",successCode,"Correct Success code was returned");
		
		
		
		
		
	}

}
