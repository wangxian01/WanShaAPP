package com.example.l.wanshaapp.RankingAdapter; import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TabHost;

import com.example.l.wanshaapp.R;

import fm.jiecao.jcvideoplayer_lib.JCVideoPlayer;
import fm.jiecao.jcvideoplayer_lib.JCVideoPlayerStandard;

;


public class RankingXQplayer extends AppCompatActivity {

    TabHost tabs;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rankingxiangqing);

        JCVideoPlayerStandard jcVideoPlayerStandard = (JCVideoPlayerStandard) findViewById(R.id.xiangqingvideo);
        jcVideoPlayerStandard.setUp("blob:https://www.bilibili.com/243df54c-6f50-4b71-b5c1-47419514c8ae", JCVideoPlayerStandard.SCREEN_LAYOUT_NORMAL, "准备播放");
        jcVideoPlayerStandard.thumbImageView.setImageResource(R.drawable.rankinggood_01);


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


}