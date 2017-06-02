package ru.ifmo.ctddev.features;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.json.simple.parser.ParseException;

public class StageLengthFeature {

    public static Feature createFeature(String json) throws ParseException {

        JSONObject jsonObj = (JSONObject) JSONValue.parseWithException(json);
        JSONObject jsonObj1 = (JSONObject) jsonObj.get("planningSettings");
        JSONArray jsonObj2 = (JSONArray) jsonObj1.get("tariffs");
        long result = 0;
        int number = 0;
        for (int i = 0; i < jsonObj2.size(); i++) {
            JSONObject jsonObj3 = (JSONObject) jsonObj2.get(i);
            JSONArray jsonObj4 = (JSONArray) jsonObj3.get("constraints");
            for (int j = 0; j < jsonObj4.size(); j++) {
                JSONObject jsonObj5 = (JSONObject) jsonObj4.get(j);
                result += (long) jsonObj5.get("stageLength");
                number++;
            }
        }

        return new Feature("AverageStageLength", result / number, "Средняя длительность временного отрезка в минутах (для исполнителя) \\ длительность пути в метрах (для транспорта).");

    }

}
