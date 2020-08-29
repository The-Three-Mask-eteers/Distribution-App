package com.example.loginapp;



import android.net.Uri;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;


public class Objects {

    public interface PlaceDataCallback {
        void PlaceDataCollect(JSONArray places);
    }
    public static String parameter = "";
    public static String place_name = "";



    public static void getPlaceData(String parameter, PlaceDataCallback callback) {


        Uri.Builder builder = new Uri.Builder();
        builder.scheme("http")
                .encodedAuthority("aalay.duckdns.org:3000")
                .appendPath(parameter);


        try {
            final String urlStr = builder.build().toString();

            URL url = new URL(urlStr);

            HttpURLConnection c = (HttpURLConnection) url.openConnection();
            c.setRequestMethod("GET");
            c.setRequestProperty("Content-length", "0");
            c.setUseCaches(false);
            c.setAllowUserInteraction(false);
            c.connect();
            int status = c.getResponseCode();

            switch (status) {
                case 200:
                case 201:
                    BufferedReader br = new BufferedReader(new InputStreamReader(c.getInputStream()));
                    StringBuilder sb = new StringBuilder();
                    String line;
                    while ((line = br.readLine()) != null) {
                        sb.append(line + "\n");
                    }
                    br.close();
                    JSONObject resp = new JSONObject(sb.toString());
                    JSONArray placeInfo = resp.getJSONArray("response");
                    callback.PlaceDataCollect(placeInfo);
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());

        }

        return;
    }

    public static ArrayList<String> getNames(JSONArray placeInfo) throws JSONException {
        ArrayList<String> nameList = new ArrayList<>();
        for (int i = 0; i < placeInfo.length(); i++) {
            nameList.add(placeInfo.getJSONObject(i).getString("name"));
        }
        return nameList;
    }
    public static String findPlaceInfo(JSONArray placeInfo, String name) throws JSONException {
        String data = "";
        for (int i = 0; i < placeInfo.length(); i++) {
            if (placeInfo.getJSONObject(i).getString("name") == name) {
                data = placeInfo.getJSONObject(i).toString();
            }
        }
        return data;
    }
}
