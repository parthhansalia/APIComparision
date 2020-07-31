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

	 static JSONObject json = null;

	public final static String FILE1 = "/Users/parthhansalia/Documents/MyProject/APIComparisionGoJek-master/src/main/java/com/qa/testdata/APIFile1";
	public final static String FILE2 = "/Users/parthhansalia/Documents/MyProject/APIComparisionGoJek-master/src/main/java/com/qa/testdata/APIFile2";
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


	//Convert string to map

	public static Map<String, Object> jsonToMap(String json1) {
		Map<String, Object> retMap = new HashMap<>();
		JSONParser parser = new JSONParser();

		try {
			json = (JSONObject) parser.parse(json1);

		} catch (ParseException e) {
			e.printStackTrace();
		}

		if(json != null) {
			retMap = toMap(json);
		}
		return retMap;
	}

	public static Map<String, Object> toMap(JSONObject object) {
		Map<String, Object> map = new HashMap<>();

		Iterator<String> keysItr = object.keySet().iterator();
		while(keysItr.hasNext()) {
			String key = keysItr.next();
			Object value = object.get(key);

			if(value instanceof JSONArray) {
				value = toList((JSONArray) value);
			}

			else if(value instanceof JSONObject) {
				value = toMap((JSONObject) value);
			}
			map.put(key, value);
		}

		return map;
	}

	public static List<Object> toList(JSONArray array) {
		List<Object> list = new ArrayList<>();
		for(int i = 0; i < array.size(); i++) {
			Object value = array.get(i);
			if(value instanceof JSONArray) {
				value = toList((JSONArray) value);
			}

			else if(value instanceof JSONObject) {
				value = toMap((JSONObject) value);
			}
			list.add(value);
		}
		return list;
	}
	

}
