package com.example.l.wanshaapp.DynamicCommentDetails;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.TextView;


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
    private Toolbar toolbar;
    private JCVideoPlayerStandard mCommentViodeoview;
    private List<Map<String,Object>> dataList;
    private ListView mCommentsChoicenessMain;
    private ScrollView mCommentScroll;
    private CircleImageView mDetailPageUserLogo;
    private TextView mDetailPageUserName;
    private TextView mDetailPageTime;
    private ImageView mDetailPageFocus;
    private TextView mCommentTextMain;

//    private RecyclerView mCommentsRecyclerview;

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comment_details);
        mDetailPageUserLogo = (CircleImageView) findViewById(R.id.detail_page_userLogo);
        mDetailPageUserName = (TextView) findViewById(R.id.detail_page_userName);
        mDetailPageTime = (TextView) findViewById(R.id.detail_page_time);
        mDetailPageFocus = (ImageView) findViewById(R.id.detail_page_focus);
        mCommentTextMain = (TextView) findViewById(R.id.comment_text_main);
       // mCommentScroll = (ScrollView) findViewById(R.id.comment_scroll);
        toolbar = findViewById(R.id.toolbar);
        mCommentsChoicenessMain = (ListView) findViewById(R.id.comments_choiceness_main);
//        mCommentsRecyclerview = (RecyclerView) findViewById(R.id.comments_recyclerview);
        setSupportActionBar(toolbar);
        //设置显示返回按钮
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        CollapsingToolbarLayout collapsingToolbar =
                (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);
        //设置标题为空
        collapsingToolbar.setTitle(" ");

        //传递参数
        Bundle bundle = getIntent().getExtras();
        SerializableMap serializableMap = (SerializableMap) bundle.get("map");

        mCommentViodeoview = (JCVideoPlayerStandard) findViewById(R.id.comment_viodeoview);
        //设置网络视频
        mCommentViodeoview.setUp(String.valueOf(serializableMap.getMap("map").get("ChoicenessViodeoview"))
                , JCVideoPlayerStandard.SCREEN_LAYOUT_NORMAL, "\t\t\t\t\t\t"+String.valueOf(serializableMap.getMap("map").get("UpIntroduce")));

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
        initDataList();
       // setListViewHeightBasedOnChildren();
        //添加适配器
        AdapterCommentMain adapterCommentMain = new AdapterCommentMain(this, dataList, R.layout.item_main_comment);
        mCommentsChoicenessMain.setAdapter(adapterCommentMain);
//
//        RecyclerViewAdapter recyclerViewAdapter = new RecyclerViewAdapter(this,dataList);
//        //mCommentsRecyclerview.setHasFixedSize(false);
//        mCommentsRecyclerview.setAdapter(recyclerViewAdapter);
//        mCommentsRecyclerview.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));
//        //设置分隔线
//        mCommentsRecyclerview.addItemDecoration( new DividerItemDecoration(this,LinearLayoutManager.VERTICAL));
//        //mCommentsRecyclerview.setNestedScrollingEnabled(false);


        //重新调整ScrollView的位置
//        mCommentScroll.post(new Runnable() {
//            @Override
//            public void run() {
//                mCommentScroll.scrollTo(0,0);
//            }
//        });
    }

    public void setListViewHeightBasedOnChildren(ListView listView) {
        ListAdapter listAdapter = listView.getAdapter();
        if (listAdapter == null) {
            return;
        }

        int totalHeight = 0;
        for (int i = 0; i < listAdapter.getCount(); i++) {
            View listItem = listAdapter.getView(i, null, listView);
            listItem.measure(0, 0);  // 获取item高度
            totalHeight += listItem.getMeasuredHeight();
        }

        ViewGroup.LayoutParams params = listView.getLayoutParams();
        // 最后再加上分割线的高度和padding高度，否则显示不完整。
        params.height = totalHeight + (listView.getDividerHeight() * (listAdapter.getCount() - 1))+listView.getPaddingTop()+listView.getPaddingBottom();
        listView.setLayoutParams(params);
    }

    /**
     * 初始化适配器需要的数据格式
     */
    private void initDataList() {
        dataList = new ArrayList<Map<String, Object>>();
        for (int i = 0; i <9; i++) {
            Map<String, Object> map = new HashMap<String, Object>();

            map.put("UpId", "游客甲"+i);

            dataList.add(map);
        }

    }


    //添加返回按钮点击事件
    public boolean onOptionsItemSelected(MenuItem item)
    {
        // TODO Auto-generated method stub

        //android.R.id.home对应应用程序图标的id
        if(item.getItemId() == android.R.id.home)
        {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
