package techproedturkish01.techproedturkish01api;

import static org.junit.Assert.assertEquals;
import org.junit.Test;
import org.testng.asserts.SoftAssert;
import static io.restassured.RestAssured.*;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class GetRequest09 extends TestBase{
	@Test
	public void get01() {
		
		Response response = given().
								spec(spec02).
							when().
								get();
		response.prettyPrint();
		
//		JsonPath objesi olusturun
		JsonPath json = response.jsonPath();
		
//		Tum employee isimlerini consola yazdirin
//		System.out.println(json.getString("data.employee_name"));
//		23. ve 25. satirlardaki kodun sonucu ayni 
		System.out.println(json.getList("data.employee_name"));
		
//		Ikinci iscinin isminin 'Garrett Winters' oldugunu "verify"(soft assertion) ediniz
		assertEquals("Isim istenen gibi degil :","Garrett Winters",json.getString("data[1].employee_name"));
//		30. ve 32. satir ayni sonucu verir. === hard Assertion yaptik
		assertEquals("Isim istenen gibi degil :","Garrett Winters", json.getList("data.employee_name").get(1));
	
		SoftAssert softAssert = new SoftAssert();
		softAssert.assertEquals(json.getString("data[1].employee_name"), "Garrett Winters","Isim istenen gibi degil");
		
		
//		 //Iscilerin arasinda Herrod Chandler'in var oldugunu "verify" ediniz.
		softAssert.assertTrue(json.getList("data.employee_name").contains("Herrod Chandler"), "Herrod Chandler yok");	
		
		
//		24 tane isci oldugunu verify ediniz
		softAssert.assertEquals(json.getList("data.id").size(),24);
		softAssert.assertAll();

//		7. iscinin maasinin 137500 oldugunu verify ediniz
		softAssert.assertEquals(json.getString("data[6].employee_salary"), "137500","Maas istenen gibi degil");
		softAssert.assertAll();
	}

}
