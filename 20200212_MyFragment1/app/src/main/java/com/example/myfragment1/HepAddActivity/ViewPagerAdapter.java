package com.example.myfragment1.HepAddActivity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.viewpager.widget.PagerAdapter;

import com.example.myfragment1.R;

import java.io.ByteArrayOutputStream;
import java.util.List;

public class ViewPagerAdapter extends PagerAdapter {
    private List<Bitmap> imageList;
    private Context mcontext;

    ViewPagerAdapter(Context context, List<Bitmap> images) {
        this.mcontext = context;
        imageList = images;
    }

    @Override
    public int getCount() {
        return imageList.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        LayoutInflater inflater = LayoutInflater.from(mcontext);
        final int positions = position;
        ViewGroup layout = (ViewGroup) inflater.inflate(R.layout.cs_item_view_pager, container, false);
        ImageView imageView = layout.findViewById(R.id.imageView);
        imageView.setImageBitmap(imageList.get(position));
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mcontext, FullImage.class);
                intent.putExtra("imageUri", BitmapToString(imageList.get(positions)));

                Log.d("Activity@@", "Start@@@@@@@@@@@@");
                mcontext.startActivity(intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK));
                Log.d("Activity@@", "End");
            }
        });
        container.addView(layout);
        return layout;
    }

    public static String BitmapToString(Bitmap bitmap) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 70, baos);
        byte[] bytes = baos.toByteArray();
        String temp = Base64.encodeToString(bytes, Base64.DEFAULT);
        return temp;
    }


    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }
}

