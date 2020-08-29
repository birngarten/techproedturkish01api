package techproedturkish01.techproedturkish01api;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

import org.junit.Test;

import io.restassured.response.Response;
import utilities.JsonUtil;

public class ObjectMapperTestWithPojo extends TestBase {
	
	@Test
	public void javaToJson() {
		
		BookingDates bookingDates = new BookingDates("2020-11-20", "2020-11-25");
		
		// bookingDates Java Object'ini json formata cevirdik ==> Serialization
		String jsonFromPojo = JsonUtil.convertJavaToJson(bookingDates);
		System.out.println(jsonFromPojo);
		
	}
	
	@Test
	public void jsonToJava() {
		Response response = given().
				spec(spec01).
			when().
				get("/booking/3");
		response.prettyPrint();
//		API'dan gelen Json Data'yi Pojo class'a cevirdik
		Booking jsonToPojoApi = JsonUtil.convertJsonToJava(response.asString(), Booking.class);
		System.out.println(jsonToPojoApi);
		
//		Tset Case'de verilen Json formatindaki data'yi Pojo object'ine cevirdik
		BookingDates bookingDates = new BookingDates("2020-11-20", "2020-11-25");
		Booking booking = new Booking("Susan", "Jones", 277, true, bookingDates, "Breakfast");
		response.then().assertThat().statusCode(200);
		
		assertEquals(booking.getBookingdates().getCheckin(), jsonToPojoApi.getBookingdates().getCheckin());
		assertEquals(booking.getBookingdates().getCheckout(), jsonToPojoApi.getBookingdates().getCheckout());



	}

}
