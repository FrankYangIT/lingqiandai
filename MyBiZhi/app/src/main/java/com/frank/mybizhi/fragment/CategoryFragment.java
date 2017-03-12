package com.frank.mybizhi.fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.frank.mybizhi.R;
import com.frank.mybizhi.adapter.CategoryBeanAdapter;
import com.frank.mybizhi.app.MyApp;
import com.frank.mybizhi.beans.CategoryBean;
import com.frank.mybizhi.utils.UrlUtils;
import com.google.gson.Gson;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Request;
import okhttp3.Response;

/**
 * 分类界面碎片
 */
public class CategoryFragment extends Fragment {

    private ListView listViewCategory;

    public CategoryFragment() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_category, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //初始化ListView控件
        listViewCategory = ((ListView) view.findViewById(R.id.list_categoty));
        //初始化数据源
        //设置请求，并发送请求
        Request request = new Request.Builder().get().url(UrlUtils.URL_CATEGORY_MAIN).build();
        MyApp.okHttpClient.newCall(request).enqueue(new Callback() {
            //资源获取失败
            @Override
            public void onFailure(Call call, IOException e) {
            }
            //资源获取成功，进行解析
            @Override
            public void onResponse(Call call, Response response) throws IOException {

                String result = response.body().string();
                //使用Gson将数据进行解析,返回bean类数据
                CategoryBean categoryBean = new Gson().fromJson(result, CategoryBean.class);
                //初始化适配器
                final CategoryBeanAdapter adapter = new CategoryBeanAdapter(categoryBean, getActivity());
                //设置适配器
                listViewCategory.post(new Runnable() {
                    @Override
                    public void run() {
                        listViewCategory.setAdapter(adapter);
                    }
                });
            }
        });

    }
}
