package com.example.tamdang.restaurantguide;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

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
                Intent i = getIntent();

                EditText edtName = findViewById(R.id.edtName);
                EditText edtAddress = findViewById(R.id.edtAddress);
                EditText edtPhone = findViewById(R.id.edtPhone);
                EditText edtDescription = findViewById(R.id.edtDescription);
                EditText edtTag = findViewById(R.id.edtTag);

                String name = edtName.getText().toString();
                String address = edtAddress.getText().toString();
                String phone = edtPhone.getText().toString();
                String description = edtDescription.getText().toString();
                String tag = edtTag.getText().toString();

                Restaurant newRestaurant = new Restaurant(name, address, phone, description, tag);
                myDB.addRestaurant(db, newRestaurant);

                i.putExtra("name", name);
                i.putExtra("address", address);
                i.putExtra("phone", phone);
                i.putExtra("description", description);
                i.putExtra("tag", tag);

                setResult(RESULT_OK, i);
                finish();
            }
        });

    }
}
