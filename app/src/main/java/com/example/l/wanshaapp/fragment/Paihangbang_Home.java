package com.example.l.wanshaapp.fragment;


import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import com.example.l.wanshaapp.R;
import com.example.l.wanshaapp.RankingAdapter.RankingAdapter;
import com.example.l.wanshaapp.RankingAdapter.RankingTabFragmentPagerAdapter;
import com.example.l.wanshaapp.RankingFragment.FourFragment;
import com.example.l.wanshaapp.RankingFragment.OneFragment;
import com.example.l.wanshaapp.RankingFragment.ThreeFragment;
import com.example.l.wanshaapp.RankingFragment.TwoFragment;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


public class Paihangbang_Home extends Fragment implements View.OnClickListener {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private TextView tv_item_one;
    private TextView tv_item_two;
    private TextView tv_item_three;
    private TextView tv_item_four;
    //    private TextView tv_item_five;
    private ViewPager myViewPager;
    private List<Fragment> list;
    private RankingTabFragmentPagerAdapter adapter;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.paihangbang, container, false);
        /**
         * 初始化控件
         */

            tv_item_one = (TextView) view.findViewById(R.id.tv_item_one);
            tv_item_two = (TextView) view.findViewById(R.id.tv_item_two);
            tv_item_three = (TextView)view. findViewById(R.id.tv_item_three);
            tv_item_four=(TextView)view.findViewById(R.id.tv_item_four);
//        tv_item_five=(TextView)findViewById(R.id.tv_item_five);
            myViewPager = (ViewPager) view.findViewById(R.id.myViewPager);


// 设置菜单栏的点击事件
        tv_item_one.setOnClickListener(this);
        tv_item_two.setOnClickListener(this);
        tv_item_three.setOnClickListener(this);
        tv_item_four.setOnClickListener(this);
//        tv_item_five.setOnClickListener(this);
        myViewPager.setOnPageChangeListener(new MyPagerChangeListener());

//把Fragment添加到List集合里面
        list = new ArrayList<>();
        list.add(new OneFragment());
        list.add(new TwoFragment());
        list.add(new ThreeFragment());
        list.add(new FourFragment());
