package com.example.tamdang.restaurantguide;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import java.util.List;

public class RestaurantViewModel extends AndroidViewModel {
    private Repository repo;
    private LiveData<List<Restaurant>> mAllRes;
    public RestaurantViewModel(@NonNull Application application) {
        super(application);

        repo = new Repository(application);
        mAllRes = repo.getAllRes();
    }

    LiveData<List<Restaurant>> getmAllRes(){
        return this.mAllRes;
    }

    public void insert(Restaurant res){
        repo.insert(res);
    }
}
