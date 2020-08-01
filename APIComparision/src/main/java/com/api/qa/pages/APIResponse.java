package com.api.qa.pages;

import com.api.qa.base.TestBase;
import com.api.qa.util.TestUtil;
import io.restassured.http.ContentType;
import io.restassured.response.Response;


import static io.restassured.RestAssured.given;


public class APIResponse extends TestBase {

	
	public Response getAPIResponse(String api) {

			return     given()
				       .when()
					   		.get(api)
					   .then()
							.statusCode(TestUtil.STATUSCODE_200)
							.contentType(ContentType.JSON).extract().response();


	}

	
}
