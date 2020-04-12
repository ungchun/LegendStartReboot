package com.example.myfragment1.DataBase_Room.DirectoryRoom;

import android.location.Location;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.myfragment1.DataBase_Room.LocationRoom.LocationEntity;

import java.util.List;

@Dao
public interface DirectoryDao {
    @Query("SELECT * FROM DirectoryEntity")
    LiveData<List<DirectoryEntity>> getAll();

    @Insert
    void insert(DirectoryEntity directory);
}
