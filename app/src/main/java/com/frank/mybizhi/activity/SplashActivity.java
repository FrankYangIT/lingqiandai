package com.frank.mybizhi.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.frank.mybizhi.R;
import com.frank.mybizhi.app.MyApp;

/**
 * Created by Frank on 2016/10/10.
 */
public class SplashActivity extends Activity {
    private ImageView imageView;
    private TextView textView;

    //定义消息
    private static final int MSG_COUNT = 0x00;
    //定义欢迎界面秒数
    private int count = 5;
    Context mContext;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        //初始化控件
        mContext=this;
        imageView = ((ImageView) findViewById(R.id.splash_image));
        textView = ((TextView) findViewById(R.id.splash_text));


        imageView.setImageResource(R.mipmap.splash);
        imageView.setScaleType(ImageView.ScaleType.FIT_XY);

        textView.setText(count + "秒");

        handler.sendEmptyMessage(MSG_COUNT);
    }

    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);

            if (msg.what==MSG_COUNT) {
                count--;
                if (count > 0) {
                    textView.setText(count + "秒");
                    handler.sendEmptyMessageDelayed(MSG_COUNT, 1000);
                } else {
                    if (MyApp.firstLogin()) {//第一次登陆进入引导界面
                        Toast.makeText(mContext, "进入引导界面",Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(mContext, GuideActivity.class));
                    } else {//不是第一次登陆，进入主界面
                        Toast.makeText(mContext, "进入主界面",Toast.LENGTH_SHORT).show();
                        //startActivity(new Intent(mContext, MainActivity.class));
                    }
                }
            }


        }
    };
}
