package com.example.l.wanshaapp.DynamicHome;

import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;


import com.example.l.wanshaapp.DynamicChoiceness.FragmentChoiceness;
import com.example.l.wanshaapp.DynamicFocus.FragmentFocus;
import com.example.l.wanshaapp.DynamicForum.FragmentForum;
import com.example.l.wanshaapp.R;

import java.util.ArrayList;
import java.util.List;

public class DynamicMainActivity extends AppCompatActivity {

    private ViewPager vp;
    private TabLayout tabLayout;
    private DynamicFragmentAdapter dynamicFragmentAdapter;
    private List<Fragment> fragments = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dynamic_main);
        vp = (ViewPager) findViewById(R.id.vp);
        tabLayout = (TabLayout) findViewById(R.id.tab);

        dynamicFragmentAdapter = new DynamicFragmentAdapter(getSupportFragmentManager());
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

    }
}
