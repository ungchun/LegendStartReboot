package com.example.myfragment1.DataBase_Room.TagEntity;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.myfragment1.LocationList_RecyclerView.SendingArrayList;

import java.util.List;

@Dao
public interface TagEntity_Dao {
    @Insert
    void insert(TagEntity... tagEntities);
    @Update
    void update(TagEntity tagEntity);
    @Delete
    void delete(TagEntity... tagEntities);

    @Query("DELETE FROM Tag_Database")
    void deleteAllData();

    @Query("SELECT * FROM Tag_Database ORDER BY tag_id DESC")
    LiveData<List<TagEntity>> getAllData();

    @Query("SELECT * FROM Tag_Database WHERE location_Foreign_id = :location_id")
    List<TagEntity> dismissUsingForeignKey(int location_id);

    @Query("SELECT * FROM Tag_Database WHERE location_Foreign_id = :location_id")
    List<TagEntity> multipleSelectionByForeignKey(int location_id);
}
