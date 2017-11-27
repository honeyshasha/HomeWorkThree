package com.bwie.three;

import android.content.Intent;
import android.graphics.Color;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.bwie.three.viewCricle.MyCricleHua;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private Button btn_color,btn_jia,btn_jian;
    private MyCricleHua main_mycircle;
    private boolean isFly = true;
    private int index = 10;
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 0: {
                    main_mycircle.invalidate();
                }
                break;
                case 1: {
                    main_mycircle.invalidate();
                }
                break;
            }
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //初始化控件
        initView();
        //剪头开始移动
        startFly(isFly);
    }
    private void startFly(final boolean isFly) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    while (isFly) {
                        if (index > 0) {
                            Thread.sleep(index);
                            handler.sendEmptyMessage(0);
                        } else {
                            Thread.sleep(10);
                            handler.sendEmptyMessage(1);
                        }
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
    //初始化控件
    private void initView() {
        btn_color=findViewById(R.id.btn_color);
        btn_jia=findViewById(R.id.btn_jia);
        btn_jian=findViewById(R.id.btn_jian);
        main_mycircle=findViewById(R.id.main_mycircle);

        //添加点击事件
        btn_color.setOnClickListener(this);
        btn_jia.setOnClickListener(this);
        btn_jian.setOnClickListener(this);
        main_mycircle.setColor(Color.BLACK);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId())
        {
            case R.id.btn_color:
                main_mycircle.setColor(Color.RED);
                break;
            case R.id.btn_jia:
                index = index - 10;
                Toast.makeText(this, "加速！加速！", Toast.LENGTH_SHORT).show();
                if (index <=5) {
                    Intent intent=new Intent(MainActivity.this,TwoActivity.class);
                    startActivity(intent);
                }
                break;
            case R.id.btn_jian:
                Toast.makeText(this, "减速！减速！", Toast.LENGTH_SHORT).show();
                index = index + 10;
                break;
        }
    }
}
