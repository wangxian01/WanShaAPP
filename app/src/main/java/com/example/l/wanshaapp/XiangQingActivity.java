package com.example.l.wanshaapp;


import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

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
