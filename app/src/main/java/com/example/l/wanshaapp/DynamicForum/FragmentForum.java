package com.example.l.wanshaapp.DynamicForum;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
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
    private ListView mMyAttentionList;
    private ListView mOfficialBbsList;
    private ListView mPopularBbsList;
    private String attentionString="{\n" +
            "  \"rows\": [\n" +
            "    {\n" +
            "      \"Object\": {\n" +
            "        \"ForumId\": \"up001\",\n" +
            "        \"ForumPortrait\": \"http://p2.so.qhimgs1.com/bdr/_240_/t01f61dbbc072bac262.jpg\",\n" +
            "        \"ForumName\": \"腾讯游戏\",\n" +
            "        \"ForumTime\": \"2018/6\"\n" +
            "      }\n" +
            "    },\n" +
            "   {\n" +
            "      \"Object\": {\n" +
            "        \"ForumId\": \"up002\",\n" +
            "        \"ForumPortrait\": \"http://upload.gezila.com/data/20160912/25941473646354.jpg\",\n" +
            "        \"ForumName\": \"腾讯游戏\",\n" +
            "        \"ForumTime\": \"2018/6\"\n" +
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

        //添加适配器
        AdapterAttention adapterAttention = new AdapterAttention(getActivity(), attentionList, R.layout.item_forum);
        mMyAttentionList.setAdapter(adapterAttention);

        AdapterAttention adapterofficial = new AdapterAttention(getActivity(), attentionList, R.layout.item_forum);
        mOfficialBbsList.setAdapter(adapterofficial);

        AdapterAttention adapterpoplar = new AdapterAttention(getActivity(), attentionList, R.layout.item_forum);
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
            map.put("ForumTime ", beanAttention.getRows().get(i).getObject().getForumTime());

            attentionList.add(map);
        }

    }
}
