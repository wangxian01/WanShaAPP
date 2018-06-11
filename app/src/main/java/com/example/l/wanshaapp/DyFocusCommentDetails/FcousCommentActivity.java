package com.example.l.wanshaapp.DyFocusCommentDetails;

import android.content.Context;
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
import android.view.inputmethod.InputMethodManager;
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
import com.example.l.wanshaapp.DynamicHome.CircleImageView;
import com.example.l.wanshaapp.R;
import com.squareup.picasso.Picasso;

import org.w3c.dom.Comment;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fcous_comment);


        mFcousCommentImg = (ImageView) findViewById(R.id.fcous_comment_img);
        mFocusCommentsUserLogo = (CircleImageView) findViewById(R.id.focus_comments_userLogo);
        mFocusCommentsUserName = (TextView) findViewById(R.id.focus_comments_userName);
        mFocusCommentsTime = (TextView) findViewById(R.id.focus_comments_time);
        mFocusCommentsLike = (ImageView) findViewById(R.id.focus_comments_like);
        mFocusCommentTextMain = (TextView) findViewById(R.id.focus_comment_text_main);
          mFocusCommentsMainlist = (ListView) findViewById(R.id.focus_comments_mainlist);
          mFocusCommentsPl = (TextView) findViewById(R.id.focus_comments_pl);
        mFocusCommentsTextUnfold = (TextView) findViewById(R.id.focus_comments_text_unfold);

        //传递参数
        Bundle bundle = getIntent().getExtras();
        SerializableMap serializableMap = (SerializableMap) bundle.get("map");

//        //设置图片
//        Picasso.with(this)
//                .load(String.valueOf(serializableMap.getMap("map").get("ImgAddress")))
//                .into(mFcousCommentImg);

//        //获取头像
//        Picasso.with(this)
//                .load(String.valueOf(serializableMap.getMap("map").get("Portrait")))
//                .into(mFocusCommentsUserLogo);
//        //获取up主名称
//        mFocusCommentsUserName.setText(String.valueOf(serializableMap.getMap("map").get("FocusName")));
//        //获取发布时间
//        mFocusCommentsTime.setText(String.valueOf(serializableMap.getMap("map").get("FocusTime")));
//        //获取游戏介绍文字
//        mFocusCommentTextMain.setText(String.valueOf(serializableMap.getMap("map").get("FocusText")));
//        /**
//         * 设置收起和展开状态
//         */
//        final BeanChoiceness beanChoiceness = new BeanChoiceness();
//        beanChoiceness.setunfold(false);
//
//        mFocusCommentsTextUnfold.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                boolean flag = beanChoiceness.getunfold();
//                if (flag){
//                    mFocusCommentTextMain.setMaxLines(2);
//                    mFocusCommentsTextUnfold.setText("展开");
//                    mFocusCommentsTextUnfold.setTextColor(Color.parseColor("#464646"));
//                    beanChoiceness.setunfold(false);
//                }else{
//                    mFocusCommentTextMain.setMaxLines(1000);
//                    mFocusCommentsTextUnfold.setText("收起");
//                    mFocusCommentsTextUnfold.setTextColor(Color.parseColor("#1296DB"));
//                    beanChoiceness.setunfold(true);
//                }
//            }
//        });

        mFocusCommentsPl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showCommentDialog();
            }
        });


        initDataList();
        View headView = getLayoutInflater().inflate(R.layout.headview_fcou_comment, null);
        mFocusCommentsMainlist.addHeaderView(headView);
        //添加适配器
        adapterCommentMain = new AdapterCommentMain(this, dataList, R.layout.item_main_comment);
        mFocusCommentsMainlist.setAdapter(adapterCommentMain);

    }

    /**
     * 初始化适配器需要的数据格式
     */
    private void initDataList() {
        dataList = new ArrayList<Map<String, Object>>();
        for (int i = 0; i <9; i++) {
            Map<String, Object> map = new HashMap<String, Object>();

            map.put("UpId", "游客甲"+i);
            map.put("CommentText","评论内容"+i);
            dataList.add(map);
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

        bt_comment.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                String commentContent = commentText.getText().toString().trim();
                if(!TextUtils.isEmpty(commentContent)){
                    dialog.dismiss();
                    dataList = new ArrayList<Map<String, Object>>();
                    Map<String, Object> map = new HashMap<String, Object>();
                    map.put("UpId", "谭林");
                    map.put("CommentText",commentContent);
                    dataList.add(map);
                    adapterCommentMain.addTheCommentData(0,dataList);
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
