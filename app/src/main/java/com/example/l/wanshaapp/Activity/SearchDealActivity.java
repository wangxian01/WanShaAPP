package com.example.l.wanshaapp.Activity;


        import android.os.Bundle;
        import android.support.annotation.NonNull;
        import android.support.annotation.Nullable;
        import android.support.v7.app.AppCompatActivity;
        import android.support.v7.widget.DefaultItemAnimator;
        import android.support.v7.widget.LinearLayoutManager;
        import android.support.v7.widget.RecyclerView;
        import android.util.Log;
        import android.view.LayoutInflater;
        import android.view.View;
        import android.view.ViewGroup;
        import android.view.Window;
        import android.widget.ImageView;
        import android.widget.TextView;

        import com.example.l.wanshaapp.R;
        import com.example.l.wanshaapp.bean.GamesInfo;
        import com.google.gson.Gson;
        import com.google.gson.reflect.TypeToken;
        import com.squareup.okhttp.Request;
        import com.squareup.picasso.Picasso;
        import com.zhy.http.okhttp.OkHttpUtils;
        import com.zhy.http.okhttp.callback.StringCallback;

        import org.json.JSONException;
        import org.json.JSONObject;

        import java.util.ArrayList;
        import java.util.List;

        import static android.content.ContentValues.TAG;

public class SearchDealActivity extends AppCompatActivity {


    private RecyclerView mRecyclerView;
    private MyRecyclerViewAdapter mAdapter;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.searchdeallayout);
        //通过findViewById拿到RecyclerView实例
        mRecyclerView = findViewById(R.id.searchdealrecyclerview);
//设置RecyclerView管理器
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));

        Thread thread = new Thread(new Runnable() {
            String searchkeyword = getIntent().getStringExtra("searchkeyword");

            @Override
            public void run() {
                OkHttpUtils
                        .get()
                        .url("http://" + getApplicationContext().getString(R.string.netip) + ":8080/AndroidServers/SearchDealServlet")
                        .addParams("gamename", searchkeyword)
                        .build()
                        .execute(new StringCallback() {
                            @Override
                            public void onError(Request request, Exception e) {
                                Log.e(TAG, "网络错误");
                            }

                            @Override
                            public void onResponse(String response) {

                                if (response.toString() != null) {

                                    ArrayList<GamesInfo> list;
                                    list = new ArrayList<GamesInfo>();
                                    Gson gson = new Gson();
                                    list = gson.fromJson(response.toString(), new TypeToken<ArrayList<GamesInfo>>() {
                                    }.getType());

//初始化适配器
                                    mAdapter = new MyRecyclerViewAdapter(list);
//设置添加或删除item时的动画，这里使用默认动画
                                    mRecyclerView.setItemAnimator(new DefaultItemAnimator());
//设置适配器
                                    mRecyclerView.setAdapter(mAdapter);
                                } else {
                                    Log.e(TAG, "没有接受到json信息");
                                }
                            }
                        });
            }
        });
        thread.start();


    }

    //适配器
    public class MyRecyclerViewAdapter extends RecyclerView.Adapter<MyRecyclerViewAdapter.ViewHolder> {

        ArrayList<GamesInfo> haha;

        public MyRecyclerViewAdapter(ArrayList<GamesInfo> hehe) {
            this.haha = hehe;
        }

        @Override
        public MyRecyclerViewAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.guanzhugame_item, parent, false);
            MyRecyclerViewAdapter.ViewHolder viewHolder = new MyRecyclerViewAdapter.ViewHolder(view);
            return viewHolder;
        }

        @Override
        public void onBindViewHolder(@NonNull MyRecyclerViewAdapter.ViewHolder holder, int position) {
        }

        @Override
        public int getItemCount() {
            return haha.size();
        }


        class ViewHolder extends RecyclerView.ViewHolder {
            ImageView imageView, imageView1;
            TextView textView1, textView2, textView3, textView4;

            ViewHolder(View itemView) {

                super(itemView);
                imageView = (ImageView) itemView.findViewById(R.id.lefticon);
                imageView1 = (ImageView) itemView.findViewById(R.id.righticon);
                textView1 = (TextView) itemView.findViewById(R.id.gamename);
                textView2 = (TextView) itemView.findViewById(R.id.gamecompanies);

                textView3 = (TextView) itemView.findViewById(R.id.newdynamic);
                textView4 = (TextView) itemView.findViewById(R.id.numberofcomments);
                Picasso.with(itemView.getContext()).load("").into(imageView);
                textView1.setText(haha.get(0).getGame_name());
                textView2.setText(haha.get(0).getPublisher());
                imageView1.setImageResource(0);
                textView3.setText("");
                textView4.setText(haha.get(0).getGrade());
            }
        }

    }
}
