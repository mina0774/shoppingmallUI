package com.example.heronation;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;

import java.util.ArrayList;


public class ShopRankingFragment extends Fragment {
    private RecyclerView shop_recyclerView;
    private ArrayList<Shop> shop_list=new ArrayList<>();
    private ImageButton filter_button;
    private Button search_button;

    /* 배너 슬라이딩을 위한 변수 */
    private ImageAdapter imageAdapter;
    private ViewPager viewPager;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        shop_list.clear();
        /* Shop 목록을 생성함 */
        this.make_shop_list();

        // Inflate the layout for this fragment
        ViewGroup rootView=(ViewGroup)inflater.inflate(R.layout.fragment_shop_ranking, container,false);
        shop_recyclerView=(RecyclerView)rootView.findViewById(R.id.recycler_view_shop_ranking);


        /* 리사이클러뷰 객체 생성 */
        ShopRecyclerViewAdapter shopRecyclerViewAdapter=new ShopRecyclerViewAdapter(getActivity(),shop_list);
        /* 레이아웃 매니저 수평으로 지정 */
        shop_recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL,false));
        /* 리사이클러뷰에 어댑터 지정 */
        shop_recyclerView.setAdapter(shopRecyclerViewAdapter);

        /* 필터 버튼
         *  필터 버튼을 눌렀을 때, 팝업창을 띄어줌
         */
        filter_button=(ImageButton)rootView.findViewById(R.id.shop_ranking_filter);
        filter_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((MainActivity)getActivity()).open_panel();
            }
        });

        /* 검색 버튼
         *  검색 버튼을 눌렀을 때 Shop 검색 Activity로 이동*/
        search_button=(Button)rootView.findViewById(R.id.shop_ranking_search);
        search_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getContext(),ShopRankingSearchActivity.class);
                startActivity(intent);
            }
        });

        /* 이미지 슬라이딩을 위해 뷰페이저를 이용했고, 이를 설정해주는 이미지 어댑터를 설정하여 슬라이딩 구현 */
        viewPager=(ViewPager)rootView.findViewById(R.id.image_view_shop_ranking);
        imageAdapter=new ImageAdapter(getActivity());
        viewPager.setAdapter(imageAdapter);

        return rootView;
    }

    public void make_shop_list(){
        /* Shop 목록을 생성함 */

        shop_list.add(new Shop("1", "크림치즈마켓", "#20대 #심플베이직 #러블리",
                "https://image.brandi.me/cproduct/2019/10/12/11088968_1570884084_image1_M.jpg",
                "https://image.brandi.me/cproduct/2020/01/10/13113748_1578653315_image1_M.jpg",
                "https://image.brandi.me/cproduct/2019/12/11/12537914_1576060273_image1_M.jpg"));
        shop_list.add(new Shop("2","기프티박스","#10대 #20대 #심플베이직 #페미닌",
                "https://image.brandi.me/cproduct/2019/12/22/12767136_1577026535_image1_M.jpg",
                "https://image.brandi.me/cproduct/2019/10/29/11520797_1572357010_image1_M.jpg",
                "https://image.brandi.me/cproduct/2019/11/15/11911973_1573813700_image1_M.jpg"));

        shop_list.add(new Shop("3","슬로우베리","#20대 #페미닌 #러블리",
                "https://image.brandi.me/cproduct/2019/12/13/12568814_1576222909_image1_M.jpg",
                "https://image.brandi.me/cproduct/2019/12/13/12541553_1576165240_image1_M.jpg",
                "https://image.brandi.me/cproduct/2019/11/21/12087816_1574321010_image1_M.jpg"));
        shop_list.add(new Shop("4","코튼로그","#20대 #심플베이직 #러블리",
                "https://image.brandi.me/cproduct/2019/09/18/10623670_1568797090_image1_M.jpg",
                "https://image.brandi.me/cproduct/2019/12/23/12782275_1577094166_image1_M.jpg",
                "https://image.brandi.me/cproduct/2019/12/23/12775332_1577083651_image1_M.jpg"));



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

    /* Acitivity와 Fragment가 통신할 때, OnFragmentInteractionListener를 사용함.
     * 프래그먼트에서 액티비티로 통신(데이터 주고 받는 것)이 있을 수도 있기 때문에
     * MainActivity 에서 이를 implement한 후 오버라이딩 시켜줄 것이다. (임시로)
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
