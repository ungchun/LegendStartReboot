package com.example.myfragment1.DataBase_Room.LocationTagEntity;


import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface LocationTag_Dao {

    @Insert
    void insert(LocationTagEntity locationTagEntity);
    @Delete
    void delete(LocationTagEntity... locationTagEntities);
    @Update
    void update(LocationTagEntity locationTagEntity);

    @Query("SELECT * FROM LocationTagTable ORDER BY location_id DESC")
    LiveData<List<LocationTagEntity>> getAllLocationTagData();

    @Query("SELECT * FROM LocationTagTable WHERE location_id = :locationId")
    List<LocationTagEntity> getDataByLocationId(int locationId);
}
