package com.example.l.wanshaapp.Activity;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.TextView;

import com.example.l.wanshaapp.R;

import java.util.List;

public class GuanzhuGameActivity extends AppCompatActivity {


    private  RecyclerView mRecyclerView;
    MyRecyclerViewAdapter mAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.guanzhu_game_layout);
        //通过findViewById拿到RecyclerView实例
        mRecyclerView =   findViewById(R.id.recyclerView);
//设置RecyclerView管理器
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
//初始化适配器
        mAdapter = new MyRecyclerViewAdapter();
//设置添加或删除item时的动画，这里使用默认动画
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
//设置适配器
        mRecyclerView.setAdapter(mAdapter);
    }


    public class MyRecyclerViewAdapter extends RecyclerView.Adapter<MyRecyclerViewAdapter.ViewHolder> {
 /*       private List<String> list;

        public MyRecyclerViewAdapter(List<String> list) {
            this.list = list;
        }
*/
        @Override
        public MyRecyclerViewAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.guanzhugame_item, parent, false);
            MyRecyclerViewAdapter.ViewHolder viewHolder = new ViewHolder(view);
            return viewHolder;
        }

        @Override
        public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
            /*holder.mText.setText(list.get(position));*/
        }

        @Override
        public int getItemCount() {
            return 2;
        }


        class ViewHolder extends RecyclerView.ViewHolder {
            ViewHolder(View itemView) {
                super(itemView);

            }
        }

    }

}



