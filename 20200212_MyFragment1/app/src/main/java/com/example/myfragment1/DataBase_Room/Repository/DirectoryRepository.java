package com.example.myfragment1.DataBase_Room.Repository;

import android.app.Application;
import android.content.Context;
import android.provider.ContactsContract;

import androidx.lifecycle.LiveData;

import com.example.myfragment1.DataBase_Room.DirectoryRoom.DirectoryDao;
import com.example.myfragment1.DataBase_Room.DirectoryRoom.DirectoryDatabase;
import com.example.myfragment1.DataBase_Room.DirectoryRoom.DirectoryEntity;
import com.example.myfragment1.DataBase_Room.DirectoryRoom.Directory_AsyncTask;
import com.example.myfragment1.DataBase_Room.LocationRoom.LocationDatabase;
import com.example.myfragment1.DataBase_Room.LocationRoom.LocationEntity;
import com.example.myfragment1.DataBase_Room.LocationRoom.Location_AsyncTask;

import java.util.List;

public class DirectoryRepository {
    private DirectoryDao directoryDao;
    private LiveData<List<DirectoryEntity>> getAllDirectory;

    public DirectoryRepository(Context application) {
        DirectoryDatabase directoryDatabase = DirectoryDatabase.getInstance(application);

        this.directoryDao = directoryDatabase.directoryDao();
        this.getAllDirectory = directoryDao.getAll();
    }

    public void insert_Directory(DirectoryEntity directoryEntity){
        new Directory_AsyncTask.InsertDirectoryAsyncTask(directoryDao).execute(directoryEntity);
    }

    public void delete_Directory(DirectoryEntity directoryEntity){
        new Directory_AsyncTask.DeleteDirectoryAsyncTask(directoryDao).execute(directoryEntity);
    }

    public void update_Directory(DirectoryEntity directoryEntity){
        new Directory_AsyncTask.UpdateDirectoryAsyncTask(directoryDao).execute(directoryEntity);
    }

    public LiveData<List<DirectoryEntity>> getAllDirectory(){
        return getAllDirectory;
    }
}
