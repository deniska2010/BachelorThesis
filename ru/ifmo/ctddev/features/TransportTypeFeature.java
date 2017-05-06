package ru.ifmo.ctddev.features;


import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.json.simple.parser.ParseException;

import java.util.ArrayList;

public class TransportTypeFeature {

    public static Feature createFeature(String json) throws ParseException {

        JSONObject jsonObj = (JSONObject) JSONValue.parseWithException(json);
        JSONObject jsonObj1 = (JSONObject) jsonObj.get("primaryData");
        JSONObject jsonObj2 = (JSONObject) jsonObj1.get("geodata");
        JSONArray jsonObj3 = (JSONArray) jsonObj2.get("routing");
        String type = "";
        for (int i = 0; i < jsonObj3.size(); i++) {
            JSONObject jsonObj4 = ((JSONObject) jsonObj3.get(i));
            if(!type.contains(jsonObj4.get("transportType").toString())) {
                type += jsonObj4.get("transportType");
            }
        }
        return new Feature("TransportType", type.hashCode(), "Типы транспортов и количество,которые участвуют в маршутизации,в данном случае тип - " + type);

    }

}
