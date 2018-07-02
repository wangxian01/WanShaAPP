package com.example.l.wanshaapp.DynamicCommentDetails;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.design.widget.BottomSheetBehavior;
import android.support.design.widget.BottomSheetDialog;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;


import com.example.l.wanshaapp.DyFocusCommentDetails.FcousCommentActivity;
import com.example.l.wanshaapp.DyFocusCommentDetails.ReplayActivity;
import com.example.l.wanshaapp.DynamicChoiceness.BeanChoiceness;
import com.example.l.wanshaapp.DynamicChoiceness.SerializableMap;
import com.example.l.wanshaapp.DynamicHome.CircleImageView;
import com.example.l.wanshaapp.R;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.squareup.picasso.Picasso;
import com.zhy.http.okhttp.OkHttpUtils;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import fm.jiecao.jcvideoplayer_lib.JCVideoPlayerStandard;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class CommentDetailsActivity extends AppCompatActivity {
    private JCVideoPlayerStandard mCommentViodeoview;
    private List<Map<String,Object>> dataList;
    private ListView mCommentsChoicenessMain;
    private CircleImageView mDetailPageUserLogo;
    private TextView mDetailPageUserName;
    private TextView mDetailPageTime;
    private ImageView mDetailPageFocus;
    private TextView mCommentTextMain;
    private TextView mChoicenessCommentsPl;
    private BottomSheetDialog dialog;
    private TextView mChoicenessCommentsTextUnfold;
    private AdapterCommentMain adapterCommentMain;
    private OkHttpClient client = new OkHttpClient();
    public static final MediaType JSON
            = MediaType.parse("application/json; charset=utf-8");
    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comment_details);

        View headView = getLayoutInflater().inflate(R.layout.headview_choiceness_comment, null);


        mChoicenessCommentsTextUnfold = (TextView)headView.findViewById(R.id.choiceness_comments_text_unfold);
        mDetailPageUserLogo = (CircleImageView) headView.findViewById(R.id.detail_page_userLogo);
        mDetailPageUserName = (TextView)headView. findViewById(R.id.detail_page_userName);
        mDetailPageTime = (TextView) headView.findViewById(R.id.detail_page_time);
        mCommentTextMain = (TextView)headView. findViewById(R.id.comment_text_main);
        mCommentsChoicenessMain = (ListView)findViewById(R.id.comments_choiceness_main);
        mChoicenessCommentsPl = (TextView) findViewById(R.id.choiceness_comments_pl);
        //传递参数
        Bundle bundle = getIntent().getExtras();
        SerializableMap serializableMap = (SerializableMap) bundle.get("map");

        mCommentViodeoview = (JCVideoPlayerStandard) headView.findViewById(R.id.comment_viodeoview);
        //设置网络视频
        mCommentViodeoview.setUp(String.valueOf(serializableMap.getMap("map").get("ChoicenessViodeoview"))
                , JCVideoPlayerStandard.SCREEN_LAYOUT_NORMAL, "\t\t"+String.valueOf(serializableMap.getMap("map").get("UpIntroduce")));

        //设置视频缩略图
        Picasso.with(this)
                .load(String.valueOf(serializableMap.getMap("map").get("VideoImg")))
                .into(mCommentViodeoview.thumbImageView);

        //获取头像
        Picasso.with(this)
                .load(String.valueOf(serializableMap.getMap("map").get("UpPortrait")))
                .into(mDetailPageUserLogo);
        //获取up主名称
        mDetailPageUserName.setText(String.valueOf(serializableMap.getMap("map").get("ChoicenessUpName")));
        //获取发布时间
        mDetailPageTime.setText(String.valueOf(serializableMap.getMap("map").get("ChoicenessUpTime")));
        //获取游戏介绍文字
        mCommentTextMain.setText(String.valueOf(serializableMap.getMap("map").get("ChoicenessUpText")));


        /**
         * 设置收起和展开状态
         */
        final BeanChoiceness beanChoiceness = new BeanChoiceness();
        beanChoiceness.setunfold(false);

        mChoicenessCommentsTextUnfold.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean flag = beanChoiceness.getunfold();
                if (flag){
                    mCommentTextMain.setMaxLines(2);
                    mChoicenessCommentsTextUnfold.setText("展开");
                    mChoicenessCommentsTextUnfold.setTextColor(Color.parseColor("#464646"));
                    beanChoiceness.setunfold(false);
                }else{
                    mCommentTextMain.setMaxLines(1000);
                    mChoicenessCommentsTextUnfold.setText("收起");
                    mChoicenessCommentsTextUnfold.setTextColor(Color.parseColor("#1296DB"));
                    beanChoiceness.setunfold(true);
                }
            }
        });
         /*
        * 点击评论
        * */
        mChoicenessCommentsPl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showCommentDialog();
            }
        });

        initDataList();
        //头布局添加适配器
        mCommentsChoicenessMain.addHeaderView(headView,null,false);
        adapterCommentMain = new AdapterCommentMain(this, dataList, R.layout.item_main_comment);
        mCommentsChoicenessMain.setAdapter(adapterCommentMain);

        /**
         * 点击listview传递参数（使用map）
         * */
        mCommentsChoicenessMain.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Map<String, Object> map = (Map<String, Object>) mCommentsChoicenessMain.getItemAtPosition(position);
                final SerializableMap myMap=new SerializableMap();
                myMap.setMap(map);//将map数据添加到封装的myMap<span></span>中
                Bundle bundle = new Bundle();
                bundle.putSerializable("map", myMap);
                Intent intent=new Intent(CommentDetailsActivity.this,ReplayActivity.class);
                intent.putExtras(bundle);
                startActivity(intent);
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
                    String restult = post("http://"+getString(R.string.netip)+":8080/AndroidServers/CommentServlet","");
                    Gson gson = new Gson();
                    ArrayList<CommentBean> commentBean = gson.fromJson(restult,new TypeToken<ArrayList<CommentBean>>() {
                    }.getType());

                    for (int i = 0; i < commentBean.size(); i++) {
                        Map<String, Object> map = new HashMap<String, Object>();
                        map.put("Comments_name", commentBean.get(i).getComments_name());
                        map.put("Comments_like", commentBean.get(i).getComments_like());
                        map.put("Comments_portrait", commentBean.get(i).getComments_portrait());
                        map.put("Comments_text", commentBean.get(i).getComments_text());
                        map.put("Comments_time", commentBean.get(i).getComments_time());
                        map.put("Comments_id", commentBean.get(i).getComments_id());
                        map.put("Comments_number", commentBean.get(i).getComments_number());
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
     * 初始化适配器需要的数据格式
     */
//    private void initDataList() {
//        dataList = new ArrayList<Map<String, Object>>();
//        Thread thread = new Thread(){
//            @Override
//            public void run() {
//                super.run();
//                try {
//
//                    String restult = post("http://"+getString(R.string.netip)+"/AndroidServers/CommentServlet","");
//                    Gson gson = new Gson();
//                    ArrayList<CommentBean> commentBean = gson.fromJson(restult,new TypeToken<ArrayList<CommentBean>>() {
//                    }.getType());
//                    Log.e("测试：", restult);
//
//                    for (int i = 0; i < commentBean.size(); i++) {
//                        Map<String, Object> map = new HashMap<String, Object>();
//                        map.put("Comments_name", commentBean.get(i).getComments_name());
//                        map.put("Comments_like", commentBean.get(i).getComments_like());
//                        map.put("Comments_portrait", commentBean.get(i).getComments_portrait());
//                        map.put("Comments_text", commentBean.get(i).getComments_text());
//                        map.put("Comments_time", commentBean.get(i).getComments_time());
//                        map.put("Comments_id", commentBean.get(i).getComments_id());
//                        map.put("Comments_number", commentBean.get(i).getComments_number());
//                        dataList.add(map);
//                    }
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//            }
//        };
//        thread.start();
//        try {
//            thread.join();
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//    }

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
                final String commentContent = commentText.getText().toString().trim();
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
                    adapterCommentMain.addTheCommentData(map);
                    mCommentsChoicenessMain.deferNotifyDataSetChanged();
                    adapterCommentMain.notifyDataSetChanged();
                    Toast.makeText(CommentDetailsActivity.this,"评论成功",Toast.LENGTH_SHORT).show();
                    Thread threads = new Thread(new Runnable() {
                        @Override
                        public void run() {
                            try {
                                OkHttpUtils
                                        .get()
                                        .url("http://"+getApplicationContext().getString(R.string.netip)+":8080/AndroidServers/AddCommentServlet")
                                        .addParams("Comments_id", "2")
                                        .addParams("Comments_name", "张三")
                                        .addParams("Comments_like", "0")
                                        .addParams("Comments_portrait", "http://uploads.sundxs.com/allimg/1705/1R3054M5-9.jpg")
                                        .addParams("Comments_text", commentContent)
                                        .addParams("Comments_time", simpleDateFormat.format(date))
                                        .addParams("Comments_number", "0")
                                        .build().execute();
                                Log.e("测试：", commentContent);
                            } catch (IOException e) {
                                e.printStackTrace();
                            }

                        }
                    });
                    threads.start();
                    try {
                        threads.join();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                }else {
                    Toast.makeText(CommentDetailsActivity.this,"评论内容不能为空",Toast.LENGTH_SHORT).show();
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
