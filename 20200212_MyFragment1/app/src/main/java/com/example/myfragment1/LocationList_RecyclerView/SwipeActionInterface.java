package com.example.myfragment1.LocationList_RecyclerView;

import androidx.recyclerview.widget.RecyclerView;

public interface SwipeActionInterface {
    void onLeftClicked(RecyclerView.ViewHolder viewHolder, int position, RecyclerviewSecondSwipeToDoHelper recyclerviewSecondSwipeToDoHelper);
    void onRightClicked(RecyclerView.ViewHolder viewHolder, int position, RecyclerviewSecondSwipeDismissHelper recyclerviewSecondSwipeDismissHelper);
}
