package com.example.chongzi007.cleverchongzi;

import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ImageView;
import android.widget.LinearLayout;

import java.util.ArrayList;

public class GuideActivity extends AppCompatActivity {
     private ViewPager guidepager;
     private ArrayList<ImageView>list=new ArrayList<ImageView>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_guide);


        initData();
        initUI();



    }

    private void initData() {
        ImageView iv1=new ImageView(this);
        iv1.setBackgroundResource(R.mipmap.guide_1);
        ImageView iv2=new ImageView(this);
        iv2.setBackgroundResource(R.mipmap.guide_2);
        ImageView iv3=new ImageView(this);
        iv3.setBackgroundResource(R.mipmap.guide_3);

        list.add(iv1);
        list.add(iv2);
        list.add(iv3);


    }


    private void initUI() {

        guidepager = (ViewPager) findViewById(R.id.vp_viewpager);
        LinearLayout ll_point= (LinearLayout) findViewById(R.id.ll_point);
        for(int i=0;i<list.size();i++){
            View view =new View(this);
            view.setBackgroundResource(R.drawable.shape_point_gray);
            LinearLayout.LayoutParams params=new LinearLayout.LayoutParams(35,35);
            if(i>0){
              params.leftMargin=35;
            }
            view.setLayoutParams(params);
            ll_point.addView(view);
        }

        guidepager.setAdapter(new myPagerAdapter());

    }

    class myPagerAdapter extends PagerAdapter{


        @Override
        public int getCount() {
            return list.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view==object;
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
