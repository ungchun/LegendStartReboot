package com.example.myfragment1.DataBase_Room.Repository;

import android.app.Application;
import android.content.Context;

import com.example.myfragment1.DataBase_Room.DirectoryRoom.DirectoryDao;
import com.example.myfragment1.DataBase_Room.DirectoryRoom.DirectoryDatabase;
import com.example.myfragment1.DataBase_Room.DirectoryRoom.DirectoryEntity;
import com.example.myfragment1.DataBase_Room.DirectoryRoom.Directory_AsyncTask;
import com.example.myfragment1.DataBase_Room.LocationRoom.LocationDatabase;
import com.example.myfragment1.DataBase_Room.LocationRoom.LocationEntity;
import com.example.myfragment1.DataBase_Room.LocationRoom.Location_AsyncTask;

public class DirectoryRepository {
    private DirectoryDao directoryDao;

    public DirectoryRepository(Context application) {
        DirectoryDatabase directoryDatabase = DirectoryDatabase.getInstance(application);

        this.directoryDao = directoryDatabase.directoryDao();
    }
    public void insert_Directory(DirectoryEntity directoryEntity){
        new Directory_AsyncTask.InsertDirectoryAsyncTask(directoryDao).execute(directoryEntity);
    }
}
