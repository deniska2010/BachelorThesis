package ru.ifmo.ctddev.features;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.json.simple.parser.ParseException;

public class AverageCargosPerDemandFeature {

    public static Feature createFeature(String json) throws ParseException {
        JSONObject jsonObj = (JSONObject) JSONValue.parseWithException(json);
        JSONObject jsonObj1 = (JSONObject) jsonObj.get("primaryData");
        JSONArray jsonObj2 = (JSONArray) jsonObj1.get("orders");
        int count = 0;
        int count1 = 0;
        for (int k = 0; k < jsonObj2.size(); k++) {
            JSONObject jsonObj3 = (JSONObject) jsonObj2.get(k);
            JSONArray jsonObj4 = (JSONArray) jsonObj3.get("demands");
            JSONObject jsonObj5 = (JSONObject) jsonObj4.get(0);
            JSONArray jsonObj6 = (JSONArray) jsonObj5.get("cargos");
            count1 += jsonObj6.size();
            count++;
        }
        return new Feature("AverageCargosPerDemand", count1 / count, "Среднее количество грузов в заказах.");
    }
}
