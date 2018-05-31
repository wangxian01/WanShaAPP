package com.example.l.wanshaapp.fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;

import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import com.example.l.wanshaapp.DynamicChoiceness.FragmentChoiceness;
import com.example.l.wanshaapp.DynamicFocus.FragmentFocus;
import com.example.l.wanshaapp.DynamicForum.FragmentForum;
import com.example.l.wanshaapp.DynamicHome.DynamicFragmentAdapter;
import com.example.l.wanshaapp.R;

import java.util.ArrayList;
import java.util.List;


public class dongtaifragment extends Fragment {
    private ViewPager vp;
    private TabLayout tabLayout;
    private DynamicFragmentAdapter dynamicFragmentAdapter;
    private List<Fragment> fragments = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_dynamic_main, null);
        vp = (ViewPager)view.findViewById(R.id.vp);
        tabLayout = (TabLayout)view.findViewById(R.id.tab);

        /**
         * 根本莫法
         * */
        dynamicFragmentAdapter = new DynamicFragmentAdapter(getChildFragmentManager());
        fragments.add(new FragmentChoiceness());
        fragments.add(new FragmentFocus());
        fragments.add(new FragmentForum());
        dynamicFragmentAdapter.setFragments(fragments);
        vp.setAdapter(dynamicFragmentAdapter);
        //设置tabLayout
        tabLayout.setupWithViewPager(vp);
        //设置文字的颜色
        tabLayout.setTabTextColors(Color.GRAY, Color.BLUE);
        //设置下划线的颜色
        tabLayout.setSelectedTabIndicatorColor(Color.BLUE);

        return view;

    }
}
