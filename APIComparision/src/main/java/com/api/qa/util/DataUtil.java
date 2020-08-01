package com.api.qa.util;

import com.api.qa.base.TestBase;
import org.apache.commons.lang3.ArrayUtils;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.IntStream;


public class DataUtil extends TestBase{

	public DataUtil() {
		TestBase();
	}


	public String FILE1 = prop.getProperty("File1");
	public final String FILE2 = prop.getProperty("File2");
	public final static int STATUSCODE_200 = 200;


	public static Object[][] getDataFromFile(String filePath) throws IOException {

		List<String> lines = Files.readAllLines(Paths.get(filePath));

		return lines.stream()
				.filter(Objects::nonNull)
				.map(obj -> new Object[]{obj})
				.toArray(Object[][]::new);

	}


	public static Object[][] combine(Object[][] a1, Object[][] a2){
		if(a1.length != a2.length){
			System.out.println("Number of APIs are not same in both file");
		}
		return IntStream.range(0,a1.length)
				.mapToObj(num -> concatAll(a1[num],a2[num]))
				.toArray(Object[][]::new);
	}

	private static Object[] concatAll(Object[] first, Object[] second) {

		return ArrayUtils.addAll(first, second);
	}


	public String getFile1() {
		return prop.getProperty("File1");
	}

	public String getFile2() {
		return prop.getProperty("File1");
	}

	

}