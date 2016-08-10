package com.example.chongzi007.cleverchongzi;

import android.os.Bundle;
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
}
