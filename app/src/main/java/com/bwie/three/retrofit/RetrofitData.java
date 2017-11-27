package com.bwie.three.retrofit;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by 小傻瓜 on 2017/11/18.
 */

public class RetrofitData {
    private static NewsURL newsURL;//网络请求
    private RetrofitData(){}
    public static NewsURL getNewsURL(){
        if(newsURL==null){
            synchronized (RetrofitData.class){
                if(newsURL==null){
                    OkHttpClient okHttpClient=new OkHttpClient.Builder()
                            .connectTimeout(5, TimeUnit.SECONDS)
                            .readTimeout(5,TimeUnit.SECONDS)
//                            .addNetworkInterceptor(null)//网络拦截器
                            .build();
                    Retrofit retrofit=new Retrofit.Builder()
                            .baseUrl(BaerURL.BASE_URL)
                            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                            .addConverterFactory(GsonConverterFactory.create())
                            .client(okHttpClient)
                            .build();
                    //获取接口的对象
                    newsURL=retrofit.create(NewsURL.class);
                }
            }
        }
        return newsURL;
    }
    public static NewsURL download(){
        OkHttpClient client = new OkHttpClient.Builder()
                .connectTimeout(5, TimeUnit.SECONDS)
                .readTimeout(5, TimeUnit.SECONDS)
//                .addNetworkInterceptor(new MyInterceptro())
                .build();
        Retrofit retrofit = new Retrofit.Builder()
                .client(client)
                .baseUrl("http://10.0.2.2:8080/aaa/")
                .build();
        NewsURL api = retrofit.create(NewsURL.class);
        return api;
    }
}
