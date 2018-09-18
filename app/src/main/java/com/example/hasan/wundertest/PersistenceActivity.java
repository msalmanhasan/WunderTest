package com.example.hasan.wundertest;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;


//For getting the json feed of cars from link
public class PersistenceActivity extends Activity {


    public void onCreate(Bundle savedInstances) {
        super.onCreate(savedInstances);
        Context context = getApplicationContext();
        PersistFeedTask task = new PersistFeedTask(context);
        URL url = null;
        try {
            url = new URL("https://s3-us-west-2.amazonaws.com/wunderbucket/locations.json");
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        task.execute(url);

        Intent homeIntent = new Intent(this, HomeActivity.class);
        startActivity(homeIntent);

    }


}


