package com.example.tamdang.restaurantguide;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class RestaurantDetailActivity extends AppCompatActivity {

    Button btnEdit;
    private int position;
    public static final int EDIT_RESTAURANT = 1;
//    private SQLiteDatabase db;
//    private RestaurantDBHelper myDB;
    public String TAG = "Main";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurant_detail);

        // Database
//        myDB = new RestaurantDBHelper(this);
//        db = myDB.getWritableDatabase();

        Intent i = getIntent();

        position = i.getIntExtra("position", -1);
        final long restaurantID = i.getLongExtra("restaurantID", -1);
        final String name = i.getStringExtra("name");
        final String address = i.getStringExtra("address");
        final String description = i.getStringExtra("description");
        final String phone = i.getStringExtra("phone");
        final String tag = i.getStringExtra("tag");

        TextView txtName = findViewById(R.id.txtName);
        TextView txtAddress = findViewById(R.id.txtAddress);
        TextView txtPhone = findViewById(R.id.txtPhone);
        TextView txtDescription = findViewById(R.id.txtDescription);
        TextView txtTag = findViewById(R.id.txtTag);

        txtName.setText(name);
        txtAddress.setText(address);
        txtPhone.setText(phone);
        txtDescription.setText(description);
        txtTag.setText(tag);

        Log.d(TAG, "ID: "+restaurantID);
        //Defining Button
        btnEdit = findViewById(R.id.btnEdit);

        btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(v.getContext(), EditActivity.class);
                i.putExtra("position", position);
                i.putExtra("restaurantID", restaurantID);
                i.putExtra("name", name);
                i.putExtra("address", address);
                i.putExtra("phone", phone);
                i.putExtra("description", description);
                i.putExtra("tag", tag);
                startActivityForResult(i, EDIT_RESTAURANT);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if(requestCode == EDIT_RESTAURANT){
            if(resultCode == RESULT_OK){
                int pos = data.getIntExtra("position", -1);
                if(pos != -1){

//                    String restaurantID = data.getStringExtra("restaurantID");
                    String name = data.getStringExtra("name");
                    String address = data.getStringExtra("address");
                    String phone = data.getStringExtra("phone");
                    String description = data.getStringExtra("description");
                    String tag = data.getStringExtra("tag");

                    TextView txtName = findViewById(R.id.txtName);
                    TextView txtAddress = findViewById(R.id.txtAddress);
                    TextView txtPhone = findViewById(R.id.txtPhone);
                    TextView txtDescription = findViewById(R.id.txtDescription);
                    TextView txtTag = findViewById(R.id.txtTag);

                    txtName.setText(name);
                    txtAddress.setText(address);
                    txtPhone.setText(phone);
                    txtDescription.setText(description);
                    txtTag.setText(tag);

                    Intent i = getIntent();
                    i.putExtra("name", name);
                    i.putExtra("address", address);
                    i.putExtra("phone", phone);
                    i.putExtra("description", description);
                    i.putExtra("tag", tag);
                    i.putExtra("position", pos);

//                    long resID = Long.parseLong(restaurantID);
//                    myDB.editRestaurant(db, resID, name, address, phone, description, tag);

//                    finish();
//                   startActivityForResult(i, EDIT_RESTAURANT);
                }
            }
        }
    }
}
