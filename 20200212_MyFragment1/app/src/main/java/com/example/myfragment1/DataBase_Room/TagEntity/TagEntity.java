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
    private int tag_id;

    @ColumnInfo(name = "location_Foreign_id")
    private int locationId;

    private String tag;

    public TagEntity(int locationId, String tag) {
        this.locationId = locationId;
        this.tag = tag;
    }

    public void setTag_id(int tag_id) {
        this.tag_id = tag_id;
    }

    public int getTag_id() {
        return tag_id;
    }

    public int getLocationId() {
        return locationId;
    }

    public String getTag() {
        return tag;
    }

    public TagEntity searchByLocationID(int position){
        if(this.locationId == position)
            return this;
        else
            return null;
    }

}


