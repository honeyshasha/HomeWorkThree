package com.bwie.three.view;

import com.bwie.three.bean.NewsBean;

import java.util.List;

/**
 * Created by 小傻瓜 on 2017/11/18.
 */

public interface IView {
    void showData(List<NewsBean.DataBean> listBean);
}
