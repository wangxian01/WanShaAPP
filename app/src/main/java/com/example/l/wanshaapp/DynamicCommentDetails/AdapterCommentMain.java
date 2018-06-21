package com.example.l.wanshaapp.DynamicCommentDetails;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.v4.view.NestedScrollingChildHelper;
import android.util.Log;
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
     *
     * @param
     */
    public void addTheCommentData(Map<String, Object> map) {

        dataList.add(map);
        notifyDataSetChanged();
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
                util.mLikeNumber = (TextView) view.findViewById(R.id.like_number);

                // 增加额外变量

                view.setTag(util);
            } else {
                util = (UtilComment) view.getTag();
            }
            final Map<String, Object> map = dataList.get(index);

            util.mCommentItemUserName.setText((String) map.get("Comments_name"));
            util.mCommentItemContent.setText((String) map.get("Comments_text"));
            //设置图片
            Picasso.with(context)
                .load(String.valueOf(map.get("Comments_portrait")))
                .into(util.mCommentItemLogo);
            util.mCommentItemTime.setText((String) map.get("Comments_time"));

            //设置点赞人数的情况
            int number = 0;
            if(map.get("Comments_number")==null){
                util.mLikeNumber.setText("0");
                number = 0;
            }else {
                util.mLikeNumber.setText((String) map.get("Comments_number"));
                number = Integer.parseInt(String.valueOf(map.get("Comments_number")));
            }

        final UtilComment finalUtil = util;
        final BeanChoiceness beanChoiceness = new BeanChoiceness();
        beanChoiceness.setmumber(number);//点赞人数
        String likeString = "0";
        if(likeString.equals(map.get("Comments_like"))){
            beanChoiceness.setlike(false);//点赞状态true为已点赞
        }else{
            if(number == 0){
                beanChoiceness.setlike(false);
            }else{
                beanChoiceness.setlike(true);//点赞状态true为已点赞
            }
        }
        final GoodView goodView = new GoodView(view.getContext());//实例化+或-1动画
        // 取出bean中当记录状态是否为true，是的话则给img设置focus点赞图片
        if (beanChoiceness.getlike()) {
            if(map.get("Comments_number")==null){
                finalUtil.mCommentItemLike.setImageResource(R.mipmap.dz);
            }else{
                finalUtil.mCommentItemLike.setImageResource(R.mipmap.rdz);
            }
        } else {
            finalUtil.mCommentItemLike.setImageResource(R.mipmap.dz);
        }

        //点击点赞图标
        util.mCommentItemLike.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onClick(View v) {
                boolean flag = beanChoiceness.getlike();
                if(flag){
                    // Log.e("判断","判断失败");
                    goodView.setText("-1");
                    goodView.setDistance(20);
                    goodView.show(v);
                    finalUtil.mLikeNumber.setText(beanChoiceness.getmumber()-1+"");
                    beanChoiceness.setmumber(beanChoiceness.getmumber()-1);
                    beanChoiceness.setlike(false);
                    finalUtil.mCommentItemLike.setImageResource(R.mipmap.dz);
                }else {
                    //设置动画文本
                    goodView.setText("+1");
                    //设置动画距离
                    goodView.setDistance(20);
                    //显示
                    goodView.show(v);
                    finalUtil.mLikeNumber.setText(beanChoiceness.getmumber()+1+"");
                    beanChoiceness.setmumber(beanChoiceness.getmumber()+1);
                    beanChoiceness.setlike(true);
                    finalUtil.mCommentItemLike.setImageResource(R.mipmap.rdz);
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
class UtilComment {
    CircleImageView mCommentItemLogo;
    TextView mCommentItemUserName;
    TextView mCommentItemTime;
    ImageView mCommentItemLike;
     TextView mCommentItemContent;
    TextView mCommentItemUnfod;
    TextView mLikeNumber;

}
