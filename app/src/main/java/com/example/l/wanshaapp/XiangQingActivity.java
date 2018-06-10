package com.example.l.wanshaapp;


import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.l.wanshaapp.DynamicChoiceness.BeanChoiceness;

import fm.jiecao.jcvideoplayer_lib.JCVideoPlayerStandard;

/**
 * Created by 侯顺发 on 2018/5/31.
 */

public class XiangQingActivity extends AppCompatActivity {
    TextView title1;
    ImageView imageView,imageView5;
    TextView item_date,item_date3,item_date2,item_date4,item_date6,item_date5,xiangqingtext2,xiangqingtext4;

    @Override

    protected void onCreate(Bundle saveInstanceState){
        super.onCreate(saveInstanceState);
        setContentView(R.layout.activity_rankingxiangqing);//接受转到详情页面

        JCVideoPlayerStandard jcVideoPlayerStandard = (JCVideoPlayerStandard) findViewById(R.id.xiangqingvideo);
        jcVideoPlayerStandard.setUp(getIntent().getStringExtra("xiangqingvideo"), JCVideoPlayerStandard.SCREEN_LAYOUT_NORMAL, getIntent().getStringExtra("title1"));
        jcVideoPlayerStandard.thumbImageView.setImageResource(getIntent().getExtras().getInt("image"));

        //下载游戏
        Button download=(Button)findViewById(R.id.download);
        download.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                //Intent是一种运行时绑定（run-time binding）机制，它能在程序运行过程中连接两个不同的组件。
                //page1为先前已添加的类，并已在AndroidManifest.xml内添加活动事件(<activity android:name="page1"></activity>),在存放资源代码的文件夹下下，
                Uri uri = Uri.parse(getIntent().getStringExtra("download"));//游戏网址
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);
                ////启动
            }
        });



        /**
         * 设置收起和展开状态
         */
        final TextView ranking_unfold=findViewById(R.id.ranking_unfold);
        final BeanChoiceness beanChoiceness=new BeanChoiceness();
        beanChoiceness.setunfold(false);

        ranking_unfold.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean flag = beanChoiceness.getunfold();
                if (flag){
                    xiangqingtext2.setMaxLines(5);
                    ranking_unfold.setText("展开");
                    ranking_unfold.setTextColor(Color.parseColor("#464646"));
                    beanChoiceness.setunfold(false);
                }else{
                    xiangqingtext2.setMaxLines(1000);
                    ranking_unfold.setText("收起");
                    ranking_unfold.setTextColor(Color.parseColor("#1296DB"));
                    beanChoiceness.setunfold(true);
                }
            }
        });


        //实例化排行榜的对象
        title1 = findViewById(R.id.xiangqingyouximing);
        item_date=findViewById(R.id.XiangQingdate1);
        item_date2=findViewById(R.id.XiangQingdate2);
        item_date3=findViewById(R.id.XiangQingdate3);
        item_date4=findViewById(R.id.xiangqingpingfen);
        item_date5=findViewById(R.id.XiangQingdate4);
        xiangqingtext2=findViewById(R.id.xiangqingtext2);
        xiangqingtext4=findViewById(R.id.xiangqingtext4);
        item_date6=findViewById(R.id.xiangqingtext1);
        imageView = findViewById(R.id.xiangqingpicture);
        imageView5 = findViewById(R.id.xiangqingstarend);

        title1.setText(getIntent().getStringExtra("title1"));//传递详情项目名
        item_date.setText(getIntent().getStringExtra("date"));
        item_date2.setText(getIntent().getStringExtra("date2"));
        item_date3.setText(getIntent().getStringExtra("date3"));
        item_date4.setText(getIntent().getStringExtra("date4"));
        item_date5.setText(getIntent().getStringExtra("date5"));
        xiangqingtext2.setText(getIntent().getStringExtra("xiangqingtext2"));
        xiangqingtext4.setText(getIntent().getStringExtra("xiangqingtext4"));
//        Log.e("打印",getIntent().getStringExtra("xiangqingtext2"));
        item_date6.setText(getIntent().getStringExtra("xiangqingtext1"));
        int image  = getIntent().getExtras().getInt("image");
        int image5  = getIntent().getExtras().getInt("image5");
        imageView5.setImageResource(image5);
        imageView.setImageResource(image);


    }



    };
