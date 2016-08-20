package Views;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.MotionEvent;

/**
 * 自己处理事件的wiew pager
 */
public class TouchViewPager extends ViewPager {
    public TouchViewPager(Context context) {
        super(context);

    }

    public TouchViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
    }


    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        int currentItem = getCurrentItem();
        if(currentItem==0){
            getParent().requestDisallowInterceptTouchEvent(false);
        }else{
            getParent().requestDisallowInterceptTouchEvent(true);
        }

        return super.dispatchTouchEvent(ev);
    }
}
