package com.example.tamdang.restaurantguide;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

@Dao
public interface RestaurantDao {

    @Insert
    void insert(Restaurant res);

    @Query("SELECT*FROM restaurant ORDER BY name ASC")
    LiveData<List<Restaurant>> getAllRes();


}
