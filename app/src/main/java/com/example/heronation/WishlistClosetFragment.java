package com.example.heronation;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import java.util.ArrayList;


public class WishlistClosetFragment extends Fragment {
    /* 리사이클러뷰*/
    private RecyclerView closet_recyclerView;
    private ArrayList<ClosetItem> item_list=new ArrayList<>();

    /* 스피너 */
    private Spinner spinner_category;

    /* 체형 수정 버튼 */
    private Button edit_button;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        item_list.clear();
        this.make_item_list();
        // Inflate the layout for this fragment
        ViewGroup rootView=(ViewGroup)inflater.inflate(R.layout.fragment_wishlist_closet, container,false);

        closet_recyclerView=(RecyclerView)rootView.findViewById(R.id.recycler_view_wishilist_closet);
        /* 리사이클러뷰 객체 생성 */
        WishlistClosetAdapter wishlistClosetAdapter=new WishlistClosetAdapter(getActivity(),item_list);
        /* 레이아웃 매니저 수평으로 지정 */
        closet_recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL,false));
        /* 리사이클러뷰에 어댑터 지정 */
        closet_recyclerView.setAdapter(wishlistClosetAdapter);

        /*스피너 */
        spinner_category=(Spinner)rootView.findViewById(R.id.wishlist_closet_spinner_category);
        //spinnerArray.xml에서 생성한 item을 String 배열로 가져오기
        String[] str_category=getResources().getStringArray(R.array.spinnerArray_category);

        //item_new_spinner_item과 str_category, str_order를 인자로 어댑터를 생성하고, 어댑터를 설정
        ArrayAdapter<String> adapter_category=new ArrayAdapter<String>(getContext(), R.layout.item_new_spinner_item,str_category);
        adapter_category.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        spinner_category.setAdapter(adapter_category);

        /* 체형 수정 버튼 */
        edit_button=(Button)rootView.findViewById(R.id.wishlist_closet_edit_button);
        edit_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getContext(),WishlistClosetEditBodyActivity.class);
                startActivity(intent);
            }
        });

        return rootView;
    }

    // 등록된 item 정보를 만드는 함수
    public void make_item_list(){
        item_list.add(new ClosetItem("티셔츠","니트","2019/10/20","육육걸즈","AR"));
        item_list.add(new ClosetItem("하의","슬랙스","2019/10/21","고고싱","직접 입력"));
        item_list.add(new ClosetItem("하의","청바지","2019/10/22","유니클로","신체 비교"));
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
