package com.frank.mybizhi.app;

import android.animation.ObjectAnimator;
import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.view.View;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;

/**
 * Created by Frank on 2016/10/10.
 */
public class MyApp extends Application{

    private static final String SHARED_PREF = "bizhi";

    private static final String KEY_ISFIRSTLOGIN = "first";

    private static SharedPreferences sharedPreferences;
    public static Context mContext;

    //屏幕的宽高
    public static int SCREEN_WIDTH;
    public static int SCREEN_HEIGHT;

    //网络请求okhttp在此设置参数，整个APP都能调用
    public static OkHttpClient okHttpClient;

    //声明图片存放的地址
    public static String SDCARD_PIC_PATH;
    private static String SUBFILES_NAME = "picture";
    @Override
    public void onCreate() {
        super.onCreate();
        mContext = this;
        initSharedPref();
        initOkhttpConfig();//设置okhttp的参数配置
        initScreenSize();//初始化屏幕的宽度
        initPicCollectPath();//初始化SD卡中存放图片的路径
    }



    /**
     * 初始化图片的储存路径
     */
    private void initPicCollectPath() {
        //在SDcard的 Android/data/应用程序的包名/files目录
        String absolutePath = mContext.getFilesDir().getAbsolutePath();
        SDCARD_PIC_PATH = absolutePath + "/"+ SUBFILES_NAME ;
    }


    //接下来对SDCard读取写入方法的封装
    public static void writeSDCard(byte[] data,String fileDir,String fileName){

        BufferedOutputStream bos = null;
        try {
            File file = new File(fileDir, fileName);
            if(!file.exists()) {
                file.createNewFile();
            }
            bos = new BufferedOutputStream(new FileOutputStream(file));
            bos.write(data);
            bos.flush();

        } catch (Exception e) {
        } finally {
            if(bos != null) {
                try {
                    bos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }




    //实现选项及下方选项卡以动画的形式移出屏幕
    public static void initViewMove(View view,float startY,float stopY,int durationTime) {
        ObjectAnimator animator = ObjectAnimator.ofFloat(view,"translationY",startY,stopY);
        animator.setDuration(durationTime);
        animator.start();
    }

    /**
     * 获取屏幕的宽高
     */
    private void initScreenSize() {
        SCREEN_WIDTH = mContext.getResources().getDisplayMetrics().widthPixels;
        SCREEN_HEIGHT = mContext.getResources().getDisplayMetrics().heightPixels;
    }

    //定义SharedPreferences的路径
    public void initSharedPref() {
        sharedPreferences = mContext.getSharedPreferences(SHARED_PREF, Context.MODE_PRIVATE);
    }

    public static boolean firstLogin() {
        boolean value = sharedPreferences.getBoolean(KEY_ISFIRSTLOGIN, true);
        if (value) {
            //修改成false，并进行提交（apply为异步提交，commit为同步提交）
            sharedPreferences.edit().putBoolean(KEY_ISFIRSTLOGIN, false).apply();
        }
        return value;
    }

    /**
     * 配置OKHTTP的网络参数设置
     */
   public void initOkhttpConfig() {
        //连接网络超时，读取超时时间，写入超时时间
       okHttpClient = new OkHttpClient.Builder()
               .connectTimeout(10, TimeUnit.SECONDS)
               .readTimeout(12,TimeUnit.SECONDS)
               .writeTimeout(8,TimeUnit.SECONDS)
               .build();
    }
}
