package com.example.tamdang.restaurantguide;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;

public class AddActivity extends AppCompatActivity {

    // Database
    private SQLiteDatabase db;
    private RestaurantDBHelper myDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        // Get DB
        myDB = new RestaurantDBHelper(this);
        db = myDB.getWritableDatabase();

        // Define Button Add
        Button btnAdd = findViewById(R.id.btnAdd);

        // set event for btnAdd
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Set intent to RestaurantActivity
                Intent i = new Intent(v.getContext(), RestaurantActivity.class);

                EditText addName = findViewById(R.id.addName);
                EditText addAddress = findViewById(R.id.addAddress);
                EditText addPhone = findViewById(R.id.addPhone);
                EditText addDescription = findViewById(R.id.addDescription);
                EditText addTag = findViewById(R.id.addTag);

                String name = addName.getText().toString();
                String address = addAddress.getText().toString();
                String phone = addPhone.getText().toString();
                String description = addDescription.getText().toString();
                String tag = addTag.getText().toString();

                // Add new Restaurant to DB
                Restaurant newRestaurant = new Restaurant(name, address, phone, description, tag);
                myDB.addRestaurant(db, newRestaurant);

                // Go back to RestaurantActivity
                startActivity(i);
            }
        });
    }
}
