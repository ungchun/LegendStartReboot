package com.example.myfragment1.MSMain;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;

import android.os.Bundle;
import android.view.MenuItem;

import com.example.myfragment1.R;

import maes.tech.intentanim.CustomIntent;


public class homeActivity extends AppCompatActivity {
    private Toolbar toolbar;
    DrawerLayout drawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ms_activity_home);
        toolbar = findViewById(R.id.realtoolbar);
        toolbar.setTitle("홈입니다");
        setSupportActionBar(toolbar);
        // 뒤로가기 버튼 생성
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        drawerLayout = findViewById(R.id.drawer_layout);
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
