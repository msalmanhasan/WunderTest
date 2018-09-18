package com.example.hasan.wundertest;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class CarsAdapter extends ArrayAdapter<CarModel> {


    //Constructor for the CarsAdaptor
    public CarsAdapter(Context context, ArrayList<CarModel> cars) {

        super(context, 0, cars);

    }



    //Returns the View for Car item
    @Override

    public View getView(int position, View convertView, ViewGroup parent) {

        // Get the data item for this position

        CarModel car = getItem(position);

        // Check if an existing view is being reused, otherwise inflate the view

        if (convertView == null) {

            convertView = LayoutInflater.from(getContext()).inflate(R.layout.car_item, parent, false);

        }

        // Lookup view for data population

        TextView tvAddress = (TextView) convertView.findViewById(R.id.Address);

        TextView tvCoordinates = (TextView) convertView.findViewById(R.id.coordinates);

        TextView tvEngineType = (TextView) convertView.findViewById(R.id.enginetype);

        TextView tvExterior = (TextView) convertView.findViewById(R.id.exterior);

        TextView tvFuel = (TextView) convertView.findViewById(R.id.fuel);

        TextView tvInterior = (TextView) convertView.findViewById(R.id.interior);

        TextView tvName = (TextView) convertView.findViewById(R.id.name);

        TextView tvVin = (TextView) convertView.findViewById(R.id.vin);


        // Populate the data into the template view using the data object

        tvAddress.setText(car.getAddress());

        tvCoordinates.setText(car.getCoordinates());

        tvEngineType.setText(car.getEngineType());

        tvExterior.setText(car.getExterior());

        tvFuel.setText(car.getFuel());

        tvInterior.setText(car.getInterior());

        tvName.setText(car.getName());

        tvVin.setText(car.getVin());

        // Return the completed view to render on screen

        return convertView;

    }

}