package com.example.myfragment1.MSMain;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class SelectLocation extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    public void SetLinearLayout(Context context, RelativeLayout li) {
        if (li.getVisibility() == View.GONE) {  //만약 셀렉트 로케이션이 보이지 않으면
            Toast.makeText(context, "검색 바 / 서브 툴바 출력", Toast.LENGTH_SHORT).show();
            li.setVisibility(View.VISIBLE);
        } else if (li.getVisibility() == View.VISIBLE) {
            Toast.makeText(context, "검색 바 / 서브 툴바 미출력", Toast.LENGTH_SHORT).show();
            li.setVisibility(View.GONE);

            } //서브 툴바 숨기기
//            setSupportActionBar(MainActivity.toolbarMain); //메인 툴바에 권한줌
//            getActionBar().show(); //메인 툴바 출력
//            toolbarSub.setTitle("메인툴바 출력");
//            li.setVisibility(View.GONE);

    }
    public void clickBtn(View v){
        switch (v.getId()){

        }

    }

}
