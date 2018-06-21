package com.example.l.wanshaapp.RankingFragment;

/**
 * Created by 侯顺发 on 2018/5/21.
 */

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;


import com.example.l.wanshaapp.R;
import com.example.l.wanshaapp.RankingFragmentadapter.OneFragmentAdapter;
import com.example.l.wanshaapp.Rankingbean.RankingFragemntBean;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.squareup.okhttp.Request;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static android.content.ContentValues.TAG;

public class OneFragment extends Fragment {

    private ListView listView;
    private List<Map<String,Object>> dataList = new ArrayList<Map<String, Object>>();;
    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container,  Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment1,container, false);

        listView=(ListView)view.findViewById(R.id.hotlistview);
//        initDataList();//初始化数据

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                OkHttpUtils
                        .get()
                        .url("http://172.17.172.84:8080/AndroidServers/RankingDataServlet")
                                /*            .addParams("username",username)
                                             .addParams("password",password)*/
                        .build()
                        .execute(new StringCallback() {
                            @Override//未响应加载网络信息，弹出对话框
                            public void onError(Request request, Exception e) {
                                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                                builder.setTitle("小问题");
                                builder.setMessage("连接网络异常");
                                        /*     builder.setPositiveButton("是" ,  null );*/
                                builder.show();
                            }

                            @Override//响应
                            public void onResponse(String response) {

                                Log.e(TAG, "onResponse: "+response.toString() );

                                ArrayList<RankingFragemntBean> homelist = new ArrayList<RankingFragemntBean>();
                                Gson gson = new Gson();
                                homelist = gson.fromJson(response.toString(), new TypeToken<List<RankingFragemntBean>>() {
                                }.getType());
                                OneFragmentAdapter mBaseAdapter = new OneFragmentAdapter(getContext(), homelist);
                                listView.setAdapter(mBaseAdapter);
                            }
                        });
            }
        });
        thread.start();

        return view;
    }





    /**
     * 初始化适配器需要的数据格式
     */
    private void initDataList() {

        RankingFragemntBean homeBean = new RankingFragemntBean();


//        dataList = new ArrayList<Map<String, Object>>();
//        for (int i = 1; i < 13; i++) {
//            Map<String, Object> map = new HashMap<String, Object>();
//            map.put("num",""+i);
//            map.put("img", Tools.img[i-1]);
//            map.put("title", Tools.title[i-1]);
//            map.put("img1", Tools.img1[i-1]);
//            map.put("img5",Tools.img5[i-1]);
//            map.put("date",Tools. date[i-1]);
//            dataList.add(map);
//        }

    }
}