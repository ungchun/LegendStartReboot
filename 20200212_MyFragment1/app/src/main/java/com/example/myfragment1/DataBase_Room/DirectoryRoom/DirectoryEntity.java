package com.example.myfragment1.DataBase_Room.DirectoryRoom;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class DirectoryEntity {
    @PrimaryKey(autoGenerate = true)
    private int seq;
    private String title;

    public DirectoryEntity(String title) {
        this.title = title;
    }

    public int getSeq() {
        return seq;
    }

    public void setSeq(int seq) {
        this.seq = seq;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        return title;
    }
}
