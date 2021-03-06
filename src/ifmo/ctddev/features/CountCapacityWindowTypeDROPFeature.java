package ru.ifmo.ctddev.features;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.json.simple.parser.ParseException;

public class CountCapacityWindowTypeDROPFeature {

    public static Feature createFeature(String json) throws ParseException {
        JSONObject jsonObj = (JSONObject) JSONValue.parseWithException(json);
        JSONObject jsonObj1 = (JSONObject) jsonObj.get("primaryData");
        JSONArray jsonObj2 = (JSONArray) jsonObj1.get("locations");
        int count = 0;
        for (int i = 0; i < jsonObj2.size(); i++) {
            JSONObject jsonObject3 = (JSONObject) jsonObj2.get(i);
            JSONArray jsonObj4 = (JSONArray) jsonObject3.get("capacityWindows");
            for (int j = 0; j < jsonObj4.size(); j++) {
                JSONObject jsonObject5 = (JSONObject) jsonObj4.get(j);
                String result = jsonObject5.get("capacityWindowType").toString();
                if (result.equals("CWT_DROP")) {
                    count++;
                }
            }
        }
        return new Feature("CapacityWindowTypeDROP", count, "Количество окон на разгрузку (перемещение груза из транспорта в локацию).");
    }
}
