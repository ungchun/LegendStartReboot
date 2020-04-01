package com.example.myfragment1.MSMain;

import android.app.AlertDialog;
import android.app.Application;
import android.content.Context;
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import com.example.myfragment1.DataBase_Room.DirectoryRoom.DirectoryDao;
import com.example.myfragment1.DataBase_Room.DirectoryRoom.DirectoryDatabase;
import com.example.myfragment1.DataBase_Room.DirectoryRoom.DirectoryEntity;
import com.example.myfragment1.DataBase_Room.DirectoryRoom.Directory_AsyncTask;
import com.example.myfragment1.DataBase_Room.LocationRoom.LocationDatabase;
import com.example.myfragment1.DataBase_Room.LocationRoom.Location_AsyncTask;
import com.example.myfragment1.DataBase_Room.Repository.LocationRepository;
import com.example.myfragment1.LocationList_RecyclerView.LocationViewModel;
import com.example.myfragment1.R;
import com.naver.maps.map.widget.LocationButtonView;

import java.util.ArrayList;
import java.util.List;


public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder> {
    private LayoutInflater layoutInflater;
    private List<String> title;
    //    private List<String> des_data;
    private List<String[]> tag;
    Context mcontext;

    private final int TYPE_HEADER = 0;
    private final int TYPE_ITEM = 1;


    @Override
    public int getItemViewType(int position) {
        if (position == 0)
            return TYPE_HEADER;
        else
            return TYPE_ITEM;
    }


    //    private String btn = "btn";
    public interface OnItemClickListener {
        void onItemClick(View v, int pos);
    }

    private OnItemClickListener mListener = null;

    private Application application;

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.mListener = listener;
    }


    Adapter(Context context, List<String> data, ArrayList<String[]> Tag, Application application) {
        this.layoutInflater = LayoutInflater.from(context);
        this.title = data;
        this.tag = Tag;
        this.application = application;
        mcontext = context;
    }

    class HeaderViewHolder extends ViewHolder {

        HeaderViewHolder(View headerView) {
            super(headerView);
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        RecyclerView.ViewHolder holder;
        View view;
        if (viewType == TYPE_HEADER) {
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.ms_recy_add, parent, false);
            holder = new HeaderViewHolder(view);
        }
        else {
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.ms_recy_test, parent, false);
            holder = new ViewHolder(view);
        }
        return (ViewHolder) holder;
    }

    @Override
    public int getItemCount() {
        return title.size()+1;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        if(holder instanceof HeaderViewHolder){
            HeaderViewHolder headerViewHolder = (HeaderViewHolder) holder;
        }
        else{
            String Title = title.get(position-1);
            holder.textTitle.setText(Title);
        }

    }

    // 여기 지워
    private static class InsertAsyncTask extends AsyncTask<DirectoryEntity, Void, Void> {
        private DirectoryDao mDierctoryDao;

        public InsertAsyncTask(DirectoryDao directoryDao) {
            this.mDierctoryDao = directoryDao;
        }

        @Override
        protected Void doInBackground(DirectoryEntity... directoryEntities) {
            return null;
        }
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView textTitle, textDescription_1, textDescription_2, textDescription_3, textDescription_4, textDescription_5, Total;

        DirectoryDatabase db = Room.databaseBuilder(mcontext, DirectoryDatabase.class, "directory_db").build();

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textTitle = (TextView)itemView.findViewById(R.id.textView);
            Total = (TextView)itemView.findViewById(R.id.textView7);

//            // LiveData
//            db.directoryDao().getAll().observe((LifecycleOwner) mcontext, new Observer<List<DirectoryEntity>>() {
//                @Override
//                public void onChanged(List<DirectoryEntity> directoryEntities) {
//                    Log.d("1","확인");
//                }
//            });


            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int pos = getAdapterPosition();
                    if (pos != RecyclerView.NO_POSITION) {
                        if(pos == 0){
                            // 삭제는 어케하누?
//                            new DeleteAsyncTask(db.directoryDao()).execute();
                            AlertDialog.Builder ad = new AlertDialog.Builder(mcontext);
                            ad.setIcon(R.mipmap.ic_launcher);
                            ad.setTitle("제목");
                            ad.setMessage("directory의 이름을 적어주세요");

                            final EditText et = new EditText(mcontext);
                            et.setSingleLine(true);
                            ad.setView(et);

                            // 긍정적인 버튼
                            ad.setPositiveButton("확인", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
//                                        String result = et.getText().toString();

                                    // 이 씨 발 ( 찬섭이형이 적은 코드 두줄 )
                                    new InsertAsyncTask(db.directoryDao()).execute(new DirectoryEntity(et.getText().toString()));
//                                    LocationRepository locationRepository = new LocationRepository(application);
//                                    locationRepository.insert_directory(new DirectoryEntity(et.getText().toString()));

//                                    new Directory_AsyncTask.InsertDirectoryAsyncTask(db.directoryDao()).execute(new DirectoryEntity(et.getText().toString()));
//                                    recy_refresh_frag = true;
//                                    MainActivity.recyclerView.setAdapter(Adapter.this);


//                                        Toast.makeText(mcontext,db.directoryDao().getAll().toString(),Toast.LENGTH_SHORT).show();
                                    dialog.dismiss(); // 모든 작업이 끝났으니 dialog를 닫어라
                                }
                            });

                            // 부정적인 버튼
                            ad.setNegativeButton("취소", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss(); // 모든 작업이 끝났으니 dialog를 닫어라
                                }
                            });
                            ad.show();

                        }
                        else if (mListener != null) {
                            mListener.onItemClick(v, pos-1);
                        }
                    }
                }
            });
        }
    }
}
