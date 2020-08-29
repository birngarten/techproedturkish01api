package techproedturkish01.techproedturkish01api;

import org.junit.Test;
import org.testng.asserts.SoftAssert;

import com.google.gson.Gson;

import io.restassured.response.Response;
import static io.restassured.RestAssured.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GetRequest12 extends TestBase {
	/*
	 GSON : 1) Json formatindaki datalari Java Objectlerine donusturur.(De-Serialization)
	 		2) Java Object'lerini Json formatindaki data'lara donusturur.(Serialization)
	 		
	 Note:	GSON ile ayni isi yapan ObjectMapper class'da var.
	 */
	@Test
	public void get01() {
		
		Response response = given().
				  				spec(spec03).
				  			when().
				  				get();
		response.prettyPrint();
		
//		Json formatindaki data'yi Java Object'ini kullanarak GSON kullanilarak cevirme ==>De-Serialization
		
		List<Map<String, Object>> listOfMap = response.as(ArrayList.class);
		
		System.out.println(listOfMap.size());
		System.out.println(listOfMap.get(0));// indeks(0)
		
		// 200 tane id oldugunu 'verify' ediniz
		SoftAssert softAssert = new SoftAssert();
		
		softAssert.assertEquals(listOfMap.size(), 200); 
//		satir 36 = 38
		softAssert.assertTrue(listOfMap.size()==200);
		
		
//		121. elemanin completed degerinin true oldugunu verify ediniz
		softAssert.assertEquals(listOfMap.get(120).get("completed"), true);

		
//		sondan bir onceki elemanin title'nin "numquam repellendus a magnam" oldugunu verify
		softAssert.assertEquals(listOfMap.get(listOfMap.size()-2).get("title"),"numquam repellendus a magnam");
		softAssert.assertAll();
		
// ***	Java Object'ini Json formatina cevirme
		Gson gson = new Gson();
		String jsonFromList = gson.toJson(listOfMap);
		System.out.println(jsonFromList);
		
	}

}
