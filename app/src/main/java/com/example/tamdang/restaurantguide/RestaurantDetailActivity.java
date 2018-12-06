package com.example.tamdang.restaurantguide;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.TextView;

public class RestaurantDetailActivity extends AppCompatActivity {

    Button btnEdit;
    private RatingBar rating_bar;
    // Define DB
    private SQLiteDatabase db;
    private RestaurantDBHelper myDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurant_detail);

        // DB instantiation
        myDB = new RestaurantDBHelper(this);
        db = myDB.getWritableDatabase();

        // Get restaurantID from intent
        Intent i = getIntent();
        final long restaurantID = i.getLongExtra("restaurantID", -1);

        TextView txtName = findViewById(R.id.txtName);
        TextView txtAddress = findViewById(R.id.txtAddress);
        TextView txtPhone = findViewById(R.id.txtPhone);
        TextView txtDescription = findViewById(R.id.txtDescription);
        TextView txtTag = findViewById(R.id.txtTag);

        Restaurant r = myDB.getRestaurant(db, restaurantID);
        txtName.setText(r.getName());
        txtAddress.setText(r.getAddress());
        txtPhone.setText(r.getPhone());
        txtDescription.setText(r.getDescription());
        txtTag.setText(r.getTag());

        //Rating Bar
        rating_bar = findViewById(R.id.ratingBar3);
        rating_bar.setRating(r.getRating());
        rating_bar.setEnabled(false);

        //Defining Button
        btnEdit = findViewById(R.id.btnEdit);
        // set even for btnEdit
        btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(v.getContext(), EditActivity.class);
                i.putExtra("restaurantID", restaurantID);
                startActivity(i);
            }
        });
    }
}
