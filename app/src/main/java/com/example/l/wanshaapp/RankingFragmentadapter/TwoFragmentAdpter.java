package com.example.l.wanshaapp.RankingFragmentadapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.l.wanshaapp.R;
import com.example.l.wanshaapp.RankingFragment.TwoFragment;
import com.example.l.wanshaapp.Rankingtools.Tools;
import com.example.l.wanshaapp.Rankingtools.TwoFragmentTools;
import com.example.l.wanshaapp.XiangQingActivity;


/**
 * Created by 侯顺发 on 2018/5/24.
 */

public class TwoFragmentAdpter extends BaseAdapter {
    private Context context;
    public  TwoFragmentAdpter(Context context){

        this.context = context;
    }
    @Override
    public int getCount() {
        return TwoFragmentTools.img.length;
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
        ImageView imageView1 = (ImageView) convertView.findViewById(R.id.item_img1);
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
                bundle.putString("title1",item_title.getText().toString());
                bundle.putString("date4",item_date4.getText().toString());
                bundle.putString("date",item_date.getText().toString());
                bundle.putString("date2",item_date2.getText().toString());
                bundle.putString("date3",item_date3.getText().toString());
                bundle.putString("date5",item_date5.getText().toString());
                bundle.putString("xiangqingvideo", TwoFragmentTools.xiangqingvideo[position]);
                bundle.putString("xiangqingtext1",item_date6.getText().toString());
                bundle.putInt("image",TwoFragmentTools.img[position]);
                bundle.putInt("image5",TwoFragmentTools.img5[position]);
                bundle.putString("xiangqingtext2",TwoFragmentTools.xiangqingtext2[position]);
                bundle.putString("xiangqingtext4",TwoFragmentTools.xiangqingtext4[position]);
                bundle.putString("download",TwoFragmentTools.download[position]);
                bundle.putString("publisher",TwoFragmentTools.publisher[position]);
                bundle.putString("shouchang", TwoFragmentTools.shouchang[position]);
//                Log.e("dayin",Tools.xiangqingtext2[position]);
                intent.putExtras(bundle);

                context.startActivity(intent);

            }
        });


        imageView1.setImageResource(TwoFragmentTools.img1[position]);
        imageView.setImageResource(TwoFragmentTools.img[position]);
        imageView5.setImageResource(TwoFragmentTools.img5[position]);
        item_date.setText(TwoFragmentTools.date[position]);
        item_date2.setText(TwoFragmentTools.date2[position]);
        item_date3.setText(TwoFragmentTools.date3[position]);
        item_date4.setText(TwoFragmentTools.data4[position]);
        item_date5.setText(TwoFragmentTools.date5[position]);
        item_date6.setText(TwoFragmentTools.data6[position]);
        item_num.setText((position+1)+"");
        item_title.setText(TwoFragmentTools.title[position]);
        return convertView;
    }
}
