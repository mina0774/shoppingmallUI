package com.example.heronation;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import java.util.ArrayList;

public class ItemNewFragment extends Fragment {

    private RecyclerView item_recyclerView1;
    private RecyclerView item_recyclerView2;
    private RecyclerView item_recyclerView_grid;

    private ItemVerticalAdapter newAdapter1;
    private ArrayList<ShopItemPackage> item_list1=new ArrayList<>();

    private ItemVerticalAdapter newAdapter2;
    private ArrayList<ShopItemPackage> item_list2=new ArrayList<>();

    private ItemNewAdapter newAdapter3;
    private ArrayList<ShopItemPackage> item_list3=new ArrayList<>();

    /* 배너 슬라이딩을 위한 변수 */
    private ImageAdapter imageAdapter;
    private ViewPager viewPager;

    /* 스피너 */
    private Spinner spinner_category;
    private Spinner spinner_order;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        item_list1.clear();
        item_list2.clear();
        item_list3.clear();
        /* 아이템(상품) 추가 */
        this.make_item_list();

        // Inflate the layout for this fragment
        ViewGroup rootView=(ViewGroup)inflater.inflate(R.layout.fragment_item_new,container,false);

        /* 첫번째 리사이클러뷰*/
        item_recyclerView1=(RecyclerView)rootView.findViewById(R.id.item_new_recyclerView1);
        /* 아이템 수직 리사이클러뷰 객체 생성 */
        newAdapter1=new ItemVerticalAdapter(item_list1,getActivity()); //New Adapter 안에 horizontal adapter를 선언하여 이에 대한 레이아웃을 horizontal로 지정
        /* 레이아웃 매니저 수직으로 지정 */
        item_recyclerView1.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false));
        item_recyclerView1.setAdapter(newAdapter1);

        /* 두번째 리사이클러뷰*/
        item_recyclerView2=(RecyclerView)rootView.findViewById(R.id.item_new_recyclerView2);
        /* 아이템 수직 리사이클러뷰 객체 생성 */
        newAdapter2=new ItemVerticalAdapter(item_list2,getActivity());  //New Adapter 안에 horizontal adapter를 선언하여 이에 대한 레이아웃을 horizontal로 지정
        /* 레이아웃 매니저 수직으로 지정 */
        item_recyclerView2.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false));
        item_recyclerView2.setAdapter(newAdapter2);

        /* 세번째 리사이클러뷰*/
        item_recyclerView_grid=(RecyclerView)rootView.findViewById(R.id.item_new_recyclerView_grid);
        /* 아이템 수직 리사이클러뷰 객체 생성 */
        newAdapter3=new ItemNewAdapter(item_list3,getActivity()); //New Adapter 안에 horizontal adapter를 선언하여 이에 대한 레이아웃을 Grid로 지정
        /* 레이아웃 매니저 수직으로 지정 */
        item_recyclerView_grid.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false));
        item_recyclerView_grid.setAdapter(newAdapter3);

        /*스피너 */
        spinner_category=(Spinner)rootView.findViewById(R.id.item_new_spinner1);
        spinner_order=(Spinner)rootView.findViewById(R.id.item_new_spinner2);

        //spinnerArray.xml에서 생성한 item을 String 배열로 가져오기
        String[] str_category=getResources().getStringArray(R.array.spinnerArray_category);
        String[] str_order=getResources().getStringArray(R.array.spinnerArray_order);

        //item_new_spinner_item과 str_category, str_order를 인자로 어댑터를 생성하고, 어댑터를 설정
        ArrayAdapter<String> adapter_category=new ArrayAdapter<String>(getContext(),R.layout.item_new_spinner_item,str_category);
        ArrayAdapter<String> adapter_order=new ArrayAdapter<String>(getContext(),R.layout.item_new_spinner_item,str_order);
        adapter_category.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        adapter_order.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        spinner_category.setAdapter(adapter_category);
        spinner_order.setAdapter(adapter_order);

        /* 이미지 슬라이딩을 위해 뷰페이저를 이용했고, 이를 설정해주는 이미지 어댑터를 설정하여 슬라이딩 구현 */
        viewPager=(ViewPager)rootView.findViewById(R.id.image_view_new);
        imageAdapter=new ImageAdapter(getActivity());
        viewPager.setAdapter(imageAdapter);

        return rootView;
    }

    public void make_item_list(){
        /* 아이템(상품) 추가 */
        ArrayList<ShopItem> shopItem1=new ArrayList<>();
        shopItem1.add(new ShopItem("https://slowand.com/web/product/medium/20191231/19123abae92c3f10204863e9d4bba5b9.webp",
                "버터 케이블 가디건", "슬로우앤드", 30000, 25000));
        shopItem1.add(new ShopItem("https://slowand.com/web/product/medium/20200106/d43bb6f547046b1924d113cd1eef352c.webp",
                "마리 세미크롭 펀칭니트", "슬로우앤드", 53000, 45000));
        shopItem1.add(new ShopItem("https://slowand.com/web/product/medium/201911/9f06ecd9233c6627262923c3e0d56c14.gif",
                "핸드메이드 코트", "고고싱", 63000, 60000));
        shopItem1.add(new ShopItem("https://slowand.com/web/product/medium/20191209/9b76134f5337b694553eb9fb190961b3.gif",
                "빈티지 노르딕 가디건", "고고싱", 40000, 31000));
        shopItem1.add(new ShopItem("https://slowand.com/web/product/medium/20191231/4ef6dd9fedd4a31ed7d56d427fc90b67.webp",
                "오트밀 세인트 핸드메이드 코트", "고고싱", 53000, 50000));
        // 상품들 묶음 추가
        item_list1.add(new ShopItemPackage("급상승",shopItem1));

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
        item_list2.add(new ShopItemPackage("신상품 best",shopItem2));

        // 수정필요
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
        item_list3.add(new ShopItemPackage("",shopItem3));
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
