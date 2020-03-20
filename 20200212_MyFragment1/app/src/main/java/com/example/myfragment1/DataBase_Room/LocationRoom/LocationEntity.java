package com.example.myfragment1.DataBase_Room.LocationRoom;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "LocationEntity")
public class LocationEntity {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    private int id;
    private String location_Title = null;
    private String location_Addr = null;
    private String location_DetailAddr = null;
    private String location_Phone = null;
    private String location_Memo = null;
    private String location_Latitude = null;
    private String location_Longitude = null;
    private String location_Timestamp = null;

    public LocationEntity(String location_Title, String location_Addr, String location_DetailAddr, String location_Phone, String location_Memo, String location_Latitude, String location_Longitude, String location_Timestamp) {
        this.location_Title = location_Title;
        this.location_Addr = location_Addr;
        this.location_DetailAddr = location_DetailAddr;
        this.location_Phone = location_Phone;
        this.location_Memo = location_Memo;
        this.location_Latitude = location_Latitude;
        this.location_Longitude = location_Longitude;
        this.location_Timestamp = location_Timestamp;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }
    public String getLocation_Title() {
        return location_Title;
    }
    public String getLocation_Addr() {
        return location_Addr;
    }

    public String getLocation_DetailAddr() {
        return location_DetailAddr;
    }

    public String getLocation_Phone() {
        return location_Phone;
    }

    public String getLocation_Memo() {
        return location_Memo;
    }

    public String getLocation_Latitude() {
        return location_Latitude;
    }

    public String getLocation_Longitude() {
        return location_Longitude;
    }

    public String getLocation_Timestamp() {
        return location_Timestamp;
    }
}
