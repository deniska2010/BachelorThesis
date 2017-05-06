package ru.ifmo.ctddev.features;


import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.json.simple.parser.ParseException;

public class CostPerShiftFeature {

    public static Feature createFeature(String json) throws ParseException {

        JSONObject jsonObj = (JSONObject) JSONValue.parseWithException(json);
        JSONObject jsonObj1 = (JSONObject) jsonObj.get("planningSettings");
        JSONArray jsonObj2 = (JSONArray) jsonObj1.get("tariffs");
        double result = 0.0;
        int number = 0;
        for (int i = 0; i < jsonObj2.size(); i++) {
            JSONObject jsonObj3 = (JSONObject) jsonObj2.get(i);
            result += (double) jsonObj3.get("costPerShift");
            number++;
        }

        return new Feature("AverageCostPerShift", result / number, "Средняя цена за использование смены исполнителем или транспортом.");

    }


}
