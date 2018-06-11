package com.example.l.wanshaapp.adapter;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.l.wanshaapp.R;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import java.util.List;
import java.util.Map;

public class jiemiAdapter extends BaseAdapter{

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
    public jiemiAdapter(Context context, List<Map<String, Object>> dataList,
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
        UtilList util=null ;
        // 中间变量
        final int flag = position;
        /**
         * 根据listView工作原理，列表项的个数只创建屏幕第一次显示的个数。
         * 之后就不会再创建列表项xml文件的对象，以及xml内部的组件，优化内存，性能效率
         */
        if (convertView == null) {
            util = new UtilList();
            // 给xml布局文件创建java对象
            LayoutInflater inflater = LayoutInflater.from(context);
            convertView = inflater.inflate(resource, null);

            // 增加额外变量
            convertView.setTag(util);
        } else {
            util = (UtilList) convertView.getTag();
        }
        util.youximing = (TextView)convertView. findViewById(R.id.youximing);
        util.jianjie=(TextView)convertView.findViewById(R.id.jianjie) ;
        util.jiemi_image=(ImageView) convertView.findViewById(R.id.wuyu);
        util.fenlei1dongzuo= (ImageView) convertView.findViewById(R.id.fenlei1dongzuo);
        util.pingfen=(ImageView) convertView.findViewById(R.id.pingfen);
        util.xiazai=(Button) convertView.findViewById(R.id.xiazai);
        util.view=(View)convertView.findViewById(R.id.view);
        // 获取数据显示在各组件
        Map<String, Object> map = dataList.get(position);


        //设置视频缩略图
      Picasso.with(convertView.getContext())
                .load(String.valueOf(map.get("ChoicenessViodeoview")))
                .into(util.jiemi_image);

        try {

            util.jiemi_image.setImageResource(Integer.parseInt(String.valueOf(map.get("jiemi_image"))));
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        util.youximing.setText((String) map.get("youximing"));
        util.jianjie.setText((String) map.get("jianjie"));
        //util.riqi.setText((String)map.get("riqi"));
        //util.wuyu.setImageResource(R.drawable.wuyu);
        return convertView;

    }

}

/**
 * 内部类，用于辅助适配
 *
 * @author qiangzi
 *
 */
class UtilList{
    TextView jianjie;
     ImageView jiemi_image;
    TextView youximing;
   ImageView pingfen;
     Button xiazai;
    ImageView fenlei1dongzuo;
     View view;


//    wuyu = (ImageView) findViewById(R.id.wuyu);
//    youximing = (TextView) findViewById(R.id.youximing);
//    pingfen = (ImageView) findViewById(R.id.pingfen);
//    xiazai = (Button) findViewById(R.id.xiazai);
//    riqi = (TextView) findViewById(R.id.riqi);
//    view = (View) findViewById(R.id.view);

}
