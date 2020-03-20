package com.example.myfragment1.MSMain;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.ListFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myfragment1.HepAddActivity.AddMainActivity;
import com.example.myfragment1.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;
import java.util.List;

import maes.tech.intentanim.CustomIntent;


public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    // 기본적으로 쓰이는 것들 선언
    public static final int ADD_MAIN_ACTIVITY_REQUEST_CODE = 1000;
    public static final int ADD_MAIN_ACTIVITY_REPLY_CODE = 2000;
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    Toolbar toolbar;
    Spinner spinner;
    RecyclerView recyclerView;
    Adapter adapter;
    TextView recy_allSee;
    TextView back_allSee;


    //애니메이션
    Animation animation;
    Animation animationH;

    //recyclerVIew test용 Title ArrayList선언
    ArrayList<String> recy_title;
    //recyclerVIew test용 Tag ArrayList선언
    ArrayList<String[]> recy_Tag;
    //recyclerView 내려왔는지 실행확인 Frag변수
    boolean recyFrag = false; //리사이클 플래그 초기값 false 안보이게 설정한다.

    //프래그먼트는  xml레이아웃 파일 하나랑 자바소스 파일 하나로 정의할 수 있다.
    //이게 하나의 뷰처럼 쓸 수 있는데 뷰하고 약간 다른특성들이 있다.
    //엑티비티를 본떠 만들었기 떄문에 프래그먼트 매니저가 소스코드에서 담당한다.
    MainFragment fragment1;
    private LocationFragment fragment2;

    private boolean menuFlag = true;
    //프래그먼트 유지
    public FragmentManager fragmentManager;
    public Fragment fa, fragmentLocationListLayout = null;
    View mView;

    //자동완성 텍스트 뷰
    MsClearableEditText ct;
    public AutoCompleteTextView ac;
    InputMethodManager imm; //키보드 설정 위한

    ConstraintLayout recy_con_layout;

    //벡프레스
    BackPressedForFinish backPressedForFinish; //백프레스 클래스

    //서치 누르면 상단 탭 교체
    private boolean searchFlag = false;
    LinearLayout searchlinearlayout;

    //플로팅 아이콘
    FloatingActionButton floatingButton;

    //리스트뷰
    private List<String> list;

    //하단 바
    LinearLayout bottomBar;

    //해시태그
    public static LinearLayout hastagView;

    CheckBox checkBoxAll; //체크박스 명 선언
    public static HashTag[] msHashTag = new HashTag[10]; //태그 배열
    public static FlowLayout.LayoutParams params = new FlowLayout.LayoutParams(20, 20);
    ; //해시태그 레이아웃을 위한 parms
    MsHashTagCheckBoxManager msHashTagCheckBoxManager = new MsHashTagCheckBoxManager();
    HasTagOnClickListener ob = new HasTagOnClickListener();
    boolean hashTagFilterFlag = false;

    // 확인을 눌렀을 때 눌린 태그들의 id값을 가져온다.

    RecyclerView re;

    //장소 선택
    public static RelativeLayout relativelayout_sub;  // SelectLocation 단의 리니어 레이아웃
    LinearLayout linearlayout_select; // SelectLocation 단의 리니어 레이아웃
    SelectLocation sl = new SelectLocation();
    //장소추가 플래그
    boolean selectLocationFlag = false;

    // Activity가 시작될 때 호출되는 함수 -> MenuItem 생성과 초기화 진행
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_list, menu);
        return true;
    }

    // Activity가 처음 실행되는 상태에 제일 먼저 호출되는 메소드 -> 실행시 필요한 각종 초기화 작업
    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recy_con_layout = findViewById(R.id.recy_con_layout);

        relativelayout_sub = findViewById(R.id.relativeLayout_s);
        linearlayout_select = findViewById(R.id.linearLayout_s);
        linearlayout_select.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_UP:
                        toasts();
                        Intent intent = new Intent(MainActivity.this, AddMainActivity.class);
                        startActivityForResult(intent,ADD_MAIN_ACTIVITY_REQUEST_CODE);
                        return true;
                }
                return false;
            }
        });

        //상단바
        searchlinearlayout = findViewById(R.id.linearLayoutSearch);

        //플로팅 아이콘
        floatingButton = findViewById(R.id.floatingActionButton);

        //자동완성
        ct = findViewById(R.id.searchView); //프로젝트 단위
        ac = findViewById(R.id.clearable_edit); //실제 자동완성 텍스트
        ac.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    //performSearch();
                    Toast.makeText(getApplicationContext(), "검색합니다 . . .", Toast.LENGTH_SHORT).show();

                    return true;
                }
                return false;
            }
        });

        imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE); //키보드-시스템서비스
        list = new ArrayList<String>();
        settingList(); //자동완성 리스트 삽입

        //https://sharp57dev.tistory.com/12 자동완성
        final AutoCompleteTextView autoCompleteTextView = (AutoCompleteTextView) findViewById(R.id.clearable_edit);
        autoCompleteTextView.setAdapter(new ArrayAdapter<String>(this,
                android.R.layout.simple_dropdown_item_1line, list));

        //하단바
        bottomBar = findViewById(R.id.linearBottombar);


        //프래그먼트는 뷰와 다르게 context를 매개변수로 넣어줄 필요가 없다.
