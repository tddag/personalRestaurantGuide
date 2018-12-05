package com.example.tamdang.restaurantguide;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;
import android.os.AsyncTask;
import android.support.annotation.NonNull;

import java.util.List;

@Database(entities = {Restaurant.class}, version = 1, exportSchema = false)
public abstract class RestaurantRoomDatabase extends RoomDatabase {
    RestaurantDao dao;
    private static volatile RestaurantRoomDatabase INSTANCE;

    static RestaurantRoomDatabase getDatabase(Context context) {
        if (INSTANCE == null) {
            synchronized (RestaurantRoomDatabase.class) {
                if (INSTANCE == null) {
                    //Create database here
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            RestaurantRoomDatabase.class, "db").addCallback(cb).build();
                }
            }
        }

        return INSTANCE;
    }

    private static RoomDatabase.Callback cb = new Callback() {
        //Where we actually access to the SQLite db
        @Override
        public void onOpen(@NonNull SupportSQLiteDatabase db) {
            super.onOpen(db);
            new PopulateDatabase(INSTANCE).execute();
        }
    };

    //populate database
    private static class PopulateDatabase extends AsyncTask<Void, Void, Void>{

        private RestaurantDao dao;

        PopulateDatabase(RestaurantRoomDatabase db){
            this.dao = db.dao;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            Restaurant res = new Restaurant("Wood Bar","256 Wood Ave"
                    ,"555-555-5555","Russian Bar","russian, bar, wood");
            dao.insert(res);
            res = new Restaurant("Insomia",
                    "5 Christie Street", "444-444-4444",
                    "Some nice ass bar", "insomia, bar, wine");
            dao.insert(res);
            return null;
        }
    }

}
