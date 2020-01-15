package com.example.heronation;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageButton;
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
        ShopRankingFragment.OnFragmentInteractionListener,
        MeasurementFragment.OnFragmentInteractionListener,
        WishlistItemFragment.OnFragmentInteractionListener,
        ItemHomeFragment.OnFragmentInteractionListener,
        ItemNewFragment.OnFragmentInteractionListener,
        ItemBestFragment.OnFragmentInteractionListener,
        ItemAiFragment.OnFragmentInteractionListener,
        ItemSaleFragment.OnFragmentInteractionListener,
        ShopFavoritesFragment.OnFragmentInteractionListener,
        WishlistShopFragment.OnFragmentInteractionListener,
        WishlistRecentlyViewedItemFragment.OnFragmentInteractionListener
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

    /*
    * 각각의 Tab Layout 선언
    * 상단 탭 layout에 해당하는 Fragment를 선언하고 Fragment 객체 생성
    * */
    private TabLayout item_tabLayout;


    private ItemHomeFragment itemHomeFragment =new ItemHomeFragment();
    private ItemNewFragment itemNewFragment =new ItemNewFragment();
    private ItemBestFragment itemBestFragment =new ItemBestFragment();
    private ItemAiFragment itemAiFragment =new ItemAiFragment();
    private ItemSaleFragment itemSaleFragment =new ItemSaleFragment();

    private TabLayout shop_tabLayout;
    private ShopRankingFragment shopRankingFragment=new ShopRankingFragment();
    private ShopFavoritesFragment shopFavoritesFragment=new ShopFavoritesFragment();

    private TabLayout wishlist_tabLayout;
    private WishlistItemFragment wishlistItemFragment=new WishlistItemFragment();
    private WishlistShopFragment wishlistShopFragment=new WishlistShopFragment();
    private WishlistRecentlyViewedItemFragment wishlistRecentlyViewedItemFragment=new WishlistRecentlyViewedItemFragment();

    /* 상단 메뉴 버튼을 눌렀을 때 뜨는 레이아웃을 위한 변수들 */
    private DrawerLayout drawerLayout;
    private View drawerView;
    /* 로그인 상태 boolean값 */
    public boolean loginState = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        /* 상태표시바를 삭제해주는 작업 */
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);
/*
    Item의 상단탭
         하단탭에서 Item의 상단탭을 선택했을 시에만 보여져야 함
         상단탭이 선택되었을 때, 상단탭의 선택된 현재 위치를 얻어 Fragment를 이동시킨다.

        item_tabLayout=(TabLayout)findViewById(R.id.item_tab_layout);
        item_tabLayout.addOnTabSelectedListener(new ItemTopItemSelectedListener());

        Shop의 상단탭
         하단탭에서 Shop의 상단탭을 선택했을 시에만 보여져야 함
         상단탭이 선택되었을 때, 상단탭의 선택된 현재 위치를 얻어 Fragment를 이동시킨다.

        shop_tabLayout=(TabLayout)findViewById(R.id.shop_tab_layout);
        shop_tabLayout.addOnTabSelectedListener(new ShopTopItemSelectedListener());

        Wishlist의 상단탭
         하단탭에서 Wishlist의 상단탭을 선택했을 시에만 보여져야 함
         상단탭이 선택되었을 때, 상단탭의 선택된 현재 위치를 얻어 Fragment를 이동시킨다.

        wishlist_tabLayout=(TabLayout)findViewById(R.id.wishlist_tab_layout);
        wishlist_tabLayout.addOnTabSelectedListener(new WishlistTopItemSelectedListener());
*/
        /* BottomNavigation view를 선언해주고, bottomNavigationView의 객체를 생성한 후,
         * bottomNavigationView에 activity_main.xml의 bottomnavigation_menu_bar를 할당해준 후,
         * bottomItemSelectedListener 클래스를, bottomNavigatioView 객체에 할당
         */
        BottomNavigationView bottomNavigationView=findViewById(R.id.bottomnavigation_menu_bar);
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
