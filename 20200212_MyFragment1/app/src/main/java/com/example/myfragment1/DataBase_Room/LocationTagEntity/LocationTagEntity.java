package com.example.myfragment1.DataBase_Room.LocationTagEntity;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

import com.example.myfragment1.DataBase_Room.LocationRoom.LocationEntity;


@Entity(tableName = "LocationTagTable")
        //foreignKeys = @ForeignKey(entity = LocationEntity.class, parentColumns = "id", childColumns = "location_id", onDelete = ForeignKey.CASCADE))
public class LocationTagEntity {
    @PrimaryKey(autoGenerate = false)
    @NonNull
    private String location_id;
    private String tag_Tag;

    public String getTag_Tag() {
        return tag_Tag;
    }

    public String getLocation_id() {
        return location_id;
    }

    public void setLocation_id(String location_id) {
        this.location_id = location_id;
    }

    public LocationTagEntity(String location_id, String tag_Tag) {
        this.location_id = location_id;
        this.tag_Tag = tag_Tag;
    }
}
