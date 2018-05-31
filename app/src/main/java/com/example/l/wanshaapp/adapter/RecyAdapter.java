package com.example.l.wanshaapp.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.l.wanshaapp.R;
import com.example.l.wanshaapp.bean.Small;

import java.util.ArrayList;
import java.util.List;

public class RecyAdapter extends RecyclerView.Adapter<RecyAdapter.ViewHolder> {


    private List<Small> mFruitList = new ArrayList<Small>();

    static class ViewHolder extends RecyclerView.ViewHolder {

        ImageView mMImageView;
        TextView mTextView;

        public ViewHolder(View itemView) {
            super(itemView);
            mMImageView =  (ImageView) itemView.findViewById(R.id.imga);
            mTextView = (TextView) itemView.findViewById(R.id.text3);
        }
    }

    public RecyAdapter(List<Small> fruitList) {
        mFruitList = fruitList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = View.inflate(parent.getContext(), R.layout.zhuda_item, null);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }


    @Override
    public void onBindViewHolder(RecyAdapter.ViewHolder holder, int position) {
        Small fruit = mFruitList.get(position);
        holder.mMImageView.setImageResource(fruit.getImageId());
        holder.mTextView.setText(fruit.getName());
    }

    @Override
    public int getItemCount() {
        return mFruitList.size();
    }
}