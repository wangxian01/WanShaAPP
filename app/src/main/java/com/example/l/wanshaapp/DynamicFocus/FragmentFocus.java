package com.example.l.wanshaapp.DynamicFocus;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomSheetDialog;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;


import com.example.l.wanshaapp.DyFocusCommentDetails.FcousCommentActivity;
import com.example.l.wanshaapp.DynamicChoiceness.SerializableMap;
import com.example.l.wanshaapp.R;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * Created by cap on 2018/5/7.
 */

public class FragmentFocus extends Fragment {
    private OkHttpClient client = new OkHttpClient();
    public static final MediaType JSON
            = MediaType.parse("application/json; charset=utf-8");

    private List<Map<String,Object>> dataList;
    private ListView mChoicenessListview;
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_fragment_focus, null);
        mChoicenessListview = (ListView)view.findViewById(R.id.focus_list);

        FocusDataList();
        //添加适配器
        AdapterFocus adapterFocus = new AdapterFocus(getActivity(), dataList, R.layout.item_focus);
        mChoicenessListview.setAdapter(adapterFocus);

        /**
         * 点击listview传递参数（使用map）
         * */
        mChoicenessListview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Map<String, Object> map = (Map<String, Object>) mChoicenessListview.getItemAtPosition(position);
                final SerializableMap myMap=new SerializableMap();
                myMap.setMap(map);      //将map数据添加到封装的myMap中
                Bundle bundle = new Bundle();
                bundle.putSerializable("map", myMap);
                Intent intent=new Intent(getActivity(),FcousCommentActivity.class);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
        return view;
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
    private void FocusDataList() {
        dataList = new ArrayList<Map<String, Object>>();
        Thread thread = new Thread(){
            @Override
            public void run() {
                super.run();
                try {
                    String restult = post("http://172.16.59.11:8080/AndroidServers/FocuServlet","");
                    Gson gson = new Gson();
                    ArrayList<BeanFocus> beanFocus = gson.fromJson(restult,new TypeToken< ArrayList<BeanFocus>>() {
                    }.getType());

                    for (int i = 0; i < beanFocus.size(); i++) {
                        Map<String, Object> map = new HashMap<String, Object>();
                        map.put("UpId", beanFocus.get(i).getUpId() );
                        map.put("Portrait", beanFocus.get(i).getPortrait() );
                        map.put("FocusName", beanFocus.get(i).getFocusName());
                        map.put("FocusTime", beanFocus.get(i).getFocusTime());
                        map.put("FocusFrom", beanFocus.get(i).getFocusFrom());
                        map.put("FocusText", beanFocus.get(i).getFocusText());
                        map.put("Introduce", beanFocus.get(i).getInintroduce() );
                        map.put("Introduction", beanFocus.get(i).getIntroduction());
                        map.put("ImgAddress", beanFocus.get(i).getImgAddress());
                        map.put("UpLike", beanFocus.get(i).getUpLike() );
                        map.put("LikeNumber", beanFocus.get(i).getLikeNumber() );
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

}
