package com.example.heronation;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

public class WishlistClosetEditBodyActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wishlist_closet_edit_body);
    }

    //back 버튼 눌렀을 때, 액티비티 종료
    public void click_back_button(View view){
        finish();
    }
}
