package com.example.l.wanshaapp.JCVideoPlayerStandard;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TabHost;

import com.example.l.wanshaapp.R;
import com.example.l.wanshaapp.adapter.pinglunAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import fm.jiecao.jcvideoplayer_lib.JCVideoPlayer;
import fm.jiecao.jcvideoplayer_lib.JCVideoPlayerStandard;


public class player extends AppCompatActivity {
    private List<Map<String, Object>> dataList;
    ImageView imageView;
    TabHost tabs;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player);

        JCVideoPlayerStandard jcVideoPlayerStandard = (JCVideoPlayerStandard) findViewById(R.id.videoplayer);
        jcVideoPlayerStandard.setUp("http://cs107-hc50.aipai.com/user/282/47030282/1006/card/48083441/card.mp4", JCVideoPlayerStandard.SCREEN_LAYOUT_NORMAL, "准备播放");
        jcVideoPlayerStandard.thumbImageView.setImageResource(R.drawable.wuyu);


        tabs = (TabHost) findViewById(R.id.tabhost);
        tabs.setup();
        tabs.addTab(tabs.newTabSpec("tab1")
                .setIndicator("详情")
                .setContent(R.id.tab1));
        tabs.addTab(tabs.newTabSpec("tab2")
                .setIndicator("评价")
                .setContent(R.id.tab2));
        tabs.addTab(tabs.newTabSpec("tab3")
                .setIndicator("论坛")
                .setContent(R.id.tab3));



        ListView pinglun_item = (ListView)findViewById(R.id.pinglunlistview);
        DataListtest();

        pinglunAdapter pinglunAdapter = new pinglunAdapter(getBaseContext(),dataList,R.layout.pinglun_item);
        pinglun_item.setAdapter(pinglunAdapter);

        return;



    }

    @Override
    public void onBackPressed() {
        if (JCVideoPlayer.backPress()) {
            return;
        }
        super.onBackPressed();
    }

    @Override
    protected void onPause() {
        super.onPause();
        JCVideoPlayer.releaseAllVideos();

    }


    private void DataListtest() {
        dataList = new ArrayList<Map<String, Object>>();

        for (int i = 0; i < 10; i++) {
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("youximing", "创世战纪"+i);
            map.put("riqi", "创世战纪"+i);
            map.put("wuyu", "创世战纪");
            map.put("pingfen", "创世战纪");
            dataList.add(map);
        }
    }


}