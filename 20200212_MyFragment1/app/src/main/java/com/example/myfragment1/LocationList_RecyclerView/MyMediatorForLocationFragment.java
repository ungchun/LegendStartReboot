package com.example.myfragment1.LocationList_RecyclerView;

import android.app.Application;

import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.myfragment1.DataBase_Room.LocationRoom.LocationEntity;
import com.example.myfragment1.DataBase_Room.LocationTagEntity.LocationTagEntity;
import com.example.myfragment1.DataBase_Room.TagEntity.TagEntity;

import java.util.List;

public class MyMediatorForLocationFragment {
    private LocationViewModel locationViewModel;
    private AllDataRappingClass allDataRappingClass;
    private FragmentActivity fragmentActivity;
    private RecyclerAdapter recyclerAdapter;
    public MyMediatorForLocationFragment(FragmentActivity fragmentActivity,RecyclerAdapter recyclerAdapter) {
        this.fragmentActivity = fragmentActivity;
        this.recyclerAdapter = recyclerAdapter;
        locationViewModel = ViewModelProviders.of(fragmentActivity).get(LocationViewModel.class);
    }
    public AllDataRappingClass setListLiveData(LiveData<List<?>> listLiveData){
        listLiveData.observe(fragmentActivity, new Observer<List<?>>() {
            @Override
            public void onChanged(List<?> objects) {
                recyclerAdapter.setLocationEntities(new AllDataRappingClass(objects));
            }
        });
        return allDataRappingClass;
    }

  }
}

class AllDataRappingClass{
    private List<LocationEntity> locationEntities = null;
    private List<TagEntity> tagEntities = null;
    private List<LocationTagEntity> locationTagEntities = null;

    public AllDataRappingClass(List<?> list) {
        if(list !=null && !list.isEmpty()) {

            if (list.get(0) instanceof LocationEntity) {
                setLocationEntities((List<LocationEntity>) list);
            } else if (list.get(0) instanceof LocationTagEntity) {
                setLocationTagEntities((List<LocationTagEntity>) list);
            } else if (list.get(0) instanceof TagEntity) {
                setTagEntities((List<TagEntity>) list);
            }
        }
    }
    public List<LocationEntity> getLocationEntities() {
        return locationEntities;
    }

    public List<TagEntity> getTagEntities() {
        return tagEntities;
    }

    public List<LocationTagEntity> getLocationTagEntities() {
        return locationTagEntities;
    }

    public void setLocationEntities(List<LocationEntity> locationEntities) {
        this.locationEntities = locationEntities;
    }

    public void setTagEntities(List<TagEntity> tagEntities) {
        this.tagEntities = tagEntities;
    }

    public void setLocationTagEntities(List<LocationTagEntity> locationTagEntities) {
        this.locationTagEntities = locationTagEntities;
    }
}