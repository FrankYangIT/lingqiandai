package com.frank.mybizhi.activity;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.*;

import com.frank.mybizhi.R;
import com.viewpagerindicator.CirclePageIndicator;

public class GuideActivity extends AppCompatActivity {

    private ViewPager viewPager;
    private Context context;
    private CirclePageIndicator pageIndicator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guide);
        context = this;
        viewPager = ((ViewPager) findViewById(R.id.viewPager_guide));
        pageIndicator = ((CirclePageIndicator) findViewById(R.id.indicator_guide));


        initViews();
        viewPager.setAdapter(new PagerAdapter() {
            @Override
            public int getCount() {
                return images.size();
            }

            @Override
            public boolean isViewFromObject(View view, Object object) {
                return view==object;
            }

            @Override
            public Object instantiateItem(ViewGroup container, int position) {
                container.addView(images.get(position));
                return images.get(position);
            }

            @Override
            public void destroyItem(ViewGroup container, int position, Object object) {
                container.removeView((View) object);
            }
        });

        pageIndicator.setViewPager(viewPager);

        //实现引导界面向主界面跳转，对ViewPager设置监听
        skipFromGuideToMainActivity();

    }

    /**
     * 实现引导界面向主界面的跳转,有待检验
     */
    private void skipFromGuideToMainActivity() {
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    /**
     * 初始化ViewPager中的imageview对象
     */
    List<View> images;
    private void initViews() {
        images = new ArrayList<View>();
        int[] resImages = {R.mipmap.guide0, R.mipmap.guide1, R.mipmap.guide_2,
                R.mipmap.guide_3, R.mipmap.guide_4};
        for (int i = 0; i < resImages.length; i++) {
            //创建ImageView并设置资源
            ImageView imageView = new ImageView(context);

            imageView.setImageResource(resImages[i]);
            //为ImageView设置参数
            imageView.setLayoutParams(new ViewPager.LayoutParams());
            //imageView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.MATCH_PARENT));
            //设置填充类型
            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
            images.add(imageView);
        }

    }
}
