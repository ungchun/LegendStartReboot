package com.example.myfragment1.DataBase_Room.LocationRoom;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import java.util.List;

public class Location_AsyncTask {
    private LocationEntity_Dao locationEntity_dao;
    private LiveData<List<LocationEntity>> allLocations;

    public Location_AsyncTask(Application application) {

//        LocationDatabase locationDatabase = LocationDatabase.getInstance(application);
//        this.locationEntity_dao = locationDatabase.locationEntity_dao();
//        this.allLocations = locationEntity_dao.getAllData();
    }

    public static class InsertLocationAsyncTask extends AsyncTask<LocationEntity,Void, Void>{
        private LocationEntity_Dao locationEntity_dao;
        public InsertLocationAsyncTask(LocationEntity_Dao locationEntity_dao){
            this.locationEntity_dao = locationEntity_dao;
        }
        @Override
        protected Void doInBackground(LocationEntity... locationEntities) {
            locationEntity_dao.insert(locationEntities[0]);
            return null;
        }
    }
    public static class UpdateLocationAsyncTask extends AsyncTask<LocationEntity,Void, Void> {
        private LocationEntity_Dao locationEntity_dao;
        public UpdateLocationAsyncTask(LocationEntity_Dao locationEntity_dao){
            this.locationEntity_dao = locationEntity_dao;
        }
        @Override
        protected Void doInBackground(LocationEntity... locationEntities) {
            locationEntity_dao.update(locationEntities[0]);
            return null;
        }
    }
    public static class DeleteLocationAsyncTask extends AsyncTask<LocationEntity,Void, Void>{
        private LocationEntity_Dao locationEntity_dao;
        public DeleteLocationAsyncTask(LocationEntity_Dao locationEntity_dao){
            this.locationEntity_dao = locationEntity_dao;
        }
        @Override
        protected Void doInBackground(LocationEntity... locationEntities) {
            locationEntity_dao.delete(locationEntities[0]);
            return null;
        }
    }
    public static class DeleteAllLocationAsyncTask extends AsyncTask<Void,Void, Void>{
        private LocationEntity_Dao locationEntity_dao;
        public DeleteAllLocationAsyncTask(LocationEntity_Dao locationEntity_dao){
            this.locationEntity_dao = locationEntity_dao;
        }
        @Override
        protected Void doInBackground(Void... voids) {
            locationEntity_dao.deleteAllData();
            return null;
        }
    }

}
