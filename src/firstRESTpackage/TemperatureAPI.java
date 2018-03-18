package firstRESTpackage;


import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

	public class TemperatureAPI {

		@Test
		public void GetWeatherDetails()
		{   
			// Specify the base URL to the RESTful web service
			RestAssured.baseURI = "http://restapi.demoqa.com/utilities/weather/city";

			// Get the RequestSpecification of the request that you want to sent
			// to the server. The server is specified by the BaseURI that we have
			// specified in the above step.
			RequestSpecification httpRequest = RestAssured.given();

			// Make a request to the server by specifying the method Type and the method URL.
			// This will return the Response from the server. Store the response in a variable.
			Response response = httpRequest.request(Method.GET, "/Hyderabad");
			
			// Now let us print the body of the message to see what response
			// we have recieved from the server
			String responseBody = response.getBody().asString();
			System.out.println("Response getSessionId is =>  " + response.getSessionId());
			System.out.println("Response getStatusCode is =>  " + response.getStatusCode());
			System.out.println("Response getStatusLine is =>  " + response.getStatusLine());
			System.out.println("Response Body is =>  " + responseBody);
			String contentType = response.header("Content-Type");
			System.out.println("Content-Type value: " + contentType);
		 
			// Reader header of a give name. In this line we will get
			// Header named Server
			String serverType =  response.header("Server");
			System.out.println("Server value: " + serverType);
		 
			// Reader header of a give name. In this line we will get
			// Header named Content-Encoding
			String acceptLanguage = response.header("Content-Encoding");
			System.out.println("Content-Encoding: " + acceptLanguage);
			
			
			
			JsonPath jsonPathEvaluator = response.jsonPath();
			 
			// Then simply query the JsonPath object to get a String value of the node
			// specified by JsonPath: City (Note: You should not put $. in the Java code)
			String city = jsonPathEvaluator.get("City");
		 
			// Let us print the city variable to see what we got
			System.out.println("City received from Response " + city);
		 
			// Validate the response
			Assert.assertEquals(city, "Hyderabad", "Correct city name received in the Response");
			
			// Let us print the city variable to see what we got
			System.out.println("City received from Response " + jsonPathEvaluator.get("City"));
		 
			// Print the temperature node
			System.out.println("Temperature received from Response " + jsonPathEvaluator.get("Temperature"));
		 
			// Print the humidity node
			System.out.println("Humidity received from Response " + jsonPathEvaluator.get("Humidity"));
		 
			// Print weather description
			System.out.println("Weather description received from Response " + jsonPathEvaluator.get("Weather"));
		 
			// Print Wind Speed
			System.out.println("City received from Response " + jsonPathEvaluator.get("WindSpeed"));
		 
			// Print Wind Direction Degree
			System.out.println("City received from Response " + jsonPathEvaluator.get("WindDirectionDegree"));
			
		}

	}

