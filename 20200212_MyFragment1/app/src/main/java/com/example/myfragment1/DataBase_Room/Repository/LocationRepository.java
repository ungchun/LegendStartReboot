package com.example.myfragment1.DataBase_Room.Repository;

import android.app.Application;
import androidx.lifecycle.LiveData;

import com.example.myfragment1.DataBase_Room.LocationRoom.LocationDatabase;
import com.example.myfragment1.DataBase_Room.LocationRoom.LocationEntity;
import com.example.myfragment1.DataBase_Room.LocationRoom.LocationEntity_Dao;
import com.example.myfragment1.DataBase_Room.LocationRoom.Location_AsyncTask;
import com.example.myfragment1.DataBase_Room.LocationTagEntity.LocationTagEntity;
import com.example.myfragment1.DataBase_Room.LocationTagEntity.LocationTag_AsyncTask;
import com.example.myfragment1.DataBase_Room.LocationTagEntity.LocationTag_Dao;
import com.example.myfragment1.DataBase_Room.TagEntity.TagAsyncTask;
import com.example.myfragment1.DataBase_Room.TagEntity.TagEntity;
import com.example.myfragment1.DataBase_Room.TagEntity.TagEntity_Dao;

import java.util.List;

public class LocationRepository {
    private LocationEntity_Dao locationEntity_dao;
    private TagEntity_Dao tagEntity_dao;
    private LocationTag_Dao locationTag_dao;

    private LiveData<List<LocationEntity>> allLocations;
    private LiveData<List<TagEntity>> allTags;
    private LiveData<List<LocationTagEntity>> allLocationTagData;

    public LiveData<List<TagEntity>> getAllTags() {
        return allTags;
    }

    public LocationRepository(Application application) {
        LocationDatabase locationDatabase = LocationDatabase.getInstance(application);



        this.locationEntity_dao = locationDatabase.locationEntity_dao();
        this.tagEntity_dao = locationDatabase.tagEntity_dao();
        this.locationTag_dao = locationDatabase.locationTag_dao();

        allLocations = locationEntity_dao.getAllData();
        allLocationTagData = locationTag_dao.getAllLocationTagData();
        allTags = tagEntity_dao.getAllData();


    }
    public void insert_Location(LocationEntity locationEntity){
        new Location_AsyncTask.InsertLocationAsyncTask(locationEntity_dao).execute(locationEntity);
    }
    public void update_Location(LocationEntity locationEntity){
        new Location_AsyncTask.UpdateLocationAsyncTask(locationEntity_dao).execute(locationEntity);
    }
    public LocationEntity delete_Location(LocationEntity locationEntity){
        new Location_AsyncTask.DeleteLocationAsyncTask(locationEntity_dao).execute(locationEntity);
        return locationEntity;
    }
    public void insert_Tag(TagEntity tagEntity){
        new TagAsyncTask.InsertTagAsyncTask(tagEntity_dao).execute(tagEntity);
    }
    public TagEntity[] delete_Tag(TagEntity... tagEntities){
        new TagAsyncTask.DeleteTagAsyncTask(tagEntity_dao).execute(tagEntities);
        return tagEntities;
    }
    public void insert_LocationTag(LocationTagEntity locationTagEntity){
        new LocationTag_AsyncTask.InsertLocationTagAsyncTask(locationTag_dao).execute(locationTagEntity);
    }
    public LocationTagEntity[] delete_LocationTag(LocationTagEntity... locationTagEntities){
        new LocationTag_AsyncTask.DeleteLocationTagAsyncTask(locationTag_dao).execute(locationTagEntities);
        return locationTagEntities;
    }
    public void update_LocationTag(LocationTagEntity locationTagEntity){
        new LocationTag_AsyncTask.UpdateLocationTagAsyncTask(locationTag_dao).execute(locationTagEntity);
    }
    public List<LocationTagEntity> searchByLocationID_LocationTag(int locationID){
        return (List<LocationTagEntity>) new LocationTag_AsyncTask.SearchForTagIDByLocationID(locationTag_dao).execute(locationID);
    }


    public List<TagEntity> searchAboutLocationId(int position){
        final List<TagEntity> result = (List<TagEntity>) new TagAsyncTask.SearchAboutLocationId(tagEntity_dao).execute(position);
        return result;
    }


    public void deleteAllDates(){
        new Location_AsyncTask.DeleteAllLocationAsyncTask(locationEntity_dao).execute();
    }
    public LiveData<List<LocationEntity>> getAllLocations(){
        return allLocations;
    }

}
