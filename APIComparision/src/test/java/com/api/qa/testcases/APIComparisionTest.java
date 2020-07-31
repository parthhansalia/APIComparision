package com.api.qa.testcases;

import com.api.qa.util.TestUtil;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.Map;

import static io.restassured.RestAssured.given;


public class APIComparisionTest {

	private Map<String, Object> mapFile1;
	private Map<String, Object> mapFile2;


	@DataProvider()
	public Object[][] getDataFile1() throws IOException {
		Object[][] testData = TestUtil.getDataFromFile(TestUtil.FILE1);
		return testData;
	}
	@DataProvider()
	public Object[][] getDataFile2() throws IOException {
		Object testData[][] = TestUtil.getDataFromFile(TestUtil.FILE2);
		return testData;
	}

	//to run dataprovier parallel
	@DataProvider(name = "Combined", parallel = true)
	public Object[][] getCombinedData() throws IOException {
		return TestUtil.combine(getDataFile1(), getDataFile2());
	}


	@Test(priority = 1, dataProvider = "Combined")
	public void getDataFromFile (String api1, String api2) {


		RequestSpecification httpRequest = given();


		//Hit the first API
		Response response1 = httpRequest.request(Method.GET, api1);
		//Hit the second API
		Response response2 = httpRequest.request(Method.GET, api2);


		//checking 200 response code to make sure not compare for invalid response
		Assert.assertTrue(response1.statusCode() == TestUtil.STATUSCODE_200 && response2.statusCode() == TestUtil.STATUSCODE_200);

		String responseFile1 = response1.getBody().asString();
		mapFile1 = TestUtil.jsonToMap(responseFile1);


		String responseFile2 = response2.getBody().asString();
		mapFile2 = TestUtil.jsonToMap(responseFile2);


		//Using map for comparision to prevent nested and unordered responses
		Assert.assertTrue(mapFile1.equals(mapFile2), api1 +" not equals " + api2);




	}


}
