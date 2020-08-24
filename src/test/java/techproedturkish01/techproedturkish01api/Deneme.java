package techproedturkish01.techproedturkish01api;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.hamcrest.Matchers;
import org.junit.Test;
import org.testng.asserts.SoftAssert;

import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import static io.restassured.RestAssured.*;

public class Deneme extends TestBase{
	/*
	 When I send GET Request to URL
	 http://dummy.restapiexample.com/api/v1/employees
	 Then
	  Status code is 200
	  1)10'dan buyuk tum id’leri console’a yazdir
	  10'dan buyuk 14 tane id oldugunu verify et
	  2)30'dan kucuk tum yaslari console’a yazdir
	  30 dan kucuk en buyuk yasin 23 oldugunu verify et
	  3)Maasi 350000'den cok olan iscilerin isimlerini console’a yazdir
	  Charde Marshall’in maasinin 350000'den buyuk oldugunu verify et
	 */
	
	@Test
	public void get01() {
		
		Response response = given().
								spec(spec02).
							when().
								get();
		response.prettyPrint();
		response.
		then().
		assertThat().
		statusCode(200);
		
		//1)10'dan buyuk tum id’leri console’a yazdir
		JsonPath json = response.jsonPath();
		List<String> idList = json.getList("data.findAll{Integer.valueOf(it.id)>10}.id");
			SoftAssert softAssert = new SoftAssert();			
		
		 // 10'dan buyuk 14 tane id oldugunu verify et
			softAssert.assertEquals(idList.size(), 14);
			softAssert.assertAll();
		
		//  2)30'dan kucuk tum yaslari console’a yazdir
			List<String> yasList = json.getList("data.findAll{Integer.valueOf(it.employee_age)<30}.employee_age");
			System.out.println(yasList);
		//  30 dan kucuk en buyuk yasin 23 oldugunu verify et
		
		//  3)Maasi 350000'den cok olan iscilerin isimlerini console’a yazdir
		//  Charde Marshall’in maasinin 350000'den buyuk oldugunu verify et
		
		
	}
	
	
}
