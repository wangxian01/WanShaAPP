//package com.example.l.wanshaapp.RankingFragment;
//
//import android.content.Context;
//import android.os.Bundle;
//import android.support.v4.app.Fragment;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.BaseAdapter;
//import android.widget.ListView;
//
//import com.example.l.wanshaapp.R;
//import com.example.l.wanshaapp.RankingFragmentadapter.MyShoucangAdapter;
//import com.example.l.wanshaapp.RankingFragmentadapter.ThreeFragmentAdapter;
//
///**
// * Created by 侯顺发 on 2018/6/25.
// */
//
//public class MyShoucangFragment  extends Fragment {
//
//    private ListView listView;
//
//    //    private List<Map<String,Object>> dataList = new ArrayList<Map<String, Object>>();;\
//
//    @Override
//    public View onCreateView(LayoutInflater inflater,
//                             ViewGroup container, Bundle savedInstanceState) {
//        View view = inflater.inflate(R.layout.activity_myshoucangfragment, container,false);
//
////        view.setVisibility(View.INVISIBLE);视图隐藏
//
//        listView=(ListView)view.findViewById(R.id.myshoucanglistview);
////        initDataList();//初始化数据
//        MyShoucangAdapter adapter = new MyShoucangAdapter(getContext());
//        listView.setAdapter(adapter);
//        return view;
//
//    }
//}
