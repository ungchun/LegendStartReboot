package com.example.myfragment1.HepAddActivity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Point;
import android.os.Environment;
import android.util.Base64;
import android.util.Log;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.viewpager.widget.PagerAdapter;

import com.example.myfragment1.R;

import java.io.ByteArrayOutputStream;
import java.util.List;

public class ViewPagerAdapter extends PagerAdapter {
    private List<Bitmap> bitmapList;
    private Context mcontext;

    ViewPagerAdapter(Context context, List<Bitmap> bitmapList) {
        this.mcontext = context;
        this.bitmapList = bitmapList;
    }

    @Override
    public int getCount() {
        return bitmapList.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, final int position) {
        LayoutInflater inflater = LayoutInflater.from(mcontext);
        final int positions = position;
        ViewGroup layout = (ViewGroup) inflater.inflate(R.layout.cs_item_view_pager, container, false);
        ImageView imageView = layout.findViewById(R.id.imageView);
        imageView.setImageBitmap(bitmapList.get(position));

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(mcontext, FullImage.class);

                //Display display = ((WindowManager)mcontext.getApplicationContext().getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay();
                //Point size = new Point();
                //display.getSize(size);

                //intent.putExtra("imageUri", BitmapToString(bitmapList.get(position)));

                //mcontext.startActivity(intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK));

                Bitmap b = bitmapList.get(position); // your bitmap
                ByteArrayOutputStream bs = new ByteArrayOutputStream();
                b.compress(Bitmap.CompressFormat.PNG, 50, bs);
                intent.putExtra("byteArray", bs.toByteArray());
                mcontext.startActivity(intent);
            }
        });
        container.addView(layout);
        return layout;
    }



    public static String BitmapToString(Bitmap bitmap) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, baos);
        byte[] bytes = baos.toByteArray();
        String temp = Base64.encodeToString(bytes, Base64.DEFAULT);
        return temp;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }
}

