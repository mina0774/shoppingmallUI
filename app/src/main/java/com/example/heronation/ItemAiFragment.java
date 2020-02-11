package com.example.heronation;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.core.widget.NestedScrollView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class ItemAiFragment extends Fragment {
    private RecyclerView item_recyclerView1;
    private ArrayList<ShopItemPackage> item_list=new ArrayList<>();
    private ItemVerticalAdapter verticalAdapter1;


    /* 배너 슬라이딩을 위한 변수 */
    private ImageAdapter imageAdapter;
    private ViewPager viewPager;

    private NestedScrollView nested_item_ai;
    /* 상품 리스트 묶음 번호 */
    private Integer package_num;
    /* 상품 리스트 묶음 이름의 리스트 */
    private ArrayList<String> package_name_list=new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        ViewGroup rootView=(ViewGroup)inflater.inflate(R.layout.fragment_item_ai,container,false);
        /* 수직 리사이클러뷰의 하나의 아이템에 수평 리사이클러뷰의 아이템을 수평 방향으로 배치 설정, 어댑터 지정
         * (ex)  수평 리사이클러뷰
         *       수평 리사이클러뷰
         *       수평 리사이클러뷰
         * */
        item_recyclerView1=(RecyclerView)rootView.findViewById(R.id.item_ai_recyclerViewVertical1);
        nested_item_ai=(NestedScrollView)rootView.findViewById(R.id.nested_item_ai);
        /* 아이템 수직 리사이클러뷰 객체 생성 */
        verticalAdapter1=new ItemVerticalAdapter(item_list,getActivity());
        /* 레이아웃 매니저 수직으로 지정 */
        item_recyclerView1.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL,false));
        item_recyclerView1.setAdapter(verticalAdapter1);

        /* 상품 목록 리스트의 이름 리스트 생성*/
        package_name_list.add("스타일 추천");
        package_name_list.add("사이즈 추천");
        package_name_list.add("같은 사이즈 유저의 추천");
        package_name_list.add("비슷한 스타일 유저의 추천");
        loadItems(nested_item_ai,getActivity());


        /* 이미지 슬라이딩을 위해 뷰페이저를 이용했고, 이를 설정해주는 이미지 어댑터를 설정하여 슬라이딩 구현 */
        viewPager=(ViewPager)rootView.findViewById(R.id.image_view_ai);
        imageAdapter=new ImageAdapter(getActivity());
        viewPager.setAdapter(imageAdapter);

        return rootView;
    }

    /*Item의 정보를 얻는 함수*/
    public void GetItemInfo(Integer page_num,String package_name) {
        String authorization = "zeyo-api-key QVntgqTsu6jqt7hQSVpF7ZS8Tw==";
        String accept = "application/json";

        ItemHomeFragment.ItemInfoService itemInfoService = ServiceGenerator.createService(ItemHomeFragment.ItemInfoService.class);
        retrofit2.Call<ShopItemInfo> request = itemInfoService.ItemInfo(page_num,5,"id,asc","heronation","cafe24", authorization, accept);
        request.enqueue(new Callback<ShopItemInfo>() {
            @Override
            public void onResponse(Call<ShopItemInfo> call, Response<ShopItemInfo> response) {
                System.out.println("Response" + response.code());
                if(response.code()==200) {
                    //아이템의 데이터를 받는 리스트
                    ArrayList<ItemContent> item_info=new ArrayList<>();
                    ShopItemInfo shopItemInfo = response.body();
                    /* Shop 목록을 생성함 */
                    for(int i = 0; i<shopItemInfo.getContent().size(); i++){
                        item_info.add(shopItemInfo.getContent().get(i));
                    }
                    item_list.add(new ShopItemPackage(package_name,item_info));
                    verticalAdapter1.notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure(Call<ShopItemInfo> call, Throwable t) {
                System.out.println("error + Connect Server Error is " + t.toString());
            }
        });
    }

    //package 넘버가 page 넘버 (임의로 이렇게 구현해둠 변경 필요)
    /** 동적 로딩을 위한 NestedScrollView의 아래 부분을 인식 **/
    public void loadItems(NestedScrollView nestedScrollView, final Context context) {
        package_num=1;
        GetItemInfo(package_num,package_name_list.get(package_num-1));
        package_num+=1;
        GetItemInfo(package_num,package_name_list.get(package_num-1));
        nestedScrollView.setOnScrollChangeListener(new NestedScrollView.OnScrollChangeListener() {
            @Override
            public void onScrollChange(NestedScrollView v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
                if (scrollY == (v.getChildAt(0).getMeasuredHeight() - v.getMeasuredHeight())) {
                    if(package_num<4) {
                        package_num+=1;
                        GetItemInfo(package_num, package_name_list.get(package_num-1));
                    }
                }
            }
        });
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
