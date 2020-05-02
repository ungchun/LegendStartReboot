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
        super.onCreate(savedInstanceState);

        setContentView(R.layout.full_image);

        ImageView imageView = findViewById(R.id.Image_full);

        Intent intent = getIntent();

        if(getIntent().hasExtra("byteArray")) {
            Bitmap b = BitmapFactory.decodeByteArray(getIntent().getByteArrayExtra("byteArray"),0, getIntent().getByteArrayExtra("byteArray").length);
            imageView.setImageBitmap(b);
        }

        //Bitmap bitmap = StringToBitmap(intent.getExtras().getString("imageUri"));

        //imageView.setImageBitmap(bitmap);
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
