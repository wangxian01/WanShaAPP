package com.example.l.wanshaapp.RankingFragmentadapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.l.wanshaapp.R;
import com.example.l.wanshaapp.Rankingbean.RankingFragemntBean;
import com.example.l.wanshaapp.Rankingtools.Tools;
import com.example.l.wanshaapp.XiangQingActivity;

import java.util.ArrayList;


/**
 * Created by 侯顺发 on 2018/5/24.
 */

public class OneFragmentAdapter extends BaseAdapter{


    ArrayList<RankingFragemntBean> ranklist;
    private  Context context;
    public OneFragmentAdapter(Context context,ArrayList<RankingFragemntBean> ranklistchuan) {
        this.context = context;
        this.ranklist=ranklistchuan;

    }
    @Override
    public int getCount() {
        return ranklist.size();
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        convertView =  LayoutInflater.from(context).inflate(R.layout.list_view, parent,false);
        ImageView imageView=(ImageView) convertView.findViewById(R.id.item_img) ;
        ImageView imageView5 = (ImageView) convertView.findViewById(R.id.item_img5);
        TextView item_num = (TextView) convertView.findViewById(R.id.item_num);
        final TextView item_title=(TextView) convertView.findViewById(R.id.item_title);
        final TextView item_date=(TextView) convertView.findViewById(R.id.item_date);
        final TextView item_date2=(TextView) convertView.findViewById(R.id.item_date2);
        final TextView item_date3=(TextView) convertView.findViewById(R.id.item_date3);
        final TextView item_date4=(TextView) convertView.findViewById(R.id.item_date4);
        final TextView item_date5=(TextView) convertView.findViewById(R.id.item_date5);
        final TextView item_date6=(TextView) convertView.findViewById(R.id.item_date6);



        //点击按钮跳转页面并实现传参
        Button button = (Button) convertView.findViewById(R.id.button_info);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Log.e("测试","点击了"+item_title.getText());
                Bundle bundle = new Bundle();
                Intent intent=new Intent(context,XiangQingActivity.class);
                bundle.putString("title1",ranklist.get(position).getGame_name());
                bundle.putString("date4",ranklist.get(position).getGrade());
                bundle.putString("date",ranklist.get(position).getGame_type1());
                bundle.putString("date2",ranklist.get(position).getGame_type2());
                bundle.putString("date3",ranklist.get(position).getGame_type3());
                bundle.putString("date5",ranklist.get(position).getGame_type4());
                bundle.putString("xiangqingtext1",ranklist.get(position).getGame_introduction());
                bundle.putString("xiangqingvideo",ranklist.get(position).getGame_videourl());
                bundle.putInt("image",Tools.img[position]);
                bundle.putInt("image5",Tools.img5[position]);
                bundle.putString("xiangqingtext2",ranklist.get(position).getGame_recommend());
                bundle.putString("xiangqingtext4",ranklist.get(position).getGame_details());
                bundle.putString("download",ranklist.get(position).getGame_downloadurl());
//                Log.e("网络数据",ranklist.get(position).getGames_downloadurl());
                bundle.putString("publisher",ranklist.get(position).getPublisher());
                bundle.putString("shouchang", Tools.shouchang[position]);
//                Log.e("dayin",Tools.xiangqingtext2[position]);
                intent.putExtras(bundle);
                context.startActivity(intent);

            }
        });


        imageView.setImageResource(Tools.img[position]);
        imageView5.setImageResource(Tools.img5[position]);
        item_date.setText(ranklist.get(position).getGame_type1());
        item_date2.setText(ranklist.get(position).getGame_type2());
        item_date3.setText(ranklist.get(position).getGame_type3());
        item_date4.setText(ranklist.get(position).getGrade());
        item_date5.setText(ranklist.get(position).getGame_type4());
        item_date6.setText(ranklist.get(position).getGame_introduction());
        item_num.setText((position+1)+"");
        item_title.setText(ranklist.get(position).getGame_name());
        return convertView;
    }
}
