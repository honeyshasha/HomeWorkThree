package com.bwie.three;

import android.app.Application;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.bwie.three.presenter.IBannerPresneter;

public abstract class BaseActivity<T extends IBannerPresneter> extends AppCompatActivity {
    //使用范型定义个Presenter对象  该范型必须满足IPresenter类型 所以使用<T extends IPresenter>
    T presenter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        createPresenter();
//        initView();
    }
    public abstract void createPresenter();
//    public abstract void initView();
    //让所有的Activity销毁时  统一在Base中进行和Presenter解绑
    @Override
    public void onDestroy() {
        super.onDestroy();
        if(presenter != null){
            presenter.delete();
        }
    }
}
