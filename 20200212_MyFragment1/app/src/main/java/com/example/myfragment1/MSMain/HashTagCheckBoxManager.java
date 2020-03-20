package com.example.myfragment1.MSMain;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.myfragment1.R;

import java.util.ArrayList;
import java.util.List;

import static com.example.myfragment1.MSMain.MainActivity.params;

public class HashTagCheckBoxManager extends AppCompatActivity {
    HashTag[] hs = MainActivity.msHashTag; //메인액티비티 해시태그 배열
    public static List<String> hashTagText = new ArrayList(); //확인누르면 추가된다.

    public static Context mContext;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = this;
    }

    public void HashTagClickEvent(Context context, View v){
        for (int j = 1; j < hs.length; j++) {
            if (v.getId() == j) {
                hs[j].init(hs[j].getHashText(), "#3F729B", R.drawable.hashtagclick, params);
                hs[j].setId(-j);
                Toast.makeText(context,"id : " + hs[j].getId() + "/ text : " + hs[j].getHashText(),Toast.LENGTH_SHORT).show();
                hashTagText.add(hs[j].getHashText());
                break;
            } else if (v.getId() == -j) {
                hs[j].init(hs[j].getHashText(), "#3F729B", R.drawable.hashtagunclick, params);
                hs[j].setId(j);
                hashTagText.remove(hs[j].getHashText());
                break;
            }
        }//for 문 종료
    }

    public void CheckBoxAllClick(Context context) {
        Toast.makeText(context,"3gfgff",Toast.LENGTH_SHORT).show();
        hashTagText.clear();
        for (int j = 1; j < hs.length; j++) {
            hs[j].init(hs[j].getHashText(), "#3F729B", R.drawable.hashtagclick, params);
            hs[j].setId(-j);

            hashTagText.add(hs[j].getHashText());
        } //for 종료
    }

    String imsi = "";

    public void AddClickHashTag(Context context) {
        //어레이 리스트를 스트링으로 변경 후 쿼리문 날리기
        String[] stockArr = new String[hashTagText.size()]; //어레이 리스트 스트링으로 변환
        stockArr = hashTagText.toArray(stockArr);

        for(String s : stockArr)
            imsi += s + " ";
        Toast.makeText(context,"Hashtag : " + imsi ,Toast.LENGTH_SHORT).show();
        imsi = "";
    }

    public void CheckBoxAllClick2(Context context) {
        //int a = MainActivity.hashTag.length;
        Toast.makeText(context,"3gfgff",Toast.LENGTH_SHORT).show();
    }
    public void CheckBoxAllUnClick(Context context) {
        HashTag[] hs = MainActivity.msHashTag;
        for (int j = 1; j < hs.length; j++) {
            hs[j].init(hs[j].getHashText(), "#3F729B", R.drawable.hashtagunclick, params);
            hs[j].setId(j);
            hashTagText.clear();
        } //for 종료
    }

}
