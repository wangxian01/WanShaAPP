package com.example.l.wanshaapp.DynamicChoiceness;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
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
import com.wx.goodview.GoodView;
import com.zhy.http.okhttp.OkHttpUtils;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import fm.jiecao.jcvideoplayer_lib.JCVideoPlayerStandard;

/**
 * Created by cap on 2018/5/9.
 */

public class AdapterChoiceness extends BaseAdapter {
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
    public AdapterChoiceness(Context context, List<Map<String, Object>> dataList,
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
         Util util = null;
        // 中间变量
        final int flag = index;
        /**
         * 根据listView工作原理，列表项的个数只创建屏幕第一次显示的个数。
         * 之后就不会再创建列表项xml文件的对象，以及xml内部的组件，优化内存，性能效率
         */
        if (view == null) {
            util = new Util();
            // 给xml布局文件创建java对象
            LayoutInflater inflater = LayoutInflater.from(context);
            view = inflater.inflate(resource, null);

            // 指向布局文件内部组件
            util.mChoicenessUpPortrait = (CircleImageView) view.findViewById(R.id.choiceness_up_portrait);
            util.mChoicenessUpName = (TextView) view.findViewById(R.id.choiceness_up_name);
            util.mChoicenessUpTime = (TextView) view.findViewById(R.id.choiceness_up_time);
            util.mChoicenessUpText = (TextView) view.findViewById(R.id.choiceness_up_text);
            util.mChoicenessViodeoview = (JCVideoPlayerStandard)view. findViewById(R.id.choiceness_viodeoview);
            util.mChoicenessUpLike = (ImageView) view.findViewById(R.id.choiceness_up_like);
            util.mTextLike = (TextView)view. findViewById(R.id.text_like);
            util.mChoicenessUpComments = (ImageView)view. findViewById(R.id.choiceness_up_comments);
            util.mTextComments = (TextView) view.findViewById(R.id.text_comments);
            util.mChoicenessUpStep = (ImageView)view. findViewById(R.id.choiceness_up_step);
            util.mTextStep = (TextView) view.findViewById(R.id.text_step);
            util.mChoicenessUnfold = (TextView) view.findViewById(R.id.choiceness_unfold);
            // 增加额外变量
            view.setTag(util);
        } else {
            util = (Util) view.getTag();
        }

        // 获取数据显示在各组件
        final Map<String, Object> map = dataList.get(index);

        Picasso.with(view.getContext())
                .load(String.valueOf(map.get("UpPortrait")))
                .into(util.mChoicenessUpPortrait);
        //获取up主名称
        util.mChoicenessUpName.setText((String) map.get("ChoicenessUpName"));
        //获取发布时间
        util.mChoicenessUpTime.setText((String) map.get("ChoicenessUpTime"));
        //获取游戏介绍文字
        util.mChoicenessUpText.setText((String) map.get("ChoicenessUpText"));
//        Log.e("测试：", (String) map.get("ChoicenessUpName"));
        //设置网络视频
        util.mChoicenessViodeoview.setUp(String.valueOf(map.get("ChoicenessViodeoview"))
                , JCVideoPlayerStandard.SCREEN_LAYOUT_NORMAL, String.valueOf(map.get("UpIntroduce")));
        //设置视频缩略图
        Picasso.with(view.getContext())
                .load(String.valueOf(map.get("VideoImg")))
                .into(util.mChoicenessViodeoview.thumbImageView);

        final Util finalUtil = util;
        final BeanChoiceness beanChoiceness = new BeanChoiceness();


        /**
         * 设置收起和展开状态
         */
        beanChoiceness.setunfold(false);

        util.mChoicenessUnfold.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean flag = beanChoiceness.getunfold();
                if (flag){
                    finalUtil.mChoicenessUpText.setMaxLines(2);
                    finalUtil.mChoicenessUnfold.setText("展开");
                    finalUtil.mChoicenessUnfold.setTextColor(Color.parseColor("#464646"));
                    beanChoiceness.setunfold(false);
                }else{
                    finalUtil.mChoicenessUpText.setMaxLines(1000);
                    finalUtil.mChoicenessUnfold.setText("收起");
                    finalUtil.mChoicenessUnfold.setTextColor(Color.parseColor("#1296DB"));
                    beanChoiceness.setunfold(true);
                }
            }
        });

        /**
         * 设置点赞和踩的动作和状态
         */
        //获取点赞和踩的人数
        util.mTextLike.setText(map.get("LikeNumber")+"");
        util.mTextStep.setText(map.get("StepNumber")+"");

        //设置默认点赞状态和人数
        String likeString = "0";
        if(likeString.equals(map.get("UpLike"))){
            beanChoiceness.setlike(false);//点赞状态true为已点赞
        }else{
            beanChoiceness.setlike(true);//点赞状态true为已点赞
        }

        if(likeString.equals(map.get("UpLike"))){
            beanChoiceness.setstep(false);//踩状态true为已点赞
        }else{
            beanChoiceness.setstep(true);//踩状态true为已点赞
        }

        beanChoiceness.setmumber(Integer.parseInt((String) map.get("LikeNumber")));//点赞人数
        beanChoiceness.setstepnumber(Integer.parseInt((String) map.get("StepNumber")));//踩人数
        final GoodView goodView = new GoodView(view.getContext());//实例化+或-1动画


        // 取出bean中当记录状态是否为true，是的话则给img设置focus点赞图片
        if (beanChoiceness.getlike()) {
            finalUtil.mChoicenessUpLike.setImageResource(R.mipmap.rdz);
        } else {
            finalUtil.mChoicenessUpLike.setImageResource(R.mipmap.dz);
        }
        if (beanChoiceness.getstep()) {
            finalUtil.mChoicenessUpStep.setImageResource(R.mipmap.rcai);
        } else {
            finalUtil.mChoicenessUpStep.setImageResource(R.mipmap.cai);
        }

        /*
        * 定义线程，方便操作点赞
        * */
