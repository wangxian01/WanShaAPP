package com.example.l.wanshaapp.RankingFragmentadapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.l.wanshaapp.R;
import com.example.l.wanshaapp.Rankingtools.Tools;


/**
 * Created by 侯顺发 on 2018/5/24.
 */

public class OneFragmentAdapter extends BaseAdapter{

    private  Context context;
    public OneFragmentAdapter(Context context) {
        this.context = context;

    }
    @Override
    public int getCount() {
        return Tools.img.length;
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
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView =  LayoutInflater.from(context).inflate(R.layout.list_view, parent,false);
        ImageView imageView=(ImageView) convertView.findViewById(R.id.item_img) ;
        ImageView imageView1 = (ImageView) convertView.findViewById(R.id.item_img1);
        ImageView imageView5 = (ImageView) convertView.findViewById(R.id.item_img5);
        TextView item_num = (TextView) convertView.findViewById(R.id.item_num);
        TextView item_title=(TextView) convertView.findViewById(R.id.item_title);
        TextView item_date=(TextView) convertView.findViewById(R.id.item_date);
        TextView item_date2=(TextView) convertView.findViewById(R.id.item_date2);
        TextView item_date3=(TextView) convertView.findViewById(R.id.item_date3);
        TextView item_date4=(TextView) convertView.findViewById(R.id.item_date4);
        TextView item_date5=(TextView) convertView.findViewById(R.id.item_date5);
        TextView item_date6=(TextView) convertView.findViewById(R.id.item_date6);

        imageView1.setImageResource(Tools.img1[position]);
        imageView.setImageResource(Tools.img[position]);
        imageView5.setImageResource(Tools.img5[position]);
        item_date.setText(Tools.date[position]);
        item_date2.setText(Tools.date2[position]);
        item_date3.setText(Tools.date3[position]);
        item_date4.setText(Tools.data4[position]);
        item_date5.setText(Tools.data5[position]);
        item_date6.setText(Tools.data6[position]);
        item_num.setText((position+1)+"");
        item_title.setText(Tools.title[position]);
        return convertView;
    }
}
