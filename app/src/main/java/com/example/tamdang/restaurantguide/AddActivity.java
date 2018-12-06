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

        myDB = new RestaurantDBHelper(this);
        db = myDB.getWritableDatabase();

        Button btnAdd = findViewById(R.id.btnAdd);
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(v.getContext(), RestaurantActivity.class);

                EditText edtName = findViewById(R.id.edtName);
                EditText edtAddress = findViewById(R.id.edtAddress);
                EditText edtPhone = findViewById(R.id.edtPhone);
                EditText edtDescription = findViewById(R.id.edtDescription);
                EditText edtTag = findViewById(R.id.edtTag);
                RatingBar rating_bar = findViewById(R.id.ratingBar3);

                String name = edtName.getText().toString();
                String address = edtAddress.getText().toString();
                String phone = edtPhone.getText().toString();
                String description = edtDescription.getText().toString();
                String tag = edtTag.getText().toString();
//                float rating = rating_bar.getRating();

                Restaurant newRestaurant = new Restaurant(name, address, phone, description, tag);
                myDB.addRestaurant(db, newRestaurant);

                startActivity(i);
            }
        });

    }
}
