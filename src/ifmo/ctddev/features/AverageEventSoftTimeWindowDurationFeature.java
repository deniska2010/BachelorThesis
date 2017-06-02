package ru.ifmo.ctddev.features;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.json.simple.parser.ParseException;

public class AverageEventSoftTimeWindowDurationFeature {

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
                for (int j = 0; j < jsonObj6.size(); j++) {
                    JSONObject jsonObject7 = (JSONObject) jsonObj6.get(j);
                    JSONObject jsonObject8 = (JSONObject) jsonObject7.get("softTimeWindow");
                    result += (long) jsonObject8.get("timeTo") - (long) jsonObject8.get("timeFrom");
                    count++;
                }
            }
        }
        return new Feature("AverageEventSoftTimeWindowDuration", result / count, "Среднее мягкое(желаемое) временное окно для события. Продолжительность");
    }
}