/*
        fragment1 = new MainFragment();
        fragment2 = new MenuFragment();
        onFragmentChange(0);
*/
        // tollbar 선언
        toolbar = findViewById(R.id.toolbar);
        // spinner 선언
        spinner = findViewById(R.id.spinner);
        // toolbar 초기 Title 선언
        toolbar.setTitle("여기는 됌?");
        // **NoActionBar 해주고 이 메서드 호출하면 toolbar를 Activity의 앱바로 사용가능
        setSupportActionBar(toolbar);

        // Drawer Navigation
        // drawerLayout -> Drawer 기능을 이용하기 위해서 밑에 까는 레이아웃
        drawerLayout = findViewById(R.id.drawer_layout);
        // navigationVIew -> 왼쪽에서 드래그 했을 때 보이는 VIew
        navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        // ActionBarDrawerToggle 을 통해 Toolbar 와 DrawerLayout 을 연결
        // Toolbar 에 DrawerArrowDrawerbleToggle 이 NavigationIcon 로 등록되고 (hamburger icon)
        // NavigationOnClickListner 에 DrawerLayout open, close 관련 기능이 연결됨.
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.drawer_open, R.string.drawer_close);
        drawerLayout.addDrawerListener(toggle);
        // 현재 Drawerlayout 상태와 ActionBarDrawerToggle 의 상태를 sync
        toggle.syncState();

        // 어플키면 먼저 Map을 보여주기위함
        // MainActivity에 MapFragment(MainFragment)를 올림
        fragmentManager = getSupportFragmentManager();
        fa = new MainFragment();
        fragmentManager.beginTransaction().replace(R.id.frameLayout, fa).commit();

        //recyclerView test Tag 배열선언
//        final String[] tag1 = {"소고기", "안녕하세요", "김성훈입니다", "방가방가", "소고소고소고소고"};
//        String[] tag2 = {"1"};
//        String[] tag3 = {"1", "2", "3", "4"};
//        String[] tag4 = {"1", "2", "3"};
//        String[] tag5 = {"1", "2"};
        //recyclerView test Title ArrayList
        recy_title = new ArrayList<>();
        recy_title.add("Frist");
        recy_title.add("Two");
        recy_title.add("Third");
        recy_title.add("Four");
        recy_title.add("FIve");
        //recyclerView test Tag ArrayList
