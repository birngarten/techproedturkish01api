package techproedturkish01.techproedturkish01api;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import io.restassured.response.Response;

public class GetRequest07 extends TestBase{
	/*
	 Among the data there are someones whose first name is “Susan”
	 URL spec01 from TestBase
	 */
	
	@Test
	public void get01() {
		Response response = given().
								spec(spec01).
								get("/booking?firstname=Susan&depositpaid=true");//Iyi degil
		response.prettyPrint();
		assertTrue(response.getBody().asString().contains("bookingid"));
		
	}
// 		***		get01() ve get02() methodlari ayni isi yapiyor.
	@Test
	public void get02() {
		
		spec01.queryParams("firstname", "Susan", 
						   "depositpaid", true);//iyi
		
		Response response = given().
								spec(spec01).
								get("/booking");
		response.prettyPrint();
		assertTrue(response.getBody().asString().contains("bookingid"));
		
	}
	
}
