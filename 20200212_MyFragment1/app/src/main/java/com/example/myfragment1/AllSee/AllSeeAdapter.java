package com.example.myfragment1.AllSee;

import android.content.Context;
import android.provider.ContactsContract;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupMenu;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myfragment1.DataBase_Room.DirectoryRoom.DirectoryEntity;
import com.example.myfragment1.R;
import com.google.gson.internal.$Gson$Preconditions;

import java.util.ArrayList;
import java.util.List;

public class AllSeeAdapter extends RecyclerView.Adapter<AllSeeAdapter.AllSeeHolder> {
    private List<DirectoryEntity> directories = new ArrayList<>();
    private Context context;

    public AllSeeAdapter(Context context) {
        this.context = context;
    }

    public void setDirectories(List<DirectoryEntity> directories){
        this.directories = directories;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public AllSeeHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.ms_allsee_item,parent,false);
        return new AllSeeHolder(itemView);
    }



    @Override
    public void onBindViewHolder(@NonNull final AllSeeHolder holder, int position) {
        DirectoryEntity directoryEntity = directories.get(position);
        holder.Title.setText(directoryEntity.getTitle());
        holder.Total.setText("Total");

        // ... 클릭했을 때
        holder.btnViewOption.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PopupMenu popupMenu = new PopupMenu(context, holder.btnViewOption);
                popupMenu.inflate(R.menu.allsee_menu_list);
                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        switch (item.getItemId()){
                            case R.id.menu1:
                                Toast.makeText(context,"삭제",Toast.LENGTH_SHORT).show();
                                break;
                        }
                        return false;
                    }
                });
                popupMenu.show();
            }
        });

    }

    @Override
    public int getItemCount() {
        return directories.size();
    }

    class AllSeeHolder extends RecyclerView.ViewHolder{

        private TextView Title;
        private TextView Total;
        private TextView btnViewOption;


        public AllSeeHolder(@NonNull final View itemView) {
            super(itemView);
            Title = itemView.findViewById(R.id.allsee_text_view_title);
            Total = itemView.findViewById(R.id.allsee_text_view_total);
            // menu ... 버튼
            btnViewOption = itemView.findViewById(R.id.allsee_ViewOptions);
        }
    }
}
