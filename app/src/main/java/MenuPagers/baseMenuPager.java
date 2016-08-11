package MenuPagers;

import android.app.Activity;
import android.view.View;

/**
 * 侧滑栏基类布局
 */
public abstract class baseMenuPager {
    public Activity mactivity;
    public View mrootView;

    public baseMenuPager(Activity activity) {
        mactivity = activity;
        mrootView = initViews();
    }

    /**
     * 初始化界面
     *
     * @return
     */
    public abstract View initViews();


    /**
     * 初始化数据
     */

    public void initData() {

    }

}
