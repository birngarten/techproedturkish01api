package techproedturkish01.techproedturkish01api;
import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.given;

import java.util.HashMap;
import java.util.Map;

import org.json.JSONObject;
import org.junit.Before;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class TestBase {
	
	protected RequestSpecification spec01;
	protected RequestSpecification spec02;
	protected RequestSpecification spec03;
	protected Map<String, String> bookingDatesMap = new HashMap<>();
	protected Map<String, Object> requestBodyMap = new HashMap<>();
	protected JSONObject jsonBookingDatesBody = new JSONObject();
	protected JSONObject jsonRequestBody = new JSONObject();
	
	@Before
	public void setUp01() {
		spec01 = new RequestSpecBuilder().
				setBaseUri("https://restful-booker.herokuapp.com").
				build();				
	}
	@Before
	public void setUp02() {		
		spec02 = new RequestSpecBuilder().
				setBaseUri("http://dummy.restapiexample.com/api/v1/employees").
				build();		
	}
	
	@Before
	public void setUp03() {		
		spec03 = new RequestSpecBuilder().
				setBaseUri("https://jsonplaceholder.typicode.com/todos").
				build();		
	}
	
	protected Response createRequestBodyByJsonObjectClass() {		
		
		jsonBookingDatesBody.put("checkin", "2020-05-02");
		jsonBookingDatesBody.put("checkout", "2020-05-05");
		
		jsonRequestBody.put("firstname", "Levent");
		jsonRequestBody.put("lastname", "Yurt");
		jsonRequestBody.put("totalprice", 123);
		jsonRequestBody.put("depositpaid", true);
		jsonRequestBody.put("bookingdates", jsonBookingDatesBody); // ic JSON'i yukarida create 
										 // ettik "jsonBookingDatesBody", onu burada kullaniyoruz
		jsonRequestBody.put("additionalneeds", "Wifi");
		
		Response response = given().
	                			contentType(ContentType.JSON).
	                			spec(spec01).
	                			auth().
	                			basic("admin", "password123").
	                			body(jsonRequestBody.toString()).
                			when().
                				post("/booking");
		return response;
		
	}
	
	protected Response createRequestBodyByMap() {		
		
		bookingDatesMap.put("checkin", "2020-05-02");
		bookingDatesMap.put("checkout", "2020-05-05");		
		
		requestBodyMap.put("firstname", "Resit");
		requestBodyMap.put("lastname", "Yurt");
		requestBodyMap.put("totalprice", 123);
		requestBodyMap.put("depositpaid", true);
		requestBodyMap.put("bookingdates", bookingDatesMap);
		requestBodyMap.put("additionalneeds", "Wifi");
	
		Response response = given().
	               contentType(ContentType.JSON). // or “application/json”
	               spec(spec01).
	               auth().
	               basic("admin","password123").
	               body(requestBodyMap).
	            when().
	               post("/booking");
		return response;
		
		
	}
}
