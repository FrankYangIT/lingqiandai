package com.frank.mybizhi.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;

import com.frank.mybizhi.R;
import com.frank.mybizhi.activity.ImageDetailsActivity;
import com.frank.mybizhi.adapter.RecommendImageBeanAdapter;
import com.frank.mybizhi.app.MyApp;
import com.frank.mybizhi.beans.RecommendImageBean;
import com.frank.mybizhi.utils.UrlUtils;
import com.google.gson.Gson;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Request;
import okhttp3.Response;

/**
 * 推荐类ViewPager当中的碎片
 *
 * 1.网络请求获取Json数据
 * 2.解析Json数据,转化成JavaBean
 * 3.找到支持JavaBean的UI控件
 * 4.构写UI控件的适配器，并设置适配器
 */
public class ImageRecommendFragment extends Fragment {

    private static final String IMAGE_URL = "param1";

    private String mParam1;

    private RecommendImageBean recommendImageBean;
    private GridView gridView;

    public ImageRecommendFragment() {
    }

    /**
     * 工厂方法
     */
    public static ImageRecommendFragment newInstance(String param1) {
        ImageRecommendFragment fragment = new ImageRecommendFragment();
        Bundle args = new Bundle();
        args.putString(IMAGE_URL, param1);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(IMAGE_URL);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_recommend_gridview, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        //初始化控件
        gridView = ((GridView) view.findViewById(R.id.recommend_grid));
        //JDK1.7以上switch_case支持字符串，本来支持int 、byte 、char类型
        String webSide = null;
        switch (mParam1) {
            case "recommend0":
                webSide = UrlUtils.URL_RECOMMEND_LASTEST;
                break;
            case "recommend1":
                webSide = UrlUtils.URL_RECOMMEND_HOT;
                break;
            case "recommend2":
                webSide = UrlUtils.URL_RECOMMEND_RANDOM;
                break;
            default:
                break;

        }
        //初始化数据源：网络请求（封装方法）
        if (webSide != null)
            getNetWorkData(webSide);
        //对GridView设置事件监听，用来跳转至大图
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //跳转详情界面
                //传递参数(位置position,RecommandBean)

                Intent intent = new Intent(getActivity(), ImageDetailsActivity.class);
                intent.putExtra("position",position);
                intent.putExtra("data",recommendImageBean);//注意:传递对象必须要序列化
                startActivity(intent);
            }
        });


    }

    /**
     * 获取网络数据
     * @param webSide
     */
    private void getNetWorkData(String webSide) {
        //最近使用率很高的网络框架（2003年Google在IO峰会上推出了Voley的网络框架，解放了程序员
        //都是对Android原生的HTTPURLConnection进行封装
        //square公司出品的okhttp框架

        //1.发起请求
        Request request = new Request.Builder().get().url(webSide).build();
        //2.网络连接并且异步下载
        MyApp.okHttpClient.newCall(request).enqueue(new Callback() {
            //请求失败的方法回调
            @Override
            public void onFailure(Call call, IOException e) {
            }
            //请求成功的方法回调，第二个参数：获取下载内容的结果的实体类Response
            @Override
            public void onResponse(Call call, Response response) throws IOException {
                //打印结果返回结果
                String result = response.body().string();
                //利用谷歌Goson将数据进行解析
                if (result != null) {
                    recommendImageBean = new Gson().fromJson(result, RecommendImageBean.class);

                    //初始化适配器
                    final RecommendImageBeanAdapter adapter = new RecommendImageBeanAdapter(recommendImageBean,getActivity());
                    //设置设配器

                    gridView.post(new Runnable() {
                        @Override
                        public void run() {
                            gridView.setAdapter(adapter);
                        }
                    });

                }
            }
        });
    }
}
