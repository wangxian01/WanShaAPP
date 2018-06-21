package com.example.l.wanshaapp.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.EditText;
import android.widget.ImageView;

import com.example.l.wanshaapp.R;
import com.example.l.wanshaapp.bean.GamesInfo;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.squareup.okhttp.Request;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.ArrayList;

import static android.content.ContentValues.TAG;

public class SearchApp extends AppCompatActivity {

    private ImageView searchimageview;
    private EditText editText;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.searchapplayout);
        searchimageview = (ImageView) findViewById(R.id.searchimageview);
        editText = (EditText) findViewById(R.id.searchname);


        searchimageview.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View v) {

                final String searchkeyword = editText.getText().toString();

                Thread thread = new Thread(new Runnable() {

                    @Override
                    public void run() {
                        OkHttpUtils
                                .get()
                                .url("http://" + getApplicationContext().getString(R.string.netip) + ":8080/AndroidServers/SearchDealServlet")
                                .addParams("gamename", searchkeyword)
                                .build()
                                .execute(new StringCallback() {
                                    @Override
                                    public void onError(Request request, Exception e) {
                                        new AlertDialog.Builder(SearchApp.this).setMessage("网络错误！！").create().show();
                                    }

                                    @Override
                                    public void onResponse(String response) {

                                        if (response !=null) {
                            /*                Log.e(TAG, response.toString());*/
                                            ArrayList<GamesInfo> list = new ArrayList<GamesInfo>();
                                            Gson gson = new Gson();
                                            list = gson.fromJson(response.toString(), new TypeToken<ArrayList<GamesInfo>>() {
                                            }.getType());

                                            Intent intent = new Intent(SearchApp.this, SearchDealActivity.class);
                                            intent.setAction("action");
                                            intent.putExtra("data", list);
                                            startActivity(intent);

                                        } else {
                                            new AlertDialog.Builder(SearchApp.this).setMessage("没有相关的游戏信息！！").create().show();
                                        }
                                    }
                                });
                    }
                });
                thread.start();


            }
        });

    }
}
