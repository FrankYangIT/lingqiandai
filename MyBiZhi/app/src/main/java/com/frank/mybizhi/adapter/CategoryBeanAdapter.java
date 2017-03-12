package com.frank.mybizhi.adapter;

import android.content.Context;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.frank.mybizhi.R;
import com.frank.mybizhi.beans.CategoryBean;

import java.util.List;

/**
 * Created by Administrator on 2016/10/14.
 */

public class CategoryBeanAdapter extends BaseAdapter{
    //声明变量
    private CategoryBean categoryBean;
    private LayoutInflater mInflater;
    Context mContext ;
    //获取数据集合
    private List<CategoryBean.DataBean> categoryBeanData;
    public CategoryBeanAdapter(CategoryBean categoryBean, FragmentActivity activity) {
        this.categoryBean = categoryBean;
        mInflater = LayoutInflater.from(activity);
        categoryBeanData = categoryBean.getData();
        mContext = activity;
    }

    @Override
    public int getCount() {
        return categoryBeanData == null ? 0 : categoryBeanData.size();
    }

    @Override
    public Object getItem(int position) {
        return categoryBeanData.get(position);
    }

    @Override
    public long getItemId(int position) {

        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.fragment_category_listview_item, parent, false);
        }
        //对布局中的控件进行初始化
        ImageView imageView = (ImageView) convertView.findViewById(R.id.category_list_item_Image);
        TextView textTitle = (TextView) convertView.findViewById(R.id.category_list_item_title);
        TextView textSubTitle = (TextView) convertView.findViewById(R.id.category_list_item_sub_title);

        //从数据源中获取数据资源
        //图片资源地址
        String categoryPic = categoryBeanData.get(position).getCategoryPic();
        //副标题名称
        String descWords = categoryBeanData.get(position).getDescWords();
        //主标题名称
        String picCategoryName = categoryBeanData.get(position).getPicCategoryName();


        //将获取的资源设置到控件中
        textSubTitle.setText(descWords);
        textTitle.setText(picCategoryName);
        //下载图片资源并将图片设置到ImageView中
        Glide.with(mContext).load(categoryPic)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .skipMemoryCache(false)
                .placeholder(R.mipmap.ic_icon).into(imageView);
        return convertView;
    }
}
