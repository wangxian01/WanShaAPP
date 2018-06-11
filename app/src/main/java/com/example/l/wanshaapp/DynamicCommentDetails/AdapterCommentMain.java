package com.example.l.wanshaapp.DynamicCommentDetails;

import android.content.Context;
import android.support.v4.view.NestedScrollingChildHelper;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;


import com.example.l.wanshaapp.DynamicHome.CircleImageView;
import com.example.l.wanshaapp.R;

import org.w3c.dom.Comment;

import java.util.List;
import java.util.Map;

/**
 * Created by cap on 2018/5/9.
 */

public class AdapterCommentMain extends BaseAdapter {
    private List<Map<String, Object>> dataList;
    private Context context;
    private int resource;
    private NestedScrollingChildHelper mScrollingChildHelper;


    /***
     * 有参构造
     *
     * @param context  界面
     * @param dataList 数据
     * @param resource 列表项资源文件
     */
    public AdapterCommentMain(Context context, List<Map<String, Object>> dataList,
                              int resource) {

        this.context = context;
        this.dataList = dataList;
        this.resource = resource;

    }

    /**
     * by moos on 2018/04/20
     * func:评论成功后插入一条数据
     * @param index
     * @param dataList 新的评论数据
     */
    public void addTheCommentData(int index, List<Map<String, Object>> dataList) {
        Map<String, Object> map = dataList.get(index);
        dataList.add(map);
        notifyDataSetChanged();
        Log.e("测试：", (String) map.get("CommentText"));
        Log.e("测试：", (String) map.get("UpId"));
    }

    @Override
    public int getCount() {
        if (dataList == null)
            return 0;
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

            // 声明内部类
            UtilComment util = null;
            // 中间变量
            final int flag = index;
            /**
             * 根据listView工作原理，列表项的个数只创建屏幕第一次显示的个数。
             * 之后就不会再创建列表项xml文件的对象，以及xml内部的组件，优化内存，性能效率
             */
            if (view == null) {
                util = new UtilComment();
                // 给xml布局文件创建java对象
                LayoutInflater inflater = LayoutInflater.from(context);
                view = inflater.inflate(resource, null);

                // 指向布局
                util.mCommentItemLogo = (CircleImageView)view.findViewById(R.id.comment_item_logo);
                util.mCommentItemUserName = (TextView) view.findViewById(R.id.comment_item_userName);
                util.mCommentItemTime = (TextView) view.findViewById(R.id.comment_item_time);
                util.mCommentItemLike = (ImageView)view. findViewById(R.id.comment_item_like);
                util.mCommentItemContent = (TextView) view.findViewById(R.id.comment_item_content);
                util.mCommentItemUnfod = (TextView) view.findViewById(R.id.comment_item_unfod);
                // 增加额外变量

                view.setTag(util);
            } else {
                util = (UtilComment) view.getTag();
            }
            final Map<String, Object> map = dataList.get(index);

            util.mCommentItemUserName.setText((String) map.get("UpId"));
            util.mCommentItemContent.setText((String) map.get("CommentText"));

        return view;
    }



}

/**
 * 内部类，用于辅助适配
 *
 * @author qiangzi
 *
 */
class UtilComment {
    CircleImageView mCommentItemLogo;
    TextView mCommentItemUserName;
    TextView mCommentItemTime;
    ImageView mCommentItemLike;
     TextView mCommentItemContent;
    TextView mCommentItemUnfod;

}
