package com.example.chongzi007.cleverchongzi;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import java.util.ArrayList;

import Utils.prfUtils;

public class GuideActivity extends Activity {
    private ViewPager guidepager;
    private ImageView iv_point;
    private ArrayList<ImageView> list = new ArrayList<ImageView>();
    private int width;
    private Button btn_start1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_guide);


        initData();
        initUI();


    }

    private void initData() {
        ImageView iv1 = new ImageView(this);
        iv1.setBackgroundResource(R.mipmap.guide_1);
        ImageView iv2 = new ImageView(this);
        iv2.setBackgroundResource(R.mipmap.guide_2);
        ImageView iv3 = new ImageView(this);
        iv3.setBackgroundResource(R.mipmap.guide_3);

        list.add(iv1);
        list.add(iv2);
        list.add(iv3);


    }


    private void initUI() {
        btn_start1 = (Button) findViewById(R.id.btn_start);
        SharedPreferences mpref = getSharedPreferences("config", MODE_PRIVATE);
        btn_start1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                prfUtils.setBoolean(GuideActivity.this,"is_guide_user",true);
                startActivity(new Intent(GuideActivity.this,MainActivity.class));
                finish();
            }
        });
        guidepager = (ViewPager) findViewById(R.id.vp_viewpager);
        final LinearLayout ll_point = (LinearLayout) findViewById(R.id.ll_point);
        for (int i = 0; i < list.size(); i++) {
            View view = new View(this);
            view.setBackgroundResource(R.drawable.shape_point_gray);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(35, 35);
            if (i > 0) {
                params.leftMargin = 35;
            }
            view.setLayoutParams(params);
            ll_point.addView(view);
        }

        ll_point.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                ll_point.getViewTreeObserver().removeGlobalOnLayoutListener(this);
                width = ll_point.getChildAt(1).getLeft() - ll_point.getChildAt(0).getLeft();
                //  Log.e("width===",String.valueOf(width));

            }
        });
        iv_point = (ImageView) findViewById(R.id.iv_point);

        guidepager.setAdapter(new myPagerAdapter());
        guidepager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            //滚动时调用
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                int leftwidth = (int)  (width * positionOffset);
                RelativeLayout.LayoutParams Params= (RelativeLayout.LayoutParams) iv_point.getLayoutParams();
                Params.leftMargin=leftwidth+(position*width);
                iv_point.setLayoutParams(Params);
            }

            //某个页面被选中
            @Override
            public void onPageSelected(int position) {
                //判断当前是否需要显示按钮
                if(position==list.size()-1){
                    btn_start1.setVisibility(View.VISIBLE);
                }else{
                    btn_start1.setVisibility(View.INVISIBLE);
                }
            }

            //滑动状态变化时调用
            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

    }

    class myPagerAdapter extends PagerAdapter {


        @Override
        public int getCount() {
            return list.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {

            container.addView(list.get(position));
            return list.get(position);
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {

            container.removeView((View) object);

        }


    }

}
