package com.example.l.wanshaapp.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.l.wanshaapp.R;
import com.example.l.wanshaapp.adapter.jiemiAdapter;
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


        ListView mlistview_jiemi = (ListView) view.findViewById(R.id.maoxianlistview);
        DataListtest();

        jiemiAdapter jiemiAdapter = new jiemiAdapter(getContext(),dataList,R.layout.jiemi_item);
        mlistview_jiemi.setAdapter(jiemiAdapter);
        return view;
    }
    private void DataListtest() {
        dataList = new ArrayList<Map<String, Object>>();

        for (int i = 0; i < 10; i++) {
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("youximing",maoxianresource.maoxian_youximing[i]);
            map.put("jiemi_image",maoxianresource.maoxian_string[i]);
            map.put("jianjie",maoxianresource.maoxian_jianjie[i]);
            dataList.add(map);
        }
    }
}


