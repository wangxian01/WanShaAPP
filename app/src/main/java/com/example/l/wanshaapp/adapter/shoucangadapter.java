package com.example.l.wanshaapp.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;

import com.example.l.wanshaapp.R;
import com.example.l.wanshaapp.bean.shoucangbean;

import java.util.ArrayList;
import java.util.List;

public class shoucangadapter extends BaseAdapter {

    List<shoucangbean> data = new ArrayList<shoucangbean>();
    Context context;
    public shoucangadapter(Context context, List<shoucangbean> data) {
        this.context = context;
        this.data = data;
    }

    @Override
    public int getCount() {
        return data.size();// 返回20条数据
    }

    @Override
    public Object getItem(int arg0) {
        return data.get(arg0);
    }

    @Override
    public long getItemId(int position) {
        // TODO Auto-generated method stub
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        final ViewHolder holder;
        final shoucangbean bean = data.get(position);

        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item,
                    parent, false);
            holder = new ViewHolder();
            holder.shoucan_img = (Switch) convertView.findViewById(R.id.xiazai);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        // 取出bean中当记录状态是否为true，是的话则给img设置release收藏图片

        holder.shoucan_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 获取上次是否已经被点击
                boolean flag = bean.isShoucanFocus();
                // 判断当前flag是收藏还是取收藏,是的话就给bean值减1，否则就加1
                if (flag) {
                    bean.setShoucanNum(bean.getShoucanNum() - 1);
                } else {
                    bean.setShoucanNum(bean.getShoucanNum() + 1);
                }
                // 反向存储记录，实现取消收藏功能
                bean.setShoucanFocus(!flag);
                //动画
            }
        });
        return convertView;
    }

    private class ViewHolder {
        private Switch shoucan_img;
    }

}
