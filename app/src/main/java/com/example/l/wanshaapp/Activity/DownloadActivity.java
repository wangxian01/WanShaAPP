package com.example.l.wanshaapp.Activity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.l.wanshaapp.R;
import com.example.l.wanshaapp.bean.GamesInfo;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.squareup.okhttp.Request;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.ArrayList;

public class DownloadActivity extends AppCompatActivity {


    private RecyclerView mRecyclerView;
    private DownloadAdapter mAdapter;
    private ArrayList<GamesInfo> list;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.yuyuexiazai);

        //通过findViewById拿到RecyclerView实例
        mRecyclerView = findViewById(R.id.yuyuelist);
        //设置RecyclerView管理器
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        TextView topicon=findViewById(R.id.topicon);
        topicon.setText("下载管理");

        Thread thread = new Thread(new Runnable() {

            @Override
            public void run() {
                OkHttpUtils
                        .get()
                        .url("http://" + getApplicationContext().getString(R.string.netip) + ":8080/AndroidServers/servlet/AllDownloadInfoServlet")
                        .build()
                        .execute(new StringCallback() {
                            @Override
                            public void onError(Request request, Exception e) {
                                new AlertDialog.Builder(DownloadActivity.this).setMessage("网络错误！！").create().show();
                            }
                            @Override
                            public void onResponse(String response) {
                                if (response != null) {
                                    Gson gson = new Gson();
                                    list = gson.fromJson(response, new TypeToken<ArrayList<GamesInfo>>() {
                                    }.getType());
                                    //初始化适配器
                                    mAdapter = new DownloadAdapter(list);
                                    //设置添加或删除item时的动画，这里使用默认动画
                                    mRecyclerView.setItemAnimator(new DefaultItemAnimator());
                                    //设置适配器
                                    mRecyclerView.setAdapter(mAdapter);
                                } else {
                                    AlertDialog.Builder builder = new AlertDialog.Builder(DownloadActivity.this);
                                    builder.setTitle("小提示！！");
                                    builder.setMessage("您还没有预约游戏哟！！赶紧去预约");
                                    builder.show();
                                }
                            }
                        });
            }
        });
        thread.start();

    }


    public class DownloadAdapter extends RecyclerView.Adapter<DownloadAdapter.ViewHolder> {

        ArrayList<GamesInfo> hehe;

        DownloadAdapter(ArrayList<GamesInfo> haha) {
            this.hehe = haha;
        }

        @NonNull
        @Override
        public DownloadAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.guanzhugame_item, parent, false);
            return new DownloadAdapter.ViewHolder(view);
        }
        @Override
        public void onBindViewHolder(@NonNull DownloadAdapter.ViewHolder holder, @SuppressLint("RecyclerView") final int position) {
            /*holder.mText.setText(list.get(position));*/
            holder.game_name.setText(hehe.get(position).getGame_name());
            holder.publisher.setText(hehe.get(position).getPublisher());
            holder.yujifabu.setText(hehe.get(position).getDue_Date());
            holder.yuyueshu.setText(hehe.get(position).getEstimated_time());
            holder.yiyuyuetubiao.setImageResource(R.drawable.yixiazai);
            holder.yiyuyuetubiao.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Thread thread = new Thread(new Runnable() {
                        @Override
                        public void run() {
                            OkHttpUtils
                                    .get()
                                    .url("http://" + getApplicationContext().getString(R.string.netip) + ":8080/AndroidServers/servlet/DeleteOrderInfo")
                                    .addParams("gamename", hehe.get(position).getGame_name())
                                    .build()
                                    .execute(new StringCallback() {
                                        @Override
                                        public void onError(Request request, Exception e) {

                                        }
                                        @Override
                                        public void onResponse(String response) {
                                            new AlertDialog.Builder(DownloadActivity.this).setMessage(response).create().show();
                                            list.clear();
                                            mAdapter = new DownloadAdapter(list);
                                            //设置添加或删除item时的动画，这里使用默认动画
                                            mRecyclerView.setItemAnimator(new DefaultItemAnimator());
                                            //设置适配器
                                            mRecyclerView.setAdapter(mAdapter);
                                        }
                                    });
                        }
                    });
                    thread.start();
                }

            });

        }

        @Override
        public int getItemCount() {
            return hehe.size();
        }


         class ViewHolder extends RecyclerView.ViewHolder {
            TextView yujifabu, yuyueshu, game_name, publisher;
            ImageView yiyuyuetubiao;

             ViewHolder(View itemView) {
                super(itemView);
                game_name = itemView.findViewById(R.id.gamename);
                publisher = itemView.findViewById(R.id.gamecompanies);

                yujifabu = itemView.findViewById(R.id.newdynamic);
                yuyueshu = itemView.findViewById(R.id.numberofcomments);
                yiyuyuetubiao = itemView.findViewById(R.id.righticon);
            }
        }

    }
}
