package com.example.hasan.wundertest;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;

import java.util.ArrayList;


public class ListCarsActivity extends FragmentActivity {


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listcars);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.


        // Construct the data source

        ArrayList<CarModel> arrayOfCars = new ArrayList<CarModel>();


        try {
            FileInputStream fis = openFileInput("CarsFeed.txt");
            InputStreamReader isr = new InputStreamReader(fis);
            BufferedReader bufferedReader = new BufferedReader(isr);
            StringBuilder sb = new StringBuilder();
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                sb.append(line);
            }


            JSONObject jsonObject = new JSONObject(sb.toString());

            JSONArray jsonArray = jsonObject.getJSONArray("placemarks");

            arrayOfCars = CarModel.fromJson(jsonArray);



        } catch (Exception e) {
            e.printStackTrace();
        }

        // Create the adapter to convert the array to views

        CarsAdapter adapter = new CarsAdapter(this, arrayOfCars);


        // Attach the adapter to a ListView

        ListView listView = findViewById(R.id.list_view);

        listView.setAdapter(adapter);



    }

}
