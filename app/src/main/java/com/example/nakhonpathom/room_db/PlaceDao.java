package com.example.nakhonpathom.room_db;

import androidx.room.Dao;
import androidx.room.Query;

import com.example.nakhonpathom.model.Place;

import java.util.List;

@Dao
public interface PlaceDao {

    @Query("SELECT * FROM place")
    List<Place> getAllPlace();
}
