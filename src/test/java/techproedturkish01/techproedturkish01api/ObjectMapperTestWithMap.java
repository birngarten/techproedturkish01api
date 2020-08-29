package techproedturkish01.techproedturkish01api;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

import io.restassured.response.Response;
import utilities.JsonUtil;
import static io.restassured.RestAssured.*;
import static org.junit.Assert.assertEquals;

public class ObjectMapperTestWithMap extends TestBase{
	
	@Test
	public void javaToJson() {
		
		HashMap<Integer, String> map = new HashMap<>();
		map.putIfAbsent(101, "Ali");
		map.putIfAbsent(102, "Can");
		map.putIfAbsent(103, "Remziye");
		
		System.out.println(map); // {101=Ali, 102=Can, 103=Remziye}
		
//		Map Java Object'ini 
		String jsonFromMap = JsonUtil.convertJavaToJson(map);
		System.out.println(jsonFromMap);  // {"101":"Ali","102":"Can","103":"Remziye"}
		
	}
	
	@Test
	public void jsonToJava() {
		
		Response response = given().
								spec(spec01).
							when().
								get("/booking/3");
		response.prettyPrint();
//		API'dan gelen Json formatindaki data'yi Map'e cevirdik ==> De-Serialization
		Map<String, Object> jsonToMapApi = JsonUtil.convertJsonToJava(response.asString(), Map.class);
		System.out.println(jsonToMapApi);
		
		/*
		 1) API'dan gelen Json formatindaki data'yi Map'e cevirdim
		 2) TestCase'de bana verilen data'yi Map'e cevirecegim
		 3) 1.adimdaki olusturulan Map ile 2.adimda olusturulan Map'teki data'lari karsilastirarak
		  	verification yapilir.		 
		 */
		
		Map<String, Object> jsonToMapTestCase = new HashMap<>();
		jsonToMapTestCase.put("firstname", "Jim");
		jsonToMapTestCase.put("lastname", "Jones");
		jsonToMapTestCase.put("totalprice", 764);
		jsonToMapTestCase.put("depositpaid", false);
		
		response.
			then().
			assertThat().
			statusCode(200);
		assertEquals(jsonToMapTestCase.get("firstname"),jsonToMapApi.get("firstname"));
		assertEquals(jsonToMapTestCase.get("lastname"),jsonToMapApi.get("lastname"));
		assertEquals(jsonToMapTestCase.get("totalprice"),jsonToMapApi.get("totalprice"));
		assertEquals(jsonToMapTestCase.get("depositpaid"),jsonToMapApi.get("depositpaid"));
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
	}
}
