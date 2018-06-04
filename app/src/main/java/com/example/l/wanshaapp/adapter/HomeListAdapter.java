package com.example.l.wanshaapp.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.l.wanshaapp.R;

import java.util.ArrayList;


/**
 *   适配器
 */
public class HomeListAdapter  extends BaseAdapter {
    private Context mContext;
    ArrayList<String> shuju;


    public HomeListAdapter(Activity mContext,ArrayList<String> newList) {
        this.mContext = mContext;
        this.shuju=newList;
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
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        View view = inflater.inflate(R.layout.home_recommend_item, parent,false);
        TextView texttitle=(TextView)view.findViewById(R.id.recommendtitle);
        texttitle.setText(shuju.get(0));
        TextView texttitle2=(TextView)view.findViewById(R.id.bigtitle);
        texttitle2.setText(shuju.get(1));
        ImageView imageView=(ImageView) view.findViewById(R.id.recommendpicture);
        imageView.setImageResource(R.drawable.h);
        return view;
    }

}
