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
import java.util.List;

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
            convertView = inf.inflate(R.layout.activity_restaurant_detail, parent, false);
        }
        TextView name = convertView.findViewById(R.id.txtName);
        TextView address = convertView.findViewById(R.id.txtAddress);
        TextView phone = convertView.findViewById(R.id.txtPhone);
        TextView description = convertView.findViewById(R.id.txtDescription);
        TextView tag = convertView.findViewById(R.id.txtTag);

        Restaurant r = dataSet.get(position);
        name.setText(r.getName());
        address.setText(r.getAddress());
        phone.setText(r.getPhone());
        description.setText(r.getDescription());
        tag.setText(r.getTag());

        return  convertView;
    }
}
