package com.example.l.wanshaapp.DynamicFocus;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.l.wanshaapp.DynamicChoiceness.BeanChoiceness;
import com.example.l.wanshaapp.DynamicHome.CircleImageView;
import com.example.l.wanshaapp.R;
import com.squareup.picasso.Picasso;
import com.wx.goodview.GoodView;

import java.util.List;
import java.util.Map;

/**
 * Created by cap on 2018/5/9.
 */

public class AdapterFocus extends BaseAdapter {
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
    public AdapterFocus(Context context, List<Map<String, Object>> dataList,
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
    public View getView(int index, View view, ViewGroup arg2) {
        // 声明内部类
        Utilfocus util = null;
        // 中间变量
        final int flag = index;
        /**
         * 根据listView工作原理，列表项的个数只创建屏幕第一次显示的个数。
         * 之后就不会再创建列表项xml文件的对象，以及xml内部的组件，优化内存，性能效率
         */
        if (view == null) {
            util = new Utilfocus();
            // 给xml布局文件创建java对象
            LayoutInflater inflater = LayoutInflater.from(context);
            view = inflater.inflate(resource, null);
            util.mFocusTxtMain = (TextView) view.findViewById(R.id.focus_txt_main);
            util.mFocusDropdown = (ImageView) view.findViewById(R.id.focus_dropdown);
            util.mFocusPortrait = (CircleImageView) view.findViewById(R.id.focus_portrait);
            util.mFocusName = (TextView) view.findViewById(R.id.focus_name);
            util. mFocusTime = (TextView)view. findViewById(R.id.focus_time);
            util.mFocusLz = (TextView) view.findViewById(R.id.focus_lz);
            util.mFocusBlueName = (TextView) view.findViewById(R.id.focus_blue_name);
            util.mFocusText = (TextView) view.findViewById(R.id.focus_text);
            util.mFocusTextmin = (TextView) view.findViewById(R.id.focus_textmin);
            util.mFocusLike = (ImageView)view. findViewById(R.id.focus_like);
            util.mFocusTextLike = (TextView) view.findViewById(R.id.focus_text_like);
            util.mFocusComments = (ImageView)view. findViewById(R.id.focus_comments);
            util.mFocusTextComments = (TextView)view. findViewById(R.id.focus_text_comments);
            util.mFocusImgAddress = (ImageView)view. findViewById(R.id.focus_img_address);

            // 增加额外变量
            view.setTag(util);
        } else {
            util = (Utilfocus) view.getTag();
        }
        // 获取数据显示在各组件
        final Map<String, Object> map = dataList.get(index);

        /**
         * 设置头像路径
         * */
        Picasso.with(view.getContext())
                .load(String.valueOf(map.get("Portrait")))
                .into(util.mFocusPortrait);

        /**
         * 设置焦点用户名
         * */
        util.mFocusName.setText((String) map.get("FocusName"));

        /**
         * 设置焦点时间
         * */
        util.mFocusTime.setText((String) map.get("FocusTime"));

        /**
         * 设置焦点来源
         * */
        util.mFocusBlueName.setText((String) map.get("FocusFrom"));

        /**
         * 设置隐藏和显示内容
        */
        util.mFocusTxtMain.setText((String) map.get("FocusText"));
        util.mFocusTxtMain.setVisibility(View.GONE);
        final Utilfocus focus = util;
        final BeanChoiceness beanChoiceness = new BeanChoiceness();
        beanChoiceness.setunfold(false);
        util.mFocusDropdown.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean flag = beanChoiceness.getunfold();
                if (flag){
                    focus.mFocusTxtMain.setVisibility(View.GONE);
                    focus.mFocusDropdown.setImageResource(R.mipmap.dropdown);
                    beanChoiceness.setunfold(false);
                }else{
                    focus.mFocusTxtMain.setVisibility(View.VISIBLE);
                    focus.mFocusDropdown.setImageResource(R.mipmap.bdropdown);
                    beanChoiceness.setunfold(true);
                }
            }
        });

        /**
         * 设置图片路径
         * */
        Picasso.with(view.getContext())
                .load(String.valueOf(map.get("ImgAddress")))
                .into(util.mFocusImgAddress);

        /**
         * 设置标题
         * */
        util.mFocusText.setText((String) map.get("Introduce"));

        /**
         * 设置介绍
         * */
        util.mFocusTextmin.setText((String) map.get("Introduction"));

        /**
         * 设置点赞功能
         * */
        //获取点赞和踩的人数
        util.mFocusTextLike.setText(map.get("LikeNumber")+"");
        //设置默认点赞状态和人数
        String likeString = "0";
        if(likeString.equals(map.get("UpLike"))){
            beanChoiceness.setlike(false);//点赞状态true为已点赞
        }else{
                beanChoiceness.setlike(true);//点赞状态true为已点赞
        }

        beanChoiceness.setmumber(Integer.parseInt((String) map.get("LikeNumber")));//点赞人数
        final GoodView goodView = new GoodView(view.getContext());//实例化+或-1动画
        // 取出bean中当记录状态是否为true，是的话则给img设置focus点赞图片
        if (beanChoiceness.getlike()) {
            focus.mFocusLike.setImageResource(R.mipmap.rdz);
        } else {
            focus.mFocusLike.setImageResource(R.mipmap.dz);
        }
        //点击点赞图标
        util.mFocusLike.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onClick(View v) {
                boolean flag = beanChoiceness.getlike();
                if(flag){
                    // Log.e("判断","判断失败");
                    goodView.setText("-1");
                    goodView.setDistance(20);
                    goodView.show(v);
                    focus.mFocusTextLike.setText(beanChoiceness.getmumber()-1+"");
                    beanChoiceness.setmumber(beanChoiceness.getmumber()-1);
                    beanChoiceness.setlike(false);
                    focus.mFocusLike.setImageResource(R.mipmap.dz);
                }else {
                    //设置动画文本
                    goodView.setText("+1");
                    //设置动画距离
                    goodView.setDistance(20);
                    //显示
                    goodView.show(v);
                    focus.mFocusTextLike.setText(beanChoiceness.getmumber()+1+"");
                    beanChoiceness.setmumber(beanChoiceness.getmumber()+1);
                    beanChoiceness.setlike(true);
                    focus.mFocusLike.setImageResource(R.mipmap.rdz);
                }

            }
        });
        return view;
    }

}

/**
 * 内部类，用于辅助适配
 *
 * @author qiangzi
 *
 */
class Utilfocus {

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
    ImageView mFocusImgAddress;

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
