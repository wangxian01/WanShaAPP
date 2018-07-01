package com.example.l.wanshaapp;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
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

import com.example.l.wanshaapp.Activity.DownloadActivity;
import com.example.l.wanshaapp.Activity.MyShouCangActivity;
import com.example.l.wanshaapp.Activity.SearchApp;
import com.example.l.wanshaapp.Activity.yuyuexiazai;
import com.example.l.wanshaapp.WanShaLogin.LoginActivity;
import com.example.l.wanshaapp.fragment.HomeFragment;
import com.example.l.wanshaapp.fragment.Paihangbang_Home;
import com.example.l.wanshaapp.fragment.SecondFragment;
import com.example.l.wanshaapp.fragment.dongtaifragment;

import java.util.ArrayList;

/*created by tanlin

time 2018.6.28*/


public class HomeBannerActivity extends AppCompatActivity   {

    private ArrayList<Fragment> fragmentsList = new ArrayList<>();
    private  PopupWindow mPopWindow;
    private TextView logintv, yuyuegame,guanzhutubiao,textView_shezhi,xiazaiguanli,wodeshoucang;
    private String username;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.homebannerlayout);

        //设置顶部栏
        Toolbar toolbar =findViewById(R.id.toolbar);
        if (toolbar != null) {//mActionBarToolbar就是android.support.v7.widget.Toolbar
            toolbar.setTitle("");//设置为空，可以自己定义一个居中的控件，当做标题控件使用
        }
        setSupportActionBar(toolbar);
        username=getIntent().getStringExtra("username");//获取登陆页传来的用户信息

        ImageView homesidebaricon =findViewById(R.id.homesidebaricon);
        homesidebaricon.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("RtlHardcoded")
            @Override
            public void onClick(View v) {
                //设置contentView
                @SuppressLint("InflateParams") View contentView = LayoutInflater.from(HomeBannerActivity.this).inflate(R.layout.sidebar, null);

                mPopWindow = new PopupWindow(contentView, ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.MATCH_PARENT, true);
                mPopWindow.setContentView(contentView);

                //显示PopupWindow
                @SuppressLint("InflateParams") View rootview = LayoutInflater.from(HomeBannerActivity.this).inflate(R.layout.homebannerlayout, null);

                mPopWindow.showAtLocation(rootview, Gravity.LEFT, 0, 0);

                logintv =contentView.findViewById(R.id.login);
                ImageButton touxiang=contentView.findViewById(R.id.touxiang);
                //获取sharedPreferences对象
                SharedPreferences sharedPreferences = getSharedPreferences("login", Context.MODE_PRIVATE);
                if (sharedPreferences.getBoolean("islogin",false))
                {
                    logintv.setText(sharedPreferences.getString("username",null));
                    touxiang.setBackgroundResource(R.mipmap.touxiang);
                }
               else
                {
                    logintv.setText("登陆");
                }

                wodeshoucang=contentView.findViewById(R.id.wodeshoucang);
                wodeshoucang.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        //获取sharedPreferences对象
                        SharedPreferences sharedPreferences = getSharedPreferences("login", Context.MODE_PRIVATE);
                        if (sharedPreferences.getBoolean("islogin",false))
                        {
                            Intent intent = new Intent(getApplicationContext(), MyShouCangActivity.class);
                            startActivity(intent);

                        }else {
                            Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                            startActivity(intent);
                        }
                    }
                });

                //设置监听
                textView_shezhi=contentView.findViewById(R.id.shezhi);



                logintv.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                        startActivity(intent);
                    }
                });

                //关注
                guanzhutubiao=contentView.findViewById(R.id.guanzhutubiao) ;
                guanzhutubiao.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        //获取sharedPreferences对象
                        SharedPreferences sharedPreferences = getSharedPreferences("login", Context.MODE_PRIVATE);
                        if (sharedPreferences.getBoolean("islogin",false))
                        {
                            Intent intent = new Intent(getApplicationContext(), AttentionActivity.class);
                            startActivity(intent);

                        }else {
                            Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                            startActivity(intent);
                        }

                    }
                });

                //下载管理
                xiazaiguanli=contentView.findViewById(R.id.downloadmanage) ;
                xiazaiguanli.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(getApplicationContext(), DownloadActivity.class);
                        startActivity(intent);
                    }
                });
                //预约下载
                yuyuegame =contentView.findViewById(R.id.yuyuexiazaiid);
                yuyuegame.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        //获取sharedPreferences对象
                        SharedPreferences sharedPreferences = getSharedPreferences("login", Context.MODE_PRIVATE);
                        //Log.e("预约布尔",  "你说"+sharedPreferences.getBoolean("islogin",false));
                        if (sharedPreferences.getBoolean("islogin",false))
                        {
                            Intent intent = new Intent(getApplicationContext(), yuyuexiazai.class);
                            startActivity(intent);
                        }else {
                            Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                            startActivity(intent);
                        }
                    }
                });
                //设置
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
        ImageView searchbutton =findViewById(R.id.searchbtu);
        searchbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), SearchApp.class);
                startActivity(intent);
            }
        });
        RadioGroup group = findViewById(R.id.rg);

        // 给group设置监听事件，在监听事件实现fragment之间的切换
        RadioGroup.OnCheckedChangeListener listener = new MyOnCheckedChangeListener();
        group.setOnCheckedChangeListener(listener);

        // 选中首页，否则开始启动的时候画面展示白板
        group.check(R.id.rb1);

    }

    //添加fragment布局
    private class MyOnCheckedChangeListener implements RadioGroup.OnCheckedChangeListener {
        // 在构造方法中创造fragment
        MyOnCheckedChangeListener() {
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
