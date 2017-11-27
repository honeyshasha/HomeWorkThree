package com.bwie.three.presenter;

import android.util.Log;

import com.bwie.three.MainActivity;
import com.bwie.three.bean.NewsBean;
import com.bwie.three.model.IModel;
import com.bwie.three.view.IView;

import java.lang.ref.SoftReference;
import java.util.List;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * Created by 小傻瓜 on 2017/11/18.
 */

public class IPresneter implements IBannerPresneter<IView>{
    private IModel iModel;
    SoftReference<IView> mIView;//软引用   W
    public IPresneter(IView iView) {
        this.iModel =new IModel();
        attch(iView);
    }
    @Override
    public void attch(IView iView) {
        mIView=new SoftReference<IView>(iView);
    }
    @Override
    public void delete() {
        mIView.clear();
    }
    public void getDataNews(){
        iModel.getData(new Observer<NewsBean>() {
            @Override
            public void onSubscribe(Disposable d) {

            }
            @Override
            public void onNext(NewsBean newsBean) {
                List<NewsBean.DataBean> data = newsBean.getData();
                Log.i("===data===","onNext"+data.size());
                mIView.get().showData(data);
            }
            @Override
            public void onError(Throwable e) {
                Log.i("===dataOnError===", "onError: ");
            }
            @Override
            public void onComplete() {

            }
        });
    }
}
