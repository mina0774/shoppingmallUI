package com.example.heronation;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.WindowManager;

import com.google.android.material.bottomnavigation.BottomNavigationView;

/*
 * implements~ 를 한 이유
 * Acitivity와 Fragment가 통신할 때, OnFragmentInteractionListener를 사용함.
 * 프래그먼트에서 액티비티로 통신(데이터 주고 받는 것)이 있을 수도 있기 때문에
 * MainActivity 에서 이를 implement한 후 오버라이딩 (임시로)
 */
public class MainActivity extends AppCompatActivity
        implements
        ShopParentFragment.OnFragmentInteractionListener,
        MypageFragment.OnFragmentInteractionListener,
        SearchFragment.OnFragmentInteractionListener,
        MeasurementFragment.OnFragmentInteractionListener,
        WishlistFragment.OnFragmentInteractionListener {
    /*
    * Fragment Manager 선언 -- Acitivity 내에서 Fragment를 관리해주기 위해서는 FragmentManager를 사용해야함
    * 각각의 Fragment를 선언하고, Fragment 객체 생성
    */
    private FragmentManager fragmentManager=getSupportFragmentManager(); //Fragment 가져오기
    private ShopParentFragment shopParentFragment =new ShopParentFragment();
    private SearchFragment searchFragment=new SearchFragment();
    private MeasurementFragment measurementFragment=new MeasurementFragment();
    private WishlistFragment wishlistFragment=new WishlistFragment();
    private MypageFragment mypageFragment=new MypageFragment();

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
        BottomNavigationView bottomNavigationView=findViewById(R.id.bottomnavigation_menu_bar);
        bottomNavigationView.setOnNavigationItemSelectedListener(new ItemSelectedListener());

       /* 첫 화면이 ShopFragment이므로, Transaction을 getSupportFragmentManager().beginTransaction()을 통해 가져온 후,
       * acitivity_main.xml에 있는 framelayout인 fragment_container의 화면을 shopFragment로 변경해준 후,
       * commit 호출해주어야 Transaction 작업이 완료됨.
       */
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, shopParentFragment).commit();
    }

    /*
    * BottomNavaigationView Menu Bar에 있는 특정 값을 선택하였을 때
    * Switch문으로 경우를 나누어
    * Shop 버튼을  눌렀을 때, Shop Fragment로 이동
    * 검색 버튼을 눌렀을 때, Search Fragment로 이동
    * 측정 버튼을 눌렀을 때, Measurement Fragment로 이동
    * 찜 버튼을 눌렀을 때, Wishlist Fragment로 이동
    * 마이 페이지 버튼을 눌렀을 때, Mypage Fragment로 이동
    */
    class ItemSelectedListener implements  BottomNavigationView.OnNavigationItemSelectedListener{
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
            FragmentTransaction transaction=fragmentManager.beginTransaction(); //FragmentTransaction 가져오기
            switch(menuItem.getItemId()){
                case R.id.menuitem_bottombar_shop:
                    transaction.replace(R.id.fragment_container, shopParentFragment).commit();
                    return true;
                case R.id.menuitem_bottombar_search:
                    transaction.replace(R.id.fragment_container,searchFragment).commit();
                    return true;
                case R.id.menuitem_bottombar_measurement:
                    transaction.replace(R.id.fragment_container,measurementFragment).commit();
                    return true;
                case R.id.menuitem_bottombar_wishlist:
                    transaction.replace(R.id.fragment_container,wishlistFragment).commit();
                    return true;
                case R.id.menuitem_bottombar_mypage:
                    transaction.replace(R.id.fragment_container,mypageFragment).commit();
                    return true;
            }
            return false;
        }
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
