package com.example.l.wanshaapp.RankingFragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.l.wanshaapp.R;
import com.example.l.wanshaapp.RankingFragmentadapter.FourFragmentAdpter;


/**
 * Created by 侯顺发 on 2018/5/21.
 */

public class FourFragment extends Fragment {

    private ListView listView;
    //    private List<Map<String,Object>> dataList = new ArrayList<Map<String, Object>>();;
    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container,  Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment4, container,false);

        listView=(ListView)view.findViewById(R.id.haopinglistview);
//        initDataList();//初始化数据

        FourFragmentAdpter adapter = new FourFragmentAdpter(getContext());
        listView.setAdapter(adapter);
        return view;

    }


}