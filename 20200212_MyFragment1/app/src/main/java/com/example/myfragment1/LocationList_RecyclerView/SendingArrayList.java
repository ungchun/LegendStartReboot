package com.example.myfragment1.LocationList_RecyclerView;

import com.example.myfragment1.DataBase_Room.TagEntity.TagEntity;

import java.util.Collections;
import java.util.List;

//When send Arraylist of can empty to another class, using this class

public class SendingArrayList {

    public <T extends Iterable> List<?> SendingArrayList(List<?> item) {
        if (item == null) {
            return Collections.emptyList();
        } else {
            return item;
        }
    }
}
