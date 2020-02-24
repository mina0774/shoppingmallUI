package com.example.heronation.shop.ShoplistRecyclerViewAdapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.heronation.R;
import com.example.heronation.shop.ShoplistRecyclerViewAdapter.dataClass.ShopContent;
import com.example.heronation.shop.ShopWebViewActivity;

import java.util.ArrayList;
import java.util.List;

/* Shop들의 랭킹을 볼 수 있고, 이를 리사이클러뷰로 표현하기 위해
*  필요한 어댑터*/
public class ShopRecyclerViewAdapter extends RecyclerView.Adapter<ShopRecyclerViewAdapter.Holder> {

    private Context context;
    private List<ShopContent> shopContent =new ArrayList<>();

    public ShopRecyclerViewAdapter(Context context,List<ShopContent> shop_list) {
        this.context = context;
        this.shopContent = shop_list;
    }

    /* viewType 형태의 아이템 뷰를 위한 뷰홀더 객체 생성*/
    @NonNull
    @Override
    public ShopRecyclerViewAdapter.Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.shop_list_item,parent,false);
        Holder holder=new Holder(view);
        return holder;
    }

    /* position에 해당하는 데이터를 뷰홀더의 아이템뷰에 표시 */
    @Override
    public void onBindViewHolder(@NonNull final ShopRecyclerViewAdapter.Holder holder, int position) {
        holder.shop_ranking.setText(shopContent.get(position).getId().toString());
        holder.shop_name.setText(shopContent.get(position).getName());
        holder.shop_link.setText(shopContent.get(position).getUrl());

        String shop_tag="";

        for(int i = 0; i< shopContent.get(position).getAgeTagResponses().size(); i++){
            shop_tag+="#"+ shopContent.get(position).getAgeTagResponses().get(i).getName()+" ";
        }
        for(int i = 0; i< shopContent.get(position).getStyleTagResponses().size(); i++){
            shop_tag+="#"+ shopContent.get(position).getStyleTagResponses().get(i).getName()+" ";
        }

        holder.shop_tag.setText(shop_tag);

        /* Glide를 통해 URL로 받아온 이미지를 로드해서 뷰홀더에 있는 이미지뷰에 뿌려줌
        int item_position=position;
        Glide.with(context).load(shop_list.get(item_position).getShop_image1()).error(R.drawable.shop_img1).crossFade().into(holder.shop_img1);
        Glide.with(context).load(shop_list.get(item_position).getShop_image2()).error(R.drawable.shop_img2).crossFade().into(holder.shop_img2);
        Glide.with(context).load(shop_list.get(item_position).getShop_image3()).error(R.drawable.shop_img3).crossFade().into(holder.shop_img3);
         */

        /* 즐겨찾기 버튼 별 모양을 클릭했을 때,
        선택될 시에 사진을 노란색 별모양으로 설정
        선택되지 않을 시에 사진을 검은색 별모양으로 설정
        노란색 별모양일때 클릭하면 검은색 별모양
        검은색 별모양일때 클릭하면 노란색 별모양
        */

        holder.favorite_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(holder.favorite_button.isSelected()==false) { //별이 선택되었을 경우
                    holder.favorite_button.setSelected(true);
                } else{
                    holder.favorite_button.setSelected(false); //별이 선택되지 않았을 경우
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
    public class Holder extends RecyclerView.ViewHolder{
        public TextView shop_ranking;
        public TextView shop_name;
        public TextView shop_tag;
        public TextView shop_link;
   //     public ImageView shop_img1;
   //    public ImageView shop_img2;
   //     public ImageView shop_img3;
        public ImageButton favorite_button;

        public Holder(View view){
            super(view);
            shop_ranking=(TextView)view.findViewById(R.id.shop_ranking_shop_ranking);
            shop_name=(TextView)view.findViewById(R.id.shop_ranking_shop_name);
            shop_tag=(TextView)view.findViewById(R.id.shop_ranking_shop_tag);
   //         shop_img1=(ImageView)view.findViewById(R.id.shop_ranking_shop_img1);
   //         shop_img2=(ImageView)view.findViewById(R.id.shop_ranking_shop_img2);
   //         shop_img3=(ImageView)view.findViewById(R.id.shop_ranking_shop_img3);
            favorite_button=(ImageButton)view.findViewById(R.id.favorite_button);
            shop_link=(TextView)view.findViewById(R.id.shop_ranking_site_link);


            //특정 아이템이 클릭되었을 때 아이템에 대한 데이터 정보를 아이템 수정 페이지로 이동시켜줌
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position=getAdapterPosition();
                    if(position!=RecyclerView.NO_POSITION){
                        Intent intent=new Intent(context, ShopWebViewActivity.class);
                        intent.putExtra("shop_link",shop_link.getText().toString());
                        context.startActivity(intent);
                    }
                }
            });
        }
    }
}
