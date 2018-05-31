package com.example.l.wanshaapp.fragment;


import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.l.wanshaapp.R;
import com.example.l.wanshaapp.adapter.zhudaAdapter;

import java.util.ArrayList;
import java.util.List;


public class SecondFragment extends Fragment {

    private ViewPager vp;
    private TabLayout tabLayout;
    private zhudaAdapter mclassfication;

    public SecondFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.zhuda_tablelayout, container, false);
        vp = (ViewPager) view.findViewById(R.id.viewpagertop);
        tabLayout = (TabLayout) view.findViewById(R.id.tablelayouttop);

        mclassfication = new zhudaAdapter(getChildFragmentManager());
        List<Fragment> fragments = new ArrayList<>();
        fragments.add(new zhuda_fragment());
        fragments.add(new jiemi_fragment());
        fragments.add(new kongbu_fragment());
        fragments.add(new maoxian_fragment());
        fragments.add(new kapai_fragment());
        fragments.add(new jiaose_fragment());
        mclassfication.setFragments(fragments);
        vp.setAdapter(mclassfication);
        //设置tabLayout
        tabLayout.setupWithViewPager(vp);
        //设置文字的颜色
        tabLayout.setTabTextColors(Color.GRAY, Color.BLUE);
        //设置下划线的颜色
        tabLayout.setSelectedTabIndicatorColor(Color.BLUE);

        tabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);

        tabLayout.setupWithViewPager(vp);
//        //设置tabLayout
//        tabLayout.setupWithViewPager(vp);
//        //设置文字的颜色
//        tabLayout.setTabTextColors(Color.GRAY, Color.BLUE);
//        //设置下划线的颜色
//        tabLayout.setSelectedTabIndicatorColor(Color.BLUE);

        return  view;
    }

}
