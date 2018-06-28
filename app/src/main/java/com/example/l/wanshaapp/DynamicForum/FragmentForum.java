package com.example.l.wanshaapp.DynamicForum;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

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

public class FragmentForum extends Fragment {
    private List<Map<String,Object>> attentionList;
    private List<Map<String,Object>> attentionList2;
    private List<Map<String,Object>> attentionList3;
    private ListView mMyAttentionList;
    private ListView mOfficialBbsList;
    private ListView mPopularBbsList;
    private String attentionString="{\n" +
            "  \"rows\": [\n" +
            "    {\n" +
            "      \"Object\": {\n" +
            "        \"ForumId\": \"up001\",\n" +
            "        \"ForumPortrait\": \"https://ss1.bdstatic.com/70cFuXSh_Q1YnxGkpoWK1HF6hhy/it/u=4216381624,1720286147&fm=27&gp=0.jpg\",\n" +
            "        \"ForumName\": \"腾讯游戏\",\n" +
            "        \"ForumTime\": \"2018/6\"\n" +
            "      }\n" +
            "    },\n" +
            "   {\n" +
            "      \"Object\": {\n" +
            "        \"ForumId\": \"up002\",\n" +
            "        \"ForumPortrait\": \"https://ss0.bdstatic.com/70cFvHSh_Q1YnxGkpoWK1HF6hhy/it/u=890406337,1867343117&fm=27&gp=0.jpg\",\n" +
            "        \"ForumName\": \"网易游戏\",\n" +
            "        \"ForumTime\": \"2018/1\"\n" +
            "      }\n" +
            "    }\n" +
            "  ]\n" +
            "}";

    private String String="{\n" +
            "  \"rows\": [\n" +
            "    {\n" +
            "      \"Object\": {\n" +
            "        \"ForumId\": \"up001\",\n" +
            "        \"ForumPortrait\": \"https://ss0.bdstatic.com/70cFuHSh_Q1YnxGkpoWK1HF6hhy/it/u=3841148545,3190016395&fm=11&gp=0.jpg\",\n" +
            "        \"ForumName\": \"暴雪游戏\",\n" +
            "        \"ForumTime\": \"2018/6\"\n" +
            "      }\n" +
            "    },\n" +
            "   {\n" +
            "      \"Object\": {\n" +
            "        \"ForumId\": \"up002\",\n" +
            "        \"ForumPortrait\": \"https://ss3.bdstatic.com/70cFv8Sh_Q1YnxGkpoWK1HF6hhy/it/u=3921877027,1832577444&fm=27&gp=0.jpg\",\n" +
            "        \"ForumName\": \"九游游戏\",\n" +
            "        \"ForumTime\": \"2018/6\"\n" +
            "      }\n" +
            "    }\n" +
            "  ]\n" +
            "}";

