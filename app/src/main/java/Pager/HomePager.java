package Pager;

import android.app.Activity;
import android.graphics.Color;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;



/**
 * 首页界面
 */
public class HomePager extends BasePager {

    public HomePager(Activity activity) {
        super(activity);
    }

    @Override
    public void initData() {
        ig_button.setVisibility(View.GONE);
        tv_title.setText("智慧虫子");
        TextView textView=new TextView(mActivity);
        textView.setText("智慧首页");
        textView.setTextSize(22);
        textView.setTextColor(Color.RED);
        textView.setGravity(Gravity.CENTER);
        fl_content.addView(textView);
        setSlidingMenuEnable(false);

    }



}
