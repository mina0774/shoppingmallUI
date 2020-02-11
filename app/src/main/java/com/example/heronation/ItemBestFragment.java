package com.example.heronation;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

public class ItemBestFragment extends Fragment {
    private RecyclerView category_recyclerView;
    private RecyclerView item_recyclerView;
    private ItemBestCategoryAdapter itemBestCategoryAdapter;
    private ItemVerticalAdapter verticalAdapter;
    private ArrayList<ItemBestCategory> list=new ArrayList<>();
    private ArrayList<ShopItemPackage> item_list=new ArrayList<>();

    /* 배너 슬라이딩을 위한 변수 */
    private ImageAdapter imageAdapter;
    private ViewPager viewPager;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        /* 카테고리 리스트에 아이템 추가
         *  여기서 카테고리 이름이나, 이미지 변경하면 됨*/
        this.make_category();

        /* 아이템(상품) 추가 */
        this.make_item_list();

        // Inflate the layout for this fragment
        ViewGroup rootView=(ViewGroup)inflater.inflate(R.layout.fragment_item_best,container,false);
        category_recyclerView=(RecyclerView)rootView.findViewById(R.id.item_best_item_category);

        /* 리사이클러뷰 객체 생성 */
        itemBestCategoryAdapter=new ItemBestCategoryAdapter(getActivity(),list);
        /* 레이아웃 매니저 수평으로 지정 */
        category_recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL,false));
        /* 리사이클러뷰에 어댑터 지정 */
        category_recyclerView.setAdapter(itemBestCategoryAdapter);

        /*
         * (ex)  수평 리사이클러뷰
         *       수평 리사이클러뷰
         *       수평 리사이클러뷰
         *  3개의 수평 리사이클러뷰가 보여서 수직 리사이클러뷰가 됨
         * */
        item_recyclerView=(RecyclerView)rootView.findViewById(R.id.item_best_items);

        /* 아이템 수직 리사이클러뷰 객체 생성 */
        verticalAdapter=new ItemVerticalAdapter(item_list,getActivity());
        /* 레이아웃 매니저 수직으로 지정 */
        item_recyclerView.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false));
        item_recyclerView.setAdapter(verticalAdapter);

        /* 이미지 슬라이딩을 위해 뷰페이저를 이용했고, 이를 설정해주는 이미지 어댑터를 설정하여 슬라이딩 구현 */
        viewPager=(ViewPager)rootView.findViewById(R.id.image_view_best);
        imageAdapter=new ImageAdapter(getActivity());
        viewPager.setAdapter(imageAdapter);

        return rootView;
    }

    public void make_item_list(){
        /* 아이템(상품) 추가
        ArrayList<ShopItem> shopItem1=new ArrayList<>();
        shopItem1.add(new ShopItem("https://www.ggsing.com/web/product/medium/20191122/31de60c9a2096b6bf648d111684eacb7.gif",
                "앙고라 머플러 반코트", "고고싱", 53000, 50000));
        shopItem1.add(new ShopItem("https://www.ggsing.com/web/product/medium/20191128/87b5a0ae6e03d0977e58b787bab5d1ac.gif",
                "떡볶이 코트", "고고싱", 53000, 45000));
        shopItem1.add(new ShopItem("https://www.ggsing.com/web/product/medium/201910/ec8129532e1a12ff2728d6c45ba51d39.gif",
                "핸드메이드 코트", "고고싱", 63000, 60000));
        shopItem1.add(new ShopItem("https://www.ggsing.com/web/product/medium/201911/1d060a6ef06b95fcff02f2237d661f82.gif",
                "앙고라 머플러 반코트", "고고싱", 53000, 50000));
        shopItem1.add(new ShopItem("https://www.ggsing.com/web/product/medium/201910/ec8129532e1a12ff2728d6c45ba51d39.gif",
                "앙고라 머플러 반코트", "고고싱", 53000, 50000));
        // 상품들 묶음 추가
        item_list.add(new ShopItemPackage("All best",shopItem1));

        ArrayList<ShopItem> shopItem2=new ArrayList<>();
        shopItem2.add(new ShopItem("https://www.ggsing.com/web/product/medium/20191122/31de60c9a2096b6bf648d111684eacb7.gif",
                "앙고라머플러반코트", "고고싱", 53000, 50000));
        shopItem2.add(new ShopItem("https://www.ggsing.com/web/product/medium/20191128/87b5a0ae6e03d0977e58b787bab5d1ac.gif",
                "떡볶이코트", "고고싱", 53000, 50000));
        shopItem2.add(new ShopItem("https://www.ggsing.com/web/product/medium/201910/ec8129532e1a12ff2728d6c45ba51d39.gif",
                "떡볶이코트", "고고싱", 53000, 50000));
        shopItem2.add(new ShopItem("https://www.ggsing.com/web/product/medium/201911/1d060a6ef06b95fcff02f2237d661f82.gif",
                "앙고라머플러반코트", "고고싱", 53000, 50000));
        shopItem2.add(new ShopItem("https://www.ggsing.com/web/product/medium/201910/ec8129532e1a12ff2728d6c45ba51d39.gif",
                "앙고라머플러반코트", "고고싱", 53000, 50000));
        // 상품들 묶음 추가
        item_list.add(new ShopItemPackage("상의 best",shopItem2));

        ArrayList<ShopItem> shopItem3=new ArrayList<>();
        shopItem3.add(new ShopItem("https://www.ggsing.com/web/product/medium/20191122/31de60c9a2096b6bf648d111684eacb7.gif",
                "앙고라머플러반코트", "고고싱", 53000, 50000));
        shopItem3.add(new ShopItem("https://www.ggsing.com/web/product/medium/20191128/87b5a0ae6e03d0977e58b787bab5d1ac.gif",
                "앙고라머플러반코트", "고고싱", 53000, 50000));
        shopItem3.add(new ShopItem("https://www.ggsing.com/web/product/medium/201910/ec8129532e1a12ff2728d6c45ba51d39.gif",
                "앙고라머플러반코트", "고고싱", 53000, 50000));
        shopItem3.add(new ShopItem("https://www.ggsing.com/web/product/medium/201911/1d060a6ef06b95fcff02f2237d661f82.gif",
                "앙고라머플러반코트", "고고싱", 53000, 50000));
        shopItem3.add(new ShopItem("https://www.ggsing.com/web/product/medium/201910/ec8129532e1a12ff2728d6c45ba51d39.gif",
                "앙고라머플러반코트", "고고싱", 53000, 50000));
        // 상품들 묶음 추가
        item_list.add(new ShopItemPackage("하의 best",shopItem3));

         */
    }


    public void make_category(){
        /* 카테고리 리스트에 아이템 추가
         *  여기서 카테고리 이름이나, 이미지 변경하면 됨
         */
        addItem(getResources().getDrawable(R.drawable.ic_item_all),"전체");
        addItem(getResources().getDrawable(R.drawable.ic_all_tshirts),"상의");
        addItem(getResources().getDrawable(R.drawable.ic_all_pants),"하의");
        addItem(getResources().getDrawable(R.drawable.ic_all_outer),"아우터");
        addItem(getResources().getDrawable(R.drawable.ic_all_onepiece),"원피스");
        addItem(getResources().getDrawable(R.drawable.ic_all_skirt),"스커트");
        addItem(getResources().getDrawable(R.drawable.ic_all_shoes),"슈즈");
        addItem(getResources().getDrawable(R.drawable.ic_all_bag),"가방");
        addItem(getResources().getDrawable(R.drawable.ic_all_acc),"액세서리");
        addItem(getResources().getDrawable(R.drawable.ic_all_socks),"패션소품");
    }

    public void addItem(Drawable icon, String name){
        ItemBestCategory item=new ItemBestCategory(icon,name);
        list.add(item);
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}