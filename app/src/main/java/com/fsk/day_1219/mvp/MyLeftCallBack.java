package com.fsk.day_1219.mvp;

import com.fsk.day_1219.bean.LeftBean;

import java.util.List;


public interface MyLeftCallBack {
    void onSuccess(List<LeftBean.DataBean> dataBeans);
    void onFailed(String error);
}
