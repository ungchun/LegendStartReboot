package com.example.myfragment1.AllSee;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.provider.ContactsContract;
import android.util.Log;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.PopupMenu;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myfragment1.DataBase_Room.DirectoryRoom.DirectoryEntity;
import com.example.myfragment1.DataBase_Room.Repository.DirectoryRepository;
import com.example.myfragment1.MSMain.Adapter;
import com.example.myfragment1.R;
import com.google.gson.internal.$Gson$Preconditions;

import java.util.ArrayList;
import java.util.List;

import maes.tech.intentanim.CustomIntent;

public class AllSeeAdapter extends RecyclerView.Adapter<AllSeeAdapter.AllSeeHolder> {
    private List<DirectoryEntity> directories = new ArrayList<>();
    private Context context;
    private DirectoryRepository directoryRepository;
    private View view;
    private Adapter.OnItemClickListener mListener = null;

    public void setOnItemClickListener(Adapter.OnItemClickListener listener) {
        this.mListener = listener;
    }

    public AllSeeAdapter(Context context, View view) {
        this.context = context;
        directoryRepository = new DirectoryRepository(context);
        this.view = view;
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
        final DirectoryEntity directoryEntity = directories.get(position);
        holder.Title.setText(directoryEntity.getTitle());
        holder.Total.setText("Total");

        // ... 클릭했을 때
        holder.btnViewOption.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                PopupMenu popupMenu = new PopupMenu(context, holder.btnViewOption);
                popupMenu.inflate(R.menu.allsee_menu_list);
                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        switch (item.getItemId()){
                            case R.id.menu1:
                                directoryRepository.delete_Directory(directoryEntity);
//                                notifyDataSetChanged();
                                Toast.makeText(context,"삭제",Toast.LENGTH_SHORT).show();
                                break;
                            case R.id.menu2:
                                AlertDialog.Builder dialog = new AlertDialog.Builder(context);
                                dialog.setTitle("이름 바꾸기");
                                final EditText updateName = view.findViewById(R.id.allsee_update);
                                dialog.setView(view);
                                updateName.setText(directoryEntity.getTitle().toString());

                                dialog.setPositiveButton("변경", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        // 왜 업데이트가 안됨?? 여기 고쳐야함
                                        if(updateName.getText().toString().length()>0){
                                            directoryRepository.update_Directory(new DirectoryEntity(updateName.getText().toString(),directoryEntity.getSeq()));
                                        }
                                        Toast.makeText(context,"변경완료",Toast.LENGTH_SHORT).show();
                                    }
                                });

                                dialog.setNegativeButton("취소", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        Toast.makeText(context,"취소",Toast.LENGTH_SHORT).show();
                                    }
                                });

                                dialog.show();

                                Toast.makeText(context,"이름 바꾸기",Toast.LENGTH_SHORT).show();
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

            //2
//            // 전체보기 리스트 클릭 여기도 오류
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int pos = getAdapterPosition();
                    Log.d("1", String.valueOf(pos));
                    mListener.onItemClick(v, pos);
                }
            });
        }
    }
}
