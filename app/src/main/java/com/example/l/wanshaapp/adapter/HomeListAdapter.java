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


/**
 *   适配器
 */
public class HomeListAdapter  extends BaseAdapter {
    private Context mContext;

    public HomeListAdapter(Activity mContext) {
        this.mContext = mContext;
    }

    @Override
    public int getCount() {
        return 1;
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
        texttitle.setText("寻求宽恕再度来袭");
        TextView texttitle2=(TextView)view.findViewById(R.id.bigtitle);
        texttitle2.setText("让世界惊叹的唯美解密手游");
        ImageView imageView=(ImageView) view.findViewById(R.id.recommendpicture);
        imageView.setImageResource(R.drawable.h);
        return view;
    }

}
