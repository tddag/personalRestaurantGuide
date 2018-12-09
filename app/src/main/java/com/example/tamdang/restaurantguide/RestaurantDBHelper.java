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
        addRestaurant(db, new Restaurant("Grey Gardens", "199 Augusta, Toronto ON M5T 2L4", "(647) 351-1552", "Part wine bar and part restaurant, this effortlessly cool spot has an bohemian-luxe atmosphere and an unforgettable Peking-style duck.", "Seafood, Dinner & Dessert", 4, 43.654124, -79.401534));
        addRestaurant(db, new Restaurant("Sotto Sotto", "120 Avenue Rd, Toronto ON M5R 2H4", "(416) 962-0011", "This candlelit space has plenty of nooks and crannies—perfect for curling up with a loved one or spying on an A-lister.", "Italian, Dinner, Lunch", 1,43.674025, -79.396360));
        addRestaurant(db, new Restaurant("Barberian's Steak House", "7 Elm St., Toronto ON M5G 1H1, Canada", "(416) 597-0335", "To dine at this old battle axe of a restaurant—a veritable Toronto institution since 1959—is to step back in time.", "American, Dinner & Happy Hour", 5, 43.657888, -79.382236));
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
        values.put(RestaurantContract.RestaurantEntry.LATITUDE, restaurant.getLatitude());
        values.put(RestaurantContract.RestaurantEntry.LONGITUDE, restaurant.getLongitude());

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
                RestaurantContract.RestaurantEntry.LATITUDE,
                RestaurantContract.RestaurantEntry.LONGITUDE,
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
            double latitude = cursor.getLong(cursor.getColumnIndexOrThrow(RestaurantContract.RestaurantEntry.LATITUDE));
            double longitude = cursor.getLong(cursor.getColumnIndexOrThrow(RestaurantContract.RestaurantEntry.LONGITUDE));

            Restaurant restaurant = new Restaurant(id, name, address, phone, description, tag, rating, latitude, longitude);
            return restaurant;

        }

        cursor.close();
        return null;
    }
    public boolean updateRestaurant(SQLiteDatabase db, long restaurantId, String name, String address, String phone, String description, String tag, float rating){
        ContentValues values = new ContentValues();
        values.put(RestaurantContract.RestaurantEntry.NAME, name);
        values.put(RestaurantContract.RestaurantEntry.ADDRESS, address);
        values.put(RestaurantContract.RestaurantEntry.PHONE, phone);
        values.put(RestaurantContract.RestaurantEntry.DESCRIPTION, description);
        values.put(RestaurantContract.RestaurantEntry.TAG, tag);
        values.put(RestaurantContract.RestaurantEntry.RATING, rating);
//        values.put(RestaurantContract.RestaurantEntry.LATITUDE, latitude);
//        values.put(RestaurantContract.RestaurantEntry.LONGITUDE, longitude);

        db.update(RestaurantContract.RestaurantEntry.TABLE_NAME, values, "ID= ?", new String[]{Long.toString(restaurantId)});
        return true;
    }
    public boolean removeRestaurant(SQLiteDatabase db, Restaurant restaurant){

        db.delete(RestaurantContract.RestaurantEntry.TABLE_NAME, "ID= ?", new String[]{Long.toString(restaurant.getId())});
        return true;
    }

    public Cursor getAllRestaurants(SQLiteDatabase db){
        Cursor c = db.rawQuery("SELECT * FROM " + RestaurantContract.RestaurantEntry.TABLE_NAME, null);
        return c;
    }
}
