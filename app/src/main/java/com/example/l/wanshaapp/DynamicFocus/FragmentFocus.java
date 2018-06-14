package com.example.l.wanshaapp.DynamicFocus;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;


import com.example.l.wanshaapp.DyFocusCommentDetails.FcousCommentActivity;
import com.example.l.wanshaapp.DyFocusCommentDetails.FcousCommentAdapter;
import com.example.l.wanshaapp.DynamicChoiceness.SerializableMap;
import com.example.l.wanshaapp.DynamicCommentDetails.CommentDetailsActivity;
import com.example.l.wanshaapp.R;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by cap on 2018/5/7.
 */

public class FragmentFocus extends Fragment {
    String jsonfocus="\n" +
            "{\n" +
            "  \"rows\": [\n" +
            "    {\n" +
            "      \"FocusObject\": {\n" +
            "        \"UpId\": \"upxgz002\",\n" +
            "        \"Portrait\": \"http://p3.so.qhmsg.com/t01bb2a6057a5fb3d48.jpg\",\n" +
            "        \"FocusName\": \"游戏小公举\",\n" +
            "        \"FocusTime\": \"2018/6\",\n" +
            "        \"FocusFrom\": \"来自腾讯光子工作室\",\n" +
            "        \"FocusText\": \"《王者荣耀》是腾讯第一5V5团队公平竞技手游，于10月28日开启不限号测试！5V5王者峡谷（含迷雾模式）、5V5深渊大乱斗、以及3V3、1V1等多样模式一键体验，热血竞技尽享快感！海量英雄随心选择，精妙配合默契作战！10秒实时跨区匹配，与好友组队登顶最强王者！操作简单易上手，一血、五杀、超神，极致还原经典体验！实力操作公平对战，回归MOBA初心！\",\n" +
            "        \"Inintroduce\": \"王者荣耀爆料：赵云又出新皮肤?\",\n" +
            "        \"Introduction\": \"昨天晚上，几位王者荣耀KPL选手参加了《火星情报局》的综艺活动。本以为这只是再寻常不过的一次活动，谁知活动中竟爆料出赵云新皮肤——引擎之心。\",\n" +
            "        \"ImgAddress\":\"https://ss1.bdstatic.com/70cFvXSh_Q1YnxGkpoWK1HF6hhy/it/u=242665706,4117675929&fm=27&gp=0.jpg\",\n" +
            "        \"UpLike\": true,\n" +
            "        \"LikeNumber\": 69 \n" +
            "      }\n" +
            "    }, {\n" +
            "      \"FocusObject\": {\n" +
            "        \"UpId\": \"upxgz002\",\n" +
            "        \"Portrait\": \"http://p3.so.qhmsg.com/t01bb2a6057a5fb3d48.jpg\",\n" +
            "        \"FocusName\": \"游戏小公举\",\n" +
            "        \"FocusTime\": \"2018/6\",\n" +
            "        \"FocusFrom\": \"来自腾讯光子工作室\",\n" +
            "        \"FocusText\": \"《王者荣耀》是腾讯第一5V5团队公平竞技手游，于10月28日开启不限号测试！5V5王者峡谷（含迷雾模式）、5V5深渊大乱斗、以及3V3、1V1等多样模式一键体验，热血竞技尽享快感！海量英雄随心选择，精妙配合默契作战！10秒实时跨区匹配，与好友组队登顶最强王者！操作简单易上手，一血、五杀、超神，极致还原经典体验！实力操作公平对战，回归MOBA初心！\",\n" +
            "        \"Inintroduce\": \"王者荣耀爆料：赵云又出新皮肤?\",\n" +
            "        \"Introduction\": \"昨天晚上，几位王者荣耀KPL选手参加了《火星情报局》的综艺活动。本以为这只是再寻常不过的一次活动，谁知活动中竟爆料出赵云新皮肤——引擎之心。\",\n" +
            "        \"ImgAddress\":\"https://ss1.bdstatic.com/70cFvXSh_Q1YnxGkpoWK1HF6hhy/it/u=242665706,4117675929&fm=27&gp=0.jpg\",\n" +
            "        \"UpLike\": true,\n" +
            "        \"LikeNumber\": 69\n" +
            "      }\n" +
            "    },\n" +
            "    {\n" +
            "      \"FocusObject\": {\n" +
            "        \"UpId\": \"upxgz002\",\n" +
            "        \"Portrait\": \"http://p3.so.qhmsg.com/t01bb2a6057a5fb3d48.jpg\",\n" +
            "        \"FocusName\": \"游戏小公举\",\n" +
            "        \"FocusTime\": \"2018/6\",\n" +
            "        \"FocusFrom\": \"来自腾讯光子工作室\",\n" +
            "        \"FocusText\": \"《王者荣耀》是腾讯第一5V5团队公平竞技手游，于10月28日开启不限号测试！5V5王者峡谷（含迷雾模式）、5V5深渊大乱斗、以及3V3、1V1等多样模式一键体验，热血竞技尽享快感！海量英雄随心选择，精妙配合默契作战！10秒实时跨区匹配，与好友组队登顶最强王者！操作简单易上手，一血、五杀、超神，极致还原经典体验！实力操作公平对战，回归MOBA初心！\",\n" +
            "        \"Inintroduce\": \"王者荣耀爆料：赵云又出新皮肤?\",\n" +
            "        \"Introduction\": \"昨天晚上，几位王者荣耀KPL选手参加了《火星情报局》的综艺活动。本以为这只是再寻常不过的一次活动，谁知活动中竟爆料出赵云新皮肤——引擎之心。\",\n" +
            "        \"ImgAddress\":\"https://ss1.bdstatic.com/70cFvXSh_Q1YnxGkpoWK1HF6hhy/it/u=242665706,4117675929&fm=27&gp=0.jpg\",\n" +
            "        \"UpLike\": true,\n" +
            "        \"LikeNumber\": 69\n" +
            "      }\n" +
            "    }\n" +
            "  ]\n" +
            "}";
//    @Override
//    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
//        View view = inflater.inflate(R.layout.activity_fragment_focus, null);
//        return view;
//    }
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
                myMap.setMap(map);      //将map数据添加到封装的myMap<span></span>中
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
     * 初始化适配器需要的数据格式
     */
    private void FocusDataList() {
        Gson gson = new Gson();
        BeanFocus beanFocus = gson.fromJson(jsonfocus,new TypeToken<BeanFocus>() {
        }.getType());

        dataList = new ArrayList<Map<String, Object>>();
        for (int i = 0; i <beanFocus.getRows().size(); i++) {
            Map<String, Object> map = new HashMap<String, Object>();
            /**
             * UpId : upxgz002
             * Portrait : http://p3.so.qhmsg.com/t01bb2a6057a5fb3d48.jpg
             * FocusName : 游戏小公举
             * FocusTime : 2018/6
             * FocusFrom : 来自腾讯光子工作室
             * FocusText : 《王者荣耀》是腾讯第一5V5团队公平竞技手游，于10月28日开启不限号测试！5V5王者峡谷（含迷雾模式）、5V5深渊大乱斗、以及3V3、1V1等多样模式一键体验，热血竞技尽享快感！海量英雄随心选择，精妙配合默契作战！10秒实时跨区匹配，与好友组队登顶最强王者！操作简单易上手，一血、五杀、超神，极致还原经典体验！实力操作公平对战，回归MOBA初心！
             * Inintroduce  : 剑仙李白潇洒游走，全场收割十步杀一人！
             * Introduction  : 剑仙李白潇洒游走，全场收割十步杀一人！
             * ImgAddress : http://upload.gezila.com/data/20160912/25941473646354.jpg
             * UpLike : true
             * LikeNumber : 69
             */
            map.put("Portrait", beanFocus.getRows().get(i).getFocusObject().getPortrait() );
            map.put("FocusName", beanFocus.getRows().get(i).getFocusObject().getFocusName());
            map.put("FocusTime", beanFocus.getRows().get(i).getFocusObject().getFocusTime());
            map.put("FocusFrom", beanFocus.getRows().get(i).getFocusObject().getFocusFrom());
            map.put("FocusText", beanFocus.getRows().get(i).getFocusObject().getFocusText());
            map.put("Introduce", beanFocus.getRows().get(i).getFocusObject().getInintroduce() );
            map.put("Introduction", beanFocus.getRows().get(i).getFocusObject().getIntroduction());
            map.put("ImgAddress", beanFocus.getRows().get(i).getFocusObject().getImgAddress());
            map.put("UpLike", beanFocus.getRows().get(i).getFocusObject().isUpLike() );
            map.put("LikeNumber", beanFocus.getRows().get(i).getFocusObject().getLikeNumber() );
            dataList.add(map);
        }

    }
}
