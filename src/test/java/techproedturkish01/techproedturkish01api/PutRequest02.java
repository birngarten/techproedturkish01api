package techproedturkish01.techproedturkish01api;

import org.json.JSONObject;
import org.junit.Test;
import org.testng.asserts.SoftAssert;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import static io.restassured.RestAssured.*;

public class PutRequest02 extends TestBase {
	/*
    1)spec03 kullanarak herhangi data'yi update ediniz
    2)Update edildigini status code ve response body ile verify ediniz.
    */
	
	@Test
	public void put01() {
		
		Response response = given().
								spec(spec03).
							when().
								get("/26");
		response.prettyPrint();
		
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("title", "Levent");
		jsonObject.put("userId", 25);
		jsonObject.put("completed", false);
		
		Response responseAfterPut = given().
										contentType(ContentType.JSON).
										spec(spec03).
										body(jsonObject.toString()).
									when().
										put("/26");		
		responseAfterPut.prettyPrint();
		
		responseAfterPut.
		then().
		assertThat().
		statusCode(200);
		
		JsonPath json = responseAfterPut.jsonPath();
		SoftAssert softAssert = new SoftAssert();
		
		// completed degerini verify edelim
		softAssert.assertEquals(json.getBoolean("completed"), jsonObject.get("completed"));
		
		//title degerini verify ediniz
		softAssert.assertEquals(json.getString("title"), jsonObject.get("title"));
		
		//userId degerini verify ediniz
		softAssert.assertEquals(json.getInt("userId"), jsonObject.get("userId"));
		softAssert.assertAll();
	}
}
