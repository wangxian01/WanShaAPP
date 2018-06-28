package com.example.l.wanshaapp.yindaoye;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.example.l.wanshaapp.R;

public class yindao_first extends AppCompatActivity {

    //延迟时间
    private static final  int TIME = 2000;
    @SuppressLint("HandlerLeak")
    private Handler mHander = new Handler(){
        @Override
        public void handleMessage(android.os.Message msg) {
            goHome();
        };
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);

        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE|View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);


        changeStatusBarColor();

        super.onCreate(savedInstanceState);
        setContentView(R.layout.yindao_first);
        //Toast.makeText(this, "OKOKOK！", Toast.LENGTH_LONG).show();
        mHander.sendEmptyMessageDelayed(0,TIME);
    }
    private  void goHome()
    {
        Intent intent = new Intent(yindao_first.this,WelcomeActivity.class);
        startActivity(intent);
        this.finish();
    }

    private void changeStatusBarColor(){
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP){
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(Color.TRANSPARENT);
        }
    }
}