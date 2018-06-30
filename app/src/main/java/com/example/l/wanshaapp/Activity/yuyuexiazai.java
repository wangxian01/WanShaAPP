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
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.l.wanshaapp.HomeBannerActivity;
import com.example.l.wanshaapp.R;
import com.example.l.wanshaapp.bean.GamesInfo;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.squareup.okhttp.Request;
import com.squareup.picasso.Picasso;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.ArrayList;

//预约下载
public class yuyuexiazai extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private YuYueAdapter mAdapter;
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

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                OkHttpUtils
                        .get()
                        .url("http://"+getApplicationContext().getString(R.string.netip) + ":8080/AndroidServers/servlet/OrderGameDataServlet")
                        .build()
                        .execute(new StringCallback() {
                            @Override
                            public void onError(Request request, Exception e) {
                                new AlertDialog.Builder(yuyuexiazai.this).setMessage("网络错误！！").create().show();
                            }
                            @Override
                            public void onResponse(String response) {
                                if (response != null) {
                                    Gson gson = new Gson();
                                    list = gson.fromJson(response, new TypeToken<ArrayList<GamesInfo>>() {
                                    }.getType());
                                    //初始化适配器
                                    mAdapter = new YuYueAdapter(list);
                                    //设置添加或删除item时的动画，这里使用默认动画
                                    mRecyclerView.setItemAnimator(new DefaultItemAnimator());
                                    //设置适配器
                                    mRecyclerView.setAdapter(mAdapter);
                                } else {
                                    AlertDialog.Builder builder = new AlertDialog.Builder(yuyuexiazai.this);
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

    public class YuYueAdapter extends RecyclerView.Adapter<YuYueAdapter.ViewHolder> {
        ArrayList<GamesInfo> hehe;

        YuYueAdapter(ArrayList<GamesInfo> haha) {
            this.hehe = haha;
        }

        @NonNull
        @Override
        public YuYueAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.guanzhugame_item, parent, false);
            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull YuYueAdapter.ViewHolder holder, @SuppressLint("RecyclerView") final int position) {
            /*holder.mText.setText(list.get(position));*/
            holder.game_name.setText(hehe.get(position).getGame_name());
            holder.publisher.setText(hehe.get(position).getPublisher());
            holder.yujifabu.setText(hehe.get(position).getDue_Date());
            holder.yuyueshu.setText(hehe.get(position).getEstimated_time());
            Picasso.with(getApplicationContext()).load("http://"+getString(R.string.netip) + ":8080/AndroidServers/images/rankingorder" + position + ".png").into(holder.tubiao);
            holder.yiyuyuetubiao.setImageResource(R.drawable.yiyuyuetubiao);
            holder.yiyuyuetubiao.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Thread thread = new Thread(new Runnable() {
                        @Override
                        public void run() {
                            OkHttpUtils
                                    .get()
                                    .url("http://" + getApplicationContext().getString(R.string.netip)+ ":8080/AndroidServers/servlet/DeleteOrderInfo")
                                    .addParams("gamename", hehe.get(position).getGame_name())
                                    .build()
                                    .execute(new StringCallback() {
                                        @Override
                                        public void onError(Request request, Exception e) {

                                        }
                                        @Override
                                        public void onResponse(String response) {
                                            new AlertDialog.Builder(yuyuexiazai.this).setMessage(response).create().show();
                                            list.clear();
                                            mAdapter = new YuYueAdapter(list);
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
            return (hehe != null ? hehe.size() : 0);
        }


        public class ViewHolder extends RecyclerView.ViewHolder {
            TextView yujifabu, yuyueshu, game_name, publisher;
            ImageView yiyuyuetubiao,tubiao;

            public ViewHolder(View itemView) {
                super(itemView);
                game_name = itemView.findViewById(R.id.gamename);
                publisher = itemView.findViewById(R.id.gamecompanies);
                tubiao=itemView.findViewById(R.id.lefticon);
                yujifabu = itemView.findViewById(R.id.newdynamic);
                yuyueshu = itemView.findViewById(R.id.numberofcomments);
                yiyuyuetubiao = itemView.findViewById(R.id.righticon);
            }
        }

    }
}
