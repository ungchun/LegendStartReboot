package com.example.myfragment1.DataBase_Room.DirectoryRoom;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface DirectoryDao {
    @Query("SELECT * FROM DirectoryEntity")
    LiveData<List<DirectoryEntity>> getAll();

    @Insert
    void insert(DirectoryEntity directory);
}
