package com.example.loginapp;



import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONArrayRequestListener;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class WebFunctions {
    public interface UserLoginCallback {
        void LoginResult(boolean isVerified);
    }
    public static boolean userIsVerified = false;
    public static void addUser(String username, String email, String password) {
        AndroidNetworking.get("https://localhost:3000/users/addUser")
                .addQueryParameter("username", username)
                .addQueryParameter("password", password)
                .addQueryParameter("email", email)
                .build()
                .getAsJSONArray(new JSONArrayRequestListener() {
                    @Override
                    public void onResponse(JSONArray response) {
                        // do anything with response
                    }

                    @Override
                    public void onError(ANError anError) {

                    }


                });
    }

    public static void verifyUser(String username, final String password, final UserLoginCallback callback) {
        final String passwordhash = AES.md5(password);

        AndroidNetworking.get("https://localhost:3000/users/getData")
                .addQueryParameter("name", username)
                .build()
                .getAsJSONArray(new JSONArrayRequestListener() {
                    @Override
                    public void onResponse(JSONArray response) {
                        String variable = "";
                        for (int i = 0; i < response.length(); i++) {
                            try {
                                JSONObject userInfo = response.getJSONObject(i);
                                variable = userInfo.getString("password");

                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                        if (passwordhash == variable) {
                            callback.LoginResult(true);
                            userIsVerified = true; 
                        } else {
                            callback.LoginResult(false);

                        }

                    }

                    @Override
                    public void onError(ANError anError) {

                    }


                });
        

    }

}
