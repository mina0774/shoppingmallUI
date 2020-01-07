package com.example.heronation;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;

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


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        /* 상태표시바를 삭제해주는 작업 */
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);

        /* Item의 상단탭
         하단탭에서 Item의 상단탭을 선택했을 시에만 보여져야 함
         상단탭이 선택되었을 때, 상단탭의 선택된 현재 위치를 얻어 Fragment를 이동시킨다.
         */
        item_tabLayout=(TabLayout)findViewById(R.id.item_tab_layout);
        item_tabLayout.addOnTabSelectedListener(new ItemTopItemSelectedListener());

        /* Shop의 상단탭
         하단탭에서 Shop의 상단탭을 선택했을 시에만 보여져야 함
         상단탭이 선택되었을 때, 상단탭의 선택된 현재 위치를 얻어 Fragment를 이동시킨다.
         */
        shop_tabLayout=(TabLayout)findViewById(R.id.shop_tab_layout);
        shop_tabLayout.addOnTabSelectedListener(new ShopTopItemSelectedListener());

        /* Wishlist의 상단탭
         하단탭에서 Wishlist의 상단탭을 선택했을 시에만 보여져야 함
         상단탭이 선택되었을 때, 상단탭의 선택된 현재 위치를 얻어 Fragment를 이동시킨다.
         */
        wishlist_tabLayout=(TabLayout)findViewById(R.id.wishlist_tab_layout);
        wishlist_tabLayout.addOnTabSelectedListener(new WishlistTopItemSelectedListener());

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
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, itemHomeFragment).commit();

        shop_tabLayout.setVisibility(View.GONE);
        wishlist_tabLayout.setVisibility(View.GONE);
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
                    item_tabLayout.setVisibility(View.VISIBLE); //item의 상단탭이 보임
                    shop_tabLayout.setVisibility(View.GONE); //shop의 상단탭이 안보임
                    wishlist_tabLayout.setVisibility(View.GONE); //wishlist의 상단탭이 안보임
                    transaction.replace(R.id.fragment_container, itemHomeFragment).commit();
                    return true;
                case R.id.menuitem_bottombar_shop:
                    item_tabLayout.setVisibility(View.GONE); //item의 상단탭이 안보임
                    shop_tabLayout.setVisibility(View.VISIBLE); //shop의 상단탭이 보임
                    wishlist_tabLayout.setVisibility(View.GONE); //wishlist의 상단탭이 안보임
                    transaction.replace(R.id.fragment_container, shopRankingFragment).commit();
                    return true;
                case R.id.menuitem_bottombar_measurement:
                    item_tabLayout.setVisibility(View.GONE); //item의 상단탭이 안보임
                    shop_tabLayout.setVisibility(View.GONE); //shop의 상단탭이 안보임
                    wishlist_tabLayout.setVisibility(View.GONE); //wishlist의 상단탭이 안보임
                    transaction.replace(R.id.fragment_container,measurementFragment).commit();
                    return true;
                case R.id.menuitem_bottombar_wishlist:
                    item_tabLayout.setVisibility(View.GONE); //item의 상단탭이 안보임
                    shop_tabLayout.setVisibility(View.GONE); //shop의 상단탭이 안보임
                    wishlist_tabLayout.setVisibility(View.VISIBLE); //wishlist의 상단탭이 보임
                    transaction.replace(R.id.fragment_container, wishlistItemFragment).commit();
                    return true;
                case R.id.menuitem_bottombar_mypage:
                    item_tabLayout.setVisibility(View.GONE); //item의 상단탭이 안보임
                    shop_tabLayout.setVisibility(View.GONE); //shop의 상단탭이 안보임
                    wishlist_tabLayout.setVisibility(View.GONE); //wishlist의 상단탭이 안보임
                    transaction.replace(R.id.fragment_container,mypageFragment).commit();
                    return true;
            }
            return false;
        }
    }

    /*
     * Item 상단 탭에 있는 특정 값을 선택하였을 때
     * Switch문으로 경우를 나누어
     * 홈 버튼을  눌렀을 때, ItemHomeFragment로 이동
     * 신상 버튼을 눌렀을 때, ItemNewFragment로 이동
     * 베스트 버튼을 눌렀을 때, ItemBestFragment로 이동
     * AI 버튼을 눌렀을 때, ItemAiFragment로 이동
     * 세일 버튼을 눌렀을 때, ItemSaleFragment로 이동
     */
    class ItemTopItemSelectedListener implements TabLayout.OnTabSelectedListener{
        @Override
        public void onTabSelected(TabLayout.Tab tab) {
            FragmentTransaction transaction=fragmentManager.beginTransaction(); // FragmentTransaction 가져오기
            int position=tab.getPosition(); // 상단탭의 선택된 현재 위치 받아오기
            switch(position){
                case 0:
                    transaction.replace(R.id.fragment_container, itemHomeFragment).commit();
                    break;
                case 1:
                    transaction.replace(R.id.fragment_container, itemNewFragment).commit();
                    break;
                case 2:
                    transaction.replace(R.id.fragment_container, itemBestFragment).commit();
                    break;
                case 3:
                    transaction.replace(R.id.fragment_container, itemAiFragment).commit();
                    break;
                case 4:
                    transaction.replace(R.id.fragment_container, itemSaleFragment).commit();
                    break;
            }

        }

        @Override
        public void onTabUnselected(TabLayout.Tab tab) {

        }

        @Override
        public void onTabReselected(TabLayout.Tab tab) {

        }
    }

    /*
     * Shop 상단 탭에 있는 특정 값을 선택하였을 때
     * Switch문으로 경우를 나누어
     * Shop Ranking 버튼을  눌렀을 때, ShopRankingFragment로 이동
     * 즐겨찾기 버튼을 눌렀을 때, ShopFavoritesFragment로 이동
     */
    class ShopTopItemSelectedListener implements TabLayout.OnTabSelectedListener{
        @Override
        public void onTabSelected(TabLayout.Tab tab) {
            FragmentTransaction transaction=fragmentManager.beginTransaction(); // FragmentTransaction 가져오기
            int position=tab.getPosition(); // 상단탭의 선택된 현재 위치 받아오기
            switch(position) {
                case 0:
                    transaction.replace(R.id.fragment_container, shopRankingFragment).commit();
                    break;
                case 1:
                    transaction.replace(R.id.fragment_container, shopFavoritesFragment).commit();
                    break;
            }

        }

        @Override
        public void onTabUnselected(TabLayout.Tab tab) {

        }

        @Override
        public void onTabReselected(TabLayout.Tab tab) {

        }
    }

    /*
     * Wishlist 상단 탭에 있는 특정 값을 선택하였을 때
     * Switch문으로 경우를 나누어
     * 찜 버튼을  눌렀을 때, wishlistItemFragment로 이동
     * 샵 버튼을 눌렀을 때, wishlistShopFragment로 이동
     * 최근 본 상품 버튼을 눌렀을때, wishlistRecentlyViewedItemFragment로 이동
     */
    class WishlistTopItemSelectedListener implements TabLayout.OnTabSelectedListener{

        @Override
        public void onTabSelected(TabLayout.Tab tab) {
            FragmentTransaction transaction=fragmentManager.beginTransaction(); // FragmentTransaction 가져오기
            int position=tab.getPosition(); // 상단탭의 선택된 현재 위치 받아오기
            switch(position) {
                case 0:
                    transaction.replace(R.id.fragment_container, wishlistItemFragment).commit();
                    break;
                case 1:
                    transaction.replace(R.id.fragment_container, wishlistShopFragment).commit();
                    break;
                case 2:
                    transaction.replace(R.id.fragment_container, wishlistRecentlyViewedItemFragment).commit();
                    break;
            }

        }

        @Override
        public void onTabUnselected(TabLayout.Tab tab) {

        }

        @Override
        public void onTabReselected(TabLayout.Tab tab) {

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
