package com.example.heronation;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.PopupWindow;
import android.widget.SeekBar;
import android.widget.TextView;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.tabs.TabLayout;

/*
 * implements~ 를 한 이유
 * Acitivity와 Fragment가 통신할 때, OnFragmentInteractionListener를 사용함.
 * 프래그먼트에서 액티비티로 통신(데이터 주고 받는 것)이 있을 수도 있기 때문에
 * MainActivity 에서 이를 implement한 후 오버라이딩 (임시로)
 */
public class MainActivity extends AppCompatActivity
        implements
        ShopFragment.OnFragmentInteractionListener,
        WishlistFragment.OnFragmentInteractionListener,
        ItemFragment.OnFragmentInteractionListener,
        MypageFragment.OnFragmentInteractionListener,
        MeasurementFragment.OnFragmentInteractionListener,
        WishlistItemFragment.OnFragmentInteractionListener,
        ItemHomeFragment.OnFragmentInteractionListener,
        ItemNewFragment.OnFragmentInteractionListener,
        ItemBestFragment.OnFragmentInteractionListener,
        ItemAiFragment.OnFragmentInteractionListener,
        ItemSaleFragment.OnFragmentInteractionListener,
        ShopFavoritesFragment.OnFragmentInteractionListener,
        WishlistShopFragment.OnFragmentInteractionListener,
        WishlistRecentlyViewedItemFragment.OnFragmentInteractionListener,
        WishlistClosetFragment.OnFragmentInteractionListener
{
    /*
    * Fragment Manager 선언 -- Acitivity 내에서 Fragment를 관리해주기 위해서는 FragmentManager를 사용해야함
    *
    * 각각의 Fragment를 선언하고, Fragment 객체 생성
    */
    private FragmentManager fragmentManager=getSupportFragmentManager(); //Fragment 가져오기
    private MeasurementFragment measurementFragment=new MeasurementFragment();
    private MypageFragment mypageFragment=new MypageFragment();
    private ItemFragment itemFragment=new ItemFragment();
    private ShopFragment shopFragment=new ShopFragment();
    private WishlistFragment wishlistFragment=new WishlistFragment();

    /* 하단바 */
    private BottomNavigationView bottomNavigationView;

    /* 상단 메뉴 버튼을 눌렀을 때 뜨는 레이아웃을 위한 변수들 */
    private DrawerLayout drawerLayout;
    private View drawerView;
    /* 로그인 상태 boolean값 */
    public boolean loginState = true;

    /* Shop Ranking에 필터 버튼 눌렀을 때, seekBar 설정에 필요한 변수들 */
    int number=0;
    private SeekBar seekBar;
    private TextView age;
    /* Shop Ranking에 필터 버튼에 필요한 변수들 */
    private Button gender_male;
    private Button gender_female;
    private Button category_cloth;
    private Button category_acc;
    private Button category_bag;
    private Button category_shoes;
    private Button style_feminine;
    private Button style_modern;
    private Button style_simple;
    private Button style_lovely;
    private Button style_unique;
    private Button style_missy;
    private Button style_campus;
    private Button style_vintage;
    private Button style_sexy;
    private Button style_school;
    private Button style_romantic;
    private Button style_office;
    private Button filter_return;
    private Button filter_finish;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        /* 상태표시바를 삭제해주는 작업 */
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);

        /* BottomNavigation view를 선언해주고, bottomNavigationView의 객체를 생성한 후,
         * bottomNavigationView에 activity_main.xml의 bottomnavigation_menu_bar를 할당해준 후,
         * bottomItemSelectedListener 클래스를, bottomNavigatioView 객체에 할당
         */
        bottomNavigationView=findViewById(R.id.bottomnavigation_menu_bar);
        BottomNavigationHelper.disableShiftMode(bottomNavigationView);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomItemSelectedListener());

       /* 첫 화면이 ItemFragment이므로, Transaction을 getSupportFragmentManager().beginTransaction()을 통해 가져온 후,
       * acitivity_main.xml에 있는 framelayout인 fragment_container의 화면을 shopFragment로 변경해준 후,
       * commit 호출해주어야 Transaction 작업이 완료됨.
       */
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, itemFragment).commit();

        /* 상단바 메뉴 드로워 */
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawerView = (View) findViewById(R.id.drawer);
        ImageButton btn_open = (ImageButton) findViewById(R.id.btn_open);   //openimage 정의
        final TextView id_text = (TextView)findViewById(R.id.text_id);
        btn_open.setOnClickListener(new View.OnClickListener() {
            /* 클릭했을때 Drawer open, 로그인 상태에 따라 닉네임 or 로그인/회원가입 */
            @Override   //클릭했을때 Drawer open
            public void onClick(View v) {
                if(loginState == true){
                    id_text.setText("닉네임");
                }
                else{
                    id_text.setText("로그인/회원가입");
                }
                drawerLayout.openDrawer(drawerView);
            }
        });
        Button btn_close = (Button) findViewById(R.id.btn_close);
        btn_close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawerLayout.closeDrawers();
            }
        });
        drawerView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return true;
            }
        });
        /* 상단바 메뉴 드로워 */

    }

    /*
    * BottomNavaigationView Menu Bar에 있는 특정 값을 선택하였을 때
    * Switch문으로 경우를 나누어
    * Item 버튼을  눌렀을 때, Item Fragment로 이동
    * Shop 버튼을 눌렀을 때, Shop Fragment로 이동
    * 측정 버튼을 눌렀을 때, Measurement Fragment로 이동
    * 찜 버튼을 눌렀을 때, Wishlist Fragment로 이동
    * 마이 페이지 버튼을 눌렀을 때, Mypage Fragment로 이동
    */
    class BottomItemSelectedListener implements  BottomNavigationView.OnNavigationItemSelectedListener{
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
            FragmentTransaction transaction=fragmentManager.beginTransaction(); //FragmentTransaction 가져오기
            switch(menuItem.getItemId()){
                case R.id.menuitem_bottombar_item:
                    transaction.replace(R.id.fragment_container, itemFragment).commit();
                    return true;
                case R.id.menuitem_bottombar_shop:
                    transaction.replace(R.id.fragment_container, shopFragment).commit();
                    return true;
                case R.id.menuitem_bottombar_measurement:
                    transaction.replace(R.id.fragment_container,measurementFragment).commit();
                    return true;
                case R.id.menuitem_bottombar_wishlist:
                    transaction.replace(R.id.fragment_container, wishlistFragment).commit();
                    return true;
                case R.id.menuitem_bottombar_mypage:
                    transaction.replace(R.id.fragment_container,mypageFragment).commit();
                    return true;
            }
            return false;
        }
    }

    void go_to_shop_fragment(){
        bottomNavigationView.setSelectedItemId(R.id.menuitem_bottombar_shop);
        FragmentTransaction transaction=fragmentManager.beginTransaction(); //FragmentTransaction 가져오기
        transaction.replace(R.id.fragment_container, shopFragment).commit();
    }

    void go_to_measurement(){
        bottomNavigationView.setSelectedItemId(R.id.menuitem_bottombar_measurement);
        FragmentTransaction transaction=fragmentManager.beginTransaction(); //FragmentTransaction 가져오기
        transaction.replace(R.id.fragment_container, measurementFragment).commit();
    }


    ///그냥 나중에 필요할까봐 넣어 놓았습니다
    DrawerLayout.DrawerListener listener = new DrawerLayout.DrawerListener() {
        @Override//슬라이드했을때 호출
        public void onDrawerSlide(@NonNull View drawerView, float slideOffset) {
        }

        @Override// 무언가가 오픈됐을때
        public void onDrawerOpened(@NonNull View drawerView) {
        }

        @Override//닫혔을때
        public void onDrawerClosed(@NonNull View drawerView) {
        }

        @Override //바뀌었을때
        public void onDrawerStateChanged(int newState) {
        }
    };

    void open_panel(){

        /* 필터 PopUp창 띄우기 */
        final PopupWindow mPopupWindow;
        View popupView = getLayoutInflater().inflate(R.layout.activity_filter_pop_up, null);
        mPopupWindow = new PopupWindow(popupView);
        mPopupWindow.setWindowLayoutMode(WindowManager.LayoutParams.MATCH_PARENT,
                WindowManager.LayoutParams.WRAP_CONTENT);
        //팝업 터치 가능
        mPopupWindow.setTouchable(true);
        //팝업 외부 터치 가능(외부 터치시 나갈 수 있게)
        mPopupWindow.setOutsideTouchable(true);
        //외부터치 인식을 위한 추가 설정 : 미 설정시 외부는 null로 생각하고 터치 인식 X
        mPopupWindow.setBackgroundDrawable(new BitmapDrawable());
        //애니메이션 활성화

        // PopUp 창 띄우기
        mPopupWindow.showAtLocation(popupView, Gravity.BOTTOM, 0, 0);

        /* 블러 처리 -- 대체 코드 있으면 대체하기 (불안정함)
         *  ViewGroup.LayoutParams를 WindowManager.LayoutParams에 캐스팅하기 위함인데,
         * 버전에 따라 ViewGroup의 자식인 다른 예를 들면 FrameLayout.LayoutParams를 가리키기도 함.
         * 하지만, WindowManager.LayoutParams에 FrameLayout.LayoutParams는 캐스팅 되지 않으므로 오류가 발생함
         * 이를 처리하기 위해서 if문으로 분기를 해주었으나, 불안정한 코드라서 대체 방법이 있다면 대체해야할 것 같음.
         * */
        View container;
        if (android.os.Build.VERSION.SDK_INT > 22) {
            container = (View) mPopupWindow.getContentView().getParent().getParent();
        }else{
            container = (View) mPopupWindow.getContentView().getParent();
        }
        WindowManager wm = (WindowManager) getSystemService(Context.WINDOW_SERVICE);
        WindowManager.LayoutParams p = (WindowManager.LayoutParams)container.getLayoutParams();
        // add flag
        p.flags |= WindowManager.LayoutParams.FLAG_DIM_BEHIND;
        p.dimAmount = 0.6f;
        wm.updateViewLayout(container, p);

        seekBar=(SeekBar)popupView.findViewById(R.id.seekBar);
        age=(TextView)popupView.findViewById(R.id.shop_ranking_search_age);
        /* seekBar의 값이 설정되었을 때, textview를 설정해준다. */
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                number=seekBar.getProgress();
                if(number==0){
                    age.setText("10대");
                }else if(number==1){
                    age.setText("20대 초반");
                }else if(number==2){
                    age.setText("20대 중반");
                } else if(number==3){
                    age.setText("20대 후반");
                } else if(number==4){
                    age.setText("30대 초반");
                }else if(number==5){
                    age.setText("30대 중반");
                }else if(number==6){
                    age.setText("30대 후반");
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                number=seekBar.getProgress();
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                number=seekBar.getProgress();
            }
        });

        /* 필터 버튼 정보 전달, 버튼 색, 글자 색 변경 위한 변수들 */
        gender_male = (Button) popupView.findViewById(R.id.shop_filter_gender_male);
        gender_female = (Button) popupView.findViewById(R.id.shop_filter_gender_female);
        category_cloth = (Button) popupView.findViewById(R.id.shop_filter_category_cloth);
        category_acc = (Button) popupView.findViewById(R.id.shop_filter_category_acc);
        category_bag = (Button) popupView.findViewById(R.id.shop_filter_category_bag);
        category_shoes = (Button) popupView.findViewById(R.id.shop_filter_category_shoes);
        style_feminine = (Button) popupView.findViewById(R.id.shop_filter_style_feminine);
        style_modern = (Button) popupView.findViewById(R.id.shop_filter_style_modern);
        style_simple = (Button) popupView.findViewById(R.id.shop_filter_style_simple);
        style_lovely = (Button) popupView.findViewById(R.id.shop_filter_style_lovely);
        style_unique = (Button) popupView.findViewById(R.id.shop_filter_style_unique);
        style_missy = (Button) popupView.findViewById(R.id.shop_filter_style_missy);
        style_campus = (Button) popupView.findViewById(R.id.shop_filter_style_campus);
        style_vintage = (Button) popupView.findViewById(R.id.shop_filter_style_vintage);
        style_sexy = (Button) popupView.findViewById(R.id.shop_filter_style_sexy);
        style_school = (Button) popupView.findViewById(R.id.shop_filter_style_school);
        style_romantic = (Button) popupView.findViewById(R.id.shop_filter_style_romantic);
        style_office = (Button) popupView.findViewById(R.id.shop_filter_style_office);

        /* 필터 초기화, 완료 버튼 */
        filter_return=(Button)popupView.findViewById(R.id.shop_filter_return);
        filter_finish=(Button)popupView.findViewById(R.id.shop_filter_finish);

        /* 버튼 클릭 시 색상, 글자색 변경, 정보 전달할 때 이용 */
        gender_male.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(gender_male.isSelected()==false) {
                    gender_male.setSelected(true);
                    gender_male.setTextColor(Color.parseColor("#ffffff"));
                } else{
                    gender_male.setSelected(false);
                    gender_male.setTextColor(Color.parseColor("#000000"));
                }
            }
        });

        gender_female.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(gender_female.isSelected()==false) {
                    gender_female.setSelected(true);
                    gender_female.setTextColor(Color.parseColor("#ffffff"));
                } else{
                    gender_female.setSelected(false);
                    gender_female.setTextColor(Color.parseColor("#000000"));
                }
            }

        });

        category_cloth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(category_cloth.isSelected()==false) {
                    category_cloth.setSelected(true);
                    category_cloth.setTextColor(Color.parseColor("#ffffff"));
                } else{
                    category_cloth.setSelected(false);
                    category_cloth.setTextColor(Color.parseColor("#000000"));
                }
            }
        });

        category_acc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(category_acc.isSelected()==false) {
                    category_acc.setSelected(true);
                    category_acc.setTextColor(Color.parseColor("#ffffff"));
                } else{
                    category_acc.setSelected(false);
                    category_acc.setTextColor(Color.parseColor("#000000"));
                }
            }
        });

        category_bag.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(category_bag.isSelected()==false) {
                    category_bag.setSelected(true);
                    category_bag.setTextColor(Color.parseColor("#ffffff"));
                } else{
                    category_bag.setSelected(false);
                    category_bag.setTextColor(Color.parseColor("#000000"));
                }
            }
        });

        category_shoes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(category_shoes.isSelected()==false) {
                    category_shoes.setSelected(true);
                    category_shoes.setTextColor(Color.parseColor("#ffffff"));
                } else{
                    category_shoes.setSelected(false);
                    category_shoes.setTextColor(Color.parseColor("#000000"));
                }
            }
        });

        style_feminine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(style_feminine.isSelected()==false) {
                    style_feminine.setSelected(true);
                    style_feminine.setTextColor(Color.parseColor("#ffffff"));
                } else{
                    style_feminine.setSelected(false);
                    style_feminine.setTextColor(Color.parseColor("#000000"));
                }
            }
        });

        style_modern.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(style_modern.isSelected()==false) {
                    style_modern.setSelected(true);
                    style_modern.setTextColor(Color.parseColor("#ffffff"));
                } else{
                    style_modern.setSelected(false);
                    style_modern.setTextColor(Color.parseColor("#000000"));
                }
            }
        });

        style_simple.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(style_simple.isSelected()==false) {
                    style_simple.setSelected(true);
                    style_simple.setTextColor(Color.parseColor("#ffffff"));
                } else{
                    style_simple.setSelected(false);
                    style_simple.setTextColor(Color.parseColor("#000000"));
                }
            }
        });

        style_lovely.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(style_lovely.isSelected()==false) {
                    style_lovely.setSelected(true);
                    style_lovely.setTextColor(Color.parseColor("#ffffff"));
                } else{
                    style_lovely.setSelected(false);
                    style_lovely.setTextColor(Color.parseColor("#000000"));
                }
            }
        });

        style_unique.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(style_unique.isSelected()==false) {
                    style_unique.setSelected(true);
                    style_unique.setTextColor(Color.parseColor("#ffffff"));
                } else{
                    style_unique.setSelected(false);
                    style_unique.setTextColor(Color.parseColor("#000000"));
                }
            }
        });

        style_missy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(style_missy.isSelected()==false) {
                    style_missy.setSelected(true);
                    style_missy.setTextColor(Color.parseColor("#ffffff"));
                } else{
                    style_missy.setSelected(false);
                    style_missy.setTextColor(Color.parseColor("#000000"));
                }
            }
        });

        style_campus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(style_campus.isSelected()==false) {
                    style_campus.setSelected(true);
                    style_campus.setTextColor(Color.parseColor("#ffffff"));
                } else{
                    style_campus.setSelected(false);
                    style_campus.setTextColor(Color.parseColor("#000000"));
                }
            }
        });

        style_vintage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(style_vintage.isSelected()==false) {
                    style_vintage.setSelected(true);
                    style_vintage.setTextColor(Color.parseColor("#ffffff"));
                } else{
                    style_vintage.setSelected(false);
                    style_vintage.setTextColor(Color.parseColor("#000000"));
                }
            }
        });

        style_sexy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(style_sexy.isSelected()==false) {
                    style_sexy.setSelected(true);
                    style_sexy.setTextColor(Color.parseColor("#ffffff"));
                } else{
                    style_sexy.setSelected(false);
                    style_sexy.setTextColor(Color.parseColor("#000000"));
                }
            }
        });

        style_school.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(style_school.isSelected()==false) {
                    style_school.setSelected(true);
                    style_school.setTextColor(Color.parseColor("#ffffff"));
                } else{
                    style_school.setSelected(false);
                    style_school.setTextColor(Color.parseColor("#000000"));
                }
            }
        });

        style_romantic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(style_romantic.isSelected()==false) {
                    style_romantic.setSelected(true);
                    style_romantic.setTextColor(Color.parseColor("#ffffff"));
                } else{
                    style_romantic.setSelected(false);
                    style_romantic.setTextColor(Color.parseColor("#000000"));
                }
            }
        });

        style_office.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(style_office.isSelected()==false) {
                    style_office.setSelected(true);
                    style_office.setTextColor(Color.parseColor("#ffffff"));
                } else{
                    style_office.setSelected(false);
                    style_office.setTextColor(Color.parseColor("#000000"));
                }
            }
        });

        /* 초기화 버튼 눌렀을 때 */
        filter_return.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gender_male.setSelected(false);
                gender_male.setTextColor(Color.parseColor("#000000"));
                gender_female.setSelected(false);
                gender_female.setTextColor(Color.parseColor("#000000"));
                category_cloth.setSelected(false);
                category_cloth.setTextColor(Color.parseColor("#000000"));
                category_acc.setSelected(false);
                category_acc.setTextColor(Color.parseColor("#000000"));
                category_bag.setSelected(false);
                category_bag.setTextColor(Color.parseColor("#000000"));
                category_shoes.setSelected(false);
                category_shoes.setTextColor(Color.parseColor("#000000"));
                style_feminine.setSelected(false);
                style_feminine.setTextColor(Color.parseColor("#000000"));
                style_modern.setSelected(false);
                style_modern.setTextColor(Color.parseColor("#000000"));
                style_simple.setSelected(false);
                style_simple.setTextColor(Color.parseColor("#000000"));

                style_lovely.setTextColor(Color.parseColor("#000000"));
                style_lovely.setSelected(false);
                style_unique.setTextColor(Color.parseColor("#000000"));
                style_unique.setSelected(false);
                style_missy.setTextColor(Color.parseColor("#000000"));
                style_missy.setSelected(false);
                style_campus.setTextColor(Color.parseColor("#000000"));
                style_campus.setSelected(false);
                style_vintage.setTextColor(Color.parseColor("#000000"));
                style_vintage.setSelected(false);
                style_sexy.setTextColor(Color.parseColor("#000000"));
                style_sexy.setSelected(false);
                style_school.setTextColor(Color.parseColor("#000000"));
                style_school.setSelected(false);
                style_romantic.setTextColor(Color.parseColor("#000000"));
                style_romantic.setSelected(false);
                style_office.setTextColor(Color.parseColor("#000000"));
                style_office.setSelected(false);

                //seekBar 20대 중반으로 설정 (회원가입 정보 있을 땐, 나이 기반 설정으로 수정)
                seekBar.setProgress(2);
            }
        });

        //완료 버튼 눌렀을 때 창닫기, 정보 전달할 때 사용하기
        filter_finish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mPopupWindow.dismiss();
            }
        });



    }

    /*
     * onFragmenInteraciton~ 를 한 이유
     * Acitivity와 Fragment가 통신할 때, OnFragmentInteractionListener를 사용함.
     * 프래그먼트에서 액티비티로 통신(데이터 주고 받는 것)이 있을 수도 있기 때문에
     * MainActivity 에서 이를 implement한 후 오버라이딩 (임시로)
     */
    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
