package com.example.loginapp;

//import com.androidnetworking.AndroidNetworking;

import android.net.Uri;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class WebFunctions {
    public interface UserLoginCallback {
        void LoginResult(boolean isVerified);
    }
    public static boolean userIsVerified = false;
    public static void addUser(String username, String email, String password) {
        Uri.Builder builder = new Uri.Builder();
        builder.scheme("http")
                .encodedAuthority("192.168.192.7:3000")
                .appendPath("users")
                .appendPath("addUser")
                .appendQueryParameter("username", username)
                .appendQueryParameter("password", password)
                .appendQueryParameter("email", email);

        try {
            final String urlStr = builder.build().toString();

            URL url = new URL(urlStr);
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            try {
                urlConnection.getInputStream();
            } catch (Exception e) {
                System.out.println(e.getMessage());
            } finally {
                urlConnection.disconnect();
            }


//            JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
//                    (Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
//
//                        @Override
//                        public void onResponse(JSONObject response) {
////                            HomeFragment newfragment = new HomeFragment();
////
////                            FragmentManager fragmentManager= getActivity().getSupportFragmentManager();
////                            FragmentTransaction fragmentTransaction=fragmentManager.beginTransaction();
////                            fragmentTransaction.replace(R.id.fragment_register,newfragment,"tag");
////                            fragmentTransaction.addToBackStack(null);
////                            fragmentTransaction.commit();
//                        }
//                    }, new Response.ErrorListener() {
//                        private VolleyError error;
//
//                        @Override
//                        public void onErrorResponse(VolleyError error) {
//                            this.error = error;
//                        }
//                    });

        } catch (Exception e) {
        }

//        AndroidNetworking.get("http://localhost:3000/users/addUser")
//                .addQueryParameter("username", username)
//                .addQueryParameter("password", password)
//                .addQueryParameter("email", email)
//                .build()
//                .getAsJSONArray(new JSONArrayRequestListener() {
//                    @Override
//                    public void onResponse(JSONArray response) {
//                        // do anything with response
//                    }
//
//                    @Override
//                    public void onError(ANError anError) {
//                    }
//                });
    }

    public static void verifyUser(String username, final String password, final UserLoginCallback callback) {
        final String passwordhash = password;

        Uri.Builder builder = new Uri.Builder();
        builder.scheme("http")
                .encodedAuthority("192.168.192.7:3000")
                .appendPath("users")
                .appendPath("getData")
                .appendQueryParameter("name", username);


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
                        sb.append(line+"\n");
                    }
                    br.close();
                    JSONObject resp = new JSONObject(sb.toString());
                    JSONArray userInfo = resp.getJSONArray("response");
                    if (userInfo.length()>0) {
                        final String pass = userInfo.getJSONObject(0).getString("password");
                        if (pass.equals(passwordhash)) {
                            callback.LoginResult(true);
                            return;
                        }

                    }
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());

        }
        callback.LoginResult(false);
        return;
    }
}
