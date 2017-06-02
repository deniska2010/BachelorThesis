package ru.ifmo.ctddev.features;


import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.json.simple.parser.ParseException;

public class AverageNumberOfCapacityInBoxes {

    public static Feature createFeature(String json) throws ParseException {
        JSONObject jsonObj = (JSONObject) JSONValue.parseWithException(json);
        JSONObject jsonObj1 = (JSONObject) jsonObj.get("primaryData");
        JSONArray jsonObj2 = (JSONArray) jsonObj1.get("transports");
        int result = 0;
        int count = 0;
        for (int k = 0; k < jsonObj2.size(); k++) {
            JSONObject jsonObj3 = (JSONObject) jsonObj2.get(k);
            JSONArray jsonObj4 = (JSONArray) jsonObj3.get("boxes");
            for (int i = 0; i < jsonObj4.size(); i++) {
                JSONObject jsonObj5 = (JSONObject) jsonObj4.get(i);
                JSONArray jsonObj6 = (JSONArray) jsonObj5.get("capacities");
                result += jsonObj6.size();
                count++;
            }
        }
        return new Feature("AverageNumberOfCapacitiesInBoxes", result / count, "Среднее число вариантов вместимостей в отсеках.");
    }

}
