package com.example.myfragment1.AllSee;

import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myfragment1.DataBase_Room.DirectoryRoom.DirectoryEntity;
import com.example.myfragment1.DataBase_Room.DirectoryRoom.DirectoryViewModel;
import com.example.myfragment1.DataBase_Room.Repository.DirectoryRepository;
import com.example.myfragment1.R;

import java.util.List;

import maes.tech.intentanim.CustomIntent;

public class AllSeeActivity extends AppCompatActivity {
    private Toolbar toolbar;
    private DirectoryViewModel directoryViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ms_activity_allsee);
        toolbar = findViewById(R.id.realtoolbar);
        toolbar.setTitle("전체보기");
        setSupportActionBar(toolbar);
        // 뒤로가기 버튼 생성
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        RecyclerView recyclerView = findViewById(R.id.allsee_recycler_View);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);

        // 이름 변경할 때 쓰이는 layout -> adapter 에서는 getLayoutInflater 가 안돼서 여서해서 보내줌
        View view = getLayoutInflater().inflate(R.layout.allsee_update_dashboard, null);

        final AllSeeAdapter adapter = new AllSeeAdapter(this,view);
        recyclerView.setAdapter(adapter);

        directoryViewModel = ViewModelProviders.of(this).get(DirectoryViewModel.class);
        directoryViewModel.getAllDirectory().observe(this, new Observer<List<DirectoryEntity>>() {
            @Override
            public void onChanged(List<DirectoryEntity> directoryEntities) {
                adapter.setDirectories(directoryEntities);
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home: { //toolbar의 back키 눌렀을 때 동작
                finish();
//                drawerLayout.isDrawerOpen(GravityCompat.START);
                CustomIntent.customType(this, "right-to-left");
                return true;
            }
        }
        return super.onOptionsItemSelected(item);
    }
}
