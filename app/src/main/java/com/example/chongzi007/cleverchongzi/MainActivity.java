package com.example.chongzi007.cleverchongzi;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.Window;

import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;
import com.jeremyfeinstein.slidingmenu.lib.app.SlidingFragmentActivity;

import Fragment.contentFragment;
import Fragment.menuFragment;

public class MainActivity extends SlidingFragmentActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);
        setBehindContentView(R.layout.left_menu);//设置侧边状态菜单
        SlidingMenu slidingMenu = getSlidingMenu();//获取侧边栏对象
        slidingMenu.setTouchModeAbove(SlidingMenu.TOUCHMODE_FULLSCREEN);//设置全屏触摸
        slidingMenu.setMode(SlidingMenu.LEFT);//设置展现模式
        slidingMenu.setBehindOffset(600);

          initFragment();
    }

    private void initFragment() {
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction transaction = fm.beginTransaction();
        transaction.replace(R.id.fl_left_menu,new menuFragment(),"MENU_FTAGMENT");
        transaction.replace(R.id.fl_content,new contentFragment(),"COTENT_FRAGMENT");
        transaction.commit();

    }

    /**
     * 获取菜单栏对象
     * @return
     */
    public menuFragment getLefSlidingtMenu(){

        FragmentManager fm = getSupportFragmentManager();
        menuFragment menu_ftagment = (menuFragment) fm.findFragmentByTag("MENU_FTAGMENT");
        return  menu_ftagment;
    }

    /**
     * 获取内容栏对象
     * @return
     */
    public contentFragment getContentMenu(){

        FragmentManager fm = getSupportFragmentManager();
        contentFragment fragment = (contentFragment) fm.findFragmentByTag("COTENT_FRAGMENT");
        return  fragment;
    }
}
