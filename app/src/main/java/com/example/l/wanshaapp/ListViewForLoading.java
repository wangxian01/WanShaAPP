package com.example.l.wanshaapp;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AbsListView.OnScrollListener;
import android.widget.LinearLayout.LayoutParams;

public class ListViewForLoading extends Activity implements OnScrollListener {

private listViewAdapter adapter =new listViewAdapter();
        ListView listView;
        LinearLayout loadingLayout;
private Thread mThread;
/**
 * 设置布局显示属性
 */
private LayoutParams mLayoutParams =new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
/**
 * 设置布局显示目标最大化属性
 */
private LayoutParams FFlayoutParams =new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);

private ProgressBar progressBar;

/** Called when the activity is first created. */
@Override
    public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.item);
            init();
            }

            private void init() {
            // TODO Auto-generated method stub
            // 线性布局
            LinearLayout layout =new LinearLayout(this);
            // 设置布局 水平方向
            layout.setOrientation(LinearLayout.HORIZONTAL);
            layout.setBackground(getDrawable(R.drawable.c));
            // 进度条
            progressBar =new ProgressBar(this);
            // 进度条显示位置
            progressBar.setPadding(0, 0, 15, 0);
            // 把进度条加入到layout中
            layout.addView(progressBar, mLayoutParams);
            // 文本内容
            TextView textView =new TextView(this);
            textView.setText("加载中...");
            textView.setGravity(Gravity.CENTER_VERTICAL);
            // 把文本加入到layout中
            layout.addView(textView, FFlayoutParams);
            // 设置layout的重力方向，即对齐方式是
            layout.setGravity(Gravity.CENTER);

            // 设置ListView的页脚layout
            loadingLayout =new LinearLayout(this);
            loadingLayout.addView(layout, mLayoutParams);
            loadingLayout.setGravity(Gravity.CENTER);

            // 得到一个ListView用来显示条目
         listView = (ListView) findViewById(R.id.testlist);
            // 添加到脚页显示
            listView.addFooterView(loadingLayout);
            // 给ListView添加适配器
            listView.setAdapter(adapter);
            // 给ListView注册滚动监听
        listView.setOnScrollListener(this);
            }

/**
 * 要用用于生成显示数据
 *
 * @author huangbq
 *
 */
class listViewAdapter extends BaseAdapter {
    int count =10;

    public int getCount() {
        return count;
    }

    public Object getItem(int pos) {
        return pos;
    }

    public long getItemId(int pos) {
        return pos;
    }

    public View getView(int pos, View v, ViewGroup p) {
        TextView view;
        if (v ==null) {
            view =new TextView(ListViewForLoading.this);
        } else {
            view = (TextView) v;
        }
        view.setText("ListItem "+ pos);
        view.setTextSize(20f);
        view.setGravity(Gravity.CENTER);
        view.setHeight(60);
        return view;
    }
}

    @Override
    public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
        // TODO Auto-generated method stub
        if(firstVisibleItem+visibleItemCount==totalItemCount)
        {
            //开线程去下载网络数据
            if (mThread ==null||!mThread.isAlive()) {
                mThread =new Thread() {
                    @Override
                    public void run() {
                        try {
                            //这里放你网络数据请求的方法，我在这里用线程休眠5秒方法来处理
                            Thread.sleep(5000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        Message message =new Message();
                        message.what =1;
                        handler.sendMessage(message);
                    }
                };
                mThread.start();
            }
        }
    }

    @Override
    public void onScrollStateChanged(AbsListView view, int scrollState) {
        // TODO Auto-generated method stub
    }



    @SuppressLint("HandlerLeak")
    private Handler handler =new Handler() {
        @Override
        public void handleMessage(Message msg) {
            // TODO Auto-generated method stub
            switch (msg.what) {
                case 1:
                if (adapter.count <=41) {
                    adapter.count +=10;
                    int currentPage = adapter.count /10;
                    Toast.makeText(getApplicationContext(),"第"+ currentPage +"页", Toast.LENGTH_LONG).show();
                } else {
                    listView.removeFooterView(loadingLayout);
                }
                //重新刷新Listview的adapter里面数据
                adapter.notifyDataSetChanged();
                break;
                default: break;
            }
        }
    };


}