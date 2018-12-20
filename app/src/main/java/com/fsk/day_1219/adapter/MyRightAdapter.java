package com.fsk.day_1219.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.fsk.day_1219.R;
import com.fsk.day_1219.bean.RightBean;

import java.util.ArrayList;
import java.util.List;

public class MyRightAdapter extends RecyclerView.Adapter<MyRightAdapter.MyRightViewHolder> {
    private Context mContext;
    private List<RightBean.DataBean> mDataBeanList = new ArrayList<>();
    private MyGoodsRightAdapter mMyGoodsRightAdapter;

    public MyRightAdapter(Context context, List<RightBean.DataBean> dataBeans) {
        mContext = context;
        mDataBeanList = dataBeans;
    }

    //更新适配器
    public void update(List<RightBean.DataBean> dataBeans){
        this.mDataBeanList = dataBeans;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public MyRightViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        //布局
        View view = LayoutInflater.from(mContext).inflate(R.layout.right_layout_view, viewGroup, false);
        return new MyRightViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyRightViewHolder myRightViewHolder, int i) {
        myRightViewHolder.mTextViewTitle.setText(mDataBeanList.get(i).getName());
        List<RightBean.DataBean.ListBean> list = mDataBeanList.get(i).getList();
        //添加子布局适配器
        mMyGoodsRightAdapter = new MyGoodsRightAdapter(mContext, list);
        myRightViewHolder.mRecyclerView.setLayoutManager(new GridLayoutManager(mContext,3));//网格布局
        myRightViewHolder.mRecyclerView.setAdapter(mMyGoodsRightAdapter);
        mMyGoodsRightAdapter.setData(list);
    }

    @Override
    public int getItemCount() {
        return mDataBeanList == null ? 0 : mDataBeanList.size();
    }

    //自定义viewholder
    class MyRightViewHolder extends RecyclerView.ViewHolder{
        TextView mTextViewTitle;
        RecyclerView mRecyclerView;
        public MyRightViewHolder(@NonNull View itemView) {
            super(itemView);
            mRecyclerView = itemView.findViewById(R.id.right_rv);
            mTextViewTitle = itemView.findViewById(R.id.right_txt_title);
        }
    }
}
