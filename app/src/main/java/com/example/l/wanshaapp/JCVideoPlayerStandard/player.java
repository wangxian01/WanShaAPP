package com.example.l.wanshaapp.JCVideoPlayerStandard;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Switch;
import android.widget.TabHost;
import android.widget.TabWidget;
import android.widget.TextView;
import android.widget.Toast;
import com.example.l.wanshaapp.R;
import com.example.l.wanshaapp.DynamicChoiceness.SerializableMap;
import com.example.l.wanshaapp.adapter.RecyAdapter;
import com.example.l.wanshaapp.adapter.pinglunAdapter;
import com.example.l.wanshaapp.fenleiresource.jiaoseresource;
import com.example.l.wanshaapp.fenleiresource.jiemiresource;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import fm.jiecao.jcvideoplayer_lib.JCVideoPlayer;
import fm.jiecao.jcvideoplayer_lib.JCVideoPlayerStandard;

import static com.example.l.wanshaapp.fenleiresource.jiemiresource.jiemi_string;


public class player extends AppCompatActivity {
    private List<Map<String, Object>> dataList;
    ImageView imageView;
    TabHost tabs;
    private JCVideoPlayerStandard videoplayer;
    private ImageView fenlei2image;
    private TextView fenlei2youximing;
    private TextView fenlei2youxishang;
    private TextView fenlei2faxingshang;
    private Switch sch;
    private ImageView fenlei2pingfen;
    private TextView youxijianjie;
    private TextView youxijieshao;
    private TextView youxiwanfa;
    private TextView youxiwanfajieshao;
    private ListView pinglunlistview;
    int flag=0;
    private  String id,type;
    private int imagturl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player);
        videoplayer = (JCVideoPlayerStandard) findViewById(R.id.videoplayer);
        fenlei2image = (ImageView) findViewById(R.id.fenlei2image);
        fenlei2youximing = (TextView) findViewById(R.id.fenlei2youximing);
        fenlei2youxishang = (TextView) findViewById(R.id.fenlei2youxishang);
        fenlei2faxingshang = (TextView) findViewById(R.id.fenlei2faxingshang);
        sch = (Switch) findViewById(R.id.sch);
        fenlei2pingfen = (ImageView) findViewById(R.id.fenlei2pingfen);
        youxijianjie = (TextView) findViewById(R.id.youxijianjie);
        youxijieshao = (TextView) findViewById(R.id.youxijieshao);
        youxiwanfa = (TextView) findViewById(R.id.youxiwanfa);
        youxiwanfajieshao = (TextView) findViewById(R.id.youxiwanfajieshao);
        pinglunlistview = (ListView) findViewById(R.id.pinglunlistview);


        JCVideoPlayerStandard jcVideoPlayerStandard = (JCVideoPlayerStandard) findViewById(R.id.videoplayer);
        jcVideoPlayerStandard.setUp("http://cs107-hc50.aipai.com/user/282/47030282/1006/card/48083441/card.mp4", JCVideoPlayerStandard.SCREEN_LAYOUT_NORMAL, "准备播放");
type=getIntent().getStringExtra("type");
        //listview传递参数
        if (type.equals("RecyAdapter")){
            fenlei2youximing.setText(getIntent().getStringExtra("id"));//传递详情项目名
            int image  = getIntent().getExtras().getInt("img");
            fenlei2image.setImageResource(image);
            jcVideoPlayerStandard.thumbImageView.setImageResource(image);
    }
        //recyview传值

        if(type.equals("jiemi")) {
            final Bundle bundle = getIntent().getExtras();
            SerializableMap serializableMap = (SerializableMap) bundle.get("map");
            fenlei2youximing.setText(String.valueOf(serializableMap.getMap("map").get("youximing")));
            int image  = (int) serializableMap.getMap("map").get("image");
            fenlei2image.setImageResource(image);
            fenlei2youxishang.setText(String.valueOf(serializableMap.getMap("map").get("youxishang")));
            fenlei2faxingshang.setText(String.valueOf(serializableMap.getMap("map").get("faxinggongsi")));
            jcVideoPlayerStandard.thumbImageView.setImageResource(image);
            DataListtest();

        }





        //选项卡
        tabs = (TabHost) findViewById(R.id.tabhost);
        tabs.setup();
        tabs.addTab(tabs.newTabSpec("tab1")
                .setIndicator("详情")
                .setContent(R.id.tab1));
        tabs.addTab(tabs.newTabSpec("tab2")
                .setIndicator("评价")
                .setContent(R.id.tab2));



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