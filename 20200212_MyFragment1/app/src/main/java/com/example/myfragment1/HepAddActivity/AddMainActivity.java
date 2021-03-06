package com.example.myfragment1.HepAddActivity;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.ClipData;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.media.Image;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.provider.MediaStore;
import android.util.Base64;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.room.Room;
import androidx.viewpager.widget.ViewPager;

import com.example.myfragment1.DataBase_Room.LocationRoom.LocationDatabase;
import com.example.myfragment1.DataBase_Room.LocationRoom.LocationEntity;
import com.example.myfragment1.DataBase_Room.LocationTagEntity.LocationTagEntity;
import com.example.myfragment1.DataBase_Room.Repository.LocationRepository;
import com.example.myfragment1.LocationList_RecyclerView.LocationViewModel;
import com.example.myfragment1.DataBase_Room.TagEntity.TagEntity;
import com.example.myfragment1.MSMain.GlobalFlag;
import com.example.myfragment1.MSMain.MainActivity;
import com.example.myfragment1.R;
import com.opensooq.supernova.gligar.GligarPicker;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import static android.content.ContentValues.TAG;

public class AddMainActivity extends Activity {
    public static final String SET_STORE_FLAG = "com.example.myfragment1.HepAddActivity.AddMainActivity.SET_STORE_FLAG";

    AddMainActivity addMainActivity;
    EditText Location_Title; // 이름
    TextView Location_Address; // 주소
    EditText Location_DetailAddress; // 상세주소
    EditText Location_Number; // 연락처
    EditText Location_Comment; // 메모

