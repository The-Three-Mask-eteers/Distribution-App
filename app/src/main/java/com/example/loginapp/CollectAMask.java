package com.example.loginapp;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import org.json.JSONArray;

import java.util.Map;

public class CollectAMask extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_collect_a_mask);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);



    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */


    public static JSONArray place_list;
    public static String clicked_object = "";

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        for (Map.Entry<String, Entity> entry: Objects.placesThatGiveDonations.entrySet()) {
            LatLng coordinate = null;
            coordinate = new LatLng(entry.getValue().latitude, entry.getValue().latitude);
            String name = entry.getValue().name;
            mMap.addMarker(new MarkerOptions().position(coordinate).title(name));
            mMap.moveCamera(CameraUpdateFactory.newLatLng(coordinate));
            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(coordinate, 10));
        }


        Spinner spinner = (Spinner) findViewById(R.id.spinner2);
// Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, Objects.placesThatGiveDonationsNames);
// Specify the layout to use when the list of choices appears
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
// Apply the adapter to the spinner
        spinner.setAdapter(arrayAdapter);


        spinner.setOnItemSelectedListener(
                new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {

                        Object item = parent.getItemAtPosition(pos);

                        Objects.place_name =  item.toString();
                        IndividualZoomIn fragment2=new IndividualZoomIn();
                        FragmentManager fragmentManager = getSupportFragmentManager();
                        FragmentTransaction fragmentTransaction=fragmentManager.beginTransaction();
                        fragmentTransaction.replace(R.id.map,fragment2,"tag");
                        fragmentTransaction.addToBackStack(null);
                        fragmentTransaction.commit();



                    }

                    public void onNothingSelected(AdapterView<?> parent) {
                    }
                });

    }





}


