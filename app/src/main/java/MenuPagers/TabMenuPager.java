package MenuPagers;

import android.app.Activity;
import android.content.Intent;
import android.net.http.SslCertificate;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.chongzi007.cleverchongzi.NewsDetailActivity;
import com.lidroid.xutils.BitmapUtils;
import com.example.chongzi007.cleverchongzi.R;
import com.google.gson.Gson;
import com.viewpagerindicator.CirclePageIndicator;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import Views.RefreshListView;
import domain.NewsData;
import domain.TabData;

/**
 * 页签详情页
 */
public class TabMenuPager extends baseMenuPager implements ViewPager.OnPageChangeListener {
    private NewsData.NewsTabData mTabData;
    private TextView tv_title;
    private TabData tabData;
    private ViewPager vp_tab;
    private CirclePageIndicator circlePageIndicator;
    private View headerView;
    private RefreshListView listnews;
    private String data;
    private String mMoreUrl;// 更多页面的地址
    private String moredata;
    private TabMenuPager.listAdapter listAdapter;
    private ArrayList<TabData.TabNewsData> newsList;

    public TabMenuPager(Activity activity, NewsData.NewsTabData newsTabData) {
        super(activity);
        mTabData = newsTabData;//这里得到的是children下的一个个子对象

    }

    @Override
    public View initViews() {
        View view = View.inflate(mactivity, R.layout.tab_menu, null);
        headerView = View.inflate(mactivity, R.layout.list_topnews_headerview, null);
        listnews = (RefreshListView) view.findViewById(R.id.list_topnews);
        listnews.addHeaderView(headerView);
        vp_tab = (ViewPager) view.findViewById(R.id.vp_tab);
        tv_title = (TextView) view.findViewById(R.id.tv_title);
        circlePageIndicator = (CirclePageIndicator) view.findViewById(R.id.vg_cirle);
        listnews.setOnRefreshListener(new RefreshListView.OnRefreshListener() {
            @Override
            public void onRefresh() {
                pasre(data,false);
                listnews.onRefreshComplete(true);
            }

            @Override
            public void onLoadMore() {
                if (mMoreUrl != null) {
                    try {
                        getMoreDataFromServer();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    listnews.onRefreshComplete(false);
                } else {
                    Toast.makeText(mactivity, "最后一页了", Toast.LENGTH_SHORT)
                             .show();
                    listnews.onRefreshComplete(false);// 收起加载更多的布局
                }
            }


        });

        listnews.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
               int mpostion=position-listnews.getHeaderViewsCount();
                String murl="https://www.baidu.com";
                Intent intent=new Intent(mactivity, NewsDetailActivity.class);
                intent.putExtra("url",murl);
                mactivity.startActivity(intent);
            }
        });

        return view;
    }


    @Override
    public void initData() throws IOException {

        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        InputStream is = mactivity.getClass().getClassLoader().getResourceAsStream("assets/" + mTabData.url);
        int len = 0;
        byte[] bys = new byte[1024];
        while ((len = is.read(bys)) != -1) {
            bos.write(bys, 0, len);
            bos.flush();
        }
        is.close();
        bos.close();
        //这是模拟服务器请求回来的数据；
        data = bos.toString();

        pasre(data,false);




    }

    private void pasre(String data,boolean isMore) {

        Gson gson = new Gson();
        tabData = gson.fromJson(data, TabData.class);

        // 处理下一页链接
        String more = tabData.data.more;

        if (!TextUtils.isEmpty(more)) {
            mMoreUrl = "assets/MyService" + more;
        } else {
            mMoreUrl = null;
        }


        if(!isMore){
            newsList = tabData.data.news;
            vp_tab.setAdapter(new TopnewsAdapter());

            circlePageIndicator.setViewPager(vp_tab);

            circlePageIndicator.setSnap(true);

            circlePageIndicator.setOnPageChangeListener(this);

            circlePageIndicator.onPageSelected(0);
            listAdapter = new listAdapter();
            listnews.setAdapter(listAdapter);

            tv_title.setText(tabData.data.topnews.get(0).title);

        }else{

            ArrayList<TabData.TabNewsData> news = tabData.data.news;
            newsList.addAll(news);
            listAdapter.notifyDataSetChanged();

        }



    }

    /**
     * 加载更多数据方法
     */
    private void getMoreDataFromServer() throws IOException {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        InputStream is = mactivity.getClass().getClassLoader().getResourceAsStream(mMoreUrl);
        int len = 0;
        byte[] bys = new byte[1024];
        while ((len = is.read(bys)) != -1) {
            bos.write(bys, 0, len);
            bos.flush();
        }
        is.close();
        bos.close();
        //这是模拟服务器请求回来的数据；
        moredata = bos.toString();
       pasre(moredata,true);

    }
    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        tv_title.setText(tabData.data.topnews.get(position).title);


    }

    @Override
    public void onPageScrollStateChanged(int state) {


    }

    class TopnewsAdapter extends PagerAdapter {

        private BitmapUtils bitmapUtils;


        public TopnewsAdapter() {
            bitmapUtils = new BitmapUtils(mactivity);
            bitmapUtils.configDefaultLoadingImage(R.mipmap.topnews_item_default);

        }

        @Override
        public int getCount() {
            return tabData.data.topnews.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            ImageView image = new ImageView(mactivity);
            image.setScaleType(ImageView.ScaleType.FIT_XY);
            bitmapUtils.display(image, "assets/" + tabData.data.topnews.get(position).topimage);
            container.addView(image);
            return image;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }


    }

    class listAdapter extends BaseAdapter {
        private BitmapUtils utils;

        public listAdapter() {
            utils = new BitmapUtils(mactivity);
            utils.configDefaultLoadingImage(R.mipmap.ic_launcher);
        }

        @Override
        public int getCount() {
            return newsList.size();
        }

        @Override
        public Object getItem(int position) {
            return newsList.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            ViewHolder holder;
            if (convertView == null) {
                convertView = View.inflate(mactivity, R.layout.list_news_item,
                        null);
                holder = new ViewHolder();
                holder.ivPic = (ImageView) convertView
                        .findViewById(R.id.iv_pic);
                holder.tvTitle = (TextView) convertView
                        .findViewById(R.id.tv_title);
                holder.tvDate = (TextView) convertView
                        .findViewById(R.id.tv_date);

                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }


            holder.tvTitle.setText(newsList.get(position).title);
            holder.tvDate.setText(newsList.get(position).pubdate);

            utils.display(holder.ivPic, "assets/" + newsList.get(position).listimage);

            return convertView;

        }

    }

    static class ViewHolder {
        public TextView tvTitle;
        public TextView tvDate;
        public ImageView ivPic;
    }
}