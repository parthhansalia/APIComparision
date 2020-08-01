package com.api.qa.util;

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


public class TestUtil {


	public final static String FILE1 = System.getProperty("user.dir")+"/src/main/java/com/api/qa/testdata/APIFile1";
	public final static String FILE2 = System.getProperty("user.dir")+"/src/main/java/com/api/qa/testdata/APIFile2";
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
			throw new IllegalArgumentException("object array provided do not match in length");
		}
		return IntStream.range(0,a1.length)
				.mapToObj(num -> concatAll(a1[num],a2[num]))
				.toArray(Object[][]::new);
	}

	private static Object[] concatAll(Object[] first, Object[] second) {

		return ArrayUtils.addAll(first, second);
	}



	

}
