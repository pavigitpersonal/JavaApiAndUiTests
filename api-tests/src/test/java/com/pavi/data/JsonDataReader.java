package com.pavi.data;

import java.io.FileReader;
import java.util.Arrays;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;

public class JsonDataReader {
    private static final String testDataFile = "src/test/resources/employeeData.json";

    public static List<Object[]> getTestData() {
        Gson gson = new Gson();
        JsonParser parser = new JsonParser();

        try {
            JsonObject jsonObject = (JsonObject) parser.parse(new FileReader(testDataFile));
            return gson.fromJson(jsonObject.get("testdata"), new TypeToken<List<Object[]>>() {}.getType());
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
