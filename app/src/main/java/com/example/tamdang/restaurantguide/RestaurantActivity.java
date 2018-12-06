package com.example.tamdang.restaurantguide;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
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


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurant);

        // Database
        myDB = new RestaurantDBHelper(this);
        db = myDB.getWritableDatabase();

        lvRestaurants = findViewById(R.id.restaurantsList);
        restaurants = new ArrayList<>();


        Cursor data = myDB.getAllRestaurants(db);
        while(data.moveToNext()){
            Restaurant r = myDB.getRestaurant(db, Long.parseLong(data.getString(0)));
            restaurants.add(r);
        }

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
            public boolean onItemLongClick(final AdapterView<?> parent, View view, final int position, long id) {
                new AlertDialog.Builder(view.getContext()).setTitle("Warning!")
                        .setMessage("Do you want to remove this restaurant ?")
                        .setNegativeButton(android.R.string.no, null)
                        .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Restaurant r = (Restaurant) parent.getItemAtPosition(position);
                                restaurants.remove(r);
                                myDB.removeRestaurant(db, r);
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
                startActivityForResult(i, DETAIL_RESTAURANT);
            }
        });
    }
}
