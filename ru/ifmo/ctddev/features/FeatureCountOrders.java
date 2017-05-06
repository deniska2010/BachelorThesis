package ru.ifmo.ctddev.features;

import org.json.simple.parser.*;
import org.json.simple.*;

public class FeatureCountOrders {

    public static Feature createFeature(String json) throws ParseException {

        JSONObject jsonObj = (JSONObject) JSONValue.parseWithException(json);
        JSONObject jsonObj1 = (JSONObject) jsonObj.get("primaryData");
        JSONArray jsonObj2 = (JSONArray) jsonObj1.get("orders");
        return new Feature("Amount of Orders", jsonObj2.size(), "Количество заказов");
    }
}
