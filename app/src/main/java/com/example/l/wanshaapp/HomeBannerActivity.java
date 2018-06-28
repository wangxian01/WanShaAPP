package com.example.l.wanshaapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.example.l.wanshaapp.Activity.SearchApp;
import com.example.l.wanshaapp.Activity.yuyuexiazai;
import com.example.l.wanshaapp.WanShaLogin.LoginActivity;
import com.example.l.wanshaapp.fragment.HomeFragment;
import com.example.l.wanshaapp.fragment.Paihangbang_Home;
import com.example.l.wanshaapp.fragment.SecondFragment;
import com.example.l.wanshaapp.fragment.dongtaifragment;

import java.util.ArrayList;

public class HomeBannerActivity extends AppCompatActivity   {

    private Toolbar toolbar;
    private ArrayList<Fragment> fragmentsList = new ArrayList<Fragment>();
    private RadioGroup group;
    private  PopupWindow mPopWindow;
    private TextView logintv, yuyuegame,guanzhutubiao,textView_shezhi;
    private ImageButton loginbutton;
    private String username;
    private ImageView homesidebaricon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.homebannerlayout);

        //设置顶部栏
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        if (toolbar != null) {//mActionBarToolbar就是android.support.v7.widget.Toolbar
            toolbar.setTitle("");//设置为空，可以自己定义一个居中的控件，当做标题控件使用
        }
        setSupportActionBar(toolbar);
        username=getIntent().getStringExtra("username");//获取登陆页传来的用户信息
        homesidebaricon=(ImageView)findViewById(R.id.homesidebaricon);
        homesidebaricon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //设置contentView
                View contentView = LayoutInflater.from(HomeBannerActivity.this).inflate(R.layout.sidebar, null);

                mPopWindow = new PopupWindow(contentView, ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.MATCH_PARENT, true);
                mPopWindow.setContentView(contentView);

                //显示PopupWindow
                View rootview = LayoutInflater.from(HomeBannerActivity.this).inflate(R.layout.homebannerlayout, null);

                mPopWindow.showAtLocation(rootview, Gravity.LEFT, 0, 0);


                logintv = (TextView) contentView.findViewById(R.id.login);
                if (username==null){ logintv.setText("登陆");}else {logintv.setText(username);}
                yuyuegame = (TextView) contentView.findViewById(R.id.yuyuexiazaiid);
                guanzhutubiao=(TextView)contentView.findViewById(R.id.guanzhutubiao) ;
                //设置监听
                textView_shezhi=(TextView)contentView.findViewById(R.id.shezhi);

                logintv.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                        startActivity(intent);
                    }
                });

                guanzhutubiao.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(getApplicationContext(), AttentionActivity.class);
                        startActivity(intent);
                    }
                });
                yuyuegame.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(getApplicationContext(), yuyuexiazai.class);
                        startActivity(intent);
                    }
                });

                textView_shezhi.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        Intent intent= new Intent(getApplicationContext(),shezhi_activity.class);
                        startActivity(intent);

                    }
                });
            }
        });


        //搜索监听
        ImageView searchbutton = (ImageView) findViewById(R.id.searchbtu);
        searchbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), SearchApp.class);
                startActivity(intent);
            }
        });
        group = (RadioGroup) findViewById(R.id.rg);

        // 给group设置监听事件，在监听事件实现fragment之间的切换
        RadioGroup.OnCheckedChangeListener listener = new MyOnCheckedChangeListener();
        group.setOnCheckedChangeListener(listener);

        // 选中首页，否则开始启动的时候画面展示白板
        group.check(R.id.rb1);

    }


    //添加fragment布局
    private class MyOnCheckedChangeListener implements RadioGroup.OnCheckedChangeListener {
        // 在构造方法中创造fragment
        public MyOnCheckedChangeListener() {
            // 将new出来的fragment放置在集合中，以便后续取用
            fragmentsList.add(new HomeFragment());
            fragmentsList.add(new SecondFragment());
            fragmentsList.add(new Paihangbang_Home());
            fragmentsList.add(new dongtaifragment());
        }

        @Override
        public void onCheckedChanged(RadioGroup group, int checkedId) {
            // 当选中某一个radio的时候，就展现某一个fragment,用到fragment的事务
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            switch (checkedId) {
                case R.id.rb1:
                    ft.replace(R.id.fl, fragmentsList.get(0));
                    break;
                case R.id.rb2:
                    ft.replace(R.id.fl, fragmentsList.get(1));
                    break;
                case R.id.rb3:
                    ;
                    ft.replace(R.id.fl, fragmentsList.get(2));
                    break;
                case R.id.rb4:
                    ft.replace(R.id.fl, fragmentsList.get(3));
                    break;
                default:
                    break;
            }
            // 最后事务一定要提交
            ft.commit();
        }


    }


}
