package com.api.qa.testcases;

import com.api.qa.pages.APIResponse;
import com.api.qa.util.TestUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.Map;

public class APIComparisionTest {

	private Map<String, Object> mapFile1;
	private Map<String, Object> mapFile2;

	APIResponse apiResponse = new APIResponse();


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
	@DataProvider(name = "Combined")
	public Object[][] getCombinedData() throws IOException {
		return TestUtil.combine(getDataFile1(), getDataFile2());
	}


	@Test(priority = 1, dataProvider = "Combined")
	public void getDataFromFile (String api1, String api2) throws IOException {


		Response response1 = apiResponse.getAPIResponse(api1);
		Response response2 = apiResponse.getAPIResponse(api2);

		System.out.println(response1.asString() + " " + response2.getBody());

		ObjectMapper mapper = new ObjectMapper();

		Assert.assertEquals(mapper.readTree(String.valueOf(response1.jsonPath())), mapper.readTree(String.valueOf(response2.jsonPath())));







	}


}
