package com.example.heronation;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import java.util.ArrayList;


public class ItemHomeFragment extends Fragment {
    private RecyclerView item_recyclerView;
    private ArrayList<ShopItemPackage> item_list=new ArrayList<>();
    private ItemVerticalAdapter verticalAdapter;

    /* 배너 슬라이딩을 위한 변수 */
    private ImageAdapter imageAdapter;
    private ViewPager viewPager;

    /* 검색창 */
    private EditText search_item;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        /* 아이템(상품) 추가 */
        this.make_item_list();

        // Inflate the layout for this fragment
        ViewGroup rootView=(ViewGroup)inflater.inflate(R.layout.fragment_item_home,container,false);

        /* 수직 리사이클러뷰의 하나의 아이템에 수평 리사이클러뷰의 아이템을 수평 방향으로 배치 설정, 어댑터 지정
         * (ex)  수평 리사이클러뷰
         *       수평 리사이클러뷰
         *       수평 리사이클러뷰
         * */
        item_recyclerView=(RecyclerView)rootView.findViewById(R.id.item_home_recyclerViewVertical1);
        verticalAdapter=new ItemVerticalAdapter(item_list,getActivity());
        item_recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL,false));
        item_recyclerView.setAdapter(verticalAdapter);


        /*  검색창 클릭했을 때, 아이템 검색 액티비티로 이동 */
        search_item=(EditText)rootView.findViewById(R.id.item_home_search_edittext);
        search_item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getContext(), ItemSearchActivity.class);
                startActivity(intent);
            }
        });

        /* 이미지 슬라이딩을 위해 뷰페이저를 이용했고, 이를 설정해주는 이미지 어댑터를 설정하여 슬라이딩 구현 */
        viewPager=(ViewPager)rootView.findViewById(R.id.image_view_home);
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
        item_list.add(new ShopItemPackage("신상품",shopItem1));

        ArrayList<ShopItem> shopItem2=new ArrayList<>();
        shopItem2.add(new ShopItem("https://shop-phinf.pstatic.net/20191125_109/1574647089099J0mEe_JPEG/12007827730061169_1986553615.jpg?type=f260",
                "떡볶이코트", "고고싱", 53000, 50000));
        shopItem2.add(new ShopItem( "https://shop-phinf.pstatic.net/20191218_109/1576635660713BY1b0_JPEG/13997203139003091_948360031.jpg?type=f260",
                "앙고라머플러반코트", "고고싱", 53000, 50000));
        shopItem2.add(new ShopItem("https://shop-phinf.pstatic.net/20191217_209/1576546329655vHwlr_JPEG/13907068283633976_1742837673.jpg?type=f260",
                "앙고라머플러반코트", "고고싱", 53000, 50000));
        // 상품들 묶음 추가
        item_list.add(new ShopItemPackage("내 사이즈 추천",shopItem2));

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
        item_list.add(new ShopItemPackage("내 사이즈와 같은 회원의 인기상품",shopItem3));
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
