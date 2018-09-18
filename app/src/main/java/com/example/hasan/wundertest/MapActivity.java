package com.example.hasan.wundertest;

import android.Manifest;

import android.content.pm.PackageManager;

import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Iterator;


public class MapActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private ArrayList<CoordinatesModel> listOfCoordinates = new ArrayList<CoordinatesModel>();
    private ArrayList<Marker> listOfMarkers = new ArrayList<Marker>();
    private static final int LOCATION_PERMISSION_REQUEST_CODE = 1;
    private boolean hide = true;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);


        //Read the Coordinates from the dowloaded file
        readCoordinates();

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
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        enableMyLocationIfPermitted();

        mMap.getUiSettings().setZoomControlsEnabled(true);

        mMap.setMinZoomPreference(6.0f);
        mMap.setMaxZoomPreference(14.0f);


        addPins(listOfCoordinates);

        mMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
            @Override
            public boolean onMarkerClick(Marker marker) {

                togglePins(hide);

                marker.setVisible(true);

                if (hide) {
                    marker.hideInfoWindow();
                } else {
                    marker.showInfoWindow();
                }

                return true;

            }
        });

        LatLng hamburg = new LatLng(53.59301, 10.07526);
        mMap.moveCamera(CameraUpdateFactory.newLatLng(hamburg));


    }



    // Sets the visibility of all the markers

    private void togglePins(boolean hide){

        if (hide){
            for (Iterator<Marker> i = listOfMarkers.iterator(); i.hasNext();) {
                Marker item = i.next();
                item.setVisible(false);
            }
            this.hide = false;
        }
        else {
            for (Iterator<Marker> i = listOfMarkers.iterator(); i.hasNext();) {
                Marker item = i.next();
                item.setVisible(true);
            }
            this.hide = true;
        }

        }


        // Enables the location if permission is granted.
    private void enableMyLocationIfPermitted() {
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.ACCESS_FINE_LOCATION,
                            Manifest.permission.ACCESS_FINE_LOCATION},
                    LOCATION_PERMISSION_REQUEST_CODE);
        } else if (mMap != null) {
            mMap.setMyLocationEnabled(true);

        }
    }

    // Default location to show in case of permission not granted.
    private void showDefaultLocation() {
        Toast.makeText(this, "Location permission not granted, " +
                        "showing default location",
                Toast.LENGTH_SHORT).show();
        LatLng hamburg = new LatLng(53.59301, 10.07526);
        mMap.moveCamera(CameraUpdateFactory.newLatLng(hamburg));
    }




    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        switch (requestCode) {
            case LOCATION_PERMISSION_REQUEST_CODE: {
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    enableMyLocationIfPermitted();
                } else {
                    showDefaultLocation();
                }
                return;
            }
        }
    }


    //Reads the coordinates of all the cars in the file to the listOfCoordinates field
    private void readCoordinates(){

        try {
            FileInputStream fis = openFileInput("CarsFeed.txt");
            InputStreamReader isr = new InputStreamReader(fis);
            BufferedReader bufferedReader = new BufferedReader(isr);
            StringBuilder sb = new StringBuilder();
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                sb.append(line);
            }


            JSONObject json = new JSONObject(sb.toString());

            //System.out.println("JSON OBJECT: " + jsonObject);

            JSONArray jsonCars = json.getJSONArray("placemarks");

            JSONObject car;
            for (int i=0;i<jsonCars.length();i++){

                car = (JSONObject) jsonCars.get(i);
                JSONArray coordinates = car.getJSONArray("coordinates");
                String name = car.getString("name");
                double lon = (double) coordinates.get(0);
                double lat = (double) coordinates.get(1);
                CoordinatesModel c = new CoordinatesModel(lon,lat,name);
                listOfCoordinates.add(c);

            }


        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    //Adds markers to the map and to the listOfMarkers field.
    private void addPins(ArrayList<CoordinatesModel> coordinates){

        for (Iterator<CoordinatesModel> i = coordinates.iterator(); i.hasNext();) {
            CoordinatesModel item = i.next();
            LatLng pair = new LatLng(item.latitude, item.longitude);
            Marker marker = mMap.addMarker(new MarkerOptions().position(pair).title("Car: "+ item.carName));

            listOfMarkers.add(marker);

        }
    }

}