    private String String2="{\n" +
            "  \"rows\": [\n" +
            "    {\n" +
            "      \"Object\": {\n" +
            "        \"ForumId\": \"up001\",\n" +
            "        \"ForumPortrait\": \"https://ss1.bdstatic.com/70cFuXSh_Q1YnxGkpoWK1HF6hhy/it/u=4216381624,1720286147&fm=27&gp=0.jpg\",\n" +
            "        \"ForumName\": \"腾讯游戏\",\n" +
            "        \"ForumTime\": \"2018/6\"\n" +
            "      }\n" +
            "    },\n" +
            "   {\n" +
            "      \"Object\": {\n" +
            "        \"ForumId\": \"up002\",\n" +
            "        \"ForumPortrait\": \"https://ss0.bdstatic.com/70cFuHSh_Q1YnxGkpoWK1HF6hhy/it/u=3841148545,3190016395&fm=11&gp=0.jpg\",\n" +
            "        \"ForumName\": \"暴雪游戏\",\n" +
            "        \"ForumTime\": \"2018/3\"\n" +
            "      }\n" +
            "    }\n" +
            "  ]\n" +
            "}";
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_fragment_forum, null);
        mMyAttentionList = (ListView) view.findViewById(R.id.my_attention_list);
        mOfficialBbsList = (ListView)view. findViewById(R.id.official_bbs_list);
        mPopularBbsList = (ListView) view.findViewById(R.id.popular_bbs_list);

        attentionDataList();
        attentionDataList2();
        attentionDataList3();

        //添加适配器
        AdapterAttention adapterAttention = new AdapterAttention(getActivity(), attentionList, R.layout.item_forum);
        mMyAttentionList.setAdapter(adapterAttention);

        AdapterAttention adapterofficial = new AdapterAttention(getActivity(), attentionList2, R.layout.item_forum);
        mOfficialBbsList.setAdapter(adapterofficial);

        AdapterAttention adapterpoplar = new AdapterAttention(getActivity(), attentionList3, R.layout.item_forum);
       mPopularBbsList.setAdapter(adapterpoplar);
        return view;
    }

    private void attentionDataList() {
        Gson gson = new Gson();
        BeanAttention beanAttention = gson.fromJson(attentionString,new TypeToken<BeanAttention>() {
        }.getType());

        attentionList = new ArrayList<Map<String, Object>>();
        for (int i = 0; i < beanAttention.getRows().size(); i++) {
            Map<String, Object> map = new HashMap<String, Object>();
            /**
             * ForumId : up001
             * ForumPortrait : http://p2.so.qhimgs1.com/bdr/_240_/t01f61dbbc072bac262.jpg
             * ForumName : 腾讯游戏
             * ForumTime : 2018/6
             */

            map.put("ForumId", beanAttention.getRows().get(i).getObject().getForumId());
            map.put("ForumPortrait", beanAttention.getRows().get(i).getObject().getForumPortrait());
            map.put("ForumName", beanAttention.getRows().get(i).getObject().getForumName());
            map.put("ForumTime", beanAttention.getRows().get(i).getObject().getForumTime());

            attentionList.add(map);
        }

    }

    private void attentionDataList2() {
        Gson gson = new Gson();
        BeanAttention beanAttention = gson.fromJson(String,new TypeToken<BeanAttention>() {
        }.getType());

        attentionList2 = new ArrayList<Map<String, Object>>();
        for (int i = 0; i < beanAttention.getRows().size(); i++) {
            Map<String, Object> map = new HashMap<String, Object>();
            /**
             * ForumId : up001
             * ForumPortrait : http://p2.so.qhimgs1.com/bdr/_240_/t01f61dbbc072bac262.jpg
             * ForumName : 腾讯游戏
             * ForumTime : 2018/6
             */

            map.put("ForumId", beanAttention.getRows().get(i).getObject().getForumId());
            map.put("ForumPortrait", beanAttention.getRows().get(i).getObject().getForumPortrait());
            map.put("ForumName", beanAttention.getRows().get(i).getObject().getForumName());
            map.put("ForumTime", beanAttention.getRows().get(i).getObject().getForumTime());

            attentionList2.add(map);
        }

    }

    private void attentionDataList3() {
        Gson gson = new Gson();
        BeanAttention beanAttention = gson.fromJson(String2,new TypeToken<BeanAttention>() {
        }.getType());

        attentionList3 = new ArrayList<Map<String, Object>>();
        for (int i = 0; i < beanAttention.getRows().size(); i++) {
            Map<String, Object> map = new HashMap<String, Object>();
            /**
             * ForumId : up001
             * ForumPortrait : http://p2.so.qhimgs1.com/bdr/_240_/t01f61dbbc072bac262.jpg
             * ForumName : 腾讯游戏
             * ForumTime : 2018/6
             */

            map.put("ForumId", beanAttention.getRows().get(i).getObject().getForumId());
            map.put("ForumPortrait", beanAttention.getRows().get(i).getObject().getForumPortrait());
            map.put("ForumName", beanAttention.getRows().get(i).getObject().getForumName());
            map.put("ForumTime", beanAttention.getRows().get(i).getObject().getForumTime());

            attentionList3.add(map);
        }

    }
}
