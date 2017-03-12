package com.frank.mybizhi.activity;

import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.flyco.tablayout.CommonTabLayout;
import com.flyco.tablayout.listener.CustomTabEntity;
import com.frank.mybizhi.R;
import com.frank.mybizhi.fragment.CategoryFragment;
import com.frank.mybizhi.fragment.MoreFragment;
import com.frank.mybizhi.fragment.RecommendFragment;
import com.frank.mybizhi.fragment.SearchFragment;
import com.frank.mybizhi.utils.MyCustomTabEntity;

import java.util.*;

public class MainActivity extends AppCompatActivity {

    private CommonTabLayout commonTabLayout;

    ArrayList<CustomTabEntity> tabEntitys;
    ArrayList<Fragment> fragments;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //初始化选项卡数据
        initTabs();
    }



    private void initTabs() {

        commonTabLayout = (CommonTabLayout) findViewById(R.id.tab_main);

        CustomTabEntity customTabEntity1 = new MyCustomTabEntity(R.string.recommend_tab,
                R.mipmap.bottom_recommand_selected,R.mipmap.bottom_recommand);
        CustomTabEntity customTabEntity2 = new MyCustomTabEntity(R.string.category_tab,
                R.mipmap.bottom_category_selected,R.mipmap.bottom_category);
        CustomTabEntity customTabEntity3 = new MyCustomTabEntity(R.string.search_tab,
                R.mipmap.bottom_search_selected,R.mipmap.bottom_search);
        CustomTabEntity customTabEntity4 = new MyCustomTabEntity(R.string.more_tab,
                R.mipmap.bottom_more_selected,R.mipmap.bottom_more);

        //初始化存放选项卡选项的集合,并将选项添加进去
        tabEntitys = new ArrayList<CustomTabEntity>();

        tabEntitys.add(customTabEntity1);
        tabEntitys.add(customTabEntity2);
        tabEntitys.add(customTabEntity3);
        tabEntitys.add(customTabEntity4);

        //初始化碎片
        initFragments();
        //将选项卡集合设置到CommonTabLayout中,同时将碎片集合传入CommonTabLayout实现选项卡与碎片的关联
        //commonTabLayout.setTabData(tabEntitys);
        commonTabLayout.setTabData(tabEntitys,this,R.id.fragmentContainer_main,fragments);
    }

    /**
     * 初始化碎片，并添加到
     */
    private void initFragments() {
        fragments = new ArrayList<Fragment>();
        fragments.add(new RecommendFragment());
        fragments.add(new CategoryFragment());
        fragments.add(new SearchFragment());
        fragments.add(new MoreFragment());

    }
}
