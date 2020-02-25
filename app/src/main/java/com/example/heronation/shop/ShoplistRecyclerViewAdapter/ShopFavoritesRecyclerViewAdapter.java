package com.example.heronation.shop.ShoplistRecyclerViewAdapter;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.heronation.R;
import com.example.heronation.shop.ShopWebViewActivity;
import com.example.heronation.shop.ShoplistRecyclerViewAdapter.dataClass.ShopFavoritesContent;

import java.util.ArrayList;
import java.util.List;

public class ShopFavoritesRecyclerViewAdapter extends RecyclerView.Adapter<ShopFavoritesRecyclerViewAdapter.Holder> {

    private Context context;
    private List<ShopFavoritesContent> shopContent = new ArrayList<>();

    public ShopFavoritesRecyclerViewAdapter(Context context, List<ShopFavoritesContent> shop_list) {
        this.context = context;
        this.shopContent = shop_list;
    }

    /* viewType 형태의 아이템 뷰를 위한 뷰홀더 객체 생성*/
    @NonNull
    @Override
    public ShopFavoritesRecyclerViewAdapter.Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.shop_list_item, parent, false);
        Holder holder = new Holder(view);
        return holder;
    }

    /* position에 해당하는 데이터를 뷰홀더의 아이템뷰에 표시 */
    @Override
    public void onBindViewHolder(@NonNull final ShopFavoritesRecyclerViewAdapter.Holder holder, int position) {
        holder.shop_ranking.setText(shopContent.get(position).getId().toString());
        holder.shop_name.setText(shopContent.get(position).getName());
        holder.shop_link.setText(shopContent.get(position).getUrl());
        //태그값은 찜목록에 저장하지 않으므로 없애줌
        holder.shop_tag.setVisibility(View.GONE);

        /* Glide를 통해 URL로 받아온 이미지를 로드해서 뷰홀더에 있는 이미지뷰에 뿌려줌
        int item_position=position;
        Glide.with(context).load(shop_list.get(item_position).getShop_image1()).error(R.drawable.shop_img1).crossFade().into(holder.shop_img1);
         */

        // 찜목록이므로, 별모양 버튼이 선택된 상태
        holder.favorite_button.setSelected(true);

        holder.favorite_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (holder.favorite_button.isSelected() == false) { //별이 선택되었을 경우, 찜목록 추가
                    holder.favorite_button.setSelected(true);

                } else {
                    holder.favorite_button.setSelected(false); //별이 선택되지 않았을 경우, 찜목록 삭제
                }
            }
        });

    }


    /* 전체 아이템 개수를 return */
    @Override
    public int getItemCount() {
        return shopContent.size();
    }

    /* 뷰홀더 데이터가 놓일 공간을 마련해준다. */
    public class Holder extends RecyclerView.ViewHolder {
        public TextView shop_ranking;
        public TextView shop_name;
        public TextView shop_tag;
        public TextView shop_link;
        public ImageButton favorite_button;

        public Holder(View view) {
            super(view);
            shop_ranking = (TextView) view.findViewById(R.id.shop_ranking_shop_ranking);
            shop_name = (TextView) view.findViewById(R.id.shop_ranking_shop_name);
            shop_tag = (TextView) view.findViewById(R.id.shop_ranking_shop_tag);
            favorite_button = (ImageButton) view.findViewById(R.id.favorite_button);
            shop_link = (TextView) view.findViewById(R.id.shop_ranking_site_link);

            //특정 아이템이 클릭되었을 때 아이템의 쇼핑몰을 웹뷰로 띄워줌
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position = getAdapterPosition();
                    if (position != RecyclerView.NO_POSITION) {
                        Intent intent = new Intent(context, ShopWebViewActivity.class);
                        intent.putExtra("shop_link", shop_link.getText().toString());
                        context.startActivity(intent);
                    }
                }
            });
        }
    }
}
