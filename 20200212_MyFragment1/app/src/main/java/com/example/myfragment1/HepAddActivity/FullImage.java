package com.example.myfragment1.HepAddActivity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.widget.ImageView;

import com.example.myfragment1.R;

public class FullImage extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d("Activity@@", "onCreate");
        super.onCreate(savedInstanceState);

        Log.d("Activity@@", "onCreate1");
        setContentView(R.layout.full_image);

        ImageView icon = findViewById(R.id.Image_full);

        Log.d("Activity@@", "onCreate2");
        Intent intent = getIntent();
        Bitmap bitmap = StringToBitmap(intent.getExtras().getString("imageUri"));

        Log.d("Activity@@", "onCreate3");
        icon.setImageBitmap(bitmap);
    }

    public static Bitmap StringToBitmap(String encodedString) {
        Bitmap bitmap = null;
        try {
            byte[] encodeByte = Base64.decode(encodedString, Base64.DEFAULT);
             bitmap = BitmapFactory.decodeByteArray(encodeByte, 0, encodeByte.length);
            return bitmap;
        } catch (Exception e) {
            e.getMessage();
            return bitmap;
        }
    }

    public Bitmap resizeBitmapImg(Bitmap source, int maxWidth, int maxHeight){
        //resizeBitmapImg(bitmap, ((LinearLayout) findViewById(R.id.Layout_FullImage)).getWidth(), ((LinearLayout) findViewById(R.id.Layout_FullImage)).getHeight());
        int newWidth = maxWidth;
        int newHeight = maxHeight;

        return Bitmap.createScaledBitmap(source, newWidth, newHeight, true);
    }
}
