package ru.ifmo.ctddev.features;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.json.simple.parser.ParseException;
import ru.ifmo.ctddev.utils.CountRang;

public class RangOnBoardFeature {

    public static Feature createFeature(String json) throws ParseException {

        JSONObject jsonObj = (JSONObject) JSONValue.parseWithException(json);
        JSONObject jsonObj1 = (JSONObject) jsonObj.get("primaryData");
        JSONObject  jsonObj2 =  (JSONObject)  jsonObj1.get("relations");
        JSONObject  jsonObj3 =  (JSONObject)  jsonObj2.get("cargoBox");
        JSONArray jsonObj4 =  (JSONArray)  jsonObj3.get("onboardMatrix");
        int[][] matrix = new int[jsonObj4.size()][1];
        for (int i = 0; i < jsonObj4.size(); i++) {
            boolean res = (boolean) jsonObj4.get(i);
            if (res){
                matrix[i][0] = 1;
            }
            else
            {
                matrix[i][0] = 0;
            }
        }
        int rang = new CountRang().countInteger(jsonObj4.size(),1,matrix);
        return new Feature("OnBoardMatrix", rang, "Матрица загрузки груза заявок, которые уже загружены в отсек транспорта.");

    }
}
