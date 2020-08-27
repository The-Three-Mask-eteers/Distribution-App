package com.example.loginapp;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONArrayRequestListener;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Objects {


    public static JSONArray places;

    public static void getPlaceData(String parameter) {


        AndroidNetworking.get("https://localhost:3000/" + parameter)
                .build()
                .getAsJSONArray(new JSONArrayRequestListener() {
                    @Override
                    public void onResponse(JSONArray response) {
                        places = response;
                    }

                    @Override
                    public void onError(ANError anError) {

                    }


                });

    }
}
