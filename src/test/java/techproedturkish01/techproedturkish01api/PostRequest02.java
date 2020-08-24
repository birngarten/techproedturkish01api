package techproedturkish01.techproedturkish01api;

import org.junit.Test;
import org.testng.asserts.SoftAssert;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class PostRequest02 extends TestBase{	
				/*
			    POST Scenario:
			Accept type Json olsun(Content Type demektir)
			When 
			POST request yolladigimda
			1) https://restful-booker.herokuapp.com/booking
			2) Request Body 
			{
			"firstname": "Resit",
			"lastname": "Yurt",
			"totalprice": 123,
			"depositpaid": true,
			"bookingdates": {
			   "checkin": "2020-05-02",
			   "checkout": "2020-05-05"
			},
			"additionalneeds": "Wifi"
			}
			Then 
			Status Code 200 olmali
			Response Body, Request Body ile ayni oldugunu verify ediniz.
			*/
	
//	2. YOL: JsonPath ile
	@Test
	public void post01() {
				
		Response response = createRequestBodyByJsonObjectClass(); // ***createRequestBody() methodu TestBase class'ta
		
		response.prettyPrint(); // sirket testlerinde response.prettyPrint(); olmaz
		
		//Status Code 200 olmali
		response.
		then().
		statusCode(200);
		
		// Response Body, Request Body ile ayni oldugunu verify ediniz. (JsonPath ile)
		JsonPath json = response.jsonPath();
		SoftAssert softAssert = new SoftAssert();
		
		// 1. firstname assertion
		softAssert.assertEquals(json.getString("booking.firstname"), jsonRequestBody.getString("firstname"));
		
		// 2. lastname assertion		
		softAssert.assertEquals(json.getString("booking.lastname"), jsonRequestBody.getString("lastname"));
		
		// 3. totalprice assertion
		softAssert.assertEquals(json.getInt("booking.totalprice"), jsonRequestBody.getInt("totalprice"));
		
		// 4. depositpaid assertion
		softAssert.assertEquals(json.getBoolean("booking.depositpaid"), jsonRequestBody.getBoolean("depositpaid"));
		
		// 5. checkin assertion
		softAssert.assertEquals(json.getString("booking.bookingdates.checkin"), jsonBookingDatesBody.getString("checkin"));
		
		// 6. checkout assertion
		softAssert.assertEquals(json.getString("booking.bookingdates.checkout"), jsonBookingDatesBody.getString("checkout"));
		
		// 7. additionalneeds assertion		
		softAssert.assertEquals(json.getString("booking.additionalneeds"), jsonRequestBody.getString("additionalneeds"));
		softAssert.assertAll();

		
		
	}
}
