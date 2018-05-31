package com.example.l.wanshaapp.RankingFragment;

/**
 * Created by 侯顺发 on 2018/5/21.
 */

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;


import com.example.l.wanshaapp.R;
import com.example.l.wanshaapp.RankingFragmentadapter.OneFragmentAdapter;
import com.example.l.wanshaapp.Rankingbean.HomeBean;
import com.example.l.wanshaapp.Rankingtools.Tools;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class OneFragment extends Fragment {

    private ListView listView;
    private List<Map<String,Object>> dataList = new ArrayList<Map<String, Object>>();;
    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container,  Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment1,container, false);

        listView=(ListView)view.findViewById(R.id.hotlistview);
//        initDataList();//初始化数据

        OneFragmentAdapter adapter = new OneFragmentAdapter(getContext());
        listView.setAdapter(adapter);
        return view;
    }

    /**
     * 初始化适配器需要的数据格式
     */
    private void initDataList() {

        HomeBean homeBean = new HomeBean();
        homeBean.image = Tools.img;
        homeBean.title = Tools.title;


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