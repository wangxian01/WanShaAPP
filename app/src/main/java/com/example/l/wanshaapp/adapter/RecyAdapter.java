package com.example.l.wanshaapp.adapter;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.l.wanshaapp.JCVideoPlayerStandard.player;
import com.example.l.wanshaapp.R;
import com.example.l.wanshaapp.bean.Small;

import java.util.ArrayList;
import java.util.List;

public class RecyAdapter extends RecyclerView.Adapter<RecyAdapter.ViewHolder> {



    private List<Small> mFruitList = new ArrayList<Small>();
    private OnItemClickListener mOnItemClickListener;
    private Activity activity;


    static class ViewHolder extends RecyclerView.ViewHolder {

        ImageView mMImageView;
        TextView mTextView;





        public ViewHolder(View itemView) {
            super(itemView);
            mMImageView =  (ImageView) itemView.findViewById(R.id.imga);
            mTextView = (TextView) itemView.findViewById(R.id.text3);
        }

    }

    public RecyAdapter(Activity activity ,List<Small> fruitList) {
        this.mFruitList = fruitList;
        this.activity = activity;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = View.inflate(parent.getContext(), R.layout.zhuda_item, null);
        ViewHolder holder = new ViewHolder(view);

        return holder;
    }


    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        final Small fruit = mFruitList.get(position);
        holder.mMImageView.setImageResource(fruit.getImageId());
        holder.mTextView.setText(fruit.getName());

        holder.mMImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                Intent intent=new Intent(activity,player.class);
                bundle.putInt("img",fruit.getImageId());
                bundle.putString("id",fruit.getName());
                bundle.putString("type","RecyAdapter");
                intent.putExtras(bundle);
                activity.startActivity(intent);
            }
        });
            if( mOnItemClickListener!= null){
                holder.itemView.setOnClickListener( new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mOnItemClickListener.onClick(position);
                    }
                });
                holder.itemView.setOnLongClickListener( new View.OnLongClickListener() {
                    @Override
                    public boolean onLongClick(View v) {
                        mOnItemClickListener.onLongClick(position);
                        return false;
                    }
                });
            }
        }




    @Override
    public int getItemCount() {
        return mFruitList.size();
    }

    public interface OnItemClickListener{
        void onClick( int position);
        void onLongClick( int position);
    }
    public void setOnItemClickListener(OnItemClickListener onItemClickListener ){
        this. mOnItemClickListener=onItemClickListener;
    }



}
