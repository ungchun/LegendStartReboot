package com.example.myfragment1.DataBase_Room.TagEntity;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.example.myfragment1.LocationList_RecyclerView.SendingArrayList;

import java.util.Collections;
import java.util.List;

public class TagAsyncTask {
    private TagEntity_Dao tagEntity_dao;
    private LiveData<List<TagEntity>> allTags;
    private TagEntity[] selectDismissTagEntities;


    public TagAsyncTask(Application application) {
//        TagDatabase tagDatabase = TagDatabase.getInstance(application);
//        tagEntity_dao = tagDatabase.tagEntity_dao();
//        allTags = tagEntity_dao.getAllData();
        //selectDismissTagEntities = tagEntity_dao.multipleSelectionByForeignKey(position);
    }
    public static class InsertTagAsyncTask extends AsyncTask<TagEntity, Void, Void> {
        private TagEntity_Dao tagEntity_dao;
        public InsertTagAsyncTask(TagEntity_Dao tagEntity_dao) {
            this.tagEntity_dao = tagEntity_dao;
        }

        @Override
        protected Void doInBackground(TagEntity... tagEntities) {
            tagEntity_dao.insert(tagEntities[0]);
            return null;
        }
    }
    public void update(TagEntity tagEntity){
        new UpdateTagAsyncTask(tagEntity_dao).execute(tagEntity);
    }

    public static class UpdateTagAsyncTask extends AsyncTask<TagEntity, Void, Void>{
        private TagEntity_Dao tagEntity_dao;
        public UpdateTagAsyncTask(TagEntity_Dao tagEntity_dao) {
            this.tagEntity_dao = tagEntity_dao;
        }

        @Override
        protected Void doInBackground(TagEntity... tagEntities) {
            tagEntity_dao.update(tagEntities[0]);
            return null;
        }
    }
    public static class DeleteTagAsyncTask extends AsyncTask<TagEntity, Void, Void>{
        private TagEntity_Dao tagEntity_dao;
        public DeleteTagAsyncTask(TagEntity_Dao tagEntity_dao) {
            this.tagEntity_dao = tagEntity_dao;
        }

        @Override
        protected Void doInBackground(TagEntity... tagEntities) {
            tagEntity_dao.delete(tagEntities);
            return null;
        }
    }
    public static class SearchAboutLocationId extends AsyncTask<Integer, Void, List<TagEntity>>{
        private TagEntity_Dao tagEntity_dao;
        public SearchAboutLocationId(TagEntity_Dao tagEntity_dao) {
            this.tagEntity_dao = tagEntity_dao;
        }

        @Override
        protected List<TagEntity> doInBackground(Integer... integer) {
            return ((List<TagEntity>) new SendingArrayList().SendingArrayList(tagEntity_dao.multipleSelectionByForeignKey(integer[0])));
        }
    }
    public static class DismissUsingLocationId extends AsyncTask<Integer, Void, List<TagEntity>>{
        private TagEntity_Dao tagEntity_dao;

        public DismissUsingLocationId(TagEntity_Dao tagEntity_dao) {
            this.tagEntity_dao = tagEntity_dao;
        }

        @Override
        protected List<TagEntity> doInBackground(Integer... integers) {
            return ((List<TagEntity>) new SendingArrayList().SendingArrayList(tagEntity_dao.dismissUsingForeignKey(integers[0])));
        }
    }

}
