package com.example.heronation;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class WishlistClosetItemEditActivity extends AppCompatActivity {
    public TextView category;
    public TextView item_name;
    public TextView date;
    public TextView shop_name;
    public TextView measurement_type;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wishlist_closet_item_edit);

        //옷장에서 선택한 리사이클러뷰의 아이템 정보를 받아오기 위한 인텐트
        Intent intent=getIntent();

        category=(TextView)findViewById(R.id.wishlist_closet_item_category);
        item_name=(TextView)findViewById(R.id.wishlist_closet_item_name);
        date=(TextView)findViewById(R.id.wishlist_closet_item_date);
        shop_name=(TextView)findViewById(R.id.wishlist_closet_item_shop_name);
        measurement_type=(TextView)findViewById(R.id.wishlist_closet_item_measurement_type);

        category.setText(intent.getStringExtra("category"));
        item_name.setText(intent.getStringExtra("item_name"));
        date.setText(intent.getStringExtra("date"));
        shop_name.setText(intent.getStringExtra("shop_name"));
        measurement_type.setText(intent.getStringExtra("measurement_type"));
    }

    //back 버튼 눌렀을 때, 액티비티 종료
    public void click_back_button(View view){
        finish();
    }
}
