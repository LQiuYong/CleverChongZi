package MenuPagers;

import android.app.Activity;
import android.graphics.Color;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;

/**
 * 组图的界面
 */
public class menuPhotoPager extends baseMenuPager {

    public menuPhotoPager(Activity activity) {
        super(activity);
    }

    @Override
    public View initViews() {
        TextView textView=new TextView(mactivity);
        textView.setText("这是组图哦");
        textView.setTextSize(22);
        textView.setTextColor(Color.RED);
        textView.setGravity(Gravity.CENTER);
        return textView;
    }


}
