package com.example.l.wanshaapp.adapter;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.l.wanshaapp.R;
import com.example.l.wanshaapp.Rankingtools.FourFragmentTools;
import com.example.l.wanshaapp.Rankingtools.Tools;
import com.example.l.wanshaapp.XiangQingActivity;
import com.example.l.wanshaapp.bean.HomeLIstViewBean;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;


/**
 * by  谭林
 * 适配器
 */
public class HomeListAdapter extends BaseAdapter {
    private Context mContext;
    private ArrayList<HomeLIstViewBean> shuju;


    public HomeListAdapter(Activity mContext, ArrayList<HomeLIstViewBean> beanArrayList) {
        this.mContext = mContext;
        this.shuju = beanArrayList;
    }

    @Override
    public int getCount() {
        return shuju.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        @SuppressLint("ViewHolder") View view = inflater.inflate(R.layout.home_recommend_item, parent, false);
        TextView diyihang = view.findViewById(R.id.diyihang);
        TextView dierhang = view.findViewById(R.id.dierhang);
        ImageView imageView = view.findViewById(R.id.recommendpicture);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                Intent intent = new Intent(mContext, XiangQingActivity.class);

                bundle.putString("title1",FourFragmentTools.title[position]);
                bundle.putString("date4",FourFragmentTools.data4[position]);
                bundle.putString("date",FourFragmentTools.date[position]);
                bundle.putString("date2",FourFragmentTools.date2[position]);
                bundle.putString("date3",FourFragmentTools.date3[position]);
                bundle.putString("xiangqingvideo", FourFragmentTools.xiangqingvideo[position]);
                bundle.putString("date5",FourFragmentTools.date5[position]);
                bundle.putString("xiangqingtext1",FourFragmentTools.data6[position]);
                bundle.putInt("image",FourFragmentTools.img[position]);
                bundle.putInt("image5",FourFragmentTools.img5[position]);
                bundle.putString("xiangqingtext2",FourFragmentTools.xiangqingtext2[position]);
                bundle.putString("xiangqingtext4",FourFragmentTools.xiangqingtext4[position]);
                bundle.putString("download",FourFragmentTools.download[position]);
                bundle.putString("publisher",FourFragmentTools.publisher[position]);
                bundle.putString("shouchang", FourFragmentTools.shouchang[position]);

                intent.putExtras(bundle);
                mContext.startActivity(intent);
            }
        });

        diyihang.setText(shuju.get(position).getTitle());
        dierhang.setText(shuju.get(position).getSubtitle());
        Picasso.with(view.getContext()).load("http://" + mContext.getString(R.string.netip) + ":8080/AndroidServers/images/image" + position + ".jpg").into(imageView);
        /* imageView.setImageResource(R.drawable.h);*/
        /*    imageView.setImageURI(Uri.fromFile(new File("/sdcard/wansha/image/h.jpg")));*/
        imageView.setScaleType(ImageView.ScaleType.FIT_XY);
        return view;
    }

}
