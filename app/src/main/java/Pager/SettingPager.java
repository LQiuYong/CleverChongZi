package Pager;

import android.app.Activity;
import android.graphics.Color;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;

/**
 * 设置界面
 */
public class SettingPager extends BasePager {

    public SettingPager(Activity activity) {
        super(activity);
    }

    @Override
    public void initData() {

        ig_button.setVisibility(View.GONE);
        tv_title.setText("设置");
        TextView textView=new TextView(mActivity);
        textView.setText("设置");
        textView.setTextSize(22);
        textView.setTextColor(Color.RED);
        textView.setGravity(Gravity.CENTER);
        fl_content.addView(textView);
        setSlidingMenuEnable(false);

    }
}
