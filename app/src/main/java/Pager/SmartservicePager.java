package Pager;

import android.app.Activity;
import android.graphics.Color;
import android.view.Gravity;
import android.widget.TextView;

/**
 * 智慧服务
 */
public class SmartservicePager extends BasePager {

    public SmartservicePager(Activity activity) {
        super(activity);
    }

    @Override
    public void initData() {

        tv_title.setText("智慧服务");
        TextView textView=new TextView(mActivity);
        textView.setText("智慧服务");
        textView.setTextSize(22);
        textView.setTextColor(Color.RED);
        textView.setGravity(Gravity.CENTER);
        fl_content.addView(textView);
        setSlidingMenuEnable(true);
    }
}
