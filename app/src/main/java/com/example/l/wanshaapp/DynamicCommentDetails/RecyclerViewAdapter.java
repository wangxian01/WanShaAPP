package com.example.l.wanshaapp.DynamicCommentDetails;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


import com.example.l.wanshaapp.DynamicHome.CircleImageView;
import com.example.l.wanshaapp.R;

import java.util.List;
import java.util.Map;

/**
 * Created by cap on 2018/5/24.
 */

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.myViewHodler>{

    private Context context;
    private List<Map<String,Object>> dataList;

    public RecyclerViewAdapter(Context context, List<Map<String, Object>> dataList) {
        this.context = context;
        this.dataList = dataList;
    }

    @Override
    public myViewHodler onCreateViewHolder(ViewGroup parent, int viewType) {

        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_main_comment, parent, false);
//        View itemView = View.inflate(context,R.layout.item_main_comment,null);
        return new myViewHodler(itemView);
    }

    @Override
    public void onBindViewHolder(myViewHodler holder, int position) {
        final Map<String, Object> map = dataList.get(position);
        holder.mCommentItemUserName.setText((String) map.get("UpId"));
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    class myViewHodler extends RecyclerView.ViewHolder{

        private CircleImageView mCommentItemLogo;
        private TextView mCommentItemUserName;
        private TextView mCommentItemTime;
        private ImageView mCommentItemLike;
        private TextView mCommentItemContent;
        private TextView mCommentItemUnfod;

        public myViewHodler(View itemView) {
            super(itemView);
            mCommentItemLogo = (CircleImageView)itemView.findViewById(R.id.comment_item_logo);
            mCommentItemUserName = (TextView) itemView.findViewById(R.id.comment_item_userName);
            mCommentItemTime = (TextView) itemView.findViewById(R.id.comment_item_time);
            mCommentItemLike = (ImageView)itemView. findViewById(R.id.comment_item_like);
            mCommentItemContent = (TextView) itemView.findViewById(R.id.comment_item_content);
            mCommentItemUnfod = (TextView) itemView.findViewById(R.id.comment_item_unfod);
        }
    }
}
