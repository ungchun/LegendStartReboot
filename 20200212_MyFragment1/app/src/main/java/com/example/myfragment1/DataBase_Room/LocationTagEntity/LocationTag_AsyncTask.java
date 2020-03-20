package com.example.myfragment1.DataBase_Room.LocationTagEntity;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;
import androidx.room.Update;

import com.example.myfragment1.DataBase_Room.LocationRoom.LocationDatabase;
import com.example.myfragment1.LocationList_RecyclerView.SendingArrayList;

import java.util.List;

public class LocationTag_AsyncTask {
    private LocationTag_Dao locationTag_dao;
    private LiveData<List<LocationTagEntity>> allLocationTagData;

    public LocationTag_AsyncTask(Application application) {
//        LocationTag_DataBase locationTag_dataBase = LocationTag_DataBase.getInstance(application);
//        this.locationTag_dao = locationTag_dataBase.locationTag_dao();
//        allLocationTagData = locationTag_dao.getAllLocationTagData();
    }
    public static class InsertLocationTagAsyncTask extends AsyncTask<LocationTagEntity, Void, Void>{
        private LocationTag_Dao locationTag_dao;

        public InsertLocationTagAsyncTask(LocationTag_Dao locationTag_dao) {
            this.locationTag_dao = locationTag_dao;
        }

        @Override
        protected Void doInBackground(LocationTagEntity... locationTagEntities) {
            locationTag_dao.insert(locationTagEntities[0]);
            return null;
        }
    }
    public static class DeleteLocationTagAsyncTask extends AsyncTask<LocationTagEntity, Void, Void>{
        private LocationTag_Dao locationTag_dao;

        public DeleteLocationTagAsyncTask(LocationTag_Dao locationTag_dao) {
            this.locationTag_dao = locationTag_dao;
        }

        @Override
        protected Void doInBackground(LocationTagEntity... locationTagEntities) {
            locationTag_dao.delete(locationTagEntities);
            return null;
        }
    }
    public static class UpdateLocationTagAsyncTask extends AsyncTask<LocationTagEntity, Void, Void>{
        private LocationTag_Dao locationTag_dao;

        public UpdateLocationTagAsyncTask(LocationTag_Dao locationTag_dao) {
            this.locationTag_dao = locationTag_dao;
        }

        @Override
        protected Void doInBackground(LocationTagEntity... locationTagEntities) {
            locationTag_dao.update(locationTagEntities[0]);
            return null;
        }
    }
    public static class SearchForTagIDByLocationID extends AsyncTask<Integer, Void, List<LocationTagEntity>>{
        private LocationTag_Dao locationTag_dao;
        public SearchForTagIDByLocationID(LocationTag_Dao locationTag_dao) {
            this.locationTag_dao = locationTag_dao;
        }

        @Override
        protected List<LocationTagEntity> doInBackground(Integer... integers) {
            return (List<LocationTagEntity>) new SendingArrayList().SendingArrayList(locationTag_dao.getDataByLocationId(integers[0]));
        }

    }

}
