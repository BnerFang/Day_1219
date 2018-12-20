package com.fsk.day_1219.mvp.view;

import com.fsk.day_1219.bean.RightBean;

import java.util.List;

/**
 * 作者:  方诗康
 * 描述:
 */
public interface RightView {

    void onRightSuccess(List<RightBean.DataBean> list);
    void onRightFailed(String error);
}
