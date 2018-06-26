//package com.example.l.wanshaapp.RankingFragmentadapter;
//
//import android.annotation.SuppressLint;
//import android.content.Context;
//import android.os.Bundle;
//import android.support.annotation.NonNull;
//import android.support.v7.app.AlertDialog;
//import android.support.v7.widget.DefaultItemAnimator;
//import android.support.v7.widget.RecyclerView;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.BaseAdapter;
//import android.widget.Button;
//import android.widget.ImageView;
//import android.widget.TextView;
//
//import com.example.l.wanshaapp.Activity.yuyuexiazai;
//import com.example.l.wanshaapp.R;
//import com.example.l.wanshaapp.Rankingtools.MyShoucangTools;
//import com.example.l.wanshaapp.bean.GamesInfo;
//import com.squareup.okhttp.Request;
//import com.zhy.http.okhttp.OkHttpUtils;
//import com.zhy.http.okhttp.callback.StringCallback;
//
//import java.util.ArrayList;
//
//
///**
// * Created by 侯顺发 on 2018/6/25.
// */
//
//public class MyShoucangAdapter extends BaseAdapter {
//    ArrayList<GamesInfo> ranklist;
//    private Context context;
//
//
//    public MyShoucangAdapter(Context context) {
//        this.context = context;
//    }
//
//    public int getCount() {
//        return MyShoucangTools.imageView.length;
//    }
//
//    @Override
//    public Object getItem(int position) {
//        return position;
//    }
//
//    @Override
//    public long getItemId(int position) {
//        return position;
//    }
//
//    @Override
//    public View getView(final int position, View convertView, ViewGroup parent) {
//        convertView = LayoutInflater.from(context).inflate(R.layout.activity_myshoucang, parent, false);
//        ImageView imageView = (ImageView) convertView.findViewById(R.id.ShoucangTupian);
//        final TextView item_title = (TextView) convertView.findViewById(R.id.ShoucangGamename);
//        final TextView item_title1 = (TextView) convertView.findViewById(R.id.ShoucangCompany);
//
//
//        Button button = (Button) convertView.findViewById(R.id.shouchang);
//        button.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Bundle bundle = new Bundle();
//                bundle.putString("title1", ranklist.get(position).getGame_name());
//                bundle.putString("publisher", ranklist.get(position).getGrade());
//                bundle.putInt("image", MyShoucangTools.imageView[position]);
//            }
//        });
//
//        imageView.setImageResource(MyShoucangTools.imageView[position]);
//        item_title.setText(MyShoucangTools.title1[position]);
//        item_title1.setText(MyShoucangTools.publisher[position]);
//
//
//        return convertView;
//    }
//}
//
//
