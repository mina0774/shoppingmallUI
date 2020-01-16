package com.example.heronation;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


public class WishlistItemFragment extends Fragment {

    /* 찜한 아이템이 없을 시에 MeasurementFragment로 이동하기 위해 필요한 버튼 */
    private Button wishlist_style_recommendation;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        ViewGroup rootView=(ViewGroup)inflater.inflate(R.layout.fragment_wishlist_item, container,false);

         /* 찜한 아이템이 없을 시에 MeasurementFragment로 이동*/
        wishlist_style_recommendation=(Button)rootView.findViewById(R.id.wishlist_item_togo_measurement);
        wishlist_style_recommendation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((MainActivity)getActivity()).go_to_measurement();
            }
        });
        return rootView;
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
