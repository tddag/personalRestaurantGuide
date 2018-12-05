package com.example.tamdang.restaurantguide;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.media.Rating;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.TextView;

public class EditActivity extends AppCompatActivity {

    private int position;
    private SQLiteDatabase db;
    private RestaurantDBHelper myDB;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);

        // Database
        myDB = new RestaurantDBHelper(this);
        db = myDB.getWritableDatabase();

        Intent i = getIntent();

        position = i.getIntExtra("position", -1);
//        final long restaurantID = Long.parseLong(i.getStringExtra("restaurantID"));
        final long restaurantID = i.getLongExtra("restaurantID", -1);
        String name = i.getStringExtra("name");
        String address = i.getStringExtra("address");
        String description = i.getStringExtra("description");
        String phone = i.getStringExtra("phone");
        String tag = i.getStringExtra("tag");
        float rating = i.getFloatExtra("rating", -1);

        EditText edtName = findViewById(R.id.edtName);
        EditText edtAddress = findViewById(R.id.edtAddress);
        EditText edtPhone = findViewById(R.id.edtPhone);
        EditText edtDescription = findViewById(R.id.edtDescription);
        EditText edtTag = findViewById(R.id.edtTag);
        RatingBar rb = findViewById(R.id.ratingBar4);

        edtName.setText(name);
        edtAddress.setText(address);
        edtPhone.setText(phone);
        edtDescription.setText(description);
        edtTag.setText(tag);
        rb.setRating(rating);

        Button btnUpdate = findViewById(R.id.btnUpdate);
        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent i = new Intent(v.getContext(), RestaurantActivity.class);
                Intent i = getIntent();

                EditText edtName1 = findViewById(R.id.edtName);
                EditText edtAddress1 = findViewById(R.id.edtAddress);
                EditText edtPhone1 = findViewById(R.id.edtPhone);
                EditText edtDescription1 = findViewById(R.id.edtDescription);
                EditText edtTag1 = findViewById(R.id.edtTag);
                RatingBar rb = findViewById(R.id.ratingBar4);

                String name = edtName1.getText().toString();
                String address = edtAddress1.getText().toString();
                String phone = edtPhone1.getText().toString();
                String description = edtDescription1.getText().toString();
                String tag = edtTag1.getText().toString();
                float rating = rb.getRating();


                myDB.editRestaurant(db, restaurantID, name, address, phone, description, tag);

                i.putExtra("position", position);
                i.putExtra("name", name);
                i.putExtra("address", address);
                i.putExtra("phone", phone);
                i.putExtra("description", description);
                i.putExtra("tag", tag);
                i.putExtra("rating", rating);


                setResult(RESULT_OK, i);
                finish();
//                startActivityForResult(i, RESULT_OK);
            }
        });
    }
}
