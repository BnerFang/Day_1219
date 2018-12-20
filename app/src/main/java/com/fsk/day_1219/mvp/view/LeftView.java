package com.fsk.day_1219.mvp.view;

import com.fsk.day_1219.bean.LeftBean;

import java.util.List;

/**
 * 作者:  方诗康
 * 描述:
 */
public interface LeftView {

    void onLeftSuccess(List<LeftBean.DataBean> dataBeans);
    void onLeftFailed(String error);
}
