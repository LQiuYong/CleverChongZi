package MenuPagers;

import android.app.Activity;
import android.graphics.Color;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import com.example.chongzi007.cleverchongzi.MainActivity;
import com.example.chongzi007.cleverchongzi.R;
import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;
import com.viewpagerindicator.TabPageIndicator;

import java.io.IOException;
import java.util.ArrayList;

import domain.NewsData;

/**
 * 侧边栏新闻的界面也就是新闻中心要显示的界面
 */
public class menuNewsPager extends baseMenuPager implements ViewPager.OnPageChangeListener{
   private ArrayList<NewsData.NewsTabData>newsTabDatas;
    private ViewPager mViewPager;
  private ArrayList<TabMenuPager>mList;
    private TabPageIndicator indicator;
    private ImageButton ig_next;

    public menuNewsPager(Activity activity, ArrayList<NewsData.NewsTabData> children) {
        super(activity);
        newsTabDatas=children;
    }

    @Override
    public View initViews() {
        View view = View.inflate(mactivity, R.layout.menu_news, null);
        mViewPager = (ViewPager) view.findViewById(R.id.vp_menuNews);
        ig_next = (ImageButton) view.findViewById(R.id.ig_next);
        indicator = (TabPageIndicator) view.findViewById(R.id.tb_indicator);
        indicator.setOnPageChangeListener(this);
        ig_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int currentItem = mViewPager.getCurrentItem();
                mViewPager.setCurrentItem(++currentItem);
            }
        });

        return view;
    }

    @Override
    public void initData() {
        mList=new ArrayList<>();
        for (int i=0;i<newsTabDatas.size();i++){
            TabMenuPager tabMenuPager=new TabMenuPager(mactivity,newsTabDatas.get(i));
            mList.add(tabMenuPager);
        }
        mViewPager.setAdapter(new MyMenuAdapter());
        indicator.setViewPager(mViewPager);//要在setAdapter之后设置才可以

    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        MainActivity main= (MainActivity) mactivity;
        SlidingMenu slidingMenu = main.getSlidingMenu();
        if(position==0){
           slidingMenu.setTouchModeAbove(SlidingMenu.TOUCHMODE_FULLSCREEN);
        }else{
            slidingMenu.setTouchModeAbove(SlidingMenu.TOUCHMODE_NONE);
        }

    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }


    class MyMenuAdapter extends PagerAdapter{
        @Override
        public CharSequence getPageTitle(int position) {
            return newsTabDatas.get(position).title;
        }

        @Override
        public int getCount() {
            return mList.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view==object;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            TabMenuPager tabMenuPager = mList.get(position);
            container.addView(tabMenuPager.mrootView);
            try {
                tabMenuPager.initData();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return tabMenuPager.mrootView;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {

            container.removeView((View) object);
        }
    }

}
