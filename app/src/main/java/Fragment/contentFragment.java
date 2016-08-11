package Fragment;

import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.example.chongzi007.cleverchongzi.R;

import java.util.ArrayList;

import Pager.BasePager;
import Pager.HomePager;
import Pager.NewsPager;
import Pager.OfficePager;
import Pager.SettingPager;
import Pager.SmartservicePager;


/**
 *页面
 *
 */
public class contentFragment extends BaseFragment {
    private RadioGroup rg_group;
    public RadioButton rb_home;
    public ViewPager vp_content;
    private ArrayList<BasePager> pagerlist;

    @Override
    public View initViews() {
        View view = View.inflate(mactivity, R.layout.fragment_content, null);
        vp_content = (ViewPager) view.findViewById(R.id.vp_content);
        rb_home = (RadioButton) view.findViewById(R.id.rb_home);
        rg_group= (RadioGroup) view.findViewById(R.id.rg_group);
        return view;
    }

    @Override
    public void initData() {
        rb_home.setChecked(true);//默认选首页
        pagerlist = new ArrayList<BasePager>();
        pagerlist.add(new HomePager(mactivity));
        pagerlist.add(new NewsPager(mactivity));
        pagerlist.add(new SmartservicePager(mactivity));
        pagerlist.add(new OfficePager(mactivity));
        pagerlist.add(new SettingPager(mactivity));


        //给viewpager设置内容
        vp_content.setAdapter(new mpagerAdapter());

        rg_group.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId){
                    case R.id.rb_home:
                        vp_content.setCurrentItem(0);
                        break;
                    case R.id.rb_news:
                        vp_content.setCurrentItem(1);
                        break;
                    case R.id.rb_smartservice:
                        vp_content.setCurrentItem(2);
                        break;
                    case R.id.rb_office:
                        vp_content.setCurrentItem(3);
                        break;
                    case R.id.rb_settings:
                        vp_content.setCurrentItem(4);
                        break;

                }
            }
        });
        pagerlist.get(0).initData();

        vp_content.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                pagerlist.get(position).initData();
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

    }


        class mpagerAdapter extends PagerAdapter{
            @Override
            public int getCount() {
                return pagerlist.size();
            }

            @Override
            public boolean isViewFromObject(View view, Object object) {
                return view == object;
            }

            @Override
            public Object instantiateItem(ViewGroup container, int position) {

                container.addView(pagerlist.get(position).mrootView);
                //pagerlist.get(position).initData();
                return pagerlist.get(position).mrootView;

            }

            @Override
            public void destroyItem(ViewGroup container, int position, Object object) {
                container.removeView((View) object);
            }
        }

    public NewsPager getNewsMenu(){
         return (NewsPager) pagerlist.get(1);
    }
    }

