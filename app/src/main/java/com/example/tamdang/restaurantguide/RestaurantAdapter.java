package com.example.tamdang.restaurantguide;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class RestaurantAdapter extends ArrayAdapter<Restaurant> {

    private ArrayList<Restaurant> dataSet;
    Context context;

    public RestaurantAdapter(@NonNull Context context, int resource, @NonNull ArrayList<Restaurant> objects) {
        super(context, resource, objects);
        this.context = context;
        this.dataSet = objects;
    }


    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if(convertView == null){
            LayoutInflater inf = LayoutInflater.from(context);
            convertView = inf.inflate(R.layout.custom_layout, parent, false);
        }
        TextView name = convertView.findViewById(R.id.txtName);

        Restaurant r = dataSet.get(position);
        name.setText(r.getName());

        return  convertView;
    }
}
