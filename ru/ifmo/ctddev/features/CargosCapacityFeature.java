package ru.ifmo.ctddev.features;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.json.simple.parser.ParseException;
import  java.util.*;

import java.util.ArrayList;

public class CargosCapacityFeature {
    public static Feature createFeature(String json) throws ParseException {
        JSONObject jsonObj = (JSONObject) JSONValue.parseWithException(json);
        JSONObject jsonObj1 = (JSONObject) jsonObj.get("primaryData");
        JSONArray jsonObj2 = (JSONArray) jsonObj1.get("orders");
        ArrayList<Long> answers = new ArrayList<>();
        long result;
        for (int k = 0; k < jsonObj2.size(); k++) {
            JSONObject jsonObj3 = (JSONObject) jsonObj2.get(k);
            JSONArray jsonObj4 = (JSONArray) jsonObj3.get("demands");
            JSONObject jsonObj5 = (JSONObject) jsonObj4.get(0);
            JSONArray jsonObj6 = (JSONArray) jsonObj5.get("cargos");
            for (int i = 0; i < jsonObj6.size(); i++) {
                result = 1;
                JSONObject jsonObject7 = (JSONObject) jsonObj6.get(i);
                JSONObject jsonObject8 = (JSONObject) jsonObject7.get("capacity");
                JSONArray jsonObj9 = (JSONArray) jsonObject8.get("capacityVector");
                for (int j = 0; j < jsonObj9.size(); j++) {
                    result *= (long) jsonObj9.get(j);
                }
                answers.add(result);
            }
        }
        OptionalDouble average = answers.stream().mapToLong(e -> e).average();

        return new Feature("AverageCargosCapacity", average.getAsDouble(), "Средняя вместимость грузов по заказам.");


    }
}