package com.example.l.wanshaapp.DyFocusCommentDetails;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Message;
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
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.l.wanshaapp.DynamicChoiceness.BeanChoiceness;
import com.example.l.wanshaapp.DynamicChoiceness.SerializableMap;
import com.example.l.wanshaapp.DynamicCommentDetails.AdapterCommentMain;
import com.example.l.wanshaapp.DynamicCommentDetails.CommentBean;
import com.example.l.wanshaapp.DynamicCommentDetails.CommentDetailsActivity;
import com.example.l.wanshaapp.DynamicHome.CircleImageView;
import com.example.l.wanshaapp.R;
import com.example.l.wanshaapp.Rankingbean.RankingFragemntBean;
import com.example.l.wanshaapp.WanShaLogin.RegisteredActivity;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.squareup.picasso.Picasso;

import org.w3c.dom.Comment;

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

public class FcousCommentActivity extends AppCompatActivity {

    private ImageView mFcousCommentImg;
    private CircleImageView mFocusCommentsUserLogo;
    private TextView mFocusCommentsUserName;
    private TextView mFocusCommentsTime;
    private ImageView mFocusCommentsLike;
    private TextView mFocusCommentTextMain;
    private ListView mFocusCommentsMainlist;
    private TextView mFocusCommentsPl;
    private TextView mFocusCommentsTextUnfold;
    private List<Map<String,Object>> dataList;
    private BottomSheetDialog dialog;
    private AdapterCommentMain adapterCommentMain;
    private OkHttpClient client = new OkHttpClient();
    public static final MediaType JSON
            = MediaType.parse("application/json; charset=utf-8");
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fcous_comment);

        initDataList();
        View headView = getLayoutInflater().inflate(R.layout.headview_fcou_comment, null);

        mFcousCommentImg = (ImageView)headView.findViewById(R.id.fcous_comment_img);
        mFocusCommentsUserLogo = (CircleImageView)headView. findViewById(R.id.focus_comments_userLogo);
        mFocusCommentsUserName = (TextView) headView.findViewById(R.id.focus_comments_userName);
        mFocusCommentsTime = (TextView) headView.findViewById(R.id.focus_comments_time);
        mFocusCommentTextMain = (TextView) headView.findViewById(R.id.focus_comment_text_main);
        mFocusCommentsMainlist = (ListView) findViewById(R.id.focus_comments_mainlist);
        mFocusCommentsPl = (TextView)findViewById(R.id.focus_comments_pl);
        mFocusCommentsTextUnfold = (TextView)headView. findViewById(R.id.focus_comments_text_unfold);

        //传递参数
        Bundle bundle = getIntent().getExtras();
        SerializableMap serializableMap = (SerializableMap) bundle.get("map");
        //设置图片
        Picasso.with(this)
                .load(String.valueOf(serializableMap.getMap("map").get("ImgAddress")))
                .into(mFcousCommentImg);

        //获取头像
        Picasso.with(this)
                .load(String.valueOf(serializableMap.getMap("map").get("Portrait")))
                .into(mFocusCommentsUserLogo);
        //获取up主名称
        mFocusCommentsUserName.setText(String.valueOf(serializableMap.getMap("map").get("FocusName")));
        //获取发布时间
        mFocusCommentsTime.setText(String.valueOf(serializableMap.getMap("map").get("FocusTime")));
        //获取游戏介绍文字
        mFocusCommentTextMain.setText(String.valueOf(serializableMap.getMap("map").get("FocusText")));
        /**
         * 设置收起和展开状态
         */
        final BeanChoiceness beanChoiceness = new BeanChoiceness();
        beanChoiceness.setunfold(false);

        mFocusCommentsTextUnfold.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean flag = beanChoiceness.getunfold();
                if (flag){
                    mFocusCommentTextMain.setMaxLines(2);
                    mFocusCommentsTextUnfold.setText("展开");
                    mFocusCommentsTextUnfold.setTextColor(Color.parseColor("#464646"));
                    beanChoiceness.setunfold(false);
                }else{
                    mFocusCommentTextMain.setMaxLines(1000);
                    mFocusCommentsTextUnfold.setText("收起");
                    mFocusCommentsTextUnfold.setTextColor(Color.parseColor("#1296DB"));
                    beanChoiceness.setunfold(true);
                }
            }
        });
        /*
        * 点击评论
        * */

        mFocusCommentsPl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showCommentDialog();
            }
        });
        mFocusCommentsMainlist.addHeaderView(headView,null,false);
        //添加适配器
        adapterCommentMain = new AdapterCommentMain(this, dataList, R.layout.item_main_comment);

        mFocusCommentsMainlist.setAdapter(adapterCommentMain);

        /**
         * 点击listview传递参数（使用map）
         * */
        mFocusCommentsMainlist.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Map<String, Object> map = (Map<String, Object>) mFocusCommentsMainlist.getItemAtPosition(position);
                final SerializableMap myMap=new SerializableMap();
                myMap.setMap(map);//将map数据添加到封装的myMap<span></span>中
                Bundle bundle = new Bundle();
                bundle.putSerializable("map", myMap);
                Intent intent=new Intent(FcousCommentActivity.this,ReplayActivity.class);
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
                    mFocusCommentsMainlist.deferNotifyDataSetChanged();
                    adapterCommentMain.notifyDataSetChanged();
                    Toast.makeText(FcousCommentActivity.this,"评论成功",Toast.LENGTH_SHORT).show();

                }else {
                    Toast.makeText(FcousCommentActivity.this,"评论内容不能为空",Toast.LENGTH_SHORT).show();
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
