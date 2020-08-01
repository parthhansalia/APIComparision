package com.api.qa.APIs;

import com.api.qa.util.DataUtil;
import io.restassured.http.ContentType;
import io.restassured.response.Response;


import static io.restassured.RestAssured.given;


public class APIResponse {

	
	public static Response getAPIResponse(String url) {

			return     given()
				       .when()
					   		.get(url)
					   .then()
							.statusCode(DataUtil.STATUSCODE_200)
							.contentType(ContentType.JSON).extract().response();


	}

	
}
