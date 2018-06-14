package com.example.l.wanshaapp.RankingAdapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;


import com.example.l.wanshaapp.R;
import com.example.l.wanshaapp.Rankingbean.RankingFragemntBean;
import com.example.l.wanshaapp.Rankingtools.Tools;

import java.util.List;
import java.util.Map;

/**
 * Created by 侯顺发 on 2018/5/17.
 */

public class RankingAdapter extends BaseAdapter {
    private List<Map<String, Object>> dataList;

    RankingFragemntBean homeBean = new RankingFragemntBean();
    private Context context;
//    private int resource;

    /**
     * 有参构造
     *
     * @param context
     *            界面
     * @param dataList
     *            数据
     */
    public RankingAdapter(Context context, List<Map<String, Object>> dataList) {
        this.context = context;
        this.dataList = dataList;

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
    public View getView(int index, View view, ViewGroup arg2) {

//        LayoutInflater inflater = LayoutInflater.from(context);
        view =  LayoutInflater.from(context).inflate(R.layout.list_view, arg2,false);

        ImageView imageView = (ImageView) view.findViewById(R.id.item_img1);
        ImageView imageView2 = (ImageView) view.findViewById(R.id.item_img2);
        ImageView imageView3 = (ImageView) view.findViewById(R.id.item_img3);
        ImageView imageView4 = (ImageView) view.findViewById(R.id.item_img4);
        ImageView imageView5 = (ImageView) view.findViewById(R.id.item_img5);
        imageView.setImageResource(Tools.img[index]);
//        imageView.setImageResource(homeBean.image[index]);
//        // 声明内部类
//        Util util = null;
//        // 中间变量
//        final int flag = index;
//        /**
//         * 根据listView工作原理，列表项的个数只创建屏幕第一次显示的个数。
//         * 之后就不会再创建列表项xml文件的对象，以及xml内部的组件，优化内存，性能效率
//         */
//        if (view == null) {
//            util = new Util();
//            // 给xml布局文件创建java对象
//            LayoutInflater inflater = LayoutInflater.from(context);
//            view = inflater.inflate(R.layout.list_view, arg2,false);
//            // 指向布局文件内部组件
//            util.imageView1 = (ImageView) view.findViewById(R.id.item_img1);
//            util.imageView5= (ImageView) view.findViewById(R.id.item_img5);
//            util.dateTextView = (TextView) view.findViewById(R.id.item_date);
//            util.titleTextView = (TextView) view.findViewById(R.id.item_title);
//            util.imageView = (ImageView) view.findViewById(R.id.item_img);
//            util.infoButton = (Button) view.findViewById(R.id.button_info);
//            util.titleTextView1=(TextView)view.findViewById(R.id.item_num);
//
//            view.setTag(util);
//        } else {
//            util = (Util) view.getTag();
//        }
//        // 获取数据显示在各组件
//        Map<String, Object> map = dataList.get(index);
////        util.contentTextView.setText((String) map.get("content"));
//        util.titleTextView1.setText((String)map.get("num"));
//        util.dateTextView.setText((String) map.get("date"));
//        util.titleTextView.setText((String) map.get("title"));
//        util.titleTextView.setText(homeBean.title[index]);
//        util.imageView.setImageResource((Integer) map.get("img"));
//        util.imageView1.setImageResource((Integer)map.get("img1"));
//        util.imageView5.setImageResource(Tools.img[index]);
//       // Log.e("数据为", String.valueOf(map.get("title")));
//        util.infoButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View arg0) {
//                Map<String, Object> map = dataList.get(flag);
//                String str = "序号："+map.get("num")+"\n游戏名：" + map.get("title") + "\n内容："
//                        + map.get("content") + "\n类型：" + map.get("date");
//                Toast.makeText(context, str, Toast.LENGTH_SHORT).show();
//            }
//        });
        return view;
    }

}

/**
 * 内部类，用于辅助适配
 *
 * @author qiangzi
 *
 */
//class Util {
//    ImageView imageView,imageView1,imageView5;
//    TextView dateTextView, titleTextView,titleTextView1;
//    Button infoButton;
//
//}