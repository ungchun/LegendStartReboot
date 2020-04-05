package com.example.myfragment1.DataBase_Room.TagEntity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

import com.example.myfragment1.DataBase_Room.LocationRoom.LocationEntity;

import java.util.List;

@Entity(tableName = "Tag_Database")
//        foreignKeys = {
//                @ForeignKey(entity = LocationEntity.class,
//                        parentColumns = "id", childColumns = "location_Foreign_id",
//                        onDelete = ForeignKey.CASCADE, onUpdate = ForeignKey.SET_NULL)})
// Foreign key not Recommand for resuorce
public class TagEntity {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private String tag;

    public TagEntity(String tag) {
        this.tag = tag;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setTag(String tag){
        this.tag = tag;
    }
    public String getTag() {
        return tag;
    }
}


