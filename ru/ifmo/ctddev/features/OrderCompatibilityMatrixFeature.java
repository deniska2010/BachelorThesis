package ru.ifmo.ctddev.features;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.json.simple.parser.ParseException;
import ru.ifmo.ctddev.Utils.CountRang;

public class OrderCompatibilityMatrixFeature {

    public static Feature createFeature(String json) throws ParseException {

        JSONObject jsonObj = (JSONObject) JSONValue.parseWithException(json);
        JSONObject jsonObj1 = (JSONObject) jsonObj.get("primaryData");
        JSONObject  jsonObj2 =  (JSONObject)  jsonObj1.get("relations");
        JSONObject  jsonObj3 =  (JSONObject)  jsonObj2.get("orderOrder");
        JSONArray jsonObj4 =  (JSONArray)  jsonObj3.get("compatibilityMatrix");
        int[][] matrix = new int[(int) Math.sqrt(jsonObj4.size())][(int) Math.sqrt(jsonObj4.size())];
        int j = 0;
        int t = 0;
        for (int i = 0; i < jsonObj4.size(); i++) {
            boolean res = (boolean) jsonObj4.get(i);
            if (res){
                matrix[i-t][j] = 1;
            }
            else
            {
                matrix[i-t][j] = 0;
            }
            if ((i+1) % Math.sqrt(jsonObj4.size()) == 0){
                j++;
                t=i+1;
            }
        }
        int rang = new CountRang().countInteger((int) Math.sqrt(jsonObj4.size()),(int) Math.sqrt(jsonObj4.size()),matrix);
        return new Feature("OrderOrderMatrix", rang, "Матрица совместимости.Данная связь (OrderOrderRelations) используется для определения возможности перевозки грузов разных заказов в одном отсеке одного транспорта и одним исполнителем, данная совместимость интегральная, т.е. учитываются ограничения на все заявки(грузы) входящие в заказ.");

    }
}
