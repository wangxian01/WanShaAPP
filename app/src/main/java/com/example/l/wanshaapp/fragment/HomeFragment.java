package com.example.l.wanshaapp.fragment;


import android.annotation.SuppressLint;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;

import com.example.l.wanshaapp.R;
import com.example.l.wanshaapp.adapter.HomeListAdapter;
import com.example.l.wanshaapp.bean.HomeLIstViewBean;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.oragee.banners.BannerView;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.squareup.okhttp.Request;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;


import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/*
created by 谭林
time  2018.6.27
function：主页里面的布局
*/

public class HomeFragment extends Fragment  {

    private int[] imgs = {R.drawable.banner2, R.drawable.banner1, R.drawable.banner3, R.drawable.banner4};
    private ListView listView;


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        List<View> viewList = new ArrayList<>();
        for (int img : imgs) {
            ImageView image = new ImageView(getContext());
            image.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
            //设置显示格式
            image.setScaleType(ImageView.ScaleType.CENTER_CROP);
            image.setImageResource(img);
            viewList.add(image);
        }
        BannerView bannerView = view.findViewById(R.id.banner);
        bannerView.startLoop(true);

        bannerView.setViewList(viewList);
//        bannerView.setTransformAnim(true);

        //首页游戏推荐的list
        listView =view.findViewById(R.id.homelist);
/*       HomeListAdapter mBaseAdapter = new HomeListAdapter(getActivity());
        listView.setAdapter(mBaseAdapter);*/

/*        @SuppressLint("InflateParams") View contentView = LayoutInflater.from(getActivity()).inflate(R.layout.home_recommend_item, null);
        TextView diyihang = contentView.findViewById(R.id.diyihang);
        TextView dierhang = contentView.findViewById(R.id.dierhang);
        ImageView imageView = contentView.findViewById(R.id.recommendpicture);
        diyihang.setText("让世界惊叹的唯美世界解密手游");
        dierhang.setText("寻求宽恕再度来袭");
        imageView.setImageResource(R.drawable.h);*/

        //刷新布局的使用
        final RefreshLayout refreshLayout =view.findViewById(R.id.refreshLayout);
/*        //设置 Footer 为 经典样式
        refreshLayout.setRefreshFooter(new ClassicsFooter(getContext()));*/

        /*刷新时获取网络和json数据*/
        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshlayout) {
                refreshlayout.finishRefresh(3000);//传入false表示刷新失败
            }
        });

        refreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() {

            @SuppressLint("StaticFieldLeak")
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshlayout) {

                Thread thread = new Thread(new Runnable() {
                    @Override
                    public void run() {
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                            OkHttpUtils
                                    .get()
                                    .url("http://"+ Objects.requireNonNull(getContext()).getString(R.string.netip)+":8080/AndroidServers/servlet/HomeListViewData")
                                    .build()
                                    .execute(new StringCallback() {
                                        @Override
                                        public void onError(Request request, Exception e) {
                                            AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                                            builder.setTitle("小问题");
                                            builder.setMessage("连接网络异常");
                                            /*     builder.setPositiveButton("是" ,  null );*/
                                            builder.show();
                                        }

                                        @Override
                                        public void onResponse(String response) {
                                            ArrayList<HomeLIstViewBean> homelist;
                                            Gson gson = new Gson();
                                            homelist = gson.fromJson(response, new TypeToken<List<HomeLIstViewBean>>() {
                                            }.getType());
                                            HomeListAdapter mBaseAdapter = new HomeListAdapter(getActivity(), homelist);
                                            listView.setAdapter(mBaseAdapter);
                                            refreshLayout.finishLoadMore();
                                        }
                                    });
                        }
                    }
                });
                thread.start();

                refreshlayout.finishRefresh(3000);//传入false表示刷新失败
            }
        });

        return view;
    }

}
