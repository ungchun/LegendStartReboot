package com.example.myfragment1.HepAddActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.AttributeSet;
import android.widget.AutoCompleteTextView;

@SuppressLint("AppCompatCustomView")
public class AutoFirstTextView extends AutoCompleteTextView {
    private Context mContext;
    public AutoFirstTextView(Context context) {
        super(context);
        mContext = context;
    }

    public AutoFirstTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
    }

    public AutoFirstTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mContext = context;
    }

    @Override
    public boolean enoughToFilter() {
        return true;
    }

    @Override
    protected void performFiltering(CharSequence text, int keyCode) {

    }

    @Override
    public void onFilterComplete(int count) {

    }
}