//        final Thread threads = new Thread(new Runnable() {
//            @Override
//            public void run() {
//                try {
//                    OkHttpUtils
//                            .get()
//                            .url("http://"+context.getString(R.string.netip)+":8080/AndroidServers/UpdateChoicenessServlet")
//                            .addParams("UpLike", typ[0])
//                            .addParams("LikeNumber", (String) finalUtil.mTextLike.getText())
//                            .addParams("UpStep", typ[1])
//                            .addParams("StepNumber", (String) finalUtil.mTextStep.getText())
//                            .addParams("UpId", (String) map.get("UpId"))
//                            .build().execute();
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//
//            }
//        });
//        threads.start();
//        try {
//            threads.join();
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }

        //点击点赞图标

        util.mChoicenessUpLike.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onClick(View v) {
                boolean flag = beanChoiceness.getlike();
                if(flag){
                   // Log.e("判断","判断失败");
                    goodView.setText("-1");
                    goodView.setDistance(20);
                    goodView.show(v);
                    finalUtil.mTextLike.setText(beanChoiceness.getmumber()-1+"");
                    beanChoiceness.setmumber(beanChoiceness.getmumber()-1);
                    beanChoiceness.setlike(false);
                    finalUtil.mChoicenessUpLike.setImageResource(R.mipmap.dz);


                }else {
                    //设置动画文本
                    goodView.setText("+1");
                    //设置动画距离
                    goodView.setDistance(20);
                    //显示
                    goodView.show(v);
                    finalUtil.mTextLike.setText(beanChoiceness.getmumber()+1+"");
                    beanChoiceness.setmumber(beanChoiceness.getmumber()+1);
                    beanChoiceness.setlike(true);
                    finalUtil.mChoicenessUpLike.setImageResource(R.mipmap.rdz);

                }

            }
        });


        //点击踩图标
        util.mChoicenessUpStep.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onClick(View v) {
                boolean flag = beanChoiceness.getstep();
                if(flag){
                    // Log.e("判断","判断失败");
                    goodView.setText("-1");
                    goodView.setDistance(20);
                    goodView.show(v);
                    finalUtil.mTextStep.setText(beanChoiceness.getstepnumber()-1+"");
                    beanChoiceness.setstepnumber(beanChoiceness.getstepnumber()-1);
                    beanChoiceness.setstep(false);
                    finalUtil.mChoicenessUpStep.setImageResource(R.mipmap.cai);
                }else {
                    //设置动画文本
                    goodView.setText("+1");
                    //设置动画距离
                    goodView.setDistance(20);
                    //显示
                    goodView.show(v);
                    finalUtil.mTextStep.setText(beanChoiceness.getstepnumber()+1+"");
                    beanChoiceness.setstepnumber(beanChoiceness.getstepnumber()+1);
                    beanChoiceness.setstep(true);
                    finalUtil.mChoicenessUpStep.setImageResource(R.mipmap.rcai);
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
class Util {
    CircleImageView mChoicenessUpPortrait;
    TextView mChoicenessUpName;
    TextView mChoicenessUpTime;
    TextView mChoicenessUpText;
    JCVideoPlayerStandard mChoicenessViodeoview;
    ImageView mChoicenessUpLike;
    TextView mTextLike;
    ImageView mChoicenessUpComments;
    TextView mTextComments;
    ImageView mChoicenessUpStep;
    TextView mTextStep;
    TextView mChoicenessUnfold;
}
