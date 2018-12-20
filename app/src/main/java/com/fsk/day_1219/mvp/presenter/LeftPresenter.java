package com.fsk.day_1219.mvp.presenter;

import com.fsk.day_1219.bean.LeftBean;
import com.fsk.day_1219.mvp.MyLeftCallBack;
import com.fsk.day_1219.mvp.model.LeftModel;
import com.fsk.day_1219.mvp.view.LeftView;

import java.util.List;


public class LeftPresenter {
    private LeftView mLeftView;
    private LeftModel mLeftModel;

    public LeftPresenter(LeftView leftView) {
        mLeftView = leftView;
        mLeftModel = new LeftModel();
    }

    //解绑
    public void datechView() {
        mLeftView = null;
    }

    public void leftDatas(List<LeftBean.DataBean> dataBeans) {
        mLeftModel.leftData(dataBeans, new MyLeftCallBack() {
            @Override
            public void onSuccess(List<LeftBean.DataBean> dataBeans) {
                mLeftView.onLeftSuccess(dataBeans);
            }

            @Override
            public void onFailed(String error) {
                mLeftView.onLeftFailed(error);
            }
        });
    }
}
