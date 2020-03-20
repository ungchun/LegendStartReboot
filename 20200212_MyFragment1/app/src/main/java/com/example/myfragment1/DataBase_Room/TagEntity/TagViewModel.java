package com.example.myfragment1.DataBase_Room.TagEntity;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class TagViewModel extends AndroidViewModel {

    private TagAsyncTask tagAsyncTask;
    private LiveData<List<TagEntity>> allData;
    private int location_id;
    public TagViewModel(@NonNull Application application) {
        super(application);
        tagAsyncTask = new TagAsyncTask(application);

    }
    /*
    public void insert(TagEntity tagEntity){
        tagRepository.insert(tagEntity);
    }
    public void update(TagEntity tagEntity){
        tagRepository.update(tagEntity);
    }
    public void delete(TagEntity... tagEntities){
        tagRepository.delete(tagEntities);
    }

    public LiveData<List<TagEntity>> getAllData(){
        return allData;
    }

    public TagEntity[] dismissSelectTag(int position, TagEntity... tagEntities){
        tagRepository.multiSelection(position, tagEntities);
        return tagEntities;
    }

    public void setLocation_id(int location_id) {
        this.location_id = location_id;
    }

     */
}

