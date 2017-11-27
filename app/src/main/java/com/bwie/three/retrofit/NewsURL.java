package com.bwie.three.retrofit;

import com.bwie.three.bean.NewsBean;

import io.reactivex.Observable;
import retrofit2.http.GET;

/**
 * Created by 小傻瓜 on 2017/11/18.
 */

public interface NewsURL {
    @GET("iYXEPGn4e9c6dafce6e5cdd23287d2bb136ee7e9194d3e9?uri=vedio")
    Observable<NewsBean> getNews();
}
