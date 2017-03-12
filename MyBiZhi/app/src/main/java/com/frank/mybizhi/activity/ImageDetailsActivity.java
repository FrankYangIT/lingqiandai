package com.frank.mybizhi.activity;

import android.app.WallpaperManager;
import android.content.Context;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;
import com.frank.mybizhi.R;
import com.frank.mybizhi.app.MyApp;
import com.frank.mybizhi.beans.RecommendImageBean;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Request;
import okhttp3.Response;

public class ImageDetailsActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private LinearLayout linearLayoutTitle;
    private LinearLayout linearLayoutdownSelect;
    private ViewPager viewPagerDetails;
    private RecommendImageBean recommendImageBean;
    private List<RecommendImageBean.DataBean.WallpaperListInfoBean> wallpaperListInfos;
    private Context mContext;

    //初始化界面上下两端的显示状态默认显示
    private boolean isHidden;//false表示显示，true表示不显示
    int itemPosition;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_details);
        mContext=this;
        //控件初始化
        linearLayoutTitle = ((LinearLayout) findViewById(R.id.details_title));
        linearLayoutdownSelect = ((LinearLayout) findViewById(R.id.detailsdownSelector_layout));
        viewPagerDetails = ((ViewPager) findViewById(R.id.details_viewPager));

        //初始化数据源
        recommendImageBean = (RecommendImageBean) getIntent().getSerializableExtra("data");
        wallpaperListInfos = recommendImageBean.getData().getWallpaperListInfo();
        //获取点击的Item的下标
        itemPosition = getIntent().getIntExtra("position", 0);
        //初始化，需填充的图片，并将并将碎片添加到集合中
        initViews();
        //初始化ViewPager的适配器
        viewPagerDetails.setCurrentItem(itemPosition);

    }

    //初始化，需填充的图片，并将并将碎片添加到集合中
    List<View> detailsImages;
    private void initViews() {
        detailsImages = new ArrayList<View>();
        //遍历wallpaperListInfos的信息，进行图片下载，并存放在集合中
        for (int i = 0; i < wallpaperListInfos.size(); i++) {
            ImageView imageView = new ImageView(this);
            String wallPaperBig = wallpaperListInfos.get(i).getWallPaperBig();
            Glide.with(getApplicationContext())
                    .load(wallPaperBig)
                    .skipMemoryCache(false)
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .centerCrop()
                    .placeholder(R.mipmap.ic_icon)
                    .crossFade()
                    .into(imageView);
            detailsImages.add(imageView);
        }


        viewPagerDetails.setAdapter(new PagerAdapter() {
            @Override
            public int getCount() {
                return detailsImages.size();
            }

            @Override
            public boolean isViewFromObject(View view, Object object) {
                return view == object;
            }

            @Override
            public Object instantiateItem(ViewGroup container, int position) {
                View view = detailsImages.get(position);
                /**
                 * 当前item初始化时会将当前item两边的item加载好，所以当
                 * item位于第二页和倒数第二页时，下面的第一页和第二页的吐司会弹出
                 */
                //在Item初始化的时候对其设置点击事件，实现点击图片出现的动画效果
                view.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        //通过逻辑实现动画
                        if (!isHidden) {
                            //TODO 调用封装的动画类进行操作
                            MyApp.initViewMove(linearLayoutTitle, 0, -linearLayoutTitle.getHeight(), 500);
                            MyApp.initViewMove(linearLayoutdownSelect, 0, linearLayoutdownSelect.getHeight(), 500);
                            isHidden = true;
                        } else {
                            //TODO 调用封装的动画类进行操作
                            MyApp.initViewMove(linearLayoutTitle,  -linearLayoutTitle.getHeight(), 0, 500);
                            MyApp.initViewMove(linearLayoutdownSelect, linearLayoutdownSelect.getHeight(),0 , 500);
                            isHidden = false;
                        }
                    }
                });


                if (position == 0) {
                    Toast.makeText(getApplicationContext(),"已经是第一页",Toast.LENGTH_SHORT).show();
                } else if (position==detailsImages.size()-1) {
                    Toast.makeText(getApplicationContext(),"已经是最后一页",Toast.LENGTH_SHORT).show();
                }
                container.addView(view);
                return view;
            }

            @Override
            public void destroyItem(ViewGroup container, int position, Object object) {
                container.removeView((View) object);
            }
        });
    }

    //点击返回
    public void back(View view) {
        finish();
    }

    //点击设为壁纸
    public void setWallPager(View view) {
        //获取当前图片的网络地址，加载后设置为壁纸，（ImageView中的缓存的图片质量太差）
        String wallPaperSourceUrl = wallpaperListInfos.get(viewPagerDetails.getCurrentItem()).getWallPaperSource();
        //获取壁纸管理器
        final WallpaperManager wallpaperManager = WallpaperManager.getInstance(this);

        Glide.with(this).load(wallPaperSourceUrl).asBitmap().into(new SimpleTarget<Bitmap>() {
            @Override
            public void onResourceReady(Bitmap resource, GlideAnimation<? super Bitmap> glideAnimation) {
                try {
                    wallpaperManager.setBitmap(resource);
                    Toast.makeText(ImageDetailsActivity.this,"设置壁纸成功",Toast.LENGTH_SHORT).show();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

    }



    /**
     * 点击收藏
     * @param view
     * 流程：1.网络下载网络图片
     * 2.保存到指定的目录
     */
    public void collect(View view) {
        //获取原图的网络地址
        String wallPaperSourceUrl = wallpaperListInfos
                .get(viewPagerDetails.getCurrentItem()).getWallPaperSource();
        final String picName = wallpaperListInfos
                .get(viewPagerDetails.getCurrentItem()).getPicName();

        //下载图片并将图片写入SDcard中
        //1.发起请求
        Request request = new Request.Builder().get().url(wallPaperSourceUrl).build();

        MyApp.okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {

                byte[] imageBytes = response.body().bytes();
                int mun = imageBytes.length;
                //在MyApp中初始化存放图面的路径，并封装存放图片的方法
                MyApp.writeSDCard(imageBytes, MyApp.SDCARD_PIC_PATH, picName);

            }
        });
        //Toast不能放在子线程中进行吐司
        Toast.makeText(ImageDetailsActivity.this,"收藏成功",Toast.LENGTH_SHORT).show();

    }
}
