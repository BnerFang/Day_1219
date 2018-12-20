package com.fsk.day_1219.mvp.model;

import com.fsk.day_1219.api.Apis;
import com.fsk.day_1219.bean.RightBean;
import com.fsk.day_1219.mvp.MyRightCallBack;
import com.fsk.day_1219.utils.OkHttpUtil;
import com.google.gson.Gson;

import java.util.List;


public class RightModel {
    public void rightData(List<RightBean.DataBean> list, int uid, final MyRightCallBack callBack) {
        new OkHttpUtil().OkHttpGet(Apis.RIGHT_URL + uid).setOkHttpListener(new OkHttpUtil.OkHttpListener() {
            @Override
            public void OkHttpSuccess(String data) {
                Gson gson = new Gson();
                RightBean rightBean = gson.fromJson(data, RightBean.class);
                List<RightBean.DataBean> beans = rightBean.getData();
                if (rightBean.getCode().equals("0")) {
                    callBack.onSuccesss(beans);
                } else {
                    callBack.onFaileds(rightBean.getMsg());
                }
            }

            @Override
            public void OkHttpError(String error) {
                callBack.onFaileds(error);
            }
        });
    }
}
