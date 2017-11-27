package com.bwie.three;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bwie.three.bean.NewsBean;
import com.bwie.three.net.DialogUtils;
import com.bwie.three.presenter.IPresneter;
import com.bwie.three.view.IView;
import com.stx.xhb.xbanner.XBanner;

import java.util.ArrayList;
import java.util.List;

public class TwoActivity extends BaseActivity<IPresneter> implements IView{
    private XBanner xBanner_image;
    private List<String> imgesUrl;
    private RecyclerView recyclerView;
    private IPresneter iPresneter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_two);
        //初始化控件
        initView();
        //初始化XBanner轮播图
        initBanner();
        //p层
        iPresneter.getDataNews();
    }
    @Override
    public void createPresenter() {
        iPresneter = new IPresneter(this);
    }
    /**
     *XBanner轮播图
     */
    private void initBanner() {
        imgesUrl = new ArrayList<>();
        imgesUrl.add("http://pic32.nipic.com/20130817/9745430_101836881000_2.jpg");
        imgesUrl.add("http://pic15.nipic.com/20110630/6322714_105943746342_2.jpg");
        imgesUrl.add("http://pic48.nipic.com/file/20140916/2531170_195153248000_2.jpg");
        imgesUrl.add("http://img.taopic.com/uploads/allimg/140626/240469-1406261S24553.jpg");
        imgesUrl.add("http://pic77.nipic.com/file/20150911/21721561_155058651000_2.jpg");
        imgesUrl.add("http://img4.duitang.com/uploads/item/201603/18/20160318103156_cziuY.jpeg");
        xBanner_image.setData(imgesUrl,null);
    }
    /**
     * 获取控件
     */
    private void initView() {
        xBanner_image=findViewById(R.id.xBanner_image);
        recyclerView=findViewById(R.id.recyclerView);
        xBanner_image.setmAdapter(new XBanner.XBannerAdapter() {
            @Override
            public void loadBanner(XBanner banner, View view, int position) {
                Glide.with(TwoActivity.this).load(imgesUrl.get(position)).into((ImageView) view);
            }
        });
    }
    @Override
    public void showData(final List<NewsBean.DataBean> listBean) {
        Log.i("=listBean=", "showData: " + listBean.get(0).getTitle());
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        MyAdapter myAdapter = new MyAdapter(this, listBean);
        recyclerView.setAdapter(myAdapter);
        //点击下载视频
        myAdapter.setOnItemClickListener(new MyAdapter.OnItemClickListener() {
            @Override
            public void setOnItemClickListener(View v, int position) {
                //这里做展示弹框以及功能操作
                DialogUtils.showUpdataDialog(TwoActivity.this,listBean.get(position).getVedio_url());
            }
        });
    }
}
