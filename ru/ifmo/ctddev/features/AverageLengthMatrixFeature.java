package ru.ifmo.ctddev.features;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.json.simple.parser.ParseException;
import ru.ifmo.ctddev.utils.CountRang;

public class AverageLengthMatrixFeature {

    public static Feature createFeature(String json) throws ParseException {

        JSONObject jsonObj = (JSONObject) JSONValue.parseWithException(json);
        JSONObject jsonObj1 = (JSONObject) jsonObj.get("primaryData");
        JSONObject jsonObj2 = (JSONObject) jsonObj1.get("geodata");
        JSONArray jsonObj3 = (JSONArray) jsonObj2.get("routing");
        int averageRang = 0;
        int rang = 0;
        for (int k = 0; k < jsonObj3.size(); k++) {
            JSONObject jsonObj4 = (JSONObject) jsonObj3.get(k);
            JSONArray jsonObj5 = (JSONArray) jsonObj4.get("lengthMatrix");
            long[][] matrix = new long[(int) Math.sqrt(jsonObj5.size())][(int) Math.sqrt(jsonObj5.size())];
            int j = 0;
            int t = 0;
            for (int i = 0; i < jsonObj5.size(); i++) {
                matrix[i - t][j] = (long) jsonObj5.get(i);
                if ((i + 1) % Math.sqrt(jsonObj5.size()) == 0) {
                    j++;
                    t = i + 1;
                }
            }
            rang += new CountRang().countLong((int) Math.sqrt(jsonObj5.size()), (int) Math.sqrt(jsonObj5.size()), matrix);
        }
        averageRang = rang / jsonObj3.size();
        return new Feature("RangLengthMatrix", averageRang, "Базовая матрица расстояний между точками, в метрах.Среднее расстояние между соседними точками.");

    }
}
