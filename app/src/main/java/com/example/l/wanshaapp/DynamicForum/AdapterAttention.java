package com.example.l.wanshaapp.DynamicForum;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.l.wanshaapp.DynamicHome.CircleImageView;
import com.example.l.wanshaapp.R;
import com.squareup.picasso.Picasso;

import java.util.List;
import java.util.Map;

/**
 * Created by cap on 2018/5/21.
 */

public class AdapterAttention extends BaseAdapter {
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
    public AdapterAttention(Context context, List<Map<String, Object>> dataList,
                        int resource) {
        this.context = context;
        this.dataList = dataList;
        this.resource = resource;

    }

    @Override
    public int getCount() {
        return 2;
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
        // 声明内部类
        UtilAttention util = null;
        // 中间变量
        final int flag = index;
        /**
         * 根据listView工作原理，列表项的个数只创建屏幕第一次显示的个数。
         * 之后就不会再创建列表项xml文件的对象，以及xml内部的组件，优化内存，性能效率
         */
        if (view == null) {
            util = new UtilAttention();
            // 给xml布局文件创建java对象
            LayoutInflater inflater = LayoutInflater.from(context);
            view = inflater.inflate(resource, null);
            util.mItemForumImg = (ImageView) view.findViewById(R.id.item_forum_img);
            util.mItemForumName = (TextView) view.findViewById(R.id.item_forum_name);
            util.mItemForumTime = (TextView) view.findViewById(R.id.item_forum_time);
            // 增加额外变量
            view.setTag(util);
        } else {
            util = (UtilAttention) view.getTag();
        }
        // 获取数据显示在各组件
        final Map<String, Object> map = dataList.get(index);
        /**
         * 设置头像路径
         * */
        Picasso.with(view.getContext())
                .load(String.valueOf(map.get("ForumPortrait")))
                .into(util.mItemForumImg);
        util.mItemForumName.setText(String.valueOf(map.get("ForumName")));
        util.mItemForumTime.setText(String.valueOf(map.get("ForumTime")));
        Log.e("测试：", String.valueOf(map.get("ForumTime")));
        return view;

    }

}

/**
 * 内部类，用于辅助适配
 *
 * @author qiangzi
 *
 */
class UtilAttention{

     ImageView mItemForumImg;
     TextView mItemForumName;
     TextView mItemForumTime;



}