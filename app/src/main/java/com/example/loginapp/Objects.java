package com.example.loginapp;

import android.net.Uri;
import android.util.Pair;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;


class Entity {
    String name;
    String address;
    String description;
    String acceptedItems;
    String phone;
    String email;
    double latitude;
    double longitude;
};

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

    public static ArrayList<String> placesThatGiveDonationsNames = new ArrayList<>();
    public static HashMap<String, Entity> placesThatGiveDonations = new HashMap<>();
    public static ArrayList<String> placesThatAcceptDonationsNames = new ArrayList<>();
    public static HashMap<String, Entity> placesThatAcceptDonations = new HashMap<>();

    static {
        Thread newThread = new Thread() {
            @Override
            public void run() {
                getPlaceData("placesThatGiveDonations", new PlaceDataCallback() {
                    @Override
                    public void PlaceDataCollect(JSONArray places) {
                        try {
                            placesThatGiveDonationsNames = getNames(places);
                            placesThatGiveDonations = getEntities(places);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                });

                getPlaceData("placesThatAcceptDonations", new PlaceDataCallback() {
                    @Override
                    public void PlaceDataCollect(JSONArray places) {
                        try {
                            placesThatAcceptDonationsNames = getNames(places);
                            placesThatAcceptDonations = getEntities(places);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                });
            }
        };
        newThread.start();
    }

    public static ArrayList<String> getNames(JSONArray placeInfo) throws JSONException {
        ArrayList<String> nameList = new ArrayList<>();
        for (int i = 0; i < placeInfo.length(); i++) {
            nameList.add(placeInfo.getJSONObject(i).getString("name"));
        }
        return nameList;
    }
    public static HashMap<String, Entity> getEntities(JSONArray placeInfo) throws JSONException {
        HashMap<String, Entity> entityList = new HashMap<String, Entity>();
        for (int i = 0; i < placeInfo.length(); i++) {
            Entity entity = new Entity();
            entity.name = placeInfo.getJSONObject(i).getString("name");
            entity.description = placeInfo.getJSONObject(i).getString("description");
            entity.address = placeInfo.getJSONObject(i).getString("address");
            entity.email = placeInfo.getJSONObject(i).getString("email");
            entity.acceptedItems = placeInfo.getJSONObject(i).getString("acceptedItems");
            entity.phone = placeInfo.getJSONObject(i).getString("phone");
            entity.latitude = Double.valueOf(placeInfo.getJSONObject(i).getString("latitude"));
            entity.longitude = Double.valueOf(placeInfo.getJSONObject(i).getString("longitude"));
            entityList.put(entity.name.toLowerCase(), entity);
        }
        return entityList;
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
    public static ArrayList<Pair<String, String>> getLatLng(JSONArray placeInfo) throws JSONException {
        ArrayList<Pair<String, String>> latlnglist = new ArrayList<>();
        for (int i = 0; i < placeInfo.length(); i++) {
            String x = placeInfo.getJSONObject(i).getString("latitude");
            String y = placeInfo.getJSONObject(i).getString("longitude");
            latlnglist.add(new Pair(x, y));
        }
        return latlnglist;
    }
}
