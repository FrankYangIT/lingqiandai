package com.frank.mybizhi.utils;

import com.flyco.tablayout.listener.CustomTabEntity;
import com.frank.mybizhi.app.MyApp;

/**
 * Created by Frank on 2016/10/11.
 */
public class MyCustomTabEntity implements CustomTabEntity {

    private String tabTitle;
    private int resTabSelectedIcon;
    private int resTabUnselectedIcon;

    /**
     *
     * @param tabTitle
     * @param resTabSelectedIcon
     * @param resTabUnselectedIcon
     */
    public MyCustomTabEntity(String tabTitle, int resTabSelectedIcon, int resTabUnselectedIcon) {
        this.tabTitle = tabTitle;
        this.resTabSelectedIcon = resTabSelectedIcon;
        this.resTabUnselectedIcon = resTabUnselectedIcon;
    }

    /**
     *
     * @param resTabTitle
     * @param resTabSelectedIcon
     * @param resTabUnselectedIcon
     */
    public MyCustomTabEntity( int resTabTitle, int resTabSelectedIcon, int resTabUnselectedIcon) {
        this.tabTitle = MyApp.mContext.getResources().getString(resTabTitle);
        this.resTabSelectedIcon = resTabSelectedIcon;
        this.resTabUnselectedIcon = resTabUnselectedIcon;
    }



    @Override
    public String getTabTitle() {
        return tabTitle;
    }

    @Override
    public int getTabSelectedIcon() {
        return resTabSelectedIcon;
    }

    @Override
    public int getTabUnselectedIcon() {
        return resTabUnselectedIcon;
    }
}
