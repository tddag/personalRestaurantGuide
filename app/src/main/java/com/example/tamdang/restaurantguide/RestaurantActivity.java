package com.example.tamdang.restaurantguide;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

public class RestaurantActivity extends AppCompatActivity {

    Button btnAdd;
    private ArrayList<Restaurant> restaurants;
    private RestaurantAdapter restaurantsAdapter;
    private ListView lvRestaurants;
    public static final int ADD_RESTAURANT = 1;
    public static final int DETAIL_RESTAURANT = 1;
    // Database
    private SQLiteDatabase db;
    private RestaurantDBHelper myDB;

    public String TAG = "My TAG";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurant);

        // Database
        myDB = new RestaurantDBHelper(this);
        db = myDB.getWritableDatabase();

        Restaurant r1 = myDB.getRestaurant(db, 1);
        Restaurant r2 = myDB.getRestaurant(db, 2);
        Restaurant r3 = myDB.getRestaurant(db, 3);



        lvRestaurants = findViewById(R.id.restaurantsList);
        restaurants = new ArrayList<>();


        restaurants.add(r1);
        restaurants.add(r2);
        restaurants.add(r3);

        restaurantsAdapter = new RestaurantAdapter(this, R.layout.custom_layout, restaurants);
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
        lvRestaurants.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                final int pos = position;
                new AlertDialog.Builder(view.getContext()).setTitle("Warning!")
                        .setMessage("Do you want to remove this restaurant ?")
                        .setNegativeButton(android.R.string.no, null)
                        .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                restaurants.remove(pos);
                                restaurantsAdapter.notifyDataSetChanged();
                            }
                        }).show();
                return true;
            }
        });
        lvRestaurants.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent i = new Intent(view.getContext(), RestaurantDetailActivity.class);
                i.putExtra("position", position);
                i.putExtra("restaurantID", restaurants.get(position).getId());
                i.putExtra("name", restaurants.get(position).getName());
                i.putExtra("address", restaurants.get(position).getAddress());
                i.putExtra("phone", restaurants.get(position).getPhone());
                i.putExtra("description", restaurants.get(position).getDescription());
                i.putExtra("tag", restaurants.get(position).getTag());
                i.putExtra("rating", restaurants.get(position).getRating());
                Log.d(TAG, restaurants.get(position).getRating() + "");
                Log.d(TAG, "ID: "+restaurants.get(position).getId());
                startActivityForResult(i, DETAIL_RESTAURANT);
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
                String rating = data.getStringExtra("rating");

                restaurantsAdapter.add(new Restaurant(name, address, phone, description, tag, Float.parseFloat(rating)));
            }
        }
    }
}
