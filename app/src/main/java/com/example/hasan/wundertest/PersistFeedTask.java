package com.example.hasan.wundertest;

import android.content.Context;
import android.os.AsyncTask;

import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

// Task to store the feed of cars from url to the internal file storage
public class PersistFeedTask extends AsyncTask<URL, Void, Void> {

    private Context context;

    public PersistFeedTask(Context context) {

        this.context = context;
    }

    protected Void doInBackground(URL... urls) {

        StringBuilder response = new StringBuilder();

        //Prepare the URL and the connection
        URL u = urls[0];

        HttpURLConnection conn = null;
        try {
            conn = (HttpURLConnection) u.openConnection();
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            if (conn.getResponseCode() == HttpURLConnection.HTTP_OK) {
                //Get the Stream reader ready
                BufferedReader input = new BufferedReader(new InputStreamReader(conn.getInputStream()), 8192);

                //Loop through the return data and copy it over to the response object to be processed
                String line = null;

                while ((line = input.readLine()) != null) {
                    response.append(line);
                }

                input.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        String filename = "CarsFeed.txt";
        String result = response.toString();


        FileOutputStream outputStream;
        try {
            outputStream = context.openFileOutput(filename, Context.MODE_PRIVATE);
            outputStream.write(result.getBytes());
            outputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }


        return null;

    }

    protected void onProgressUpdate() {

    }

    protected void onPostExecute(String result) {


    }

}




