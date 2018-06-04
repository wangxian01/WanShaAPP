package com.example.l.wanshaapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.l.wanshaapp.R;

import java.util.List;
import java.util.Map;

public class pinglunAdapter extends BaseAdapter{
    private List<Map<String, Object>> dataList;
    private Context context;
    private int resource;

    /**
     * 有参构造
     *
     * @param context
     *            界面
     * @param dataList
     *            数据
     * @param resource
     *            列表项资源文件
     */
    public pinglunAdapter(Context context, List<Map<String, Object>> dataList,
                          int resource) {
        this.context = context;
        this.dataList = dataList;
        this.resource = resource;
    }

    @Override
    public int getCount() {
        return dataList.size();
    }

    @Override
    public Object getItem(int index) {

        return dataList.get(index);
    }

    @Override
    public long getItemId(int index) {
        return index;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // 声明内部类
        UtilListpinglun util = null;
        // 中间变量
        final int flag = position;
        /**
         * 根据listView工作原理，列表项的个数只创建屏幕第一次显示的个数。
         * 之后就不会再创建列表项xml文件的对象，以及xml内部的组件，优化内存，性能效率
         */
        if (convertView == null) {
            util = new UtilListpinglun();
            // 给xml布局文件创建java对象
            LayoutInflater inflater = LayoutInflater.from(context);
            convertView = inflater.inflate(resource, null);

            // 增加额外变量
            convertView.setTag(util);
        } else {
            util = (UtilListpinglun) convertView.getTag();
        }
        util.pinglunimage = (ImageView) convertView. findViewById(R.id.pinglunimage);
        util.pinglunID=(TextView)convertView.findViewById(R.id.pinglunID) ;
        util.pinglunDate=(TextView) convertView.findViewById(R.id.pinglunDate);
        util.pinglunxinxi=(TextView)convertView.findViewById(R.id.pinglunxinxi);
        util.view=(View)convertView.findViewById(R.id.view);
        // 获取数据显示在各组件
        Map<String, Object> map = dataList.get(position);
        util.pinglunID.setText((String) map.get("youximing"));
        //util.riqi.setText((String)map.get("riqi"));
        return convertView;

    }

}

/**
 * 内部类，用于辅助适配
 *
 * @author qiangzi
 *
 */
class UtilListpinglun{
    ImageView pinglunimage;
    TextView pinglunID;
    TextView pinglunDate;
    TextView pinglunxinxi;
    View view;


//    wuyu = (ImageView) findViewById(R.id.wuyu);
//    youximing = (TextView) findViewById(R.id.youximing);
//    pingfen = (ImageView) findViewById(R.id.pingfen);
//    xiazai = (Button) findViewById(R.id.xiazai);
//    riqi = (TextView) findViewById(R.id.riqi);
//    view = (View) findViewById(R.id.view);

}
