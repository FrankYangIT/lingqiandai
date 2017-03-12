package com.frank.mybizhi.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.frank.mybizhi.R;
import com.frank.mybizhi.app.MyApp;
import com.frank.mybizhi.beans.RecommendImageBean;

import java.util.List;

/**
 * GridView的适配器
 * Created by Administrator on 2016/10/12.
 */

public class RecommendImageBeanAdapter extends BaseAdapter {
    private final List<RecommendImageBean.DataBean.WallpaperListInfoBean> wallpaperListInfos;
    private RecommendImageBean recommendImageBean;
    private LayoutInflater mLayoutInflater;
    private Context mContext;

    public RecommendImageBeanAdapter(RecommendImageBean recommendImageBean, Context mContext) {
        this.recommendImageBean = recommendImageBean;
        mLayoutInflater = LayoutInflater.from(mContext);
        this.mContext = mContext;
        wallpaperListInfos = recommendImageBean.getData().getWallpaperListInfo();
    }

    @Override
    public int getCount() {
        return wallpaperListInfos == null ? 0 : wallpaperListInfos.size();
    }

    @Override
    public Object getItem(int position) {
        return wallpaperListInfos.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = null;
        if (convertView == null) {
            convertView = mLayoutInflater.inflate(R.layout.fragment_recommend_gridview_item, parent, false);
            viewHolder = new ViewHolder();
            viewHolder.imageView = (ImageView) convertView.findViewById(R.id.recommend_grid_image);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        /**
         *设置图片：1.从解析出来的信息中获取图片的网络地址进行下载，
         * 2.并将下载图片设置到ImageView中
         * 3.运用第三方框架进行操作
         */
        String url = wallpaperListInfos.get(position).getWallPaperMiddle();
        viewHolder.imageView.setLayoutParams(new FrameLayout.
                LayoutParams(MyApp.SCREEN_WIDTH/3,MyApp.SCREEN_HEIGHT/3));

        viewHolder.imageView.setScaleType(ImageView.ScaleType.FIT_XY);
        //有没可以直接加载URL的Image控件
        //图片处理的第三方框架(piccaso(square公司出的),glide)
        //一句话搞定网络图片的加载
        //内置自带内存缓存+磁盘缓存
        //设置内存缓存策略
        Glide.with(mContext)
                .load(url)
                .skipMemoryCache(false)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .centerCrop()
                .placeholder(R.mipmap.ic_icon)
                //.crossFade()   //淡进淡出
                .into(viewHolder.imageView);
        return convertView;
    }

    class ViewHolder {
        public ImageView imageView;
    }
}
