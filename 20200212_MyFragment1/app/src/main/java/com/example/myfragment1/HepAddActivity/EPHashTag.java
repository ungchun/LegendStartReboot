package com.example.myfragment1.HepAddActivity;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.myfragment1.R;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class EPHashTag extends RelativeLayout {
    LayoutInflater inflater = null;
    TextView HashText;
    ImageButton deleteBtn;
    EPHashTag me; // removeView에서 사용할 객체

    static ArrayList<String> HashTagar = new ArrayList<String>();

    public static Context mContext;

    public EPHashTag(Context context){
        super(context);
        mContext = context;
        me = this;
        setLayout();
        deleteHashTag();
    }

    public static List<String> getHashTagar(){
        //If Collections.Empty_List is more less using resource than empty ArrayList
        if(HashTagar.isEmpty())
            return (List<String>) Collections.EMPTY_LIST;

        return HashTagar;
    }

    public void init(String Text, String Color, int border, FlowLayout.LayoutParams params){
        HashText.setText(Text);
        HashText.setTextColor(android.graphics.Color.parseColor(Color));
        HashText.setBackgroundResource(border);
        me.setLayoutParams(params);
    }
    public void setLayout(){
        inflater = (LayoutInflater) getContext().getSystemService(
                Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.hashtag, this, true);

        HashText = findViewById(R.id.HashTag_Text);
        deleteBtn = findViewById(R.id.HashTag_DeleteBtn);
    }

    public void deleteHashTag(){
        deleteBtn.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                ((FlowLayout) ((AddMainActivity) mContext).findViewById(R.id.flowlayout)).removeView(me);
                HashTagar.remove(HashText.getText().toString());
            }
        });
    }
}


