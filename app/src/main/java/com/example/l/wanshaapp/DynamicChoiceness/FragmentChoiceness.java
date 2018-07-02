package com.example.l.wanshaapp.DynamicChoiceness;

import android.content.Intent;
import android.os.Bundle;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.l.wanshaapp.DynamicCommentDetails.CommentDetailsActivity;
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

import static java.lang.Thread.sleep;

/**
 * Created by cap on 2018/5/7.
 */

public class FragmentChoiceness extends Fragment {
    private OkHttpClient client = new OkHttpClient();
    public static final MediaType JSON
            = MediaType.parse("application/json; charset=utf-8");
    private List<Map<String,Object>> dataList;
    private ListView mChoicenessListview;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_fragment_choiceness, null);

        mChoicenessListview = (ListView)view.findViewById(R.id.choiceness_listview);
        initDataList();

        //添加适配器
        AdapterChoiceness adapterChoiceness = new AdapterChoiceness(getActivity(),dataList , R.layout.item_choiceness);
        mChoicenessListview.setAdapter(adapterChoiceness);

        /**
         * 点击listview传递参数（使用map）
         * */

        mChoicenessListview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Map<String, Object> map = (Map<String, Object>) mChoicenessListview.getItemAtPosition(position);
                final SerializableMap myMap=new SerializableMap();
                myMap.setMap(map);//将map数据添加到封装的myMap<span></span>中
                Bundle bundle = new Bundle();
                bundle.putSerializable("map", myMap);
                Intent intent=new Intent(getActivity(),CommentDetailsActivity.class);
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

    private void initDataList() {
        dataList = new ArrayList<Map<String, Object>>();
        Thread thread = new Thread(){
            @Override
            public void run() {
                super.run();
                try {
                    String restult = post("http://"+getString(R.string.netip)+":8080/AndroidServers/ChoicenesServlet","");
                    Gson gson = new Gson();
                    ArrayList<BeanChoiceness> beanChoicenesses = gson.fromJson(restult,new TypeToken<ArrayList<BeanChoiceness>>() {
                    }.getType());

                    for (int i = 0; i < beanChoicenesses.size(); i++) {
                        Map<String, Object> map = new HashMap<String, Object>();
                        map.put("UpId", beanChoicenesses.get(i).getUpId());
                        map.put("UpPortrait", beanChoicenesses.get(i).getUpPortrait() );
                        map.put("ChoicenessUpName",beanChoicenesses.get(i).getUpName());
                        map.put("ChoicenessUpTime", beanChoicenesses.get(i).getUpTime());
                        map.put("ChoicenessUpText", beanChoicenesses.get(i).getUpText());
                        map.put("UpIntroduce", beanChoicenesses.get(i).getUpIntroduce());
                        map.put("VideoImg", beanChoicenesses.get(i).getVideoImg());
                        map.put("ChoicenessViodeoview", beanChoicenesses.get(i).getVideoAddress());
                        map.put("UpLike", beanChoicenesses.get(i).getUpLike());
                        map.put("LikeNumber", beanChoicenesses.get(i).getLikeNumber());
                        map.put("UpStep",beanChoicenesses.get(i).getUpStep());
                        map.put("StepNumber", beanChoicenesses.get(i).getStepNumber());
                        dataList.add(map);
                    }

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        };
        thread.start();
        try {
            thread.join();//使线程 从异步执行 变成同步执行
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

}