    ArrayList<ImageData> imageDataArrayList; // 이미지 리스트
    ViewPager viewPager; // 이미지 뷰

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_activity_main);

        setTitle("ADD Location");
        init();
        PermissionCheck();
        addMainActivity = this;
    }

    public void PermissionCheck() {
        // 6.0 마쉬멜로우 이상일 경우에는 권한 체크 후 권한 요청
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (checkSelfPermission(Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED && checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {
                Log.d(TAG, "권한 설정 완료");
            } else {
                Log.d(TAG, "권한 설정 요청");
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
            }
        }
    }

    public void init() {
        imageDataArrayList = new ArrayList<>();

        Location_Title = ((ClearableEditText) findViewById(R.id.Text_Name)).editText;
        Location_Title.setHint("이름");

        Location_Address = findViewById(R.id.Text_Addr);

        //등록된 주소가 없거나 오류일 때
        Location_Address.setTextColor(Color.RED);
        Location_Address.setPaintFlags(Location_Address.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
        Location_Address.setText("주소 검색 실패");
        /* ***** */

        Location_DetailAddress = ((ClearableEditText) findViewById(R.id.Text_DetailAddr)).editText;
        Location_DetailAddress.setHint("상세주소");

        Location_Number = ((ClearableEditText) findViewById(R.id.Text_Number)).editText;
        Location_Number.setHint("연락처");

        Location_Comment = ((ClearableEditText) findViewById(R.id.Text_Comment)).editText;
        Location_Comment.setHint("메모");

        viewPager = findViewById(R.id.viewPager);

        AutoCompleteTextView autoCompleteTextView = ((HashEditText) findViewById(R.id.Text_Hash)).editText;

        autoCompleteTextView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                hashtag_Add(((TextView) view).getText().toString());
            }
        });
        ((ScrollView) findViewById(R.id.Scroll_Main)).fullScroll(View.FOCUS_DOWN);
    }

    public void onButtonHashTagAddClicked(View v) {
        hashtag_Add(((HashEditText) findViewById(R.id.Text_Hash)).editText.getText().toString().trim());
    }

    public void hashtag_Add(String Hash) {
        AutoCompleteTextView HashText = ((HashEditText) findViewById(R.id.Text_Hash)).editText;

        if (HashText.getText().toString().trim().equals("")) {
            Toast.makeText(getApplicationContext(), "내용을 입력해주세요.", Toast.LENGTH_SHORT).show();
            hashtext_set(Hash);
        } else if (EPHashTag.getHashTagar().size() == 5) {
            Toast.makeText(getApplicationContext(), "태그는 5개까지 추가할 수 있습니다.", Toast.LENGTH_SHORT).show();
            hashtext_set(Hash);
        } else if (EPHashTag.getHashTagar().contains(Hash)) {
            Toast.makeText(getApplicationContext(), "이미 추가한 태그입니다.", Toast.LENGTH_SHORT).show();
            hashtext_set(Hash);
        } else {
            FlowLayout.LayoutParams params = new FlowLayout.LayoutParams(20, 20);
            EPHashTag hashtag = new EPHashTag(this);

            hashtag.init(Hash, "#3F729B", R.drawable.hashtagborder, params);

            ((FlowLayout) findViewById(R.id.flowlayout)).addView(hashtag);

            HashText.setText("");
            EPHashTag.getHashTagar().add(Hash);

            // 해시태그를 추가할 때 마다 스크롤 자동 맞춤
            View targetView = findViewById(R.id.flowlayout);
            targetView.getParent().requestChildFocus(targetView, targetView);
        }
    }

    public void btnSaveOnClick(View view) {
        storeData();
    }

    public void hashtext_set(String Hash) {
        ((HashEditText) findViewById(R.id.Text_Hash)).editText.setText(Hash);
        ((HashEditText) findViewById(R.id.Text_Hash)).editText.setSelection(((HashEditText) findViewById(R.id.Text_Hash)).editText.length());
    }

    static final int Phone_Result = 0;
    static final int PICK_IMAGE = 1;
    static final int CAPTURE_IMAGE = 2;

    public void onPersonAddClicked(View v) { // 연락처 이동
        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setData(ContactsContract.CommonDataKinds.Phone.CONTENT_URI);
        startActivityForResult(intent, Phone_Result);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // 연락처 불러오기
        if (requestCode == Phone_Result && resultCode == RESULT_OK) {
            Cursor cursor = getContentResolver().query(data.getData(),
                    new String[]{ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME,
                            ContactsContract.CommonDataKinds.Phone.NUMBER}, null, null, null);
            cursor.moveToFirst();
            Location_Title.setText(cursor.getString(0));     //0은 이름을 얻어옵니다.
            Location_Number.setText(cursor.getString(1));   //1은 번호를 받아옵니다.
            cursor.close();
        }
        // 갤러리에서 사진 가져오기
        else if (requestCode == PICK_IMAGE && resultCode == RESULT_OK) {
            String pathsList[]= data.getExtras().getStringArray(GligarPicker.IMAGES_RESULT); // return list of selected images paths.
            String result = "Number of selected Images: " + pathsList.length;

            Log.d("@@@@@@@@@@@@@@", "result = " + result);
            ClipData clipData = data.getClipData();

            if (clipData != null) {
                for (int i = 0; i < clipData.getItemCount(); i++) {
                    try {
                        Uri imageUri = clipData.getItemAt(i).getUri();
                        InputStream is = getContentResolver().openInputStream(imageUri);
                        imageDataArrayList.add(new ImageData(BitmapFactory.decodeStream(is)));
                        is.close();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            } else {
                try {
                    InputStream is = getContentResolver().openInputStream(data.getData());
                    imageDataArrayList.add(new ImageData(BitmapFactory.decodeStream(is)));
                    is.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            viewPager.setAdapter(new ViewPagerAdapter(this, imageDataArrayList));
        }
        // 카메라
        else if (requestCode == CAPTURE_IMAGE && resultCode == RESULT_OK && data.hasExtra("data")) {
            if (data != null && data.getData() != null) {
                imageDataArrayList.add(new ImageData((Bitmap) data.getExtras().get("data")));
                viewPager.setAdapter(new ViewPagerAdapter(getApplicationContext(), imageDataArrayList));
            }
        }
    }

    // 권한 요청
    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        Log.d(TAG, "onRequestPermissionsResult");
        if (grantResults[0] == PackageManager.PERMISSION_GRANTED && grantResults[1] == PackageManager.PERMISSION_GRANTED) {
            Log.d(TAG, "Permission: " + permissions[0] + "was " + grantResults[0]);
        }
    }

    // 사진 추가
    public void onBtn_ImageAddClicked(View v) {
        final CharSequence[] PhotoModels = {"갤러리", "카메라"};
        final AlertDialog.Builder alt_bld = new AlertDialog.Builder(this);
        alt_bld.setTitle("사진 가져오기");
        alt_bld.setSingleChoiceItems(PhotoModels, -1, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if (which == 0) {
                    // 갤러리
                    /*Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                    intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true);

                    //기기 기본 갤러리 접근
                    intent.setType(MediaStore.Images.Media.CONTENT_TYPE);
                    //구글 갤러리 접근
                    // intent.setType("image/*");
                    startActivityForResult(intent, PICK_IMAGE);*/
/*
                    Intent intent = new Intent(Intent.ACTION_PICK);
                    //intent.setType("image/*");
                    intent.setType(MediaStore.Images.Media.CONTENT_TYPE);
                    intent.setData(MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                    intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true);
                    intent.setAction(Intent.ACTION_GET_CONTENT);
                    startActivityForResult(intent,PICK_IMAGE);
*/
//                    Intent intent = new Intent(Intent.ACTION_PICK);
//                    //사진을 여러개 선택할수 있도록 한다
//                    intent.setType(MediaStore.Images.Media.CONTENT_TYPE);
//                    intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true);
//                    intent.setData(MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
//                    //intent.setType("image/*");
//                    startActivityForResult(Intent.createChooser(intent, "Select Picture"),  PICK_IMAGE);

                    new GligarPicker().requestCode(PICK_IMAGE).withActivity(AddMainActivity.this).show();
                } else {
                    // 카메라
                    try {
                        Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                        startActivityForResult(cameraIntent, CAPTURE_IMAGE);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                dialog.cancel();
            }
        });
        final AlertDialog alert = alt_bld.create();
        alert.show();
    }

    public void listshowOnButton(View view) {
        finish();
    }

    public void storeData(){
        String title = Location_Title.getText().toString();
        String address = Location_Address.getText().toString();
        String detailAddr = Location_DetailAddress.getText().toString();
        String number = Location_Number.getText().toString();
        String comment = Location_Comment.getText().toString();
        String latitude = null;
        String longitude = null;
        String timestamp = Long.toString(System.currentTimeMillis());
        List<String> _hashTag = EPHashTag.getHashTagar();

        LocationRepository locationRepository = new LocationRepository(getApplication());
        LocationEntity locationEntity = new LocationEntity(title, address, detailAddr, number, comment, latitude, longitude, timestamp);
        int location_id = locationRepository.insert_Location(locationEntity);
        /*
        LocationDatabase locationDatabase = Room.databaseBuilder(this, LocationDatabase.class, "LocationEntity").allowMainThreadQueries().build();
        locationDatabase.locationEntity_dao().insert(locationEntity);
        Log.d("tag","END Location insert ");


        locationDatabase.close();

         */
        if(!_hashTag.isEmpty()) {
            for (String tag : _hashTag) {
                int tag_id = locationRepository.insert_Tag(new TagEntity(tag));
                locationRepository.insert_LocationTag(new LocationTagEntity(location_id,tag_id));
            }
        }

        Intent mainActivityIntent = new Intent(this, MainActivity.class);
        mainActivityIntent.putExtra(SET_STORE_FLAG, true);
        setResult(MainActivity.ADD_MAIN_ACTIVITY_REPLY_CODE, mainActivityIntent);
        Log.d("test", "값넘어옴");
        finish();
    }
}