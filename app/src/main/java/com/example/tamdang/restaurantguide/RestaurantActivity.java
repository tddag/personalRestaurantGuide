package com.example.tamdang.restaurantguide;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

public class RestaurantActivity extends AppCompatActivity {

    Button btnAdd;
    private ArrayList<Restaurant> restaurants;
    private RestaurantAdapter restaurantsAdapter;
    private ListView lvRestaurants;
    public static final int ADD_RESTAURANT = 1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurant);

        lvRestaurants = findViewById(R.id.restaurantsList);
        restaurants = new ArrayList<>();
        restaurantsAdapter = new RestaurantAdapter(this, R.layout.activity_restaurant_detail, restaurants);
        lvRestaurants.setAdapter(restaurantsAdapter);

        // Defining Button
        btnAdd = findViewById(R.id.btnAdd);

        // Set Click Listener
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(v.getContext(), AddActivity.class);
                startActivityForResult(i, ADD_RESTAURANT);
            }
        });
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == ADD_RESTAURANT){
            if(resultCode == RESULT_OK){
                String name = data.getStringExtra("name");
                String address = data.getStringExtra("address");
                String phone = data.getStringExtra("phone");
                String description = data.getStringExtra("description");
                String tag = data.getStringExtra("tag");

                restaurantsAdapter.add(new Restaurant(name, address, phone, description, tag));
            }
        }
    }
}
