package com.example.l.wanshaapp.fragment;


import android.annotation.SuppressLint;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RadioGroup;

import com.example.l.wanshaapp.R;
import com.example.l.wanshaapp.adapter.HomeListAdapter;
import com.oragee.banners.BannerView;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.footer.ClassicsFooter;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.squareup.okhttp.Request;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static android.content.ContentValues.TAG;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment {

    private int[] imgs = {R.drawable.a, R.drawable.b, R.drawable.c, R.drawable.d};
    private List<View> viewList;
    private BannerView bannerView;
    private ListView listView;
    private ArrayList<Fragment> fragmentsList = new ArrayList<Fragment>();
    private RadioGroup group;
    public HomeFragment() {
        // Required empty public constructor
    }
    HomeListAdapter mBaseAdapter;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
     View view= inflater.inflate(R.layout.fragment_home, container, false);
        viewList = new ArrayList<View>();
        for (int i = 0; i < imgs.length; i++) {
            ImageView image = new ImageView(getContext());
            image.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
            //设置显示格式
            image.setScaleType(ImageView.ScaleType.CENTER_CROP);
            image.setImageResource(imgs[i]);
            viewList.add(image);
        }
        bannerView = (BannerView) view.findViewById(R.id.banner);
        bannerView.startLoop(true);

        bannerView.setViewList(viewList);
//        bannerView.setTransformAnim(true);

        //首页游戏推荐的list
        listView = (ListView) view.findViewById(R.id.homelist);
/*        HomeListAdapter mBaseAdapter = new HomeListAdapter(getActivity());
        listView.setAdapter(mBaseAdapter);*/

        //刷新布局的使用
        RefreshLayout refreshLayout = (RefreshLayout)view.findViewById(R.id.refreshLayout);
/*        //设置 Footer 为 经典样式
        refreshLayout.setRefreshFooter(new ClassicsFooter(getContext()));*/
        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                refreshlayout.finishRefresh(3000);//传入false表示刷新失败
                Log.e("提示", "刷新成功");
            }
        });
        refreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
            @SuppressLint("StaticFieldLeak")
            @Override
            public void onLoadMore(RefreshLayout refreshlayout) {

                //异步加载
                new AsyncTask<String,Void,Void>(){
                    @Override
                    protected Void doInBackground(String... strings) {
                        OkHttpUtils.get().url("http://192.168.1.187:8080/AndroidServers/servlet/HomeListViewData").build().execute(new StringCallback() {
                            @Override
                            public void onError(Request request, Exception e) {
                                Log.e(TAG, "网络错误");
                            }
                            @Override
                            public void onResponse(String response) {
                                Log.e("提示", "刷新成功");
                                Log.e(TAG, response.toString());
                            }
                        });
                        return null;
                    }
                };

                //模拟网络请求到的数据
      /*          ArrayList<String> newList = new ArrayList<String>();
                for (int i=0;i<6;i++){
           newList.add("寻求宽恕再度来袭");
           newList.add("让世界惊叹的唯美解密手游");
           newList.add("R.drawable.h");
                }*/
              /*  HomeListAdapter mBaseAdapter = new HomeListAdapter(getActivity(), newList);
                listView.setAdapter(mBaseAdapter);*/
                refreshlayout.finishRefresh(3000);//传入false表示刷新失败

            }
        });

        return  view;
    }
}
