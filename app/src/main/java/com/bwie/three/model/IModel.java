package com.bwie.three.model;

import com.bwie.three.retrofit.RetrofitData;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by 小傻瓜 on 2017/11/18.
 */

public class IModel implements IBannerModel{
    @Override
    public void getData(Observer observer) {
        RetrofitData.getNewsURL().getNews()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
    }
}
