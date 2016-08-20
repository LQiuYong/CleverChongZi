package Pager;

import android.app.Activity;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.chongzi007.cleverchongzi.MainActivity;
import com.example.chongzi007.cleverchongzi.R;
import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;

/**
 * 五个子页面的基类布局 其实也就是尽量的把五个子布局的共同的地方抽取出来
 * 做成的一个基类
 */

public class BasePager {
    public ImageButton ig_button;
    public Activity mActivity;
    public View mrootView;
    public TextView tv_title;
    public FrameLayout fl_content;

    public BasePager(Activity activity) {
        mActivity = activity;
        initViews();
    }

    /**
     * 初始布局
     */
    public void initViews() {

        mrootView = View.inflate(mActivity, R.layout.base_pager, null);
        tv_title = (TextView) mrootView.findViewById(R.id.tv_title);
        fl_content = (FrameLayout) mrootView.findViewById(R.id.fl_content);
        ig_button = (ImageButton) mrootView.findViewById(R.id.ig_button);
        ig_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setMenuOff();
            }
        });
    }

    /**
     * 初始化数据
     */
    public void initData() {


    }

    /**
     * s设置是否显示侧滑菜单S
     *
     * @param enable
     */
    public void setSlidingMenuEnable(boolean enable) {
        MainActivity main = (MainActivity) mActivity;
        SlidingMenu slidingMenu = main.getSlidingMenu();
        if (enable) {
            slidingMenu.setTouchModeAbove(SlidingMenu.TOUCHMODE_FULLSCREEN);
        } else {
            slidingMenu.setTouchModeAbove(SlidingMenu.TOUCHMODE_NONE);

        }
    }

    /**
     * 关闭侧滑菜单
     */
    public void setMenuOff() {
        MainActivity main= (MainActivity) mActivity;
        SlidingMenu menu = main.getSlidingMenu();
        menu.toggle();
    }
}