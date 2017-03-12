package com.frank.mybizhi.adapter;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.frank.mybizhi.R;
import com.frank.mybizhi.beans.SearchHotBean;
import com.frank.mybizhi.beans.SearchMainListBean;
import com.frank.mybizhi.beans.SearchMoreBean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/10/14.
 */
public class SearchBeanAdapter extends BaseAdapter{

    //声明条目布局的类型
    private static final int TYPE1 = 0;
    private static final int TYPE2 = 1;
    private static final int TYPE3 = 2;

    //声明数据源
    private SearchHotBean searchHotBean;
    private SearchMainListBean searchMainListBean;
    private SearchMoreBean searchMoreBean;
    private int count;

    //声明Handler实现页面的自动跳转
    Handler handler=null;
    //声明上下文和填充器
    private Context mContext;
    private LayoutInflater mInflater;

    public SearchBeanAdapter() {
        super();
    }

    public SearchBeanAdapter(FragmentActivity activity) {
        mInflater = LayoutInflater.from(activity);
        mContext = activity;

    }

    @Override
    public int getCount() {
        return count;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        int type = getItemViewType(position);
        View view = null;
        switch (type) {
            case TYPE1:
                //绘制第一种单行
                view = drawType1(position, convertView, parent);
                break;
            case TYPE2:
                //绘制第二种单行
                view = drawType2(position, convertView, parent);
                break;
            case TYPE3:
                //绘制第三种单行
                view = drawType3(position, convertView, parent);
                break;
        }
        return view;
    }

    //对单行绘制布局
    private View drawType3(int position, View convertView, ViewGroup parent) {

        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.fragment_search_main_list, parent, false);
        }

        return convertView;
    }


    private View drawType2(int position, View convertView, final ViewGroup parent) {
        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.fragment_search_more, parent, false);
        }
        final ViewPager viewPager = (ViewPager) convertView.findViewById(R.id.type2_viewpager);
        final LinearLayout dotsContainer = (LinearLayout) convertView.findViewById(R.id.type2_container);
        //接收消息实现页面跳转
        handler = new Handler(){
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                int position = msg.what;
                viewPager.setCurrentItem(position);
                position++;
                handler.sendEmptyMessageDelayed(position,3000);
            }
        };

        if(searchMoreBean != null){
            //1.初始化数据源
            final List<View> listContainer = new ArrayList<View>();
            //动态添加LinearLayout容器,里面可以设置2个ImageView的
            int size = searchMoreBean.getData().getTopic().size();
            for (int i = 0; i < size; i += 2) { //采用i+=2的方法,2个数据动态生成个容器
                View singleView = mInflater.inflate(R.layout.fragment_search_more_image,parent,false);
                ImageView imageView1 = (ImageView) singleView.findViewById(R.id.image1_type2);
                ImageView imageView2 = (ImageView) singleView.findViewById(R.id.image2_type2);
                String url1 = searchMoreBean.getData().getTopic().get(i).getCover_path();
                String url2 = searchMoreBean.getData().getTopic().get(i+1).getCover_path();
                Glide.with(mContext).load(url1).into(imageView1);
                Glide.with(mContext).load(url2).into(imageView2);
                //添加到容器中
                listContainer.add(singleView);
                //3.初始化小圆点,继续在该循环下完成,ImageView的单行布局只有个该控件,所以可以直接强转
                ImageView imageDot = (ImageView) mInflater.inflate(R.layout.image_dot,parent,false);
                if(i==0){ //初始化的时候默认第一个选中
                    imageDot.setImageResource(R.mipmap.home_img_ratio_selected);
                }
                dotsContainer.addView(imageDot);
            }
            //2.ViewPager动态绑定数据
            viewPager.setAdapter(new PagerAdapter() {
                @Override
                public int getCount() {
                    //0 1 2 3 4  5%data.size()  6  7 8
                    //0 1 2 3 4 0 1 2 3 4 0 1 2 3 4

                    return Integer.MAX_VALUE;
                }

                @Override
                public boolean isViewFromObject(View view, Object object) {
                    return view == object;
                }

                @Override
                public Object instantiateItem(ViewGroup container, int position) {
                    View view = listContainer.get(position%listContainer.size());
                    container.addView(view);
                    return view;
                }

                @Override
                public void destroyItem(ViewGroup container, int position, Object object) {
                    container.removeView((View) object);
                }
            });
            //4.ViewPager注册监听,动态修改小圆点的选中项
            viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
                //动画过程当中的处理,可以时时获取手横向移动的距离单位
                @Override
                public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

                }

                //小圆点动态改变的逻辑
                @Override
                public void onPageSelected(int position) {
                    for (int i = 0; i < dotsContainer.getChildCount(); i++) {
                        ImageView imageView = (ImageView) dotsContainer.getChildAt(i);
                        if(i == position%listContainer.size()){
                            imageView.setImageResource(R.mipmap.home_img_ratio_selected);
                        }else {
                            imageView.setImageResource(R.mipmap.home_img_ratio);
                        }
                    }
                    handler.sendEmptyMessageDelayed(position, 1000);

                }

                @Override
                public void onPageScrollStateChanged(int state) {

                }
            });

        }
        return convertView;
    }

    private View drawType1(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.fragment_search_hot, parent, false);
        }
        LinearLayout hotlayout = (LinearLayout) convertView.findViewById(R.id.container_image);
        //添加图片的数据源
        if(searchHotBean != null){
            int size = searchHotBean.getData().size();
            for (int i = 0; i < size; i++) {
                //自己定义的单个布局的获取
                View singleView = mInflater.inflate(R.layout.fragment_search_hot_image,parent,false);
                ImageView imageView = (ImageView) singleView.findViewById(R.id.image_type1);
                TextView textView = (TextView) singleView.findViewById(R.id.text_type1);
                SearchHotBean.DataBean dataBean = searchHotBean.getData().get(i);
                textView.setText(dataBean.getKeyword());
                Log.d("123","url-->"+dataBean.getImgs().get(0));
                Glide.with(mContext).
                        load(dataBean.getImgs().get(0)).
                        centerCrop()
                        .into(imageView);

                //整个单行添加到容器中
                hotlayout.addView(singleView);
            }
        }
        return convertView;
    }


    /**
     * 返回定义绘制行的种类的个数
     * @return
     */
    @Override
    public int getViewTypeCount() {
        return 3;
    }

    //根据position不同,设定的不同的类型
    @Override
    public int getItemViewType(int position) {
        if (position == 0) {
            return TYPE1;
        } else if (position == 1) {
            return TYPE2;
        } else { //其他情况返回第三种布局的类型
            return TYPE3;
        }
    }

    /***************************对外提供setter方法，用来设置数据源***********************************/

    /**
     * 对外提供setter方法，用来设置数据源
     * @param searchHot
     */
    public void setsearchHot(SearchHotBean searchHot) {
        this.searchHotBean = searchHot;
        count++;
        notifyDataSetChanged(); //同时刷新适配器,getView方法的更新
    }

    public void setsearchMoreBean(SearchMoreBean searchMoreBean) {
        this.searchMoreBean = searchMoreBean;
        count++;
        notifyDataSetChanged();//同时刷新适配器,getView方法的更新
    }

    public void setsearchMainListBean(SearchMainListBean searchMainListBean) {
        this.searchMainListBean = searchMainListBean;
        //里面有多少个data加上多上个数量
        if (searchMainListBean != null) {
            count += searchMainListBean.getData().size();
        }
        notifyDataSetChanged(); //同时刷新适配器,getView方法的更新
    }
}
