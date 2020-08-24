package techproedturkish01.techproedturkish01api;

import org.junit.Test;
import io.restassured.response.Response;
import static io.restassured.RestAssured.*;

public class GetRequest01 {
	
//	Rest-Assured kullanarak API Testing yapacagiz
	
	@Test
	public void getMethod01() {
		given().
		when().
		   get("https://restful-booker.herokuapp.com/booking").
		then().
		   assertThat().
		   statusCode(200);		
	}
	
// *** GET ile aldigim data'yi console'de gormek icin;
	@Test
	public void getMethod02() {
		Response response = given().
							when().
							get("https://restful-booker.herokuapp.com/booking/5");
//	***	Response body'i console'a yazdirmak icin response.prettyPrint(); kullanilir
		response.prettyPrint();
//		
////	***	statusCode'u console'de yazmak icin
//		System.out.println("statusCode :"+response.getStatusCode());
//		
////		statusline'i console'da gormek icin;
//		System.out.println("statusline :"+response.getStatusLine());
//		
////		Response body'deki data'nin content(icerik) type almak icin;
//		System.out.println("Content Type :"+response.getContentType());
//		
////		Headers'daki tum bilgileri almak icin;
//		System.out.println(response.getHeaders());
//		
////		Headers'dan spesifikbir data'yi almak icin;
//		System.out.println(response.getHeader("Date"));
//		
////		Assertion yapalim
////		1)Status code 200
////	***	assertThat(). ==> "Hard Assertion"'dir;
////				Yani, ilk hatadan code execution durur vr hata raporu verir
////				Ilk hatadan sonraki kodlar calismaz...
//		response.
//		then().
//		assertThat().
//		statusLine("HTTP/1.1 200 OK").
//		contentType("application/json; charset=utf-8").
//		statusCode(200);
//			
	}
}
