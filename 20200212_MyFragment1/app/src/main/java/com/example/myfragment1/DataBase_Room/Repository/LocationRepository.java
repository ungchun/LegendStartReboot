package com.example.myfragment1.DataBase_Room.Repository;

import android.app.Application;
import android.content.Context;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.example.myfragment1.DataBase_Room.DirectoryRoom.DirectoryDao;
import com.example.myfragment1.DataBase_Room.DirectoryRoom.DirectoryEntity;
import com.example.myfragment1.DataBase_Room.DirectoryRoom.Directory_AsyncTask;
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

import static com.example.myfragment1.DataBase_Room.LocationTagEntity.LocationTag_AsyncTask.*;

public class LocationRepository {
    private LocationEntity_Dao locationEntity_dao;
    private TagEntity_Dao tagEntity_dao;
    private LocationTag_Dao locationTag_dao;

    private DirectoryDao directoryDao;

    private LiveData<List<LocationEntity>> allLocations;
    private LiveData<List<TagEntity>> allTags;
    private LiveData<List<LocationTagEntity>> allLocationTagData;
    private LocationTag_AsyncTask locationTag_asyncTask;
    public LiveData<List<TagEntity>> getAllTags() {
        return allTags;
    }

    public LocationRepository(Context application) {
        LocationDatabase locationDatabase = LocationDatabase.getInstance(application);

        this.locationEntity_dao = locationDatabase.locationEntity_dao();
        this.tagEntity_dao = locationDatabase.tagEntity_dao();
        this.locationTag_dao = locationDatabase.locationTag_dao();
//        this.directoryDao = locationDatabase.directoryDao();

        allLocations = locationEntity_dao.getAllData();
        allLocationTagData = locationTag_dao.getAllLocationTagData();
        allTags = tagEntity_dao.getAllData();
        locationTag_asyncTask = new LocationTag_AsyncTask(application);

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
    public void insert_Tag(TagEntity... tagEntities){
        new TagAsyncTask.InsertTagAsyncTask(tagEntity_dao).execute(tagEntities);
    }
    public TagEntity[] delete_Tag(TagEntity... tagEntities){
        new TagAsyncTask.DeleteTagAsyncTask(tagEntity_dao).execute(tagEntities);
        return tagEntities;
    }
    public void insert_LocationTag(LocationTagEntity locationTagEntity){
        new InsertLocationTagAsyncTask(locationTag_dao).execute(locationTagEntity);
    }
    public LocationTagEntity delete_LocationTag(LocationTagEntity locationTagEntity){
        new DeleteLocationTagAsyncTask(locationTag_dao).execute(locationTagEntity);
        return locationTagEntity;
    }
    public void update_LocationTag(LocationTagEntity locationTagEntity){
        new UpdateLocationTagAsyncTask(locationTag_dao).execute(locationTagEntity);
    }
    public LocationTagEntity searchByLocationID_LocationTag(int locationID){
        return ((List<LocationTagEntity>) new SearchForTagIDByLocationID(locationTag_dao).execute(locationID)).get(0);
    }
    public List<TagEntity> searchAboutLocationId(int position){
        final List<TagEntity> result = (List<TagEntity>) new TagAsyncTask.SearchAboutLocationId(tagEntity_dao).execute(position);
        return result;
    }
    public void insert_directory (DirectoryEntity directoryEntity){
        new Directory_AsyncTask.InsertDirectoryAsyncTask(directoryDao).execute();
    }


    public void deleteAllDates(){
        new Location_AsyncTask.DeleteAllLocationAsyncTask(locationEntity_dao).execute();
    }
    public LiveData<List<LocationEntity>> getAllLocations(){
        return allLocations;
    }

}
