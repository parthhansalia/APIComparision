package com.api.qa.testcases;

import com.api.qa.APIs.APIResponse;
import com.api.qa.util.DataUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import java.io.IOException;

public class APIComparisionTest {


	ObjectMapper mapper = new ObjectMapper();

	public Object[][] getDataFile1() throws IOException {
		Object[][] testData = DataUtil.getDataFromFile(DataUtil.FILE1);
		return testData;
	}

	public Object[][] getDataFile2() throws IOException {
		Object testData[][] = DataUtil.getDataFromFile(DataUtil.FILE2);
		return testData;
	}

	//to run dataprovier parallel
	@DataProvider(name = "Combined", parallel = true)
	public Object[][] getCombinedData() throws IOException {
		return DataUtil.combine(getDataFile1(), getDataFile2());
	}


	@Test(priority = 1, dataProvider = "Combined")
	public void getDataFromFile (String api1, String api2) throws IOException {


		Response response1 = APIResponse.getAPIResponse(api1);
		Response response2 = APIResponse.getAPIResponse(api2);

		Assert.assertEquals(mapper.readTree(response1.asString()), mapper.readTree(response2.asString()));



	}


}
