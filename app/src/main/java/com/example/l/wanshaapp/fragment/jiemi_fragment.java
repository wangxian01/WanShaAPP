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
import android.widget.RatingBar;

import com.example.l.wanshaapp.DynamicChoiceness.SerializableMap;
import com.example.l.wanshaapp.JCVideoPlayerStandard.player;
import com.example.l.wanshaapp.R;
import com.example.l.wanshaapp.adapter.jiemiAdapter;
import com.example.l.wanshaapp.fenleiresource.jiemiresource;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class jiemi_fragment extends Fragment {
    private List<Map<String, Object>> dataList;
    private RatingBar ratingBar;


    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.jiemi_listview, container,false);

        final ListView mlistview_jiemi = (ListView) view.findViewById(R.id.jiemilistview);
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


        //监听


        return view;

    }

    private void DataListtest() {

        dataList = new ArrayList<Map<String, Object>>();

        for (int i = 0; i < 10; i++) {
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("youxishang",jiemiresource.jiemi_youxishang[i]);
            map.put("faxinggongsi",jiemiresource.jiemi_faxinggongsi[i]);
            map.put("youximing", jiemiresource.jiemi_youximing[i]);
            map.put("pingfen",jiemiresource.jiemi_pingfen[i]);
            map.put("image",jiemiresource.jiemi_string[i]);
            map.put("jianjie",jiemiresource.jiemi_jianjie[i]);
            map.put("fenlei1dongzuo",jiemiresource.jiemi_dongzuo[i]);
            map.put("fenlei1dongzuo2",jiemiresource.jiemi_dongzuo2[i]);
            map.put("fenlei1dongzuo3",jiemiresource.jiemi_dongzuo3[i]);
            dataList.add(map);
        }
    }
}