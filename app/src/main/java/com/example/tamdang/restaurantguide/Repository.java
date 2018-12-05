package com.example.tamdang.restaurantguide;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;

import java.util.List;

public class Repository {
    private RestaurantDao dao;
    private LiveData<List<Restaurant>> mAllRes;

    Repository(Application app){
        RestaurantRoomDatabase db = RestaurantRoomDatabase.getDatabase(app);
        dao = db.dao;
        mAllRes = dao.getAllRes();
    }

    public LiveData<List<Restaurant>> getAllRes(){
        return this.mAllRes;
    }

    void insert(Restaurant res){
        new insertAsyncTask(dao).execute(res);
    }

    private static class insertAsyncTask extends AsyncTask<Restaurant, Void, Void> {

        private RestaurantDao mAsyncDao;

        insertAsyncTask(RestaurantDao dao){
            this.mAsyncDao = dao;
        }
        @Override
        protected Void doInBackground(final Restaurant... params) {
            mAsyncDao.insert(params[0]);
            return null;
        }
    }
}
