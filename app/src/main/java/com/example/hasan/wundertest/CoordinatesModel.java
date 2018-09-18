package com.example.hasan.wundertest;


//Coordinates Model to be used for Marker placements on the map
public class CoordinatesModel {

    double longitude;
    double latitude;
    String carName;

    public CoordinatesModel(double longitude,double latitude,String carName){

        this.longitude = longitude;
        this.latitude = latitude;
        this.carName = carName;

    }
}
