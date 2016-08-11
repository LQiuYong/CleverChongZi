package domain;

import java.util.ArrayList;

/**
 * 新闻解析对象
 */
public class NewsData {
    public int retcode;
    public ArrayList<NewsMenuData> data;
//侧边栏的数据对象
    public class NewsMenuData {
        public String id;
        public String title;
        public int type;
        public String url;
        public ArrayList<NewsTabData> children;

    @Override
    public String toString() {
        return "title+"+title+"----"+"url"+"--"+url;
    }
}
//新闻栏的数据对象
    public class NewsTabData {
        public String id;
        public String title;
        public int type;
        public String url;

    @Override
    public String toString() {
        return "title+"+title+"----"+"url"+"--"+url;
    }
}

    @Override
    public String toString() {
        return "Data="+data;
    }
}
