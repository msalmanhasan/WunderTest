package com.example.hasan.wundertest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class CarModel {


    private String address;

    private String coordinates;

    private String engineType;

    private String exterior;

    private String fuel;

    private String interior;

    private String name;

    private String vin;

        // Constructor to convert JSON object into a CarModel instance

        public CarModel(JSONObject object){

            try {

                this.address = object.getString("address");
                this.coordinates = object.getString("coordinates");
                this.engineType = object.getString("engineType");
                this.exterior = object.getString("exterior");
                this.fuel = object.getString("fuel");
                this. interior = object.getString("interior");
                this.name = object.getString("name");
                this.vin = object.getString("vin");

            } catch (JSONException e) {

                e.printStackTrace();

            }

        }


        // Method to convert an array of JSON objects into a list of CarModel objects

        public static ArrayList<CarModel> fromJson(JSONArray jsonObjects) {

            ArrayList<CarModel> cars = new ArrayList<CarModel>();

            for (int i = 0; i < jsonObjects.length(); i++) {

                try {

                    cars.add(new CarModel(jsonObjects.getJSONObject(i)));

                } catch (JSONException e) {

                    e.printStackTrace();

                }
            }

            return cars;

        }



        //Getters and Setters for the fields
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(String coordinates) {
        this.coordinates = coordinates;
    }

    public String getEngineType() {
        return engineType;
    }

    public void setEngineType(String engineType) {
        this.engineType = engineType;
    }

    public String getExterior() {
        return exterior;
    }

    public void setExterior(String exterior) {
        this.exterior = exterior;
    }

    public String getFuel() {
        return fuel;
    }

    public void setFuel(String fuel) {
        this.fuel = fuel;
    }

    public String getInterior() {
        return interior;
    }

    public void setInterior(String interior) {
        this.interior = interior;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getVin() {
        return vin;
    }

    public void setVin(String vin) {
        this.vin = vin;
    }
    }

