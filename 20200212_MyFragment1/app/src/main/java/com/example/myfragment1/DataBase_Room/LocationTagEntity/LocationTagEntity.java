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
    private String tag_ID_1;
    private String tag_ID_2;
    private String tag_ID_3;
    private String tag_ID_4;
    private String tag_ID_5;

    public String getTag_ID_1() {
        return tag_ID_1;
    }

    public String getTag_ID_2() {
        return tag_ID_2;
    }

    public String getTag_ID_3() {
        return tag_ID_3;
    }

    public String getTag_ID_4() {
        return tag_ID_4;
    }

    public String getTag_ID_5() {
        return tag_ID_5;
    }

    public String getLocation_id() {
        return location_id;
    }

    public void setLocation_id(String location_id) {
        this.location_id = location_id;
    }

    public LocationTagEntity(@NonNull String location_id, String tag_ID_1, String tag_ID_2, String tag_ID_3, String tag_ID_4, String tag_ID_5) {
        this.location_id = location_id;
        this.tag_ID_1 = tag_ID_1;
        this.tag_ID_2 = tag_ID_2;
        this.tag_ID_3 = tag_ID_3;
        this.tag_ID_4 = tag_ID_4;
        this.tag_ID_5 = tag_ID_5;
    }
}
