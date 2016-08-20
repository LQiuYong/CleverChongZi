package Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.chongzi007.cleverchongzi.MainActivity;
import com.example.chongzi007.cleverchongzi.R;
import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;

import java.io.IOException;
import java.util.ArrayList;

import Pager.NewsPager;
import domain.NewsData;

/**
 * 侧滑菜单栏
 */
public class menuFragment extends BaseFragment {
    private ListView lv_menu;
    private ArrayList<NewsData.NewsMenuData> newsMenuDatas;
    private menuFragment.menuAdapter Adapter;
    private int currentPostion;

    @Override
    public View initViews() {
        View view = View.inflate(mactivity, R.layout.fragment_left_menu, null);
        lv_menu = (ListView) view.findViewById(R.id.lv_menu_listview);
        return view;

    }

    private void setCurrentMenuItem(int position) throws IOException {
        //我们通过获取newspager对象去调用他的方法去设置
        //首先获取mactivity对象
        MainActivity mainActivity= (MainActivity) mactivity;
        contentFragment contentMenu = mainActivity.getContentMenu();
        NewsPager newsMenu = contentMenu.getNewsMenu();
        newsMenu.setCurrentMenuPager(position);

    }
    @Override
    public void initData() {
        lv_menu.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                currentPostion=position;
                Adapter.notifyDataSetChanged();
                //跳转页面
                try {
                    setCurrentMenuItem(position);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                setMenuOff();
            }


        });


    }

    /**
     * 关闭侧滑菜单
     */
    public void setMenuOff() {
        MainActivity main= (MainActivity) mactivity;
        SlidingMenu menu = main.getSlidingMenu();
        menu.toggle();
    }




    public void setData(NewsData data) {
        newsMenuDatas = data.data;
        Adapter = new menuAdapter();
        lv_menu.setAdapter(Adapter);

    }

    class menuAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return newsMenuDatas.size();
        }

        @Override
        public Object getItem(int position) {
            return newsMenuDatas.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View view = View.inflate(mactivity, R.layout.listview_menu_item, null);
            TextView tv_title = (TextView) view.findViewById(R.id.tv_title);
            tv_title.setText(newsMenuDatas.get(position).title);
            if(currentPostion==position){
                //显示红色
                tv_title.setEnabled(true);
            }else{
                //显示白色
                tv_title.setEnabled(false);
            }
            return view;
        }
    }

}
