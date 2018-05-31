package com.example.l.wanshaapp.DynamicHome;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * Created by cap on 2018/5/7.
 */

public class DynamicFragmentAdapter extends FragmentPagerAdapter {
    private List<Fragment> fragments;
    private String[] titles = {"精选", "焦点", "论坛"};


    public DynamicFragmentAdapter(FragmentManager fm) {
        super(fm);
    }

    public void setFragments(List<Fragment> fragments) {
        this.fragments = fragments;
    }

    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public int getCount() {
        return 3;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return titles[position];
    }
}
