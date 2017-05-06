package ru.ifmo.ctddev.features;

import ru.ifmo.ctddev.Utils.CountRang;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.json.simple.parser.ParseException;

public class RangMaxStorageTimeMatrixFeature {

    public static Feature createFeature(String json) throws ParseException {

        JSONObject jsonObj = (JSONObject) JSONValue.parseWithException(json);
        JSONObject jsonObj1 = (JSONObject) jsonObj.get("primaryData");
        JSONObject jsonObj2 = (JSONObject) jsonObj1.get("relations");
        JSONObject jsonObj3 = (JSONObject) jsonObj2.get("cargoBox");
        JSONArray jsonObj4 = (JSONArray) jsonObj3.get("maxStorageTimeMatrix");
        long[][] matrix = new long[jsonObj4.size()][1];
        for (int i = 0; i < jsonObj4.size(); i++) {
            matrix[i][0] = (long) jsonObj4.get(i);
        }
        int rang = new CountRang().countLong(jsonObj4.size(), 1, matrix);
        return new Feature("MaxStorageTimeMatrix", rang, "Матрица совместимости (состоит из максимального времени возможного нахождения груза заявки в данном отсеке)");

    }
}
