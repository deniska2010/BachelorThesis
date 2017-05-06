package ru.ifmo.ctddev.features;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.json.simple.parser.ParseException;

import java.util.ArrayList;
import java.util.OptionalDouble;

public class BoxesCapacityFeature {
    public static Feature createFeature(String json) throws ParseException {
        JSONObject jsonObj = (JSONObject) JSONValue.parseWithException(json);
        JSONObject jsonObj1 = (JSONObject) jsonObj.get("primaryData");
        JSONArray jsonObj2 = (JSONArray) jsonObj1.get("transports");
        long result;
        ArrayList<Long> answers = new ArrayList<>();
        for (int k = 0; k < jsonObj2.size(); k++) {
            JSONObject jsonObj3 = (JSONObject) jsonObj2.get(k);
            JSONArray jsonObj4 = (JSONArray) jsonObj3.get("boxes");
            for (int i = 0; i < jsonObj4.size(); i++) {
                JSONObject jsonObj5 = (JSONObject) jsonObj4.get(i);
                JSONArray jsonObj6 = (JSONArray) jsonObj5.get("capacities");
                for (int j = 0; j < jsonObj6.size(); j++) {
                    result = 1;
                    JSONObject jsonObj7 = (JSONObject) jsonObj6.get(j);
                    JSONArray jsonObj8 = (JSONArray) jsonObj7.get("capacityVector");
                    for (int t = 0; t < jsonObj8.size(); t++) {
                        result *= (long) jsonObj8.get(t);
                    }
                    answers.add(result);
                }
            }
        }
        OptionalDouble average = answers.stream().mapToLong(e -> e).average();

        return new Feature("AverageBoxesCapacity", average.getAsDouble(), "Среднея вместимость отсеков.");
    }
}
