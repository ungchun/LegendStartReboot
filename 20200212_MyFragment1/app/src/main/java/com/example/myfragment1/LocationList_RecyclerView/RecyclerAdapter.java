package com.example.myfragment1.LocationList_RecyclerView;


import android.app.Application;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myfragment1.DataBase_Room.LocationRoom.LocationEntity;

import com.example.myfragment1.DataBase_Room.TagEntity.TagEntity;
import com.example.myfragment1.R;

import java.util.ArrayList;
import java.util.List;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ListHolder> {
    private List<LocationEntity> locationEntities = new ArrayList<>();
    private List<TagEntity> tagEntities = new ArrayList<>();

    public RecyclerAdapter() {
//        this.application = application;
    }


    @NonNull
    @Override
    public ListHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.cs_locationlist_recyclerview_cardview, parent, false);
        return new ListHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ListHolder holder, int position) {
        LocationEntity currentLocationEntity = locationEntities.get(position);

        holder.textViewTitle.setText(currentLocationEntity.getLocation_Title());
        holder.textViewAddress.setText(currentLocationEntity.getLocation_Addr());
        holder.textViewNumeric.setText(String.valueOf(currentLocationEntity.getId()));
        for(TagEntity tag : tagEntities){

        }
        //holder.textViewTag.setText();

    }

    @Override
    public int getItemCount() {
        return locationEntities.size();
    }

    public void setLocationEntities(List<LocationEntity> locationEntities){
        this.locationEntities = locationEntities;
        //Updating DataAdapter
        notifyDataSetChanged();
    }

    public void setTagEntities(List<TagEntity> tagEntities) {
        this.tagEntities = tagEntities;
        notifyDataSetChanged();
    }

    public LocationEntity getLocationEntityAt(int position){
        return locationEntities.get(position);
    }

    class ListHolder extends RecyclerView.ViewHolder{
        private TextView textViewTitle;
        private TextView textViewAddress;
        private TextView textViewNumeric;
        private TextView textViewTag;

        public ListHolder(View itemview){
            super(itemview);
            textViewTitle = itemview.findViewById(R.id.text_view_title);
            textViewAddress = itemview.findViewById(R.id.text_view_address);
            textViewNumeric = itemview.findViewById(R.id.text_view_numeric);
            textViewTag = itemview.findViewById(R.id.text_view_tag);
        }
    }
}



