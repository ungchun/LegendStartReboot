package com.example.myfragment1.LocationList_RecyclerView;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.myfragment1.DataBase_Room.LocationRoom.LocationEntity_Dao;
import com.example.myfragment1.DataBase_Room.LocationTagEntity.LocationTagEntity;
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
    public int insert_LocationEntity(LocationEntity locationEntity){
        return repository.insert_Location(locationEntity);
    }
    public void update_LocationEntity(LocationEntity locationEntity){
        repository.update_Location(locationEntity);
    }
    public LocationEntity delete_LocationEntity(LocationEntity locationEntity){
        repository.delete_Location(locationEntity);
        return locationEntity;
    }
    public LocationTagEntity SearchTagByLocationID_LocationTagEntity(int locationID){
        return repository.searchByLocationID_LocationTag(locationID);
    }
    public void insert_TagEntity(TagEntity... tagEntities){
        repository.insert_Tag(tagEntities);
    }
    public TagEntity[] delete_TagEntity(TagEntity... tagEntities){
        return repository.delete_Tag(tagEntities);
    }
    public void insert_LocationTagEntity(LocationTagEntity locationTagEntity){
        repository.insert_LocationTag(locationTagEntity);
    }
    public LocationTagEntity delete_LocationTagEntity(LocationTagEntity locationTagEntity){
        return repository.delete_LocationTag(locationTagEntity);
    }


    public void deleteAllDates_LocationEntity(){
        repository.deleteAllDates();
    }
    public LiveData<List<LocationEntity>> getAllLocationData_LocationEntity(){
        return allLocationData;
    }
    public LiveData<List<TagEntity>> getAllTagData(){
        return allTagData;
    }

    public List<TagEntity> searchTagByLocationID_TagEntity(int position){
        return repository.searchAboutLocationId(position);
    }

}
