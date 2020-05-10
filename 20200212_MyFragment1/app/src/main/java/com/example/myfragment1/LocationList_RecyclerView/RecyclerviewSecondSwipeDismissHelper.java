package com.example.myfragment1.LocationList_RecyclerView;


import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;
import androidx.room.Room;

import com.example.myfragment1.DataBase_Room.LocationRoom.LocationEntity;
import com.example.myfragment1.DataBase_Room.LocationTagEntity.LocationTagEntity;
import com.example.myfragment1.DataBase_Room.Repository.LocationRepository;
import com.example.myfragment1.DataBase_Room.TagEntity.TagEntity;
import com.google.android.material.snackbar.Snackbar;

import java.util.List;

public class RecyclerviewSecondSwipeDismissHelper extends ItemTouchHelper.SimpleCallback {
    private LocationViewModel locationViewModel;
    private RecyclerAdapter recyclerAdapter;

    private LocationEntity locationEntity;
    private LocationTagEntity locationTagEntity;
    private TagEntity[] tagEntities;

    public RecyclerviewSecondSwipeDismissHelper(int dragDirs, int swipeDirs, RecyclerAdapter recyclerAdapter, LocationViewModel locationViewModel) {
        super(dragDirs, ItemTouchHelper.LEFT);
        this.recyclerAdapter = recyclerAdapter;
        this.locationViewModel = locationViewModel;
    }

    @Override
    public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
        return false;
    }


    @Override
    public void onSwiped(@NonNull final RecyclerView.ViewHolder viewHolder, int direction) {
//        //Undo
//
//        locationEntity = locationViewModel.delete_LocationEntity(recyclerAdapter.getLocationEntityAt(viewHolder.getAdapterPosition()));
//        long locationEntityID = locationEntity.getId();
//
//        locationTagEntity = locationViewModel.SearchTagByLocationID_LocationTagEntity(locationEntityID);
//
//        if(locationEntity != null){
//            tagEntities = locationViewModel.searchTagByLocationID_TagEntity(locationEntityID).toArray(new TagEntity[0]);
//            locationViewModel.delete_TagEntity(tagEntities);
//            Log.d("tag","Tag Dismiss Success");
//            locationViewModel.delete_LocationTagEntity(locationTagEntity);
//        }
//
//        Log.d("tag","Successful dismiss Tag");

        Snackbar.make(viewHolder.itemView, "", Snackbar.LENGTH_LONG).setAction("Undo", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                locationViewModel.insert_LocationEntity(locationEntity);
                if(locationEntity != null){
                    locationViewModel.insert_LocationTagEntity(locationTagEntity);
                    locationViewModel.insert_TagEntity(tagEntities);
                }
                Log.d("tag","Successful Undo");
            }
        }).show();
        Toast.makeText(viewHolder.itemView.getContext(), "Success Undo", Toast.LENGTH_SHORT).show();
//        LocationRepository locationRepository;
//        locationEntity = locationViewModel.delete(recyclerAdapter.getLocationEntityAt(viewHolder.getAdapterPosition()));
//
//        final TagEntity[] tagList = tagDatabase.tagEntity_dao().multipleSelectionByForeignKey(locationEntity.getId()).toArray(new TagEntity[0]);
//        tagDatabase.tagEntity_dao().delete(tagList);
//        Log.d("tag","Successful dismiss Tag");
//
//        Snackbar.make(viewHolder.itemView, "", Snackbar.LENGTH_LONG).setAction("Undo", new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                locationViewModel.insert(locationEntity);
//                tagDatabase.tagEntity_dao().insert(tagList);
//                Log.d("tag","Successful Undo");
//            }
//        }).show();
//        Toast.makeText(viewHolder.itemView.getContext(), "Success Undo", Toast.LENGTH_SHORT).show();


    }
}