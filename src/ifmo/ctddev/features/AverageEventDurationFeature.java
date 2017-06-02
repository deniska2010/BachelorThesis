package ru.ifmo.ctddev.features;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.json.simple.parser.ParseException;

public class AverageEventDurationFeature {

    public static Feature createFeature(String json) throws ParseException {
        JSONObject jsonObj = (JSONObject) JSONValue.parseWithException(json);
        JSONObject jsonObj1 = (JSONObject) jsonObj.get("primaryData");
        JSONArray jsonObj2 = (JSONArray) jsonObj1.get("orders");
        int result = 0;
        int count = 0;
        for (int k = 0; k < jsonObj2.size(); k++) {
            JSONObject jsonObj3 = (JSONObject) jsonObj2.get(k);
            JSONArray jsonObj4 = (JSONArray) jsonObj3.get("demands");
            for (int i = 0; i < jsonObj4.size(); i++) {
                JSONObject jsonObject5 = (JSONObject) jsonObj4.get(i);
                JSONArray jsonObj6 = (JSONArray) jsonObject5.get("possibleEvents");
                for(int j = 0; j < jsonObj6.size();j++){
                    JSONObject jsonObject7 = (JSONObject) jsonObj6.get(j);
                    result += (long) jsonObject7.get("duration");
                    count++;
                }
            }
        }
        return new Feature("AverageEventDuration", result / count, "Среднее время выполнения события, в минутах.");
    }
}
