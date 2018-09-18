package com.example.hasan.wundertest;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;


public class HomeActivity extends FragmentActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);


    }

    public void gotToListCars(View v){
        Intent listCarsIntent = new Intent(this, ListCarsActivity.class);
        startActivity(listCarsIntent);
    }

    public void gotToMap(View v){
        Intent mapIntent = new Intent(this, MapActivity.class);
        startActivity(mapIntent);
    }


}
