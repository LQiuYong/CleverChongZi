package Pager;

import android.app.Activity;
import android.graphics.Color;
import android.view.Gravity;
import android.widget.TextView;

/**
 * 政务服务界面
 */
public class OfficePager extends BasePager {

    public OfficePager(Activity activity) {
        super(activity);
    }

    @Override
    public void initData() {

        tv_title.setText("政务");
        TextView textView=new TextView(mActivity);
        textView.setText("政务");
        textView.setTextSize(22);
        textView.setTextColor(Color.RED);
        textView.setGravity(Gravity.CENTER);
        fl_content.addView(textView);
        setSlidingMenuEnable(true);
    }
}
