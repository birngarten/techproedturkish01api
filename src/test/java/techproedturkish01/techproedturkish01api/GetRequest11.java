package techproedturkish01.techproedturkish01api;

import org.junit.Test;
import org.testng.asserts.SoftAssert;

import com.google.gson.Gson;

import io.restassured.response.Response;
import static io.restassured.RestAssured.*;
import java.util.HashMap;

public class GetRequest11 extends TestBase {

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
								get("/2");
		response.prettyPrint();
		
		HashMap<String, String> map = response.as(HashMap.class); // ==> (De-Serialization)
		System.out.println(map);//{id=2.0, completed=false, 
//											title=quis ut nam facilis et officia qui, userId=1.0}
		
		System.out.println(map.keySet()); // ==> [id, completed, title, userId]
		System.out.println(map.values());//[2.0, false, quis ut nam facilis et officia qui, 1.0]
		
//		completed key'sinin degerinin false oldugunu 'verify' ediniz?
		SoftAssert softAssert = new SoftAssert();
		softAssert.assertEquals( map.get("completed"),false);
		
		
//		userId, id ve title degerlerini verify ediniz
		softAssert.assertEquals(map.get("userId"), 1);
		softAssert.assertEquals(map.get("id"), 2);
		softAssert.assertEquals(map.get("title"), "quis ut nam facilis et officia qui");
		softAssert.assertAll();
		
//		Java Object'ini Json formatina cevirme
		Gson gson = new Gson();
		String jsonFromMapClass = gson.toJson(map);
		System.out.println(jsonFromMapClass);
		
		
		
		
		
		
		
		
		
	}
}
