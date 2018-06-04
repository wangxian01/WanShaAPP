package com.example.l.wanshaapp.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;

import com.example.l.wanshaapp.R;
import com.example.l.wanshaapp.adapter.jiemiAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class jiemi_fragment extends Fragment {
    private List<Map<String, Object>> dataList;
    ImageView imageView;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.jiemi_listview, null);

        ListView mlistview_jiemi = (ListView) view.findViewById(R.id.jiemilistview);
        DataListtest();

        jiemiAdapter jiemiAdapter = new jiemiAdapter(getContext(),dataList,R.layout.jiemi_item);
        mlistview_jiemi.setAdapter(jiemiAdapter);

        return view;

    }

    private void DataListtest() {
        dataList = new ArrayList<Map<String, Object>>();
        String string[] = {"http://p2.so.qhimgs1.com/bdr/_240_/t01f61dbbc072bac262.jpg","https://ss1.bdstatic.com/70cFuXSh_Q1YnxGkpoWK1HF6hhy/it/u=4050523606,2170310969&fm=27&gp=0.jpg",
                "https://ss2.bdstatic.com/70cFvnSh_Q1YnxGkpoWK1HF6hhy/it/u=862591842,2864954084&fm=27&gp=0.jpg","https://ss1.bdstatic.com/70cFuXSh_Q1YnxGkpoWK1HF6hhy/it/u=2248907317,2456606936&fm=27&gp=0.jpg","http://p2.so.qhimgs1.com/bdr/_240_/t01f61dbbc072bac262.jpg","https://ss1.bdstatic.com/70cFuXSh_Q1YnxGkpoWK1HF6hhy/it/u=4050523606,2170310969&fm=27&gp=0.jpg",
                "https://ss2.bdstatic.com/70cFvnSh_Q1YnxGkpoWK1HF6hhy/it/u=862591842,2864954084&fm=27&gp=0.jpg","https://ss1.bdstatic.com/70cFuXSh_Q1YnxGkpoWK1HF6hhy/it/u=2248907317,2456606936&fm=27&gp=0.jpg","http://p2.so.qhimgs1.com/bdr/_240_/t01f61dbbc072bac262.jpg","https://ss1.bdstatic.com/70cFuXSh_Q1YnxGkpoWK1HF6hhy/it/u=4050523606,2170310969&fm=27&gp=0.jpg",
                "https://ss2.bdstatic.com/70cFvnSh_Q1YnxGkpoWK1HF6hhy/it/u=862591842,2864954084&fm=27&gp=0.jpg","https://ss1.bdstatic.com/70cFuXSh_Q1YnxGkpoWK1HF6hhy/it/u=2248907317,2456606936&fm=27&gp=0.jpg"};

       String youximing[]={"创世战记","爆炸火花","香蕉足球","爱的狂想曲","小屁孩儿","来生再会","萤火之森","你知道吗","蓝色光头","人小鬼大"};


        for (int i = 0; i < 10; i++) {
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("youximing", youximing[i]);
            map.put("ChoicenessViodeoview",string[i]);
            map.put("riqi", "创世战纪"+i);
            map.put("wuyu", "创世战纪");
            map.put("pingfen", "创世战纪");
            dataList.add(map);
        }
    }
}