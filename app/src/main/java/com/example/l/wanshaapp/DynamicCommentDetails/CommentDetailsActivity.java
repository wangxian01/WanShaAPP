package com.example.l.wanshaapp.DynamicCommentDetails;

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
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;


import com.example.l.wanshaapp.DyFocusCommentDetails.FcousCommentActivity;
import com.example.l.wanshaapp.DynamicChoiceness.BeanChoiceness;
import com.example.l.wanshaapp.DynamicChoiceness.SerializableMap;
import com.example.l.wanshaapp.DynamicHome.CircleImageView;
import com.example.l.wanshaapp.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import fm.jiecao.jcvideoplayer_lib.JCVideoPlayerStandard;

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
        mDetailPageFocus = (ImageView) headView.findViewById(R.id.detail_page_focus);
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
        mCommentsChoicenessMain.addHeaderView(headView);
       adapterCommentMain = new AdapterCommentMain(this, dataList, R.layout.item_main_comment);
        mCommentsChoicenessMain.setAdapter(adapterCommentMain);

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
                    //dataList = new ArrayList<Map<String, Object>>();
                    Map<String, Object> map = new HashMap<String, Object>();

                    map.put("UpId", "谭林");
                    map.put("CommentText",commentContent);
                    // dataList.add(map);
                    adapterCommentMain.addTheCommentData(map);
                    //mFocusCommentsMainlist.smoothScrollToPosition(0);
                    mCommentsChoicenessMain.deferNotifyDataSetChanged();
                    adapterCommentMain.notifyDataSetChanged();
                    Toast.makeText(CommentDetailsActivity.this,"评论成功",Toast.LENGTH_SHORT).show();

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
