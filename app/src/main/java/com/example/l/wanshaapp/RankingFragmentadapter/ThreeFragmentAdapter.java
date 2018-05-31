package com.example.l.wanshaapp.RankingFragmentadapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.l.wanshaapp.R;
import com.example.l.wanshaapp.Rankingtools.ThreeFragmentTools;


/**
 * Created by 侯顺发 on 2018/5/25.
 */

public class ThreeFragmentAdapter extends BaseAdapter{
    private Context context;
    public ThreeFragmentAdapter(Context context){
        this.context=context;
    }
    public int getCount() {
        return ThreeFragmentTools.img.length;
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
     convertView= LayoutInflater.from(context).inflate(R.layout.list_view,parent,false);
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

        imageView1.setImageResource(ThreeFragmentTools.img1[position]);
        imageView.setImageResource(ThreeFragmentTools.img[position]);
        imageView5.setImageResource(ThreeFragmentTools.img5[position]);
        item_date.setText(ThreeFragmentTools.date[position]);
        item_date2.setText(ThreeFragmentTools.date2[position]);
        item_date3.setText(ThreeFragmentTools.date3[position]);
        item_date4.setText(ThreeFragmentTools.data4[position]);
        item_date5.setText(ThreeFragmentTools.date5[position]);
        item_date6.setText(ThreeFragmentTools.data6[position]);
        item_num.setText((position+1)+"");
        item_title.setText(ThreeFragmentTools.title[position]);
        return convertView;
    }
}
