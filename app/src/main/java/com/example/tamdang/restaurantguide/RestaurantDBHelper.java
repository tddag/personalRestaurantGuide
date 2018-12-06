package com.example.tamdang.restaurantguide;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class RestaurantDBHelper extends SQLiteOpenHelper {
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "restaurant.db";

    public RestaurantDBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(RestaurantContract.SQL_CREATE_RESTAURANT);
        addRestaurant(db, new Restaurant("John", "Doe", "Software Engineer - Google Inc.", "johndoe@gmail.com", "Canadian, Enthusiasm in Programming", 4));
        addRestaurant(db, new Restaurant("Elon", "Musk", "Founder - Tesla Inc.", "elonmusk@gmail.com", "AI, Machine Learning, Technology Entrepreneurship", 1));
        addRestaurant(db, new Restaurant("Bill", "Gates", "Founder - Microsoft Inc.", "billgates@gmail.com", "Billionaire, Windows OS Founder", 5));
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(RestaurantContract.SQL_DROP_RESTAURANT);
        onCreate(db);
    }

    public long addRestaurant(SQLiteDatabase db, Restaurant restaurant){
        ContentValues values = new ContentValues();
        values.put(RestaurantContract.RestaurantEntry.NAME, restaurant.getName());
        values.put(RestaurantContract.RestaurantEntry.ADDRESS, restaurant.getAddress());
        values.put(RestaurantContract.RestaurantEntry.PHONE, restaurant.getPhone());
        values.put(RestaurantContract.RestaurantEntry.DESCRIPTION, restaurant.getDescription());
        values.put(RestaurantContract.RestaurantEntry.TAG, restaurant.getTag());
        values.put(RestaurantContract.RestaurantEntry.RATING, restaurant.getRating());

        return db.insert(RestaurantContract.RestaurantEntry.TABLE_NAME, null, values);
    }

    public Restaurant getRestaurant(SQLiteDatabase db, long restaurantId){
        String[] projection = {
                RestaurantContract.RestaurantEntry._ID,
                RestaurantContract.RestaurantEntry.NAME,
                RestaurantContract.RestaurantEntry.ADDRESS,
                RestaurantContract.RestaurantEntry.PHONE,
                RestaurantContract.RestaurantEntry.DESCRIPTION,
                RestaurantContract.RestaurantEntry.TAG,
                RestaurantContract.RestaurantEntry.RATING,
        };
        String selection = RestaurantContract.RestaurantEntry._ID+"= ? ";
        String[] selectionArgs = {Long.toString(restaurantId)};

        Cursor cursor = db.query(
                RestaurantContract.RestaurantEntry.TABLE_NAME,
                projection,
                selection,
                selectionArgs,
                null,
                null,
                null
        );

        if(cursor.moveToFirst()){

            long id = cursor.getLong(cursor.getColumnIndexOrThrow(RestaurantContract.RestaurantEntry._ID));
            String name = cursor.getString(
                    cursor.getColumnIndexOrThrow(RestaurantContract.RestaurantEntry.NAME));
            String address = cursor.getString(
                    cursor.getColumnIndexOrThrow(RestaurantContract.RestaurantEntry.ADDRESS));
            String phone = cursor.getString(
                    cursor.getColumnIndexOrThrow(RestaurantContract.RestaurantEntry.PHONE));
            String description = cursor.getString(
                    cursor.getColumnIndexOrThrow(RestaurantContract.RestaurantEntry.DESCRIPTION));
            String tag = cursor.getString(
                    cursor.getColumnIndexOrThrow(RestaurantContract.RestaurantEntry.TAG));
            float rating = cursor.getLong(cursor.getColumnIndexOrThrow(RestaurantContract.RestaurantEntry.RATING));
            Restaurant restaurant = new Restaurant(id, name, address, phone, description, tag, rating);
            return restaurant;

        }

        cursor.close();
        return null;
    }
    public boolean editRestaurant(SQLiteDatabase db, long restaurantId, String name, String address, String phone, String description, String tag, float rating){
        ContentValues values = new ContentValues();
        values.put(RestaurantContract.RestaurantEntry.NAME, name);
        values.put(RestaurantContract.RestaurantEntry.ADDRESS, address);
        values.put(RestaurantContract.RestaurantEntry.PHONE, phone);
        values.put(RestaurantContract.RestaurantEntry.DESCRIPTION, description);
        values.put(RestaurantContract.RestaurantEntry.TAG, tag);
        values.put(RestaurantContract.RestaurantEntry.RATING, rating);
        db.update(RestaurantContract.RestaurantEntry.TABLE_NAME, values, "ID= ?", new String[]{Long.toString(restaurantId)});
        return true;
    }
    public boolean removeRestaurant(SQLiteDatabase db, Restaurant res){

        db.delete(RestaurantContract.RestaurantEntry.TABLE_NAME, "ID= ?", new String[]{Long.toString(res.getId())});
        return true;
    }

    public Cursor getAllRestaurants(SQLiteDatabase db){
        Cursor c = db.rawQuery("SELECT * FROM " + RestaurantContract.RestaurantEntry.TABLE_NAME, null);
        return c;
    }
}
