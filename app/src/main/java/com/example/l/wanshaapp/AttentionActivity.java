package com.example.l.wanshaapp;

import android.app.LocalActivityManager;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.TabHost;
import android.widget.TabWidget;
import android.widget.TextView;


public class AttentionActivity extends AppCompatActivity {
    //用于添加每一个选项卡的id
    private String[] tags = {"A_tag", "B_tag", "C_tag"};
    //所添加选项卡的文本信息
    private String[] titles = {"游戏", "玩家", "论坛"};
    //所添加选项卡的图片信息
    private int[] images = {R.drawable.a, R.drawable.b,
            R.drawable.c};
    //用于跳转至不同的Activity
    private Intent[] intents = new Intent[3];

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.attentionlayout);
        TabHost tabHost = (TabHost) findViewById(R.id.tabhost);
        TabWidget tabWidget = (TabWidget) findViewById(android.R.id.tabs);
//        tabWidget.setDividerDrawable(null);//设置tabWeight没有竖线分割
        tabWidget.setBackgroundColor(Color.WHITE);;
        //初始化activity管理者
        LocalActivityManager manager = new LocalActivityManager(AttentionActivity.this, false);
        //通过管理者保存当前页面状态
        manager.dispatchCreate(savedInstanceState);
        //将管理者类对象添加至TabHost
        tabHost.setup(manager);
        init_intent();
        for (int i = 0; i < intents.length; i++) {
            //加载底部导航栏布局
            LayoutInflater inflater = this.getLayoutInflater();
            View view = inflater.inflate(R.layout.tab, null);
            TextView textView = (TextView) view.findViewById(R.id.tv_item);
            textView.setText(titles[i]);
            //创建选项卡
            TabHost.TabSpec spec = tabHost.newTabSpec(tags[i]);
            spec.setIndicator(view);
            //设置每个页面的内容
            spec.setContent(intents[i]);
            //将创建的选项卡添加至tabHost上
            tabHost.addTab(spec);
        }
    }
    //每个页面放置的Activitye
    public void init_intent() {
        intents[0] = new Intent(this, HomeBannerActivity.class);
        intents[1] = new Intent(this, HomeBannerActivity.class);
        intents[2] = new Intent(this, HomeBannerActivity.class);
    }
    }

