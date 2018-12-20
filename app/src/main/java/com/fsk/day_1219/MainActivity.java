package com.fsk.day_1219;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.fsk.day_1219.adapter.MyLeftAdapter;
import com.fsk.day_1219.adapter.MyRightAdapter;
import com.fsk.day_1219.bean.LeftBean;
import com.fsk.day_1219.bean.RightBean;
import com.fsk.day_1219.mvp.presenter.LeftPresenter;
import com.fsk.day_1219.mvp.presenter.RightPresenter;
import com.fsk.day_1219.mvp.view.LeftView;
import com.fsk.day_1219.mvp.view.RightView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements LeftView, RightView {

    private RecyclerView mLeftRv;
    private RecyclerView mRightRv;
    private MyLeftAdapter mMyLeftAdapter;
    private LeftPresenter mLeftPresenter;
    private List<LeftBean.DataBean> mDataBeans = new ArrayList<>();
    private List<RightBean.DataBean> mDataBeanList = new ArrayList<>();
    private MyRightAdapter mMyRightAdapter;
    private RightPresenter mRightPresenter;
    private int mCid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //初始化控件
        initView();
    }

    //初始化控件
    private void initView() {
        mLeftRv = (RecyclerView) findViewById(R.id.left_rv_view);
        mRightRv = (RecyclerView) findViewById(R.id.right_rv_view);
        mLeftPresenter = new LeftPresenter(this);//左侧 presenter层
        mLeftPresenter.leftDatas(mDataBeans);
        mRightPresenter = new RightPresenter(this);//右侧 presenter层
        mRightPresenter.rightDatas(mDataBeanList, 1);// uid 默认值为  1
        //线性布局管理器
        mLeftRv.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        //调用系统自带分割线
        mLeftRv.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        //线性布局管理器
        mRightRv.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        //调用系统自带分割线
        mRightRv.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
    }

    //左侧  访问成功接口回调
    @Override
    public void onLeftSuccess(List<LeftBean.DataBean> dataBeans) {
        // TODO: 2018/12/19 必须添加,否则空指针
        mDataBeans = dataBeans;
        mMyLeftAdapter = new MyLeftAdapter(this, dataBeans);
        mLeftRv.setAdapter(mMyLeftAdapter);
        mMyLeftAdapter.update(mDataBeans);
        mMyLeftAdapter.notifyDataSetChanged();
    }
    //左侧  访问失败接口回调
    @Override
    public void onLeftFailed(String error) {
        Toast.makeText(this, error, Toast.LENGTH_SHORT).show();
    }

    //右侧  访问成功接口回调
    @Override
    public void onRightSuccess(final List<RightBean.DataBean> list) {
        // TODO: 2018/12/19 必须添加,否则空指针
        mDataBeanList = list;
        mMyRightAdapter = new MyRightAdapter(this, list);
        mRightRv.setAdapter(mMyRightAdapter);
        mMyRightAdapter.update(mDataBeanList);
        mMyRightAdapter.notifyDataSetChanged();
        mMyLeftAdapter.setLeftCheckListener(new MyLeftAdapter.LeftCheckListener() {
            @Override
            public void onItemClick(int position) {
                for (int i = 0; i < mDataBeans.size(); i++) {
                    mDataBeans.get(i).setClick(true);//默认选中第一个
                    mCid = mDataBeans.get(position).getCid();//获取每次点击的 cid
                    mRightPresenter.rightDatas(mDataBeanList, mCid);//每次点击获取cid 传给相应的接口相应
                }
                mMyLeftAdapter.notifyDataSetChanged();//刷新适配器
            }
        });
    }
    //右侧  访问失败接口回调
    @Override
    public void onRightFailed(String error) {
        Toast.makeText(this, error, Toast.LENGTH_SHORT).show();
    }

    //防止内存泄露
    @Override
    protected void onDestroy() {
        super.onDestroy();
        mLeftPresenter.datechView();
        mRightPresenter.datechView();
    }
}
