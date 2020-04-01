package testes;

import org.json.JSONArray;
import org.junit.Test;

import com.jayway.restassured.RestAssured;

public class Teste2 {

	@Test
	public void test() {
		JSONArray jsonArray = new JSONArray(RestAssured.given().relaxedHTTPSValidation().when()
				.get("https://jsonplaceholder.typicode.com/todos").then().statusCode(200).extract().body().asString());
		for (int i = 0; i < jsonArray.length(); i++) {
			System.out.println(jsonArray.getJSONObject(i));

		}
		
		
	}

}