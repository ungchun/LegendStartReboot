package com.example.myfragment1.MSMain;

import android.content.Context;
import android.graphics.Canvas;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myfragment1.DataBase_Room.LocationRoom.LocationEntity;
import com.example.myfragment1.DataBase_Room.Repository.LocationRepository;
import com.example.myfragment1.LocationList_RecyclerView.LocationViewModel;
import com.example.myfragment1.DataBase_Room.TagEntity.TagEntity;
import com.example.myfragment1.LocationList_RecyclerView.RecyclerAdapter;
import com.example.myfragment1.LocationList_RecyclerView.RecyclerviewSecondSwipeDismissHelper;
import com.example.myfragment1.LocationList_RecyclerView.RecyclerviewSecondSwipeToDoHelper;
import com.example.myfragment1.LocationList_RecyclerView.RecyclerviewSwipeHelper;
import com.example.myfragment1.LocationList_RecyclerView.SwipeActionInterface;
import com.example.myfragment1.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class LocationFragment extends Fragment {
    MainActivity activity;
    private static final int GET_ADD_LOCATION_REQUEST_CODE = 200; //For intent
    private RecyclerAdapter recyclerAdapter;
    private LocationViewModel locationViewModel;
    private RecyclerView recyclerView; //For recyclerview
    private RecyclerviewSwipeHelper recyclerviewSwipeHelper = null;
    private FloatingActionButton floatingActionButton;
    private ViewGroup rootView;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        //이 메소드가 호출될떄는 프래그먼트가 엑티비티위에 올라와있는거니깐 getActivity메소드로 엑티비티참조가능
        activity = (MainActivity) getActivity();
    }
    @Override
    public void onDetach() {
        super.onDetach();
        //이제 더이상 엑티비티 참초가안됨
        activity = null;
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //프래그먼트 메뉴를 인플레이트해주고 컨테이너에 붙여달라는 뜻임
        //ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.ms_fragment_menu, container, false);
        rootView = (ViewGroup) inflater.inflate(R.layout.cs_list_layout, container, false);
        super.onCreate(savedInstanceState);

        setRecyclerView();
        setLiveData();

        //init();
        setupSwipe();
        return rootView;
    }

    private void setRecyclerView(){
        recyclerView = rootView.findViewById(R.id.recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setHasFixedSize(true);
        recyclerAdapter = new RecyclerAdapter();
        recyclerView.setAdapter(recyclerAdapter);
        sendTagData();
    }
    private LiveData<List<TagEntity>> sendTagData(){
        LocationRepository locationRepository = new LocationRepository(getActivity().getApplication());
        return locationRepository.getAllTags();

    }

    private void setLiveData(){
        locationViewModel = ViewModelProviders.of(this).get(LocationViewModel.class);


        locationViewModel.getAllLocationData().observe(this, new Observer<List<LocationEntity>>() {
            @Override
            public void onChanged(List<LocationEntity> locationEntities) {
                //Update RecyclerView
                recyclerAdapter.setLocationEntities(locationEntities);
            }
        });
        locationViewModel.getAllTagData().observe(this, new Observer<List<TagEntity>>() {
            @Override
            public void onChanged(List<TagEntity> tagEntities) {
                recyclerAdapter.setTagEntities(tagEntities);
            }
        });
    }


    public void setupSwipe() {
        recyclerView.addItemDecoration(new RecyclerView.ItemDecoration() {
            @Override
            public void onDraw(@NonNull Canvas c, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
                recyclerviewSwipeHelper.onDraw(c);

                recyclerviewSwipeHelper.setRecyclerView(recyclerView); //For Attach ItemTouch.Right Left class
                ItemTouchHelper itemTouchHelper = new ItemTouchHelper(recyclerviewSwipeHelper);
                itemTouchHelper.attachToRecyclerView(recyclerView);
            }
        });
        recyclerviewSwipeHelper = new RecyclerviewSwipeHelper(getActivity(), locationViewModel ,recyclerAdapter ,new SwipeActionInterface() {

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
}