package Pager;

import android.app.Activity;
import android.graphics.Color;
import android.view.Gravity;
import android.widget.TextView;

import com.example.chongzi007.cleverchongzi.MainActivity;
import com.google.gson.Gson;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import Fragment.menuFragment;
import MenuPagers.baseMenuPager;
import MenuPagers.menuNewsPager;
import MenuPagers.menuPhotoPager;
import MenuPagers.menuPlayPager;
import MenuPagers.menuTitlePager;
import domain.NewsData;

/**
 * 新闻界面
 */
public class NewsPager extends BasePager {

    public NewsPager(Activity activity) {
        super(activity);
    }

    public ArrayList<baseMenuPager> menuPager;

    @Override
    public void initData() {

        tv_title.setText("新闻中心");
        TextView textView = new TextView(mActivity);
        textView.setText("新闻中心");
        textView.setTextSize(22);
        textView.setTextColor(Color.RED);
        textView.setGravity(Gravity.CENTER);
        fl_content.addView(textView);
        setSlidingMenuEnable(true);
        try {
            getDataFromService();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void getDataFromService() throws IOException {
        //因为没有服务器 我们就将就的用本地模拟一下吧
//        HttpUtils httpUtils=new HttpUtils();
//
//        httpUtils.send(HttpRequest.HttpMethod.GET, "http://pan.baidu.com/disk/home#list/path=%2Fzhbj%2FURL%2Fcategories.json", new RequestCallBack<String>() {
//            @Override
//            public void onSuccess(ResponseInfo responseInfo) {
//                 String data = (String) responseInfo.result;
//                System.out.println("返回结果是-------------------"+data+"到此结束》》》》》》》》》》》》");
//
//            }
//
//            @Override
//            public void onFailure(HttpException e, String s) {
//                Toast.makeText(mActivity,s,Toast.LENGTH_SHORT).show();
//            }
//        });
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        InputStream is = mActivity.getClass().getClassLoader().getResourceAsStream("assets/" + "MyService/categories.json");
        int len = 0;
        byte[] bys = new byte[1024];
        while ((len = is.read(bys)) != -1) {
            bos.write(bys, 0, len);
            bos.flush();
        }
        is.close();
        bos.close();
        String data = bos.toString();//这是模拟服务器请求回来的数据；
        pasre(data);
    }

    //解析数据并处理数据
    private void pasre(String data) {
        Gson gson = new Gson();
        NewsData newsData = gson.fromJson(data, NewsData.class);
        MainActivity mainActivity = (MainActivity) mActivity;
        menuFragment lefSlidingtMenu = mainActivity.getLefSlidingtMenu();
        lefSlidingtMenu.setData(newsData);
        menuPager=new ArrayList<>();
        menuPager.add(new menuNewsPager(mActivity));
        menuPager.add(new menuTitlePager(mActivity));
        menuPager.add(new menuPhotoPager(mActivity));
        menuPager.add(new menuPlayPager(mActivity));


    }

    /**
     * 设置当前页面
     */
    public void setCurrentMenuPager(int position) {

        baseMenuPager pager = menuPager.get(position);
        fl_content.removeAllViews();
        fl_content.addView(pager.mrootView);
    }


}
