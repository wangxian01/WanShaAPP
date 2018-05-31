package com.example.l.wanshaapp.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;
/*wangxin*/

public class zhudaAdapter extends FragmentPagerAdapter {


    private List<Fragment> fragments;
    private String[] titles = {"主打推荐", "解密游戏", "恐怖游戏","冒险游戏","卡牌游戏","角色扮演"};


    public zhudaAdapter(FragmentManager fm) {
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
        return 6;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return titles[position];
    }


}