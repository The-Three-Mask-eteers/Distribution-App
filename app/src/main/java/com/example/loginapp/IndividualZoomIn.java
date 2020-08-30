package com.example.loginapp;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.HashMap;

public class IndividualZoomIn extends Fragment implements OnMapReadyCallback {
    public static HashMap<String, Entity> entitylist;
    private OnMapReadyCallback callback = new OnMapReadyCallback() {

        /**
         * Manipulates the map once available.
         * This callback is triggered when the map is ready to be used.
         * This is where we can add markers or lines, add listeners or move the camera.
         * In this case, we just add a marker near Sydney, Australia.
         * If Google Play services is not installed on the device, the user will be prompted to
         * install it inside the SupportMapFragment. This method will only be triggered once the
         * user has installed Google Play services and returned to the app.
         */

        @Override
        public void onMapReady(GoogleMap googleMap) {
            GoogleMap map = googleMap;
            String red = Objects.place_name.toLowerCase();
            HashMap<String, Entity> map2;
            if (Objects.parameter.equals("placesThatGiveDonations")) {
                map2 = Objects.placesThatGiveDonations;
            } else {
                map2 = Objects.placesThatAcceptDonations;
            }

            Entity selectedPlace = map2.get(red);
            Double longitude = selectedPlace.longitude;
            Double latitude = selectedPlace.latitude;


            LatLng coordinate = new LatLng(latitude, longitude);

            map.addMarker(new MarkerOptions().position(coordinate).title(selectedPlace.name));
            map.moveCamera(CameraUpdateFactory.newLatLng(coordinate));
            map.moveCamera(CameraUpdateFactory.newLatLngZoom(coordinate, 30));


        }
    };

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_individual_zoom_in, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        SupportMapFragment mapFragment =
                (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.map);
        if (mapFragment != null) {
            mapFragment.getMapAsync(this);
        }
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        GoogleMap map = googleMap;
        String red = Objects.place_name.toLowerCase();
        HashMap<String, Entity> map2;
        if (Objects.parameter.equals("placesThatGiveDonations")) {
            map2 = Objects.placesThatGiveDonations;
        } else {
            map2 = Objects.placesThatAcceptDonations;
        }

        Entity selectedPlace = map2.get(red);
        Double longitude = selectedPlace.longitude;
        Double latitude = selectedPlace.latitude;


        LatLng coordinate = new LatLng(latitude, longitude);

        map.addMarker(new MarkerOptions().position(coordinate).title(selectedPlace.name));
        map.moveCamera(CameraUpdateFactory.newLatLng(coordinate));
        map.moveCamera(CameraUpdateFactory.newLatLngZoom(coordinate, 30));
        viewDialogue();
        }

    public void viewDialogue() {
        ExampleDialog exampleDialog = new ExampleDialog();
        exampleDialog.show(getChildFragmentManager(), "Data");



    }
}