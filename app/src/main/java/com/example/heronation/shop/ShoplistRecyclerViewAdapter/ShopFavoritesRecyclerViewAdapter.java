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
import com.example.heronation.main.MainActivity;
import com.example.heronation.shop.ShopWebViewActivity;
import com.example.heronation.shop.ShoplistRecyclerViewAdapter.dataClass.ShopFavoritesContent;
import com.example.heronation.shop.topbarFragment.ShopRankingFragment;
import com.example.heronation.zeyoAPI.ServiceGenerator;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

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
                    RegisterShop(shopContent.get(holder.getAdapterPosition()).getId());
                } else {
                    holder.favorite_button.setSelected(false); //별이 선택되지 않았을 경우, 찜목록 삭제
                    DeleteShop(shopContent.get(holder.getAdapterPosition()).getId());
                }
            }
        });

    }

    //쇼핑몰 찜 등록하는 기능, Zeyo API 연동
    public void RegisterShop(Integer shop_id) {
        String authorization = "Bearer "+MainActivity.access_token;
        String accept = "application/json";
        String content_type = "application/json";

        ShopRankingFragment.ShopRegisterService shopRegisterService = ServiceGenerator.createService(ShopRankingFragment.ShopRegisterService.class);
        retrofit2.Call<String>  request = shopRegisterService.ShopRegister(shop_id,authorization,accept,content_type);
        request.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String>  call, Response<String>  response) {
                System.out.println("Response" + response.code());
                if(response.code()==204){
                    //등록 완료
                    backgroundThreadShortToast(context,"쇼핑몰 찜 등록이 완료되었습니다.");
                }else if(response.code()==401){
                    //로그인이 필요한 서비스입니다.
                    backgroundThreadShortToast(context,"로그인이 필요한 서비스입니다.");
                }else if(response.code()==500){
                    //이미 찜목록에 등록되어있습니다.
                    backgroundThreadShortToast(context,"이미 찜 목록에 등록된 쇼핑몰입니다.");
                }

            }
            @Override
            public void onFailure(Call<String>  call, Throwable t) {
                System.out.println("error + Connect Server Error is " + t.toString());
            }
        });
    }

    //쇼핑몰 찜 목록에서 삭제하는 기능
    public void DeleteShop(Integer shop_id){
        String authorization = "Bearer "+ MainActivity.access_token;
        String accept = "application/json";
        String content_type = "application/json";

        ShopRankingFragment.ShopDeleteService shopRegisterService = ServiceGenerator.createService(ShopRankingFragment.ShopDeleteService.class);
        retrofit2.Call<String> request = shopRegisterService.ShopDelete(shop_id,authorization,accept,content_type);
        request.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String>  call, Response<String> response) {
                System.out.println("Response" + response.code());
                if(response.code()==204){
                    //등록 완료
                    backgroundThreadShortToast(context,"쇼핑몰 찜 목록에서 삭제되었습니다.");
                }else if(response.code()==401){
                    //로그인이 필요한 서비스입니다.
                    backgroundThreadShortToast(context,"로그인이 필요한 서비스입니다.");
                }
            }
            @Override
            public void onFailure(Call<String> call, Throwable t) {
                System.out.println("error + Connect Server Error is " + t.toString());
            }
        });

    }

    //Toast는 비동기 태스크 내에서 처리할 수 없으므로, 메인 쓰레드 핸들러를 생성하여 toast가 메인쓰레드에서 생성될 수 있도록 처리해준다.
    public static void backgroundThreadShortToast(final Context context, final String msg) {
        if (context != null && msg != null) {
            new Handler(Looper.getMainLooper()).post(new Runnable() {
                @Override
                public void run() {
                    Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
                }
            });
        }
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
