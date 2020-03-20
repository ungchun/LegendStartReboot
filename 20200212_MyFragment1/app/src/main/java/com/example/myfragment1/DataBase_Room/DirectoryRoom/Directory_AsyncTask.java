package com.example.myfragment1.DataBase_Room.DirectoryRoom;

import android.os.AsyncTask;

public class Directory_AsyncTask {

    public static class InsertDirectoryAsyncTask extends AsyncTask<DirectoryEntity,Void,Void>{
        private DirectoryDao directoryDao;

        public InsertDirectoryAsyncTask(DirectoryDao directoryDao) {
                this.directoryDao = directoryDao;
            }

            @Override
            protected Void doInBackground(DirectoryEntity... directoryEntities) {
                directoryDao.insert(directoryEntities[0]);
                return null;
        }
    }
}
