package com.example.myfragment1.LocationList_RecyclerView;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.myfragment1.DataBase_Room.Repository.LocationRepository;
import com.example.myfragment1.DataBase_Room.LocationRoom.LocationEntity;
import com.example.myfragment1.DataBase_Room.TagEntity.TagEntity;

import java.util.List;

public class LocationViewModel extends AndroidViewModel {
    private LocationRepository repository;
    private LiveData<List<LocationEntity>> allLocationData;
    private LiveData<List<TagEntity>> allTagData;
    public LocationViewModel(@NonNull Application application) {
        super(application);
        repository = new LocationRepository(application);
        allLocationData = repository.getAllLocations();
        allTagData = repository.getAllTags();

    }
    public int insert(LocationEntity locationEntity){
        repository.insert_Location(locationEntity);
        return locationEntity.getId();
    }
    public void update(LocationEntity locationEntity){
        repository.update_Location(locationEntity);
    }
    public LocationEntity delete(LocationEntity locationEntity){
        repository.delete_Location(locationEntity);
        return locationEntity;
    }
    public void deleteAllDates(){
        repository.deleteAllDates();
    }
    public LiveData<List<LocationEntity>> getAllLocationData(){
        return allLocationData;
    }
    public LiveData<List<TagEntity>> getAllTagData(){
        return allTagData;
    }

    public List<TagEntity> searchTagByLocationID(int position){
        return repository.searchAboutLocationId(position);
    }

}
