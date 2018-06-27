package com.example.l.wanshaapp.Activity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.NonNull;
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

/**
 * Created by 侯顺发 on 2018/6/24.
 */
//123
public class MyShouCangActivity  extends AppCompatActivity {
    private RecyclerView mRecyclerView;
    private MyShouCangActivity.ShoucangAdapter mAdapter;
    private ArrayList<GamesInfo> listview;
    @SuppressLint("WrongViewCast")
    protected void onCreate(Bundle saveInstanceState) {

        super.onCreate(saveInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_myshoucangfragment);
        mRecyclerView = findViewById(R.id.SrecyclerView);
        //设置RecyclerView管理器
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));

        Thread thread = new Thread(new Runnable() {

            @Override
            public void run() {
                OkHttpUtils
                        .get()
                        .url("http://" + getApplicationContext().getString(R.string.netip) + ":8080/AndroidServers/servlet/OrderGameDataServlet")
                        .build()
                        .execute(new StringCallback() {
                            @Override
                            public void onError(Request request, Exception e) {
                                new AlertDialog.Builder(MyShouCangActivity.this).setMessage("网络错误！！").create().show();
                            }

                            @Override
                            public void onResponse(String response) {
                                if (response != null) {
                                    Gson gson = new Gson();
                                    listview = gson.fromJson(response, new TypeToken<ArrayList<GamesInfo>>() {
                                    }.getType());
                                    //初始化适配器
                                    mAdapter =  new MyShouCangActivity.ShoucangAdapter(listview);
                                    //设置添加或删除item时的动画，这里使用默认动画
                                    mRecyclerView.setItemAnimator(new DefaultItemAnimator());
                                    //设置适配器
                                    mRecyclerView.setAdapter(mAdapter);
                                } else {
                                    AlertDialog.Builder builder = new AlertDialog.Builder(MyShouCangActivity.this);
                                    builder.setTitle("小提示！！");
                                    builder.setMessage("您还没有收藏游戏哟！！赶紧去收藏");
                                    builder.show();
                                }
                            }
                        });
            }
        });
        thread.start();

    }
    public class ShoucangAdapter extends RecyclerView.Adapter<MyShouCangActivity.ShoucangAdapter.ViewHolder> {
        ArrayList<GamesInfo> heihei;

        ShoucangAdapter(ArrayList<GamesInfo> haihai) {
            this.heihei = haihai;
        }

        @NonNull
        @Override
        public MyShouCangActivity.ShoucangAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_myshoucang, parent, false);
            return new MyShouCangActivity.ShoucangAdapter.ViewHolder(view);
        }

//        @Override
//        public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
//
//        }

        @Override
        public void onBindViewHolder(@NonNull MyShouCangActivity.ShoucangAdapter.ViewHolder holder, @SuppressLint("RecyclerView") final int position) {
            /*holder.mText.setText(list.get(position));*/
            holder.ShoucangGamename.setText(heihei.get(position).getGame_name());
            holder.ShoucangCompany.setText(heihei.get(position).getPublisher());
            holder.ShoucangDongtai.setText(heihei.get(position).getDue_Date());
            holder.ShoucangCompanyNumber.setText(heihei.get(position).getEstimated_time());
            holder.ShoucangPicture.setImageResource(R.drawable.yishoucang);
            holder.ShoucangPicture.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Thread thread = new Thread(new Runnable() {
                        @Override
                        public void run() {
                            OkHttpUtils
                                    .get()
                                    .url("http://" + getApplicationContext().getString(R.string.netip) + ":8080/AndroidServers/servlet/AddCollectionGame")
                                    .addParams("shoucangname", heihei.get(position).getGame_name())
                                    .build()
                                    .execute(new StringCallback() {
                                        @Override
                                        public void onError(Request request, Exception e) {

                                        }
                                        @Override
                                        public void onResponse(String response) {
                                            new AlertDialog.Builder(MyShouCangActivity.this).setMessage(response).create().show();
                                            listview.clear();
                                            mAdapter = new MyShouCangActivity.ShoucangAdapter(listview);
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
            return (heihei != null ? heihei.size() : 0);
        }


        public class ViewHolder extends RecyclerView.ViewHolder {
            TextView ShoucangGamename, ShoucangCompany, ShoucangDongtai, ShoucangCompanyNumber;
            ImageView ShoucangPicture;

            public ViewHolder(View itemView) {
                super(itemView);
                ShoucangGamename = itemView.findViewById(R.id.ShoucangGamename);
                ShoucangCompany = itemView.findViewById(R.id.ShoucangCompany);

                ShoucangDongtai = itemView.findViewById(R.id.ShoucangDongtai);
                ShoucangCompanyNumber = itemView.findViewById(R.id.ShoucangCompanyNumber);
                ShoucangPicture = itemView.findViewById(R.id.ShoucangPicture);
            }
        }

    }


                }



