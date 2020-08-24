package techproedturkish01.techproedturkish01api;

import static io.restassured.RestAssured.given;

import org.junit.Test;
import org.testng.asserts.SoftAssert;

import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class PostRequest05 extends TestBase{
	
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
		
//		*** Booking class'indan urettigimiz obje uzerinden daha dinamik bir test yaptik.
//		***	Yukaridaki veriler("Resit", "Yurt", 123, true, bookingDates, "Wifi")
//			ne kadar degisirse degissin kod yine calisir.
//									==>	(..., "Resit") bu iken ==> (..., booking.getFirstname()); oldu
		
		// 1. firstname assertion
		softAssert.assertEquals(json.getString("booking.firstname"), booking.getFirstname());
		
		// 2. lastname assertion		
		softAssert.assertEquals(json.getString("booking.lastname"), booking.getLastname());
		
		// 3. totalprice assertion
		softAssert.assertEquals(json.getInt("booking.totalprice"),(int) booking.getTotalprice());
		
		// 4. depositpaid assertion
		softAssert.assertEquals(json.getBoolean("booking.depositpaid"), (boolean)booking.getDepositpaid());
		
		// 5. checkin assertion
		softAssert.assertEquals(json.getString("booking.bookingdates.checkin"), booking.getBookingdates().getCheckin());
		
		// 6. checkout assertion
		softAssert.assertEquals(json.getString("booking.bookingdates.checkout"), booking.getBookingdates().getCheckout());
		
		// 7. additionalneeds assertion		
		softAssert.assertEquals(json.getString("booking.additionalneeds"), booking.getAdditionalneeds());
		softAssert.assertAll();
		
		
		
	}
}