//        recy_Tag = new ArrayList<String[]>();
//        recy_Tag.add(tag1);
//        recy_Tag.add(tag2);
//        recy_Tag.add(tag3);
//        recy_Tag.add(tag4);
//        recy_Tag.add(tag5);

        // recyclerView
        recyclerView = findViewById(R.id.recyclerView);
        // recyclerView set ( HORIZONTAL -> 수평 / if) 수직이면 false -> true)
        // 어떤형태로 배치될 아이템 뷰를 만들것인지 결정하는요소 -> LayoutManager -> Linear -> 수직 또는 수평으로 일렬형태로 배치
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        // recyclerView에 표시될 아이템 뷰를 생성하는 역할 adapter
        adapter = new Adapter(getApplicationContext(), recy_title, recy_Tag);
        recyclerView.setAdapter(adapter);

        //recyclerview pos (위치) 값 가져와서 items 리스트 안에 있는 pos (위치)에 있는 Title 가져옴
        adapter.setOnItemClickListener(new Adapter.OnItemClickListener() {
            @Override
            public void onItemClick(View v, int pos) {

                toolbar.setTitle(recy_title.get(pos));
                Toast.makeText(getApplicationContext(), recy_title.get(pos), Toast.LENGTH_SHORT).show();
            }
        });

        // frameLayout 위에 recyclerView가 나타나야함으로 frameLayout 선언
        mView = findViewById(R.id.frameLayout);

        recy_allSee = findViewById(R.id.recy_allSee);

        recy_allSee.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Log.e("1","ㅅㅄㅂ");
                Intent intent = new Intent(getApplicationContext(), AllSeeActivity.class);
                startActivity(intent);
                CustomIntent.customType(getApplicationContext(), "left-to-right");
                Toast.makeText(getApplicationContext(), "전체보기클릭", Toast.LENGTH_SHORT).show();
            }
        });

        // spinner 터치(클릭) 시 이벤트처리
        spinner.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN && recyFrag == false) {
                    //애니메
                    Toast.makeText(getApplicationContext(), "스피너 오류", Toast.LENGTH_SHORT).show();

                    showRecyclerView(); //리사이클 보여주고 플래그 true 로 변경
                } else if (event.getAction() == MotionEvent.ACTION_DOWN && recyFrag == true) {
                    hideRecyclerView(); //리사이클 가리고 플래그 false 로 변경
                }
                return true;
            }
        });

        //벡프레스 객체 생성
        backPressedForFinish = new BackPressedForFinish(this);

        //해시태그 레이아웃 선언
        hastagView = findViewById(R.id.HasTagView);
        hastagView.setBackgroundResource(R.drawable.hashtag);

        //해시태그
        addHashTag(); //해시태그 추가
        checkAllHashTag(); //체크 해시태그

    } //oncreate 종료

    // fragment 화면전환 + 유지
    public void onButtonClicked(View v) {
        switch (v.getId()) {
            case R.id.btnMain:
                //getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout, fragment1).commit();/*프래그먼트 매니저가 프래그먼트를 담당한다!*/
                if (fa == null) {
                    fa = new ListFragment();
                    fragmentManager.beginTransaction().add(R.id.frameLayout, fa).commit();
                }
                if (fa != null) {
                    //clearSearchBar(ac); //서치바 초기화
                    fragmentManager.beginTransaction().show(fa).commit();
                    Toast.makeText(this, "맵 생성완료", Toast.LENGTH_SHORT).show();
                }
                if (fragmentLocationListLayout != null)
                    fragmentManager.beginTransaction().hide(fragmentLocationListLayout).commit();
                break;
            case R.id.btnLocationList:
                setFragmentLocationListLayout();

                if (fa != null) {
                    fragmentManager.beginTransaction().hide(fa).commit();
                }
                break;
            case R.id.btnFilterSelect:
                msHashTagCheckBoxManager.AddClickHashTag(this);
                break;
            case R.id.btnFilterCancel:
                hideHashTagFilter();
                setFloatingItem(hashTagFilterFlag);
                break;

            case R.id.floatingActionButton:
//clearSearchBar(ac); //서치바 초기화
                if(fa != null)
                    fragmentManager.beginTransaction().hide(fa).commit();
                if(fragmentLocationListLayout != null)
                    fragmentManager.beginTransaction().hide(fragmentLocationListLayout).commit();

                selectLocationFlag = true;
                SetToolbar(); //툴바 세팅
                sl.SetLinearLayout(getApplicationContext(), relativelayout_sub);



                //floating icon
                setBottomBar(selectLocationFlag);
                setFloatingItem(selectLocationFlag);


//                startActivity(intent);

//updateTextureViewSize((int) motionEvent.getX(), (int) motionEvent.getY());
                //sl.SetLinearLayout(getApplicationContext(), relativelayoutSelect);

                break;
            default:
                break;
        }
    }

    // fragment 상에서 다른 fragment로 이동
    //프래그먼트와 프래그먼트끼리 직접접근을하지않는다. 프래그먼트와 엑티비티가 접근함
    public void onFragmentChange(int index) {
        if (index == 0) {
            getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout, fragment1).commit();
        } else if (index == 1) {
            getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout, fragment2).commit();
        }
    }

    @Override
    public void onBackPressed() {
        // BackPressedForFinish 클래시의 onBackPressed() 함수를 호출한다.

        if (searchFlag == false && recyFrag == false && selectLocationFlag == false && hashTagFilterFlag == false)
            backPressedForFinish.onBackPressed();
        //서치상태 아닐때만 종료 가능

        if (drawerLayout.isDrawerOpen(GravityCompat.START) && searchFlag == false && recyFrag == false && selectLocationFlag == false && hashTagFilterFlag == false) {
            drawerLayout.closeDrawers();
        } else if (searchFlag == true) {
            getSupportActionBar().show();
            searchFlag = false;

            setBottomBar(searchFlag);
            setSearchBar(searchFlag);
            setFloatingItem(searchFlag);
        }

        if (selectLocationFlag == true) {
            selectLocationFlag = false;
            getSupportActionBar().show();
            sl.SetLinearLayout(getApplicationContext(), relativelayout_sub);
            setBottomBar(selectLocationFlag);
            setFloatingItem(selectLocationFlag);
        }
        if (hashTagFilterFlag == true) {
            hideHashTagFilter();
            setFloatingItem(hashTagFilterFlag);
        }


        hideRecyclerView(); //만약 떠있으면 디렉토리 종료 후 recy플래그 false
    }

    public void showRecyclerView() { //리사이클 플래그가 false 이면 - 리사이클러 뷰가 안보이면 실행해준다. true 로 바꾼다.
        if (recyFrag == false) { //호출했을 때 리사이클 없을 경우에만 실행. 떠있을 때 재실행 방지
            animation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.translate);
            recy_con_layout.setAnimation(animation);
            recy_con_layout.setVisibility(mView.VISIBLE);
            recyFrag = true;
        }
    }

    public void hideRecyclerView() { //리사이클 플래그가 false 이면 - 리사이클러 뷰가 안보이면 실행해준다. true 로 바꾼다.
        if (recyFrag == true) { //호출하였을 때 리사이클이 떠있을 경우에만 실행한다. 안떠있을 때 재실행 방지.
            animationH = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.translatehide);
            recy_con_layout.setAnimation(animationH);
            recy_con_layout.setVisibility(mView.GONE);
            recyFrag = false;
        }
    }

    public void showHashTagFilter() {
        if (hashTagFilterFlag == false) { //호출했을 때 해시필터 없을 경우에만 실행.
            animation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.alpha);
            hastagView.setAnimation(animation);
            hastagView.setVisibility(mView.VISIBLE);
            hashTagFilterFlag = true;
        }
    }

    public void hideHashTagFilter() {
        if (hashTagFilterFlag == true) { //호출했을 때 해시필터 없을 경우에만 실행.
            animation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.alphahide);
            hastagView.setAnimation(animation);
            hastagView.setVisibility(mView.GONE);
            hashTagFilterFlag = false;
        }
    }

    // navigation 메뉴 선택 이벤트처리
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        int id = menuItem.getItemId();

        if (id == R.id.home) {
            // Handle the camera action
            Intent intent = new Intent(this, homeActivity.class);
            startActivity(intent);
            CustomIntent.customType(this, "left-to-right");
        } else if (id == R.id.setting) {

        } else if (id == R.id.todo) {

        } else if (id == R.id.excel) {

        } else if (id == R.id.call) {

        } else if (id == R.id.help) {

        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
//        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    //상단 툴바 클릭 이벤트
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_item_search: {//상단 검색 버튼 클릭 시

                hideRecyclerView(); //일단 디렉토리 열려있으면 삭제

                Toast.makeText(getApplicationContext(), "검색할 장소를 입력하세요.", Toast.LENGTH_LONG).show();

                //툴바 제거
                if (getSupportActionBar().isShowing()) {
                    searchFlag = true;

                    getSupportActionBar().hide();
                    ct.requestFocus();
                    //editText.setFocusableInTouchMode(true);
                    Log.d("오류", "requestFocus 오류", null);
                    //clearbleText.requestFocus();
                    Log.d("오류2", "requestFocus 오류", null);

                    //imm.showSoftInput(ac, 0);
                    imm.toggleSoftInput(InputMethodManager.SHOW_FORCED, 0);
//                    출처: https://kkangsnote.tistory.com/35 [깡샘의 토마토]

                    setBottomBar(searchFlag);
                    setSearchBar(searchFlag);
                    setFloatingItem(searchFlag);
                }
                return true;
            } //검색 버튼 종료
            case R.id.menu_tag_filter: {
                showHashTagFilter(); // 안보인다면 해시태그를 보이게 한 뒤 해시플래그를 트루로 만든다.
                setFloatingItem(hashTagFilterFlag); //해시플래그가 트루면 숨긴다.

                return true;
            }

            default:
                Toast.makeText(getApplicationContext(), "나머지 버튼 클릭됨", Toast.LENGTH_LONG).show();
                return super.onOptionsItemSelected(item);
        }
    }

    //상단 검색 눌렀을 때

    public void setFloatingItem(boolean searchFlag) { //searchFlag 에 맞게 상단 검색 바 출력
        if (searchFlag == true)
            floatingButton.hide();
        else
            floatingButton.show();
    }


    public void setSearchBar(boolean searchFlag) { //searchFlag 에 맞게 상단 검색 바 출력
        if (searchFlag == true)
            searchlinearlayout.setVisibility(View.VISIBLE);
        else
            searchlinearlayout.setVisibility(View.GONE);
    }

    public void setBottomBar(boolean Flag) { //searchFlag 에 맞게 하단 바 가리기
        if (Flag == true)
            bottomBar.setVisibility(View.GONE);
        else
            bottomBar.setVisibility(View.VISIBLE);
    }

    // 검색에 사용될 데이터를 리스트에 추가한다.
    private void settingList() {
        list.add("채수빈");
        list.add("박지현");
        list.add("수지");
        list.add("남태현");
        list.add("하성운");
        list.add("크리스탈");
        list.add("강승윤");
        list.add("손나은");
        list.add("남주혁");
        list.add("루이");
        list.add("진영");
        list.add("슬기");
        list.add("이해인");
        list.add("고원희");
        list.add("설리");
        list.add("공명");
        list.add("김예림");
        list.add("혜리");
        list.add("웬디");
        list.add("박혜수");
        list.add("카이");
        list.add("진세연");
        list.add("동호");
        list.add("박세완");
        list.add("도희");
        list.add("창모");
        list.add("허영지");
    }

    public void clearSearchBar(AutoCompleteTextView ac) {
        ac.clearFocus(); //포커스 해제
        ac.setText(null); //텍스트 초기화
        getSupportActionBar().show(); //툴바 보이게
    }

    public void checkAllHashTag() { //해시태그 모두 선택
        checkBoxAll = findViewById(R.id.checkBox);
        checkBoxAll.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (checkBoxAll.isChecked()) {
                    msHashTagCheckBoxManager.CheckBoxAllClick(MainActivity.this);
                } else
                    msHashTagCheckBoxManager.CheckBoxAllUnClick(getApplicationContext());
            }
        });
    } //checkAllHashTag 종료

    public void addHashTag() { //초기 해시태그 세팅
        for (int i = 1; i < msHashTag.length; i++) {
            msHashTag[i] = new HashTag(this);
            msHashTag[i].setOnClickListener(ob);
            msHashTag[i].setId(i);
            if (i % 3 == 0)
                msHashTag[i].init("1", "#22FFFF", R.drawable.hashtagborder, params);
            else if (i % 2 == 0)
                msHashTag[i].init("초기값", "#22FFFF", R.drawable.hashtagborder, params);
            else
                msHashTag[i].init("asdfan32of2ofndladf", "#3F729B", R.drawable.hashtagborder, params);

            ((FlowLayout) findViewById(R.id.flowlayout)).addView(msHashTag[i]);
        }
    }//addHashTag 종료

    //해시태그 선택
    public class HasTagOnClickListener implements Button.OnClickListener {
        @Override
        public void onClick(View v) {
            msHashTagCheckBoxManager.HashTagClickEvent(MainActivity.this, v);
        }
    }

    //장소 선택 위한 툴바 제거
    public void SetToolbar() { //액션바 상태에 따라서 세팅해준다.
        if (getSupportActionBar().isShowing()) { //만약 액션바 보이고 있으면 숨기기
            getSupportActionBar().hide();
        } else {
            getSupportActionBar().show(); //만약 액션바 안보이면 보이게
        }
    }

    public void toasts() {
        Toast.makeText(this, "시발되라고개새끼야", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == ADD_MAIN_ACTIVITY_REQUEST_CODE && resultCode == ADD_MAIN_ACTIVITY_REPLY_CODE) {
            if (data.getBooleanExtra(AddMainActivity.SET_STORE_FLAG, false)) {
                if (relativelayout_sub.getVisibility() != View.GONE)
                    relativelayout_sub.setVisibility(View.GONE);
            }
        }
        setFragmentLocationListLayout();
    }
    public void setFragmentLocationListLayout(){
        if (fragmentLocationListLayout == null) {
            fragmentLocationListLayout = new LocationFragment();
            fragmentManager.beginTransaction().add(R.id.frameLayout, fragmentLocationListLayout).commit();
        } else {
            clearSearchBar(ac); //서치바 초기화
            fragmentManager.beginTransaction().show(fragmentLocationListLayout).commit();
        }
    }
}
