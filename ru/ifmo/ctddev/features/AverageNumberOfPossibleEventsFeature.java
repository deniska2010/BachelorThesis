package ru.ifmo.ctddev.features;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.json.simple.parser.ParseException;

public class AverageNumberOfPossibleEventsFeature {

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
                result += jsonObj6.size();
                count++;
            }
        }
        return new Feature("AverageNumberOfPossibleEvents", result / count, "Среднее количество событий в каждой заявке, выполнение любого из которых (из событий) дает выполнение заказа.");
    }
}
