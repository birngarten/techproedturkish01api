package techproedturkish01.techproedturkish01api;

import org.json.JSONObject;
import org.junit.Test;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static io.restassured.RestAssured.*;

public class PutRequest01 extends TestBase{
//	Data degistirme (UpDate)
	
	@Test
	public void put01() {
		
		Response response = given().
								spec(spec03).
							when().
								get("/25");
		response.prettyPrint();
		
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("title", "Resit");
		jsonObject.put("userId", 91);
		jsonObject.put("completed", false);
		
//		PUT Response'i
		Response responseAfterPut = given().
										contentType(ContentType.JSON).
										spec(spec03).
										body(jsonObject.toString()).
									when().
										put("/25"); //put("/25"); diyerek ==> get("/25"); icinden yukarida
		responseAfterPut.prettyPrint();				// yazilan code'un datalarini degistiriyoruz
	}

}
