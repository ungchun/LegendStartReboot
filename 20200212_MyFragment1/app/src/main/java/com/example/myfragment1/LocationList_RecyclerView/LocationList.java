package com.example.myfragment1.LocationList_RecyclerView;

import android.graphics.Canvas;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myfragment1.DataBase_Room.LocationRoom.LocationEntity;
import com.example.myfragment1.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

;


public class LocationList extends AppCompatActivity {
    private static final int GET_ADD_LOCATION_REQUEST_CODE = 200; //For intent
    private RecyclerAdapter recyclerAdapter;
    private LocationViewModel locationViewModel;
    private RecyclerView recyclerView; //For recyclerview
    private RecyclerviewSwipeHelper recyclerviewSwipeHelper = null;
    private FloatingActionButton floatingActionButton;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cs_list_layout);

        setRecyclerView();

        locationViewModel = ViewModelProviders.of(this).get(LocationViewModel.class);
        locationViewModel.getAllLocationData().observe(this, new Observer<List<LocationEntity>>() {
            @Override
            public void onChanged(List<LocationEntity> locationEntities) {
                //Update RecyclerView
                recyclerAdapter.setLocationEntities(locationEntities);
            }
        });
        init();
    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.check_toggle:
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.check_list_menu, menu);
        return true;
    }

    private void setRecyclerView(){
        recyclerView = findViewById(R.id.recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);
        recyclerAdapter = new RecyclerAdapter();
        recyclerView.setAdapter(recyclerAdapter);
    }

    private void init() {
//
//
//        //recyclerViewSortingMethod(sortingCondition);
//
//        disSortingButton = findViewById(R.id.sort_distanceButton);
//        updatedSortingButton = findViewById(R.id.sort_recently);
//        nameSortingButton = findViewById(R.id.sort_name);
//
        setupSwipe();
    }

    public void setupSwipe() {
        recyclerView.addItemDecoration( new RecyclerView.ItemDecoration() {
            @Override
            public void onDraw(@NonNull Canvas c, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
                recyclerviewSwipeHelper.onDraw(c);

                recyclerviewSwipeHelper.setRecyclerView(recyclerView); //For Attach ItemTouch.Right Left class
                ItemTouchHelper itemTouchHelper = new ItemTouchHelper(recyclerviewSwipeHelper);
                itemTouchHelper.attachToRecyclerView(recyclerView);
            }
        });
        recyclerviewSwipeHelper = new RecyclerviewSwipeHelper(getApplicationContext(), locationViewModel ,recyclerAdapter ,new SwipeActionInterface() {

            @Override
            public void onRightClicked(RecyclerView.ViewHolder viewHolder, int position, RecyclerviewSecondSwipeDismissHelper recyclerviewSecondSwipeDismissHelper) {
                recyclerviewSecondSwipeDismissHelper.onSwiped(viewHolder, ItemTouchHelper.LEFT| ItemTouchHelper.RIGHT);
            }

            @Override
            public void onLeftClicked(RecyclerView.ViewHolder viewHolder, int position, RecyclerviewSecondSwipeToDoHelper recyclerviewSecondSwipeToDoHelper) {
                recyclerviewSecondSwipeToDoHelper.onSwiped(viewHolder, ItemTouchHelper.RIGHT);
            }
        });
    }
    /*
    public void storeLocation(){

        Intent data = getIntent();
        String title = data.getStringExtra(AddMainActivity.EXTRA_TITLE);
        String address = data.getStringExtra(AddMainActivity.EXTRA_Addr);
        String detailAddr = data.getStringExtra(AddMainActivity.EXTRA_DetailAddr);
        String number = data.getStringExtra(AddMainActivity.EXTRA_Number);
        String comment = data.getStringExtra(AddMainActivity.EXTRA_Comment);
        String latitude = data.getStringExtra(AddMainActivity.EXTRA_Latitude);
        String longitude = data.getStringExtra(AddMainActivity.EXTRA_Longitude);
        String timestamp = data.getStringExtra(AddMainActivity.EXTRA_Timestamp);
        ArrayList<String> hashTag = data.getStringArrayListExtra(AddMainActivity.EXTRA_HASHTAG);
        LocationEntity locationEntity = new LocationEntity(title, address, detailAddr, number, comment, latitude, longitude, timestamp);
        int location_id = locationViewModel.insert(locationEntity);
        if(hashTag.isEmpty()){
            tagDatabase = Room.databaseBuilder(this, TagDatabase.class, "Tag_Database").allowMainThreadQueries().build();
            for(String tag : hashTag){
                Log.d("Tag","TagData Store");
                TagEntity tagEntity = new TagEntity(location_id, tag);
                tagDatabase.tagEntity_dao().insert(tagEntity);
            }
            tagDatabase.close();
        }


        Toast.makeText(this, "Save",Toast.LENGTH_SHORT).show();
    }else
            Toast.makeText(this, "Not Save",Toast.LENGTH_SHORT).show();
    }


     */

    //Override onActivityResult. This is called when the Second Activity finishes. You can make sure that it is actually the Second Activity by checking the result code.
    //(This is useful when you are starting multiple different activities from the same main activity.)
    /*
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.d("tag","onActivityResultEnter");
        if(requestCode == GET_ADD_LOCATION_REQUEST_CODE && resultCode == RESULT_OK){
            Log.d("tag","onActivityResult");
            String title = data.getStringExtra(AddMainActivity.EXTRA_TITLE);
            String address = data.getStringExtra(AddMainActivity.EXTRA_Addr);
            String detailAddr = data.getStringExtra(AddMainActivity.EXTRA_DetailAddr);
            String number = data.getStringExtra(AddMainActivity.EXTRA_Number);
            String comment = data.getStringExtra(AddMainActivity.EXTRA_Comment);
            String latitude = data.getStringExtra(AddMainActivity.EXTRA_Latitude);
            String longitude = data.getStringExtra(AddMainActivity.EXTRA_Longitude);
            String timestamp = data.getStringExtra(AddMainActivity.EXTRA_Timestamp);
            ArrayList<String> hashTag = data.getStringArrayListExtra(AddMainActivity.EXTRA_HASHTAG);
//String location_Title, String location_Addr, String location_DetailAddr, String location_Phone, String location_Memo, String location_Latitude, String location_Longitude, String location_Timestamp
            LocationEntity locationEntity = new LocationEntity(title, address, detailAddr, number, comment, latitude, longitude, timestamp);
            int location_id = locationViewModel.insert(locationEntity);
            if(hashTag.isEmpty()){
                tagDatabase = Room.databaseBuilder(this, TagDatabase.class, "Tag_Database").allowMainThreadQueries().build();
                for(String tag : hashTag){
                    Log.d("Tag","TagData Store");
                    TagEntity tagEntity = new TagEntity(location_id, tag);
                    tagDatabase.tagEntity_dao().insert(tagEntity);
                }
                tagDatabase.close();
            }


            Toast.makeText(this, "Save",Toast.LENGTH_SHORT).show();
        }else
            Toast.makeText(this, "Not Save",Toast.LENGTH_SHORT).show();
    }

     */
}
