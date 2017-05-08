package ru.ifmo.ctddev.features;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.json.simple.parser.ParseException;

public class GeolocationFeature {

    public Feature createFeature(String json) {
        String city = "";
        try {
            JSONObject jsonObj = (JSONObject) JSONValue.parseWithException(json);
            JSONObject jsonObj1 = (JSONObject) jsonObj.get("primaryData");
            JSONObject jsonObj2 = (JSONObject) jsonObj1.get("geodata");
            JSONArray jsonObj3 = (JSONArray) jsonObj2.get("geopoints");
            JSONObject jsonObj4 = (JSONObject) jsonObj3.get(0);
            double latitude = (double) jsonObj4.get("latitude");
            double longitude = (double) jsonObj4.get("longitude");
            if (latitude > 90 || latitude < -90 || longitude > 180 || longitude < -180) {
                return new Feature("City", "Unknown".hashCode(), "Город,где проводится доставка,в данном случае - " + city);

            }
            URL url = new URL("http://maps.googleapis.com/maps/api/geocode/json?latlng=" + latitude + "," + longitude + "&sensor=true");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Accept", "application/json");
            if (conn.getResponseCode() != 200) {
                throw new RuntimeException("Failed : HTTP error code : "
                        + conn.getResponseCode());
            }

            BufferedReader br = new BufferedReader(new InputStreamReader(
                    (conn.getInputStream())));

            String output;
            String out = "";
            while ((output = br.readLine()) != null) {
                //System.out.println(output);
                out += output;
            }
            // Converting Json formatted string into JSON object
            JSONObject json1 = (JSONObject) JSONValue.parseWithException(out);

            JSONArray results = (JSONArray) json1.get("results");
            if (results.isEmpty()) {
                return new Feature("City", "Unknown".hashCode(), "Город,где проводится доставка,в данном случае - " + city);

            }
            JSONObject rec = (JSONObject) results.get(0);
            JSONArray address_components = (JSONArray) rec.get("address_components");
            for (int i = 0; i < address_components.size(); i++) {
                JSONObject rec1 = (JSONObject) address_components.get(i);
                JSONArray types = (JSONArray) rec1.get("types");
                String comp = types.get(0).toString();

                if (comp.equals("locality")) {
                    city = rec1.get("long_name").toString();
                }
            }
            conn.disconnect();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return new Feature("City", city.hashCode(), "Город,где проводится доставка,в данном случае - " + city);

    }
}