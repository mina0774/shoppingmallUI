package com.example.heronation;

import android.animation.ValueAnimator;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.airbnb.lottie.LottieAnimationView;
import com.bumptech.glide.Glide;

import java.util.ArrayList;

/* 수평 리사이클러뷰를 위한 어댑터 */
public class ItemHorizontalAdapter extends RecyclerView.Adapter<ItemHorizontalAdapter.HorizontalViewHolder> {
    private ArrayList<ItemContent> itemList;
    /* glide를 통해 URL을 통해서 이미지를 받아올 때,
     * 현재 어떤 액태비티의 Context인지 알아야하므로, 이를 받아오기 위함
     * 생성자에서 받아줌*/
    private Context context;

    public ItemHorizontalAdapter(ArrayList<ItemContent> itemList, Context context){
        this.itemList=itemList;
        this.context=context;
    }

    /* viewType 형태의 아이템 뷰를 위한 뷰홀더 객체 생성*/
    @NonNull
    @Override
    public ItemHorizontalAdapter.HorizontalViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        /* 아이템 하나를 나타내는 xml파일을 뷰에 바인딩 */
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.shopitem_list_item,parent,false);
        /* 뷰홀더 객체 생성 */
        HorizontalViewHolder holder=new HorizontalViewHolder(view);
        return holder;
    }

    /* position에 해당하는 데이터를 뷰홀더의 아이템뷰에 표시 */
    @Override
    public void onBindViewHolder(@NonNull final HorizontalViewHolder holder, int position) {
        int item_position=position;
        /* Glide를 통해 URL로 받아온 이미지를 로드해서 뷰홀더에 있는 이미지뷰에 뿌려줌 */
        Glide.with(context).load(itemList.get(item_position).getShopImage()).error(R.drawable.shop_item_example_img_2).crossFade().into(holder.item_image);
        holder.item_name.setText(itemList.get(item_position).getName());
      //  holder.shop_name.setText(itemList.get(item_position).getShopName());
      //    holder.originalPrice.setText(itemList.get(item_position).getOriginalPrice().toString());
      //  holder.salePrice.setText(itemList.get(item_position).getSalePrice().toString());


        holder.heart_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!holder.isSongLikedClicked){
                    // 애니메이션을 한번 실행시킨다.
                    // Custom animation speed or duration.
                    // ofFloat(시작 시간, 종료 시간).setDuration(지속시간)
                    ValueAnimator animator = ValueAnimator.ofFloat(0f, 0.5f).setDuration(1000);
                    animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                        @Override
                        public void onAnimationUpdate(ValueAnimator animation) {
                            holder.heart_button.setProgress((Float) animation.getAnimatedValue());
                        }
                    });
                    animator.start();
                    holder.isSongLikedClicked = true;
                }
                else {
                    // 애니메이션을 한번 실행시킨다.
                    // Custom animation speed or duration.
                    // ofFloat(시작 시간, 종료 시간).setDuration(지속시간)
                    ValueAnimator animator = ValueAnimator.ofFloat(0.5f, 1f).setDuration(1000);
                    animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                        @Override
                        public void onAnimationUpdate(ValueAnimator animation) {
                            holder.heart_button.setProgress((Float) animation.getAnimatedValue());
                        }
                    });
                    animator.start();
                    holder.isSongLikedClicked = false;
                }

            }
        });
    }

    /* 전체 아이템 개수를 return */
    @Override
    public int getItemCount() {
        return itemList.size();
    }

    /* 뷰홀더 데이터가 놓일 공간을 마련해준다. */
    public class HorizontalViewHolder extends RecyclerView.ViewHolder{
        private ImageView item_image;
        private TextView item_name;
//        private TextView shop_name;
 //       private TextView originalPrice;
 //       private TextView salePrice;
        private LottieAnimationView heart_button;
        // 좋아요 클릭 여부
        private boolean isSongLikedClicked = false;

        public HorizontalViewHolder(View view){
            super(view);
            item_image=view.findViewById(R.id.recycler_view_item_best_item_image);
            item_name=view.findViewById(R.id.recycler_view_item_best_item_name);
    //        shop_name=view.findViewById(R.id.recycler_view_item_best_shop_name);
    //        originalPrice=view.findViewById(R.id.recycler_view_item_best_original_price);
    //        salePrice=view.findViewById(R.id.recycler_view_item_best_sale_price);
            heart_button=view.findViewById(R.id.heart_button);
        }

    }
}
