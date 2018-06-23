package com.example.l.wanshaapp.DyFocusCommentDetails;

import android.graphics.Color;
import android.support.design.widget.BottomSheetBehavior;
import android.support.design.widget.BottomSheetDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.l.wanshaapp.DynamicChoiceness.BeanChoiceness;
import com.example.l.wanshaapp.DynamicChoiceness.SerializableMap;
import com.example.l.wanshaapp.DynamicCommentDetails.AdapterCommentMain;
import com.example.l.wanshaapp.DynamicCommentDetails.CommentBean;
import com.example.l.wanshaapp.DynamicCommentDetails.ReplayBean;
import com.example.l.wanshaapp.DynamicHome.CircleImageView;
import com.example.l.wanshaapp.R;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class ReplayActivity extends AppCompatActivity {
    private ListView mReplayList;
    private TextView mReplayPl;
    private CircleImageView mReplayUserLogo;
    private TextView mReplayName;
    private TextView mReplayTime;
    private ImageView mReplayImg;
    private TextView mReplayTextMain;
    private TextView mReplayUnfold;
    private List<Map<String,Object>> dataList;
    private AdapterCommentMain adapterCommentMain;
    private OkHttpClient client = new OkHttpClient();
    private BottomSheetDialog dialog;
    public static final MediaType JSON
            = MediaType.parse("application/json; charset=utf-8");
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_replay);
        mReplayList = (ListView) findViewById(R.id.replay_list);
        mReplayPl = (TextView) findViewById(R.id.replay_pl);
        View headView = getLayoutInflater().inflate(R.layout.headview_replsy, null);
        mReplayUserLogo = (CircleImageView) headView.findViewById(R.id.replay_userLogo);
        mReplayName = (TextView)  headView.findViewById(R.id.replay__name);
        mReplayTime = (TextView)  headView.findViewById(R.id.replay__time);
        mReplayImg = (ImageView)  headView.findViewById(R.id.replay_img);
        mReplayTextMain = (TextView)  headView.findViewById(R.id.replay__text_main);
        mReplayUnfold = (TextView)  headView.findViewById(R.id.replay_unfold);

        //传递参数
        Bundle bundle = getIntent().getExtras();
        SerializableMap serializableMap = (SerializableMap) bundle.get("map");

        //获取头像
        Picasso.with(this)
                .load(String.valueOf(serializableMap.getMap("map").get("Comments_portrait")))
                .into(mReplayUserLogo);
        //获取up主名称
        mReplayName.setText(String.valueOf(serializableMap.getMap("map").get("Comments_name")));
        //获取发布时间
        mReplayTime.setText(String.valueOf(serializableMap.getMap("map").get("Comments_time")));
        //获取游戏介绍文字
        mReplayTextMain.setText(String.valueOf(serializableMap.getMap("map").get("Comments_text")));
        /**
         * 设置收起和展开状态
         */
        final BeanChoiceness beanChoiceness = new BeanChoiceness();
        beanChoiceness.setunfold(false);

        mReplayUnfold.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean flag = beanChoiceness.getunfold();
                if (flag){
                    mReplayTextMain.setMaxLines(2);
                    mReplayUnfold.setText("展开");
                    mReplayUnfold.setTextColor(Color.parseColor("#464646"));
                    beanChoiceness.setunfold(false);
                }else{
                    mReplayTextMain.setMaxLines(1000);
                    mReplayUnfold.setText("收起");
                    mReplayUnfold.setTextColor(Color.parseColor("#1296DB"));
                    beanChoiceness.setunfold(true);
                }
            }
        });

        initDataList();
        mReplayList.addHeaderView(headView);
        //添加适配器
        adapterCommentMain = new AdapterCommentMain(this, dataList, R.layout.item_main_comment);
        mReplayList.setAdapter(adapterCommentMain);

        mReplayPl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showCommentDialog();
            }
        });
    }

    /**
     * post请求*/
    private String post(String url, String json) throws IOException {
        RequestBody body = RequestBody.create(JSON, json);
        Request request = new Request.Builder()
                .url(url)
                .post(body)
                .build();
        Response response = client.newCall(request).execute();
        return response.body().string();
    }

    /**
     * 初始化适配器需要的数据格式
     */
    private void initDataList() {
        dataList = new ArrayList<Map<String, Object>>();
        Thread thread = new Thread(){
            @Override
            public void run() {
                super.run();
                try {
                    String restult = post("http://172.16.22.46:8080/AndroidServers/ReplysServlet","");
                    Gson gson = new Gson();
                    ArrayList<ReplayBean> replayBeans = gson.fromJson(restult,new TypeToken<ArrayList<ReplayBean>>() {
                    }.getType());

                    for (int i = 0; i < replayBeans.size(); i++) {
                        Map<String, Object> map = new HashMap<String, Object>();
                        map.put("Comments_name", replayBeans.get(i).getReplys_name());
                        map.put("Comments_like",replayBeans.get(i).getReplys_like());
                        map.put("Comments_portrait", replayBeans.get(i).getReplys_portrait());
                        map.put("Comments_text", replayBeans.get(i).getReplys_text());
                        map.put("Comments_time",replayBeans.get(i).getReplys_time());
                        map.put("Comments_id", replayBeans.get(i).getReplys_id());
                        map.put("Comments_number", replayBeans.get(i).getReplys_number());
                        dataList.add(map);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        };
        thread.start();
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    /**
     * by moos on 2018/06
     * func:弹出评论框
     */
    private void showCommentDialog(){
        dialog = new BottomSheetDialog(this);
        final View commentView = LayoutInflater.from(this).inflate(R.layout.comment_dialog_layout,null);
        final EditText commentText = (EditText) commentView.findViewById(R.id.dialog_comment_et);
        final Button bt_comment = (Button) commentView.findViewById(R.id.dialog_comment_bt);
        dialog.setContentView(commentView);
        /**
         * 解决bsd显示不全的情况
         */
        View parent = (View) commentView.getParent();
        BottomSheetBehavior behavior = BottomSheetBehavior.from(parent);
        commentView.measure(0,0);
        behavior.setPeekHeight(commentView.getMeasuredHeight());

        final SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy.MM.dd");
        //获取当前时间
        final Date date = new Date(System.currentTimeMillis());
        bt_comment.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                String commentContent = commentText.getText().toString().trim();
                if(!TextUtils.isEmpty(commentContent)){
                    dialog.dismiss();
                    //dataList = new ArrayList<Map<String, Object>>();
                    Map<String, Object> map = new HashMap<String, Object>();

                    map.put("Comments_name", "张三");
                    map.put("Comments_like","0");
                    map.put("Comments_portrait", "http://uploads.sundxs.com/allimg/1705/1R3054M5-9.jpg");
                    map.put("Comments_text", commentContent);
                    map.put("Comments_time",simpleDateFormat.format(date));
                    map.put("Comments_id", "");
                    map.put("Comments_number","0");
                    // dataList.add(map);
                    adapterCommentMain.addTheCommentData(map);
                    //mFocusCommentsMainlist.smoothScrollToPosition(0);
                    mReplayList.deferNotifyDataSetChanged();
                    adapterCommentMain.notifyDataSetChanged();
                    Toast.makeText(ReplayActivity.this,"评论成功",Toast.LENGTH_SHORT).show();

                }else {
                    Toast.makeText(ReplayActivity.this,"评论内容不能为空",Toast.LENGTH_SHORT).show();
                }
            }
        });
        commentText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if(!TextUtils.isEmpty(charSequence) && charSequence.length()>2){
                    bt_comment.setBackgroundColor(Color.parseColor("#FFB568"));
                }else {
                    bt_comment.setBackgroundColor(Color.parseColor("#D8D8D8"));
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        dialog.show();
    }

}
