package com.example.myfragment1.HepAddActivity;

import android.graphics.Bitmap;
import android.os.Parcel;
import android.os.Parcelable;

public class ImageData implements Parcelable {
    Bitmap bitmap;

    public ImageData(Bitmap bitmap){
        this.bitmap = bitmap;
    }

    protected ImageData(Parcel in) {
        bitmap = in.readParcelable(Bitmap.class.getClassLoader());
    }

    public static final Creator<ImageData> CREATOR = new Creator<ImageData>() {
        @Override
        public ImageData createFromParcel(Parcel in) {
            return new ImageData(in);
        }

        @Override
        public ImageData[] newArray(int size) {
            return new ImageData[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeParcelable(bitmap, flags);
    }
}
