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

        shop_list.add(new Shop("1","크림치즈마켓","#20대 #심플베이직 #러블리",
                "https://creamcheese.co.kr/web/product/extra/big/20200103/a6f044e55e57a52499d86d8d52fbbe97.jpg",
                "https://creamcheese.co.kr/web/product/extra/big/201910/771a37dd6951ee991d401d58000999d6.jpeg",
                "https://creamcheese.co.kr/web/product/extra/big/201908/4bb1ddaaaacbbc33aef005355888877a.jpeg"));


        shop_list.add(new Shop("2","기프티박스","#10대 #20대 #심플베이직 #페미닌",
                "https://shop-phinf.pstatic.net/20191125_109/1574647089099J0mEe_JPEG/12007827730061169_1986553615.jpg?type=f260",
                "https://shop-phinf.pstatic.net/20191218_109/1576635660713BY1b0_JPEG/13997203139003091_948360031.jpg?type=f260",
                "https://shop-phinf.pstatic.net/20191217_209/1576546329655vHwlr_JPEG/13907068283633976_1742837673.jpg?type=f260"));

        shop_list.add(new Shop("3","슬로우베리","#20대 #페미닌 #러블리",
                "https://dthumb-phinf.pstatic.net/?src=%22https%3A%2F%2Fscontent.cdninstagram.com%2Fv%2Ft51.2885-15%2Fsh0.08%2Fe35%2Fs640x640%2F79378202_260690368242068_8567282746947102671_n.jpg%3F_nc_ht%3Dscontent.cdninstagram.com%26_nc_ohc%3DZoVtD8ke2sAAX-FPnlo%26oh%3D42f1d4055d2b254a8b4943f424e032cd%26oe%3D5E947627%22&twidth=353&theight=353&opts=12",
                "https://dthumb-phinf.pstatic.net/?src=%22https%3A%2F%2Fscontent.cdninstagram.com%2Fv%2Ft51.2885-15%2Fsh0.08%2Fe35%2Fs640x640%2F72231290_2685527241536547_5781413796061254177_n.jpg%3F_nc_ht%3Dscontent.cdninstagram.com%26_nc_ohc%3D-jjghygC95YAX8k-s4G%26oh%3Dd6d0641caa41c87017d3de6fffd8c0ff%26oe%3D5E8D703C%22&twidth=353&theight=353&opts=12",
                "https://dthumb-phinf.pstatic.net/?src=%22https%3A%2F%2Fscontent.cdninstagram.com%2Fv%2Ft51.2885-15%2Fsh0.08%2Fe35%2Fs640x640%2F79981404_585579498666535_1326040980112499335_n.jpg%3F_nc_ht%3Dscontent.cdninstagram.com%26_nc_ohc%3DWeUgSvZAwrUAX-uhnLM%26oh%3D5f0e9cc45ae817f461b9ca1983d61ec8%26oe%3D5EAA85DD%22&twidth=353&theight=353&opts=12"));

        shop_list.add(new Shop("4","코튼로그","#20대 #심플베이직 #러블리",
                "https://shop-phinf.pstatic.net/20191125_109/1574647089099J0mEe_JPEG/12007827730061169_1986553615.jpg?type=f260",
                "https://shop-phinf.pstatic.net/20191218_109/1576635660713BY1b0_JPEG/13997203139003091_948360031.jpg?type=f260",
                "https://shop-phinf.pstatic.net/20191217_209/1576546329655vHwlr_JPEG/13907068283633976_1742837673.jpg?type=f260"));



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
