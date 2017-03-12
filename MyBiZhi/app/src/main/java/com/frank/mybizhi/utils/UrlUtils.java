package com.frank.mybizhi.utils;

/**
 * 网址常量维护
 * Created by Administrator on 2016/10/12.
 */

public class UrlUtils {

    //主网址，将主网址提出方便维护
    public static final String  URL_BASICURL = "http://bz.budejie.com/";

    //推荐_最新下载网址
    public static final String URL_RECOMMEND_LASTEST = URL_BASICURL + "?typeid=2&ver=3.4.3&no_cry=1&client=android&c=wallPaper&a=wallPaperNew&index=1&size=60&bigid=0";
    //推荐_热门下载网址
    public static final String URL_RECOMMEND_HOT = URL_BASICURL + "?typeid=2&ver=3.4.3&no_cry=1&client=android&c=wallPaper&a=hotRecent&index=2&size=60&bigid=0";
   //推荐_随机下载
    public static final String URL_RECOMMEND_RANDOM = URL_BASICURL + "?typeid=2&ver=3.4.3&no_cry=1&client=android&c=wallPaper&a=random&bigid=0";

    //分类主列表界面网址
    public static final String URL_CATEGORY_MAIN =URL_BASICURL + "?typeid=2&ver=3.4.3&no_cry=1&client=android&c=wallPaper&a=category";
    //分类_美女图片界面_最新网址
    public static final String URL_CATEGORY_BUAUTY_LASTEST =URL_BASICURL + "?typeid=2&ver=3.4.3&no_cry=1&client=android&c=wallPaper&a=wallPaperNew&index=1&size=60&bigid=1042";
    //分类_美女图片界面_热门网址
    public static final String URL_CATEGORY_BUAUTY_HOT =URL_BASICURL + "?typeid=2&ver=3.4.3&no_cry=1&client=android&c=wallPaper&a=hotRecent&index=1&size=60&bigid=1042";
    //分类_美女图片界面_随机网址
    public static final String URL_CATEGORY_BUAUTY_RANDOM =URL_BASICURL + "?typeid=2&ver=3.4.3&no_cry=1&client=android&c=wallPaper&a=random&bigid=1042";
    //分类_创意设计图片界面_最新网址
    public static final String URL_CATEGORY_DESIGN_LASTEST =URL_BASICURL + "?typeid=2&ver=3.4.3&no_cry=1&client=android&c=wallPaper&a=wallPaperNew&index=1&size=60&bigid=1056";
    //分类_创意设计图片界面_热门网址
    public static final String URL_CATEGORY_DESIGN_HOT =URL_BASICURL + "?typeid=2&ver=3.4.3&no_cry=1&client=android&c=wallPaper&a=hotRecent&index=1&size=60&bigid=1056";
    //分类_创意设计图片界面_随机网址
    public static final String URL_CATEGORY_DESIGN_RANDOM =URL_BASICURL + "?typeid=2&ver=3.4.3&no_cry=1&client=android&c=wallPaper&a=random&bigid=1056";

    //搜索_热门搜索
    public static final String URL_SEARCH_HOT_SEARCH =URL_BASICURL + "?typeid=2&ver=3.4.3&no_cry=1&client=android&c=search&a=hot&location=1";
    //搜索_查看更多
    public static final String URL_SEARCH_CHECK_MORE =URL_BASICURL + "?typeid=2&ver=3.4.3&no_cry=1&client=android&c=topic&a=list&topictype=2&size=10";
    //搜索_下方主列表
    public static final String URL_SEARCH_THE_MAIN_LIST =URL_BASICURL + "?typeid=2&ver=3.4.3&no_cry=1&client=android&c=search&a=hot&location=3";

}
