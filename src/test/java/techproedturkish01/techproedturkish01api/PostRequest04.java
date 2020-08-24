package techproedturkish01.techproedturkish01api;

import static io.restassured.RestAssured.given;

import org.junit.Test;
import org.testng.asserts.SoftAssert;

import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class PostRequest04 extends TestBase{
	
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

//	4. YOL: POJO ile (Plain Old Java Object)
	
//			Bu Class BookingDates() ve Booking() class'lardan Const. ve method kullanarak calisiyor
	@Test
	public void post01() { 
		
		BookingDates bookingDates = new BookingDates("2020-05-02", "2020-05-05"); //Ctrl+Space ile Cont icini gor
		Booking booking = new Booking("Resit", "Yurt", 123, true, bookingDates, "Wifi");
		
		Response response = given().
								contentType(ContentType.JSON).
								spec(spec01).
								auth().
								basic("admin", "password123").
								body(booking).
							when().
								post("/booking");
		response.prettyPrint();
		//Status Code 200 olmali
		response.
		then().
		statusCode(200);
		
		// Response Body, Request Body ile ayni oldugunu verify ediniz. (JsonPath ile)
		JsonPath json = response.jsonPath();
		SoftAssert softAssert = new SoftAssert();
		
		// 1. firstname assertion
		softAssert.assertEquals(json.getString("booking.firstname"), "Resit");
		
		// 2. lastname assertion		
		softAssert.assertEquals(json.getString("booking.lastname"), "Yurt");
		
		// 3. totalprice assertion
		softAssert.assertEquals(json.getInt("booking.totalprice"), 123);
		
		// 4. depositpaid assertion
		softAssert.assertEquals(json.getBoolean("booking.depositpaid"), true);
		
		// 5. checkin assertion
		softAssert.assertEquals(json.getString("booking.bookingdates.checkin"), "2020-05-02");
		
		// 6. checkout assertion
		softAssert.assertEquals(json.getString("booking.bookingdates.checkout"), "2020-05-05");
		
		// 7. additionalneeds assertion		
		softAssert.assertEquals(json.getString("booking.additionalneeds"), "Wifi");
		softAssert.assertAll();
		
		
		
	}
}
