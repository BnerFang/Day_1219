package com.fsk.day_1219.mvp.model;

import com.fsk.day_1219.api.Apis;
import com.fsk.day_1219.bean.LeftBean;
import com.fsk.day_1219.mvp.MyLeftCallBack;
import com.fsk.day_1219.utils.OkHttpUtil;
import com.google.gson.Gson;

import java.util.List;


public class LeftModel {
    public void leftData(List<LeftBean.DataBean> dataBeans, final MyLeftCallBack callBack) {
        new OkHttpUtil().OkHttpGet(Apis.LEFT_URL).setOkHttpListener(new OkHttpUtil.OkHttpListener() {
            @Override
            public void OkHttpSuccess(String data) {
                Gson gson = new Gson();
                LeftBean leftBean = gson.fromJson(data, LeftBean.class);
                List<LeftBean.DataBean> beans = leftBean.getData();
                if (leftBean.getCode().equals("0")) {
                    callBack.onSuccess(beans);
                } else {
                    callBack.onFailed(leftBean.getMsg());
                }
            }

            @Override
            public void OkHttpError(String error) {
                callBack.onFailed(error);
            }
        });
    }
}
