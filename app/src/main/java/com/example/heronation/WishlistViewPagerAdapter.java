package com.example.heronation;

import android.os.Parcelable;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.fragment.app.FragmentStatePagerAdapter;

public class WishlistViewPagerAdapter extends FragmentPagerAdapter {
    private int mPageCount;

    /*
     * 생성자를 통해서 프래그먼트 관리를 도와주는 FragmentManager와
     * 페이지의 개수를 탭의 개수와 맞춰주기 위해 Page Count를 받아온다.
     */
    public WishlistViewPagerAdapter(@NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);
        this.mPageCount=behavior;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch(position){
            case 0:
                WishlistClosetFragment wishlistClosetFragment=new WishlistClosetFragment();
                return wishlistClosetFragment;
            case 1:
                WishlistItemFragment wishlistItemFragment=new WishlistItemFragment();
                return wishlistItemFragment;
            case 2:
                WishlistShopFragment wishlistShopFragment=new WishlistShopFragment();
                return wishlistShopFragment;
            case 3:
                WishlistRecentlyViewedItemFragment wishlistRecentlyViewedItemFragment=new WishlistRecentlyViewedItemFragment();
                return wishlistRecentlyViewedItemFragment;
        }
        return null;
    }


    @Override
    public int getCount() {
        return mPageCount;
    }

    @Override
    public Parcelable saveState() {
        return null;
    }
}
