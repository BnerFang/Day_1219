package com.fsk.day_1219.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.fsk.day_1219.R;
import com.fsk.day_1219.bean.RightBean;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;


public class MyGoodsRightAdapter extends RecyclerView.Adapter<MyGoodsRightAdapter.MyGoodsViewHolder> {
    private Context mContext;
    private List<RightBean.DataBean.ListBean> mListBeans = new ArrayList<>();

    public MyGoodsRightAdapter(Context context, List<RightBean.DataBean.ListBean> listBeans) {
        mContext = context;
        mListBeans = listBeans;
    }

    //更新适配器
    public void setData(List<RightBean.DataBean.ListBean> listBeans){
        mListBeans = listBeans;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public MyGoodsViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        //布局
        View view = LayoutInflater.from(mContext).inflate(R.layout.right_goods_view, viewGroup, false);
        return new MyGoodsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyGoodsViewHolder myGoodsViewHolder, int i) {
        Glide.with(mContext).load(mListBeans.get(i).getIcon()).into(myGoodsViewHolder.mCircleImageViewImg);
        myGoodsViewHolder.mTextViewName.setText(mListBeans.get(i).getName());
    }

    @Override
    public int getItemCount() {
        return mListBeans == null ? 0 : mListBeans.size();
    }

    //自定义viewholder
    class MyGoodsViewHolder extends RecyclerView.ViewHolder{
        CircleImageView mCircleImageViewImg;
        TextView mTextViewName;
        public MyGoodsViewHolder(@NonNull View itemView) {
            super(itemView);
            mCircleImageViewImg = itemView.findViewById(R.id.goods_c_imgicon);
            mTextViewName = itemView.findViewById(R.id.goods_right_txt_name);
        }
    }
}