//        list.add(new FiveFragment());
        adapter = new RankingTabFragmentPagerAdapter(getChildFragmentManager(), list);
        myViewPager.setAdapter(adapter);
        myViewPager.setCurrentItem(0);  //初始化显示第一个页面
        tv_item_one.setBackgroundColor(Color.rgb(0,229,238));//被选中就为红色
        return  view;

    }


    private void initDataList() {
//        //图片资源
//        int img[] = { R.drawable.logo, R.drawable.logo, R.drawable.logo,
//                R.drawable.logo, R.drawable.logo, R.drawable.logo, R.drawable.logo,
//                R.drawable.logo, R.drawable.logo, R.drawable.logo, R.drawable.logo , R.drawable.logo , R.drawable.logo  };
//        int img1[]={R.drawable.star, R.drawable.star, R.drawable.star,
//                R.drawable.star, R.drawable.star, R.drawable.star, R.drawable.star,
//                R.drawable.star, R.drawable.star, R.drawable.star, R.drawable.star , R.drawable.star , R.drawable.star };
//        //文字资源（游戏名）
//        String title[]={"黎明杀机","第五人格","第五人格","第五人格","第五人格","第五人格","第五人格","第五人格","第五人格",
//                "第五人格","第五人格","第五人格","第五人格"};
//        String date[]={"竞技  暴力","竞技","竞技","竞技","竞技","竞技","竞技","竞技","竞技","竞技","竞技","竞技","竞技"};
//        dataList = new ArrayList<Map<String, Object>>();
//        for (int i = 1; i < 13; i++) {
//            Map<String, Object> map = new HashMap<String, Object>();
//            map.put("num",""+i);
//            map.put("img", img[i-1]);
//            map.put("title", title[i-1]);
//            map.put("img1", img1[i-1]);
//            map.put("date", date[i-1]);
//            dataList.add(map);
//        }

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.tv_item_one:
                myViewPager.setCurrentItem(0);
                tv_item_one.setBackgroundColor(Color.rgb(0,229,238));
                tv_item_two.setBackgroundColor(Color.rgb(220,220,220));
                tv_item_three.setBackgroundColor(Color.rgb(220,220,220));
                tv_item_four.setBackgroundColor(Color.rgb(220,220,220));
//                tv_item_five.setBackgroundColor(Color.rgb(220,220,220));
                break;
            case R.id.tv_item_two:
                myViewPager.setCurrentItem(1);
                tv_item_one.setBackgroundColor(Color.rgb(220,220,220));
                tv_item_two.setBackgroundColor(Color.rgb(0,229,238));
                tv_item_three.setBackgroundColor(Color.rgb(220,220,220));
                tv_item_four.setBackgroundColor(Color.rgb(220,220,220));
//                tv_item_five.setBackgroundColor(Color.rgb(220,220,220));
                break;
            case R.id.tv_item_three:
                myViewPager.setCurrentItem(2);
                tv_item_one.setBackgroundColor(Color.rgb(220,220,220));
                tv_item_two.setBackgroundColor(Color.rgb(220,220,220));
                tv_item_three.setBackgroundColor(Color.rgb(0,229,238));
                tv_item_four.setBackgroundColor(Color.rgb(220,220,220));
//                tv_item_five.setBackgroundColor(Color.rgb(220,220,220));
                break;
            case R.id.tv_item_four:
                myViewPager.setCurrentItem(3);
                tv_item_one.setBackgroundColor(Color.rgb(220,220,220));
                tv_item_two.setBackgroundColor(Color.rgb(220,220,220));
                tv_item_three.setBackgroundColor(Color.rgb(220,220,220));
                tv_item_four.setBackgroundColor(Color.rgb(0,229,238));
//                tv_item_five.setBackgroundColor(Color.rgb(220,220,220));
                break;

//            case R.id.tv_item_five:
//                myViewPager.setCurrentItem(4);
//                tv_item_one.setBackgroundColor(Color.rgb(220,220,220));
//                tv_item_two.setBackgroundColor(Color.rgb(220,220,220));
//                tv_item_three.setBackgroundColor(Color.rgb(220,220,220));
//                tv_item_four.setBackgroundColor(Color.rgb(220,220,220));
//                tv_item_five.setBackgroundColor(Color.rgb(0,229,238));
//                break;
        }
    }

    /**
     * 设置一个ViewPager的侦听事件，当左右滑动ViewPager时菜单栏被选中状态跟着改变
     *
     */
    public class MyPagerChangeListener implements ViewPager.OnPageChangeListener {

        @Override
        public void onPageScrollStateChanged(int arg0) {
        }

        @Override
        public void onPageScrolled(int arg0, float arg1, int arg2) {
        }

        @Override
        public void onPageSelected(int arg0) {
            switch (arg0) {
                case 0:
                    tv_item_one.setBackgroundColor(Color.rgb(0,229,238));
                    tv_item_two.setBackgroundColor(Color.rgb(220,220,220));
                    tv_item_three.setBackgroundColor(Color.rgb(220,220,220));
                    tv_item_four.setBackgroundColor(Color.rgb(220,220,220));
//                    tv_item_five.setBackgroundColor(Color.rgb(220,220,220));
                    break;
                case 1:
                    tv_item_one.setBackgroundColor(Color.rgb(220,220,220));
                    tv_item_two.setBackgroundColor(Color.rgb(0,229,238));
                    tv_item_three.setBackgroundColor(Color.rgb(220,220,220));
                    tv_item_four.setBackgroundColor(Color.rgb(220,220,220));
//                    tv_item_five.setBackgroundColor(Color.rgb(220,220,220));
                    break;
                case 2:
                    tv_item_one.setBackgroundColor(Color.rgb(220,220,220));
                    tv_item_two.setBackgroundColor(Color.rgb(220,220,220));
                    tv_item_three.setBackgroundColor(Color.rgb(0,229,238));
                    tv_item_four.setBackgroundColor(Color.rgb(220,220,220));
//                    tv_item_five.setBackgroundColor(Color.rgb(220,220,220));
                    break;

                case 3:
                    tv_item_one.setBackgroundColor(Color.rgb(220,220,220));
                    tv_item_two.setBackgroundColor(Color.rgb(220,220,220));
                    tv_item_three.setBackgroundColor(Color.rgb(220,220,220));
                    tv_item_four.setBackgroundColor(Color.rgb(0,229,238));
//                    tv_item_five.setBackgroundColor(Color.rgb(220,220,220));
                    break;

//                case 4:
//                    tv_item_one.setBackgroundColor(Color.rgb(220,220,220));
//                    tv_item_two.setBackgroundColor(Color.rgb(220,220,220));
//                    tv_item_three.setBackgroundColor(Color.rgb(220,220,220));
//                    tv_item_four.setBackgroundColor(Color.rgb(220,220,220));
//                    tv_item_five.setBackgroundColor(Color.rgb(0,229,238));
//                    break;
            }
        }
    }

}
