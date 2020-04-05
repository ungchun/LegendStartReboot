package com.example.myfragment1.DataBase_Room.LocationTagEntity;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

import com.example.myfragment1.DataBase_Room.LocationRoom.LocationEntity;
import com.example.myfragment1.DataBase_Room.TagEntity.TagEntity;

@Entity(tableName = "LocationTagTable")/*, foreignKeys = {
        @ForeignKey(
                entity = LocationEntity.class,
                parentColumns = "id",
                childColumns = "location_id"
        ),
        @ForeignKey(
                entity = TagEntity.class,
                parentColumns = "id",
                childColumns = "tag_id"
        )
})*/

public class LocationTagEntity {
    @PrimaryKey(autoGenerate = true)
    private int locationtag_id;

    private int location_id;
    private int tag_id;

    public int getLocationtag_id() {
        return locationtag_id;
    }

    public void setLocationtag_id(int locationtag_id) {
        this.locationtag_id = locationtag_id;
    }

    public LocationTagEntity(@NonNull int location_id, int tag_id) {
        this.location_id = location_id;
        this.tag_id = tag_id;
    }

    public int getLocation_id() {
        return location_id;
    }

    public void setLocation_id(int location_id) {
        this.location_id = location_id;
    }

    public int getTag_id() {
        return tag_id;
    }

    public void setTag_id(int tag_id) {
        this.tag_id = tag_id;
    }
}
