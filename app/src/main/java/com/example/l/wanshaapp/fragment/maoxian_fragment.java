package com.example.l.wanshaapp.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.l.wanshaapp.DynamicChoiceness.SerializableMap;
import com.example.l.wanshaapp.JCVideoPlayerStandard.player;
import com.example.l.wanshaapp.R;
import com.example.l.wanshaapp.adapter.jiemiAdapter;
import com.example.l.wanshaapp.fenleiresource.kongburesource;
import com.example.l.wanshaapp.fenleiresource.maoxianresource;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class maoxian_fragment extends Fragment {
    private List<Map<String, Object>> dataList;
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.maoxian_listview, null);


        final ListView mlistview_jiemi = (ListView) view.findViewById(R.id.maoxianlistview);
        DataListtest();

        jiemiAdapter jiemiAdapter = new jiemiAdapter(getContext(),dataList,R.layout.jiemi_item);
        mlistview_jiemi.setAdapter(jiemiAdapter);


        mlistview_jiemi.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Map<String, Object> map = (Map<String, Object>) mlistview_jiemi.getItemAtPosition(position);
                final SerializableMap myMap=new SerializableMap();
                myMap.setMap(map);      //将map数据添加到封装的myMap<span></span>中
                Bundle bundle = new Bundle();
                bundle.putSerializable("map", myMap);
                bundle.putString("type","jiemi");
                Intent intent=new Intent(getActivity(),player.class);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
        return view;
    }
    private void DataListtest() {
        dataList = new ArrayList<Map<String, Object>>();

        for (int i = 0; i < 10; i++) {
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("youximing",maoxianresource.maoxian_youximing[i]);
            map.put("image",maoxianresource.maoxian_string[i]);
            map.put("jianjie",maoxianresource.maoxian_jianjie[i]);
            map.put("fenlei1dongzuo", maoxianresource.maoxian_dongzuo[i]);
            map.put("fenlei1dongzuo2",maoxianresource.maoxian_dongzuo2[i]);
            map.put("fenlei1dongzuo3",maoxianresource.maoxian_dongzuo3[i]);
            map.put("youxishang",maoxianresource.maoxian_youxishang[i]);
            map.put("faxinggongsi",maoxianresource.maoxian_faxinggongsi[i]);
            dataList.add(map);
        }
    }
}


