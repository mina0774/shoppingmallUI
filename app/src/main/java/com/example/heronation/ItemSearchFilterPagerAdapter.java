package com.example.heronation;

import android.os.Parcelable;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

public class ItemSearchFilterPagerAdapter extends FragmentPagerAdapter{
    private int mPageCount;
    /*
     * 생성자를 통해서 프래그먼트 관리를 도와주는 FragmentManager와
     * 페이지의 개수를 탭의 개수와 맞춰주기 위해 Page Count를 받아온다.
     */
    public ItemSearchFilterPagerAdapter(@NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);
        this.mPageCount=behavior;
    }


    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch(position){
            case 0:
                filter_price filter_price=new filter_price();
                return filter_price;
            case 1:
                filter_color filter_color=new filter_color();
                return filter_color;
            case 2:
                filter_category filter_category=new filter_category();
                return filter_category;
            case 3:
                filter_size filter_size=new filter_size();
                return filter_size;

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
