package com.example.myfragment1.DataBase_Room.LocationRoom;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.example.myfragment1.DataBase_Room.LocationTagEntity.LocationTagEntity;
import com.example.myfragment1.DataBase_Room.LocationTagEntity.LocationTag_Dao;
import com.example.myfragment1.DataBase_Room.TagEntity.TagEntity;
import com.example.myfragment1.DataBase_Room.TagEntity.TagEntity_Dao;

@Database(entities = {LocationEntity.class, LocationTagEntity.class, TagEntity.class}, version = 1
//          get rid of warning
//        ,exportSchema = false
)
public abstract class LocationDatabase extends RoomDatabase {
    private static LocationDatabase instance;
    public abstract LocationEntity_Dao locationEntity_dao();
    public abstract TagEntity_Dao tagEntity_dao();
    public abstract LocationTag_Dao locationTag_dao();

    public static synchronized LocationDatabase getInstance(Context context){
        if(instance == null){
            instance = Room.databaseBuilder(context.getApplicationContext().getApplicationContext(),
                    LocationDatabase.class, "Location_database")
                    .fallbackToDestructiveMigration()
                    .addCallback(locationcallback)
                    .build();
        }
        return instance;
    }
    private static Callback locationcallback = new Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            new PopulateDbAsyncTask(instance).execute();
        }
    };


    private static class PopulateDbAsyncTask extends AsyncTask<Void, Void, Void> {
        private LocationEntity_Dao locationEntity_dao;
        private TagEntity_Dao tagEntity_dao;
        private LocationTag_Dao locationTag_dao;
        private PopulateDbAsyncTask(LocationDatabase locationDatabase){
            locationEntity_dao = locationDatabase.locationEntity_dao();
            tagEntity_dao = locationDatabase.tagEntity_dao();
            locationTag_dao = locationDatabase.locationTag_dao();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            locationEntity_dao.insert(new LocationEntity("Strart","END","DETAIL","111","Hi"
            ,"11","22","22"));
            return null;
        }
    }

}
