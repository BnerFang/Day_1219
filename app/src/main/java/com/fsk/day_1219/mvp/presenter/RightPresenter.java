package com.fsk.day_1219.mvp.presenter;

import com.fsk.day_1219.bean.RightBean;
import com.fsk.day_1219.mvp.MyRightCallBack;
import com.fsk.day_1219.mvp.model.RightModel;
import com.fsk.day_1219.mvp.view.RightView;

import java.util.List;


public class RightPresenter {
    private RightView mRightView;
    private RightModel mRightModel;

    public RightPresenter(RightView rightView) {
        mRightView = rightView;
        mRightModel = new RightModel();
    }

    //解绑
    public void datechView() {
        mRightView = null;
    }

    public void rightDatas(final List<RightBean.DataBean> list, int uid) {
        mRightModel.rightData(list, uid, new MyRightCallBack() {
            @Override
            public void onSuccesss(List<RightBean.DataBean> list) {
                mRightView.onRightSuccess(list);
            }

            @Override
            public void onFaileds(String error) {
                mRightView.onRightFailed(error);
            }
        });
    }
}
