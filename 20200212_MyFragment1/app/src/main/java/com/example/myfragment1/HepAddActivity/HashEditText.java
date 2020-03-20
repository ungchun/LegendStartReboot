package com.example.myfragment1.HepAddActivity;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.RelativeLayout;

import com.example.myfragment1.R;

import java.util.ArrayList;

//import static com.example.ls_listsave.DataBase.LSSQLContract.TagTable.TABLE_NAME;

public class HashEditText extends RelativeLayout {

    LayoutInflater inflater = null;
    AutoCompleteTextView editText;
    Button btnClear;
    ArrayList list = new ArrayList();
    public static Context mContext;

    public HashEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
        setLayout();
        mContext = context;
    }

    private void setLayout() {
        //레이아웃을 설정
        inflater = (LayoutInflater) getContext().getSystemService(
                Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.hash_edit_text, this, true);

        editText = findViewById(R.id.clearable_edit);
        btnClear = (Button) findViewById(R.id.clearable_button_clear);
        btnClear.setVisibility(RelativeLayout.INVISIBLE);

        clearText();
        showHideClearButton();
    }

    //X버튼이 나타났다 사라지게하는 메소드
    private void showHideClearButton() {
        //TextWatcher를 사용해 에디트 텍스트 내용이 변경 될 때 동작할 코드를 입력
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            //에디트 텍스트 안 내용이 변경될 때마다 호출되는 메소드
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length() > 0) {
                    btnClear.setVisibility(RelativeLayout.VISIBLE);

                    SQLiteDatabase db = null;
                    //LSDBHelper lsdbHelper = new LSDBHelper(mContext);
                    //db = lsdbHelper.getReadableDatabase();

                    String query = searchSql(s.toString());
                    Log.d("Search", query);
                    list.clear();
                    try {
                        Cursor cursor = db.rawQuery(query + ";", null);
                        while (cursor.moveToNext()) {
                            String result = cursor.getString(2);
                            list.add(result);
                            Log.d("Search", result);
                        }
                        db.close();

                        AutoCompleteTextView autoCompleteTextView = ((HashEditText) findViewById(R.id.Text_Hash)).editText;
                        autoCompleteTextView.setAdapter(new ArrayAdapter<String>(mContext, android.R.layout.simple_dropdown_item_1line, list));
                    } catch (Exception e) {
                        e.printStackTrace();
                        db.close();
                    }
                } else {
                    btnClear.setVisibility(RelativeLayout.INVISIBLE);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s.toString().contains(" ")) {
                    ((AddMainActivity) mContext).hashtag_Add(s.toString().replaceAll(" ", "").trim());
                }
            }
        });
    }

    public String searchSql(String searchStr) {
    /*
        String sql = "Select * FROM " + TABLE_NAME;
        if (TextUtils.isEmpty(searchStr) == false) {
            sql += " WHERE ";
            sql += ChoSearchQuery.makeQuery(searchStr);
        }

        return sql;

     */
    return "삭제";
    }



    private void clearText() {
        btnClear.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                editText.setText("");
            }
        });
    }
}
