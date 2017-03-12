package com.frank.mybizhi.fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.frank.mybizhi.R;
import com.frank.mybizhi.adapter.SearchBeanAdapter;
import com.frank.mybizhi.app.MyApp;
import com.frank.mybizhi.beans.SearchHotBean;
import com.frank.mybizhi.beans.SearchMainListBean;
import com.frank.mybizhi.beans.SearchMoreBean;
import com.frank.mybizhi.utils.UrlUtils;
import com.google.gson.Gson;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Request;
import okhttp3.Response;

/**
 * 搜索界面碎片
 */
public class SearchFragment extends Fragment {


    public SearchFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_search, container, false);
    }

    //声明全局变量
    ListView searchListView;
    LinearLayout searchTitle;
    SearchBeanAdapter adapter;
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //初始化控件
        searchTitle = (LinearLayout) view.findViewById(R.id.search_title);
        searchListView = (ListView) view.findViewById(R.id.search_list_view);
        //TODO 为searchTitle添加搜索的点击事件
        //初始化数据源，通过发送网络请求获取资源并进行解析
        /**
         *由于此ListView有三种Json数据，需要在不同的Item发送不同的网络请求
         * 所以需要对现有的Item的位置进行判断来保证发送正确的网络请求
         * 由产品的布局图得知，共有三种不同的Item,前面两种占用一个条目
         * 后面一种占用的条目个数为数据自身的条数
         *
         * 以下封装方法进行判断(将所有的需要发送的网络地址作为参数传入)，并访问下载资源
         */

        downLoadDataFromNetWork(UrlUtils.URL_SEARCH_HOT_SEARCH
                ,UrlUtils.URL_SEARCH_CHECK_MORE,UrlUtils.URL_SEARCH_THE_MAIN_LIST);


        //初始化适配器
        //ListView设置适配器
        adapter = new SearchBeanAdapter(getActivity());
        searchListView.setAdapter(adapter);



    }

    //判断情况并进行下载资源
    private void downLoadDataFromNetWork(String...url) {
        for (int i = 0; i < url.length; i++) {
            //创建请求
            Request request = new Request.Builder().get().url(url[i]).build();
            //设置常量接收i值，方便下方网络请求时使用
            final int position = i;
            //发送请求
            MyApp.okHttpClient.newCall(request).enqueue(new Callback() {
                //资源加载失败
                @Override
                public void onFailure(Call call, IOException e) {

                }
                //资源加载成功
                @Override
                public void onResponse(Call call, Response response) throws IOException {

                    //区分url,通过下标
                    String result = response.body().string();
                    //使用的适配器可以显示3中不同的数据来源
                    switch (position) {
                        case 0:
                            //解析的实体类不同
                            final SearchHotBean searchHot = new Gson().fromJson(result, SearchHotBean.class);
                            searchListView.post(new Runnable() {
                                @Override
                                public void run() {
                                    //保证内部的count正确累加,数据就不会错乱了,有没有数据显示再做处理
                                    adapter.setsearchHot(searchHot);
                                }
                            });
                            break;
                        case 1:
                            //解析的实体类不同
                            final SearchMoreBean searchMoreBean = new Gson().fromJson(result, SearchMoreBean.class);
                            searchListView.post(new Runnable() {
                                @Override
                                public void run() {
                                    adapter.setsearchMoreBean(searchMoreBean);
                                }
                            });
                            break;
                        case 2:
                            //解析的实体类不同
                            final SearchMainListBean searchMainListBean = new Gson().fromJson(result, SearchMainListBean.class);
                            searchListView.post(new Runnable() {
                                @Override
                                public void run() {
                                    adapter.setsearchMainListBean(searchMainListBean);
                                }
                            });
                            break;
                        default:
                            break;
                    }
                }
            });
        }

    }
}
