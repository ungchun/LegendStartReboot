package com.example.myfragment1.DataBase_Room.DirectoryRoom;

import android.app.Application;
import android.provider.ContactsContract;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.myfragment1.DataBase_Room.Repository.DirectoryRepository;

import java.util.List;

public class DirectoryViewModel extends AndroidViewModel {
    private DirectoryRepository repository;
    private LiveData<List<DirectoryEntity>> getAll;

    public DirectoryViewModel(@NonNull Application application) {
        super(application);
        repository = new DirectoryRepository(application);
        getAll = repository.getAllDirectory();
    }

    public void insert(DirectoryEntity directoryEntity){
        repository.insert_Directory(directoryEntity);
    }

    public LiveData<List<DirectoryEntity>> getAllDirectory(){
        return getAll;
    }
}
