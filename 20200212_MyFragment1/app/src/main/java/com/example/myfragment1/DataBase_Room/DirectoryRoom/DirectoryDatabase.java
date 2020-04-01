package com.example.myfragment1.DataBase_Room.DirectoryRoom;

import androidx.room.Database;
import androidx.room.RoomDatabase;

// 여기를 수정하든 어쨌든 Room DB 연결시켜야하는데 오류남
//,exportSchema = false
@Database(entities = {DirectoryEntity.class}, version = 1)
public abstract class DirectoryDatabase extends RoomDatabase {
    public abstract DirectoryDao directoryDao();
}
