package com.example.l.wanshaapp.DynamicForum;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.l.wanshaapp.DynamicHome.CircleImageView;

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

            // 增加额外变量
            view.setTag(util);
        } else {
            util = (UtilAttention) view.getTag();
        }

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

    CircleImageView mFocusPortrait;
    TextView mFocusName;
    TextView mFocusTime;
    TextView mFocusLz;
    TextView mFocusBlueName;
    TextView mFocusTxtMain;
    TextView mFocusText;
    TextView mFocusTextmin;
    ImageView mFocusLike;
    TextView mFocusTextLike;
    ImageView mFocusComments;
    TextView mFocusTextComments;
    ImageView mFocusDropdown;

//    mFocusPortrait = (CircleImageView) findViewById(R.id.focus_portrait);
//    mFocusName = (TextView) findViewById(R.id.focus_name);
//    mFocusTime = (TextView) findViewById(R.id.focus_time);
    //mFocusDropdown = (ImageView) findViewById(R.id.focus_dropdown);
//    mFocusLz = (TextView) findViewById(R.id.focus_lz);
//    mFocusBlueName = (TextView) findViewById(R.id.focus_blue_name);
//    mFocusTxtMain = (TextView) findViewById(R.id.focus_txt_main);
//    mFocusText = (TextView) findViewById(R.id.focus_text);
//    mFocusTextmin = (TextView) findViewById(R.id.focus_textmin);
//    mFocusLike = (ImageView) findViewById(R.id.focus_like);
//    mFocusTextLike = (TextView) findViewById(R.id.focus_text_like);
//    mFocusComments = (ImageView) findViewById(R.id.focus_comments);
//    mFocusTextComments = (TextView) findViewById(R.id.focus_text_comments);

}