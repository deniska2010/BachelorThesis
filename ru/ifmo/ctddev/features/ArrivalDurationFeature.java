package ru.ifmo.ctddev.features;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.json.simple.parser.ParseException;

public class ArrivalDurationFeature {
    public static Feature createFeature(String json) throws ParseException {

        JSONObject jsonObj = (JSONObject) JSONValue.parseWithException(json);
        JSONObject jsonObj1 = (JSONObject) jsonObj.get("primaryData");
        JSONArray jsonObj2 = (JSONArray) jsonObj1.get("locations");
        int result = 0;
        for (int i = 0; i < jsonObj2.size(); i++) {
            JSONObject jsonObject3 = (JSONObject) jsonObj2.get(i);
            result += (long) jsonObject3.get("arrivalDuration");
        }
        return new Feature("AverageArrivalDuration", result / jsonObj2.size(), "Среднее время на подъезд к локации.");
    }
}
