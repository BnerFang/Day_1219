package com.fsk.day_1219.adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.fsk.day_1219.R;
import com.fsk.day_1219.bean.LeftBean;

import java.util.ArrayList;
import java.util.List;


public class MyLeftAdapter extends RecyclerView.Adapter<MyLeftAdapter.MyViewHolder> {

    private Context mContext;
    private List<LeftBean.DataBean> mDataBeans = new ArrayList<>();

    public MyLeftAdapter(Context context, List<LeftBean.DataBean> dataBeans) {
        mContext = context;
        mDataBeans = dataBeans;
    }

    //更新适配器
    public void update(List<LeftBean.DataBean> dataBeans) {
        this.mDataBeans = dataBeans;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        //布局
        View view = LayoutInflater.from(mContext).inflate(R.layout.left_layout_view, viewGroup, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder myViewHolder, final int i) {
        myViewHolder.mTextViewTitle.setText(mDataBeans.get(i).getName());
        myViewHolder.mTextViewTitle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mLeftCheckListener.onItemClick(i);
                Toast.makeText(mContext, "点击了:" + mDataBeans.get(i).getName(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return mDataBeans == null ? 0 : mDataBeans.size();
    }

    //自定义viewholder
    class MyViewHolder extends RecyclerView.ViewHolder {
        TextView mTextViewTitle;
        LinearLayout mLinearLayout;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            mTextViewTitle = itemView.findViewById(R.id.left_txt_title);
            mLinearLayout = itemView.findViewById(R.id.left_layout);
        }
    }

    private LeftCheckListener mLeftCheckListener;

    public void setLeftCheckListener(LeftCheckListener leftCheckListener) {
        mLeftCheckListener = leftCheckListener;
    }

    //自定义接口
    public interface LeftCheckListener {
        void onItemClick(int position);
    }
}
