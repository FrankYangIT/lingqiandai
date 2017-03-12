package com.frank.mybizhi.fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.flyco.tablayout.SlidingTabLayout;
import com.frank.mybizhi.R;

import java.util.ArrayList;

/**
 * 推荐碎片
 */
public class RecommendFragment extends Fragment {

    SlidingTabLayout tabLayout;
    ViewPager viewPager;

    public RecommendFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_recommend, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        tabLayout = ((SlidingTabLayout) view.findViewById(R.id.slidingTabLayout_recommend));

        viewPager = (ViewPager) view.findViewById(R.id.viewPager_recommend);

        String[] titles = {getResources().getString(R.string.lastest_tab),
                getResources().getString(R.string.hot_tab),
                getResources().getString(R.string.random_tab)};

        viewPager.setOffscreenPageLimit(titles.length-1);

        //初始化碎片，并将碎片填充到ViewPager中
        final ArrayList<Fragment> fragments = new ArrayList<Fragment>();

        for (int i = 0; i < titles.length; i++) {
            ImageRecommendFragment imageFragement = ImageRecommendFragment.newInstance("recommend"+i);
            fragments.add(imageFragement);
        }

        //将碎片设置到ViewPager中
        viewPager.setAdapter(new FragmentPagerAdapter(getChildFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return fragments.get(position);
            }

            @Override
            public int getCount() {
                return fragments.size();
            }
        });
        tabLayout.setViewPager(viewPager,titles);
    }
}
