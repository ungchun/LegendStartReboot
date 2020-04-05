package com.example.myfragment1.DataBase_Room.DirectoryRoom;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.example.myfragment1.DataBase_Room.LocationRoom.LocationDatabase;
import com.example.myfragment1.DataBase_Room.LocationRoom.LocationEntity;
import com.example.myfragment1.DataBase_Room.LocationRoom.LocationEntity_Dao;
import com.example.myfragment1.DataBase_Room.LocationTagEntity.LocationTag_Dao;
import com.example.myfragment1.DataBase_Room.TagEntity.TagEntity_Dao;

// 여기를 수정하든 어쨌든 Room DB 연결시켜야하는데 오류남
//,exportSchema = false
@Database(entities = {DirectoryEntity.class}, version = 1)
public abstract class DirectoryDatabase extends RoomDatabase {
    private static DirectoryDatabase instance;
    public abstract DirectoryDao directoryDao();

    public static synchronized DirectoryDatabase getInstance(Context context){
        if(instance == null){
            instance = Room.databaseBuilder(context.getApplicationContext().getApplicationContext(),
                    DirectoryDatabase.class, "directory_database")
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
            new DirectoryDatabase.PopulateDbAsyncTask(instance).execute();
        }
    };
    private static class PopulateDbAsyncTask extends AsyncTask<Void, Void, Void> {
        private LocationEntity_Dao locationEntity_dao;
        private TagEntity_Dao tagEntity_dao;
        private LocationTag_Dao locationTag_dao;
        private DirectoryDao directoryDao;
        private PopulateDbAsyncTask(DirectoryDatabase directoryDatabase){
//            locationEntity_dao = locationDatabase.locationEntity_dao();
//            tagEntity_dao = locationDatabase.tagEntity_dao();
//            locationTag_dao = locationDatabase.locationTag_dao();
            directoryDao = directoryDatabase.directoryDao();
        }

        @Override
        protected Void doInBackground(Void... voids) {
//            locationEntity_dao.insert(new LocationEntity("Strart","END","DETAIL","111","Hi"
//                    ,"11","22","22"));
            return null;
        }
    }
}
