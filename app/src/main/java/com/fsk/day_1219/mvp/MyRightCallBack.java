package com.fsk.day_1219.mvp;

import com.fsk.day_1219.bean.RightBean;

import java.util.List;


public interface MyRightCallBack {
    void onSuccesss(List<RightBean.DataBean> list);
    void onFaileds(String error);
}
